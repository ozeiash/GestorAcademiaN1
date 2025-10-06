package com.academia.repository;

import com.academia.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Optional<Aluno> findByCpf(String cpf);

	boolean existsByCpf(String cpf);
	
}