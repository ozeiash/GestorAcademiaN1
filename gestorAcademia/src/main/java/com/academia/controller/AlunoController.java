package com.academia.controller;

import com.academia.dto.AlunoDTO;
import com.academia.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunoController {
	@Autowired
	private AlunoService service;

	@PostMapping
	public ResponseEntity<AlunoDTO> cadastrar(@RequestBody AlunoDTO dto) {
		return ResponseEntity.status(201).body(service.cadastrarAluno(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> buscar(@PathVariable Long id) {
		return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlunoDTO> atualizar(@PathVariable Long id, @RequestBody AlunoDTO dto) {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}

	@PatchMapping("/{id}/inativar")
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		service.inativar(id);
		return ResponseEntity.noContent().build();
	}
}