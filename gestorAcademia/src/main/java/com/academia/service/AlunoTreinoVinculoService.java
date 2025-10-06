package com.academia.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.exception.RecursoNaoEncontradoException;
import com.academia.exception.RegraDeNegocioException;
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
		Aluno aluno = alunoRepository.findById(alunoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado com ID: " + alunoId));
		Treino treino = treinoRepository.findById(treinoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Treino não encontrado com ID: " + treinoId));

		AlunoTreinoVinculo.AlunoTreinoKey key = new AlunoTreinoVinculo.AlunoTreinoKey();
		key.setAlunoId(alunoId);
		key.setTreinoId(treinoId);

		if (vinculoRepository.existsById(key)) {

			throw new RegraDeNegocioException("O aluno já está vinculado a este treino.");
		}

		AlunoTreinoVinculo vinculo = new AlunoTreinoVinculo();
		vinculo.setId(key);
		vinculo.setAluno(aluno);
		vinculo.setTreino(treino);
		vinculo.setDataAssociacao(LocalDateTime.now());
		vinculoRepository.save(vinculo);
	}
}
