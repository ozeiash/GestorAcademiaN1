package com.academia.service;

import com.academia.dto.AlunoDTO;
import com.academia.model.Aluno;
import com.academia.model.Plano;
import com.academia.repository.AlunoRepository;
import com.academia.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private PlanoRepository planoRepository;

	@Transactional
	public AlunoDTO cadastrarAluno(AlunoDTO dto) {
		if (alunoRepository.existsByCpf(dto.getCpf())) {
			throw new RuntimeException("Erro: CPF duplicado");
		}
		Aluno aluno = new Aluno();
		aluno.setNome(dto.getNome());
		aluno.setCpf(dto.getCpf());
		aluno.setDataNascimento(dto.getDataNascimento());
		aluno.setAtivo(true);
		if (dto.getPlanoId() != null) {
			Plano plano = planoRepository.findById(dto.getPlanoId())
					.orElseThrow(() -> new RuntimeException("Plano não encontrado"));
			aluno.setPlano(plano);
		}
		aluno = alunoRepository.save(aluno);
		dto.setId(aluno.getId());
		dto.setAtivo(aluno.getAtivo());
		return dto;
	}

	@Transactional(readOnly = true)
	public Optional<AlunoDTO> buscarPorId(Long id) {
		return alunoRepository.findById(id).map(this::toDTO);
	}

	@Transactional
	public AlunoDTO atualizar(Long id, AlunoDTO dto) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
		aluno.setNome(dto.getNome());
		aluno.setAtivo(dto.getAtivo());
		aluno.setDataNascimento(dto.getDataNascimento());
		if (dto.getPlanoId() != null) {
			Plano plano = planoRepository.findById(dto.getPlanoId())
					.orElseThrow(() -> new RuntimeException("Plano não encontrado"));
			aluno.setPlano(plano);
		}
		aluno = alunoRepository.save(aluno);
		return toDTO(aluno);
	}

	@Transactional
	public void inativar(Long id) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
		aluno.setAtivo(false);
		alunoRepository.save(aluno);
	}

	private AlunoDTO toDTO(Aluno aluno) {
		AlunoDTO dto = new AlunoDTO();
		dto.setId(aluno.getId());
		dto.setNome(aluno.getNome());
		dto.setCpf(aluno.getCpf());
		dto.setDataNascimento(aluno.getDataNascimento());
		dto.setAtivo(aluno.getAtivo());
		if (aluno.getPlano() != null) {
			dto.setPlanoId(aluno.getPlano().getId());
		}
		return dto;
	}
}