package com.academia.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class PlanoDTO {

	private Long id;
	private String nome;
	private BigDecimal valorMensal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(BigDecimal valorMensal) {
		this.valorMensal = valorMensal;
	}

}