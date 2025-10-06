package com.academia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // CORRETO!
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.dto.AlunoTreinoVinculoDTO;
import com.academia.service.AlunoTreinoVinculoService;

@RestController
@RequestMapping("/api/v1/alunos-treinos")
public class AlunoTreinoVinculoController {
	@Autowired
	private AlunoTreinoVinculoService vinculoService;

	@PostMapping
	public ResponseEntity<Void> vincular(@RequestBody AlunoTreinoVinculoDTO dto) {
		vinculoService.vincularAlunoTreino(dto.getAlunoId(), dto.getTreinoId());
		return ResponseEntity.status(201).build();
	}
}