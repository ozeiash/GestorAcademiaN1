package com.academia.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.model.Aluno;
import com.academia.model.AlunoTreinoVinculo;
import com.academia.model.Treino;
import com.academia.repository.AlunoRepository;
import com.academia.repository.AlunoTreinoVinculoRepository;
import com.academia.repository.TreinoRepository;

import jakarta.transaction.Transactional;

@Service
public class AlunoTreinoVinculoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private TreinoRepository treinoRepository;
	@Autowired
	private AlunoTreinoVinculoRepository vinculoRepository;

	@Transactional
	public void vincularAlunoTreino(Long alunoId, Long treinoId) {
		Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
		Treino treino = treinoRepository.findById(treinoId)
				.orElseThrow(() -> new RuntimeException("Treino não encontrado"));

		AlunoTreinoVinculo.AlunoTreinoKey key = new AlunoTreinoVinculo.AlunoTreinoKey();
		key.setAlunoId(alunoId);
		key.setTreinoId(treinoId);

		if (vinculoRepository.existsById(key)) {
			throw new RuntimeException("Vínculo já existe");
		}

		AlunoTreinoVinculo vinculo = new AlunoTreinoVinculo();
		vinculo.setId(key);
		vinculo.setAluno(aluno);
		vinculo.setTreino(treino);
		vinculo.setDataAssociacao(LocalDateTime.now());
		vinculoRepository.save(vinculo);
	}
	
	
}