package com.academia.controller;

import com.academia.dto.TreinoDTO;
import com.academia.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/treinos")
public class TreinoController {
	
	@Autowired
	private TreinoService service;

	@PostMapping
	public ResponseEntity<TreinoDTO> criar(@RequestBody TreinoDTO dto) {
		return ResponseEntity.status(201).body(service.criar(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TreinoDTO> buscar(@PathVariable Long id) {
		return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<TreinoDTO> atualizar(@PathVariable Long id, @RequestBody TreinoDTO dto) {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
}