package com.academia.service;

import com.academia.dto.TreinoDTO;
import com.academia.model.Treino;
import com.academia.repository.TreinoRepository;
import com.academia.repository.AlunoTreinoVinculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TreinoService {
	@Autowired
	private TreinoRepository treinoRepository;
	@Autowired
	private AlunoTreinoVinculoRepository alunoTreinoVinculoRepository;

	@Transactional
	public TreinoDTO criar(TreinoDTO dto) {
		Treino treino = new Treino();
		treino.setNome(dto.getNome());
		treino.setDescricao(dto.getDescricao());
		treino.setNivel(Treino.Nivel.valueOf(dto.getNivel()));
		treino = treinoRepository.save(treino);
		dto.setId(treino.getId());
		return dto;
	}

	@Transactional(readOnly = true)
	public Optional<TreinoDTO> buscarPorId(Long id) {
		return treinoRepository.findById(id).map(this::toDTO);
	}

	@Transactional
	public TreinoDTO atualizar(Long id, TreinoDTO dto) {
		Treino treino = treinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Treino não encontrado"));
		treino.setNome(dto.getNome());
		treino.setDescricao(dto.getDescricao());
		treino.setNivel(Treino.Nivel.valueOf(dto.getNivel()));
		treino = treinoRepository.save(treino);
		return toDTO(treino);
	}

	@Transactional
	public void remover(Long id) {
		if (!alunoTreinoVinculoRepository.findByTreinoId(id).isEmpty()) {
			throw new RuntimeException("Erro: Não pode remover. Treino está associado a alunos.");
		}
		treinoRepository.deleteById(id);
	}

	private TreinoDTO toDTO(Treino treino) {
		TreinoDTO dto = new TreinoDTO();
		dto.setId(treino.getId());
		dto.setNome(treino.getNome());
		dto.setDescricao(treino.getDescricao());
		dto.setNivel(treino.getNivel().name());
		return dto;
	}
}