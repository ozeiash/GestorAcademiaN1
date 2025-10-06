package com.academia.service;

import com.academia.dto.TreinoDTO;
import com.academia.exception.RecursoNaoEncontradoException; // IMPORTADO
import com.academia.exception.RegraDeNegocioException; // IMPORTADO
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
		Treino treino = treinoRepository.findById(id)

				.orElseThrow(() -> new RecursoNaoEncontradoException("Treino não encontrado com ID: " + id));
		treino.setNome(dto.getNome());
		treino.setDescricao(dto.getDescricao());
		treino.setNivel(Treino.Nivel.valueOf(dto.getNivel()));
		treino = treinoRepository.save(treino);
		return toDTO(treino);
	}

	@Transactional
	public void remover(Long id) {

		if (!alunoTreinoVinculoRepository.findByTreinoId(id).isEmpty()) {
			throw new RegraDeNegocioException(
					"Não é possível remover. Este treino ainda está associado a um ou mais alunos.");
		}

		if (!treinoRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Treino não encontrado com ID: " + id);
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