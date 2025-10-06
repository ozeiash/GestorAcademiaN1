package com.academia.repository;

import com.academia.model.AlunoTreinoVinculo;
import com.academia.model.AlunoTreinoVinculo.AlunoTreinoKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoTreinoVinculoRepository extends JpaRepository<AlunoTreinoVinculo, AlunoTreinoKey> {
	
	List<AlunoTreinoVinculo> findByTreinoId(Long treinoId);
	
}