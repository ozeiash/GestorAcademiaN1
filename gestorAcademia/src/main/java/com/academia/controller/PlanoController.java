package com.academia.controller;

import com.academia.dto.PlanoDTO;
import com.academia.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/planos")
public class PlanoController {
	
	@Autowired
	private PlanoService service;

	@PostMapping
	public ResponseEntity<PlanoDTO> criar(@RequestBody PlanoDTO dto) {
		return ResponseEntity.status(201).body(service.criar(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlanoDTO> buscar(@PathVariable Long id) {
		return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<PlanoDTO> atualizar(@PathVariable Long id, @RequestBody PlanoDTO dto) {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
}