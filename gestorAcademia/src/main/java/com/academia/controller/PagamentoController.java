package com.academia.controller;

import com.academia.dto.PagamentoDTO;
import com.academia.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentoService service;

	@PostMapping
	public ResponseEntity<PagamentoDTO> registrar(@RequestBody PagamentoDTO dto) {
		return ResponseEntity.status(201).body(service.registrar(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PagamentoDTO> buscar(@PathVariable Long id) {
		return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
}