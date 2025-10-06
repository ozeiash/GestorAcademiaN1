package com.academia.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pagamento")
public class Pagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_pagamento", nullable = false)
	private LocalDateTime dataPagamento = LocalDateTime.now();

	@Column(name = "valor_pago", nullable = false)
	private BigDecimal valorPago;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false)
	private FormaPagamento formaPagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;

	public enum Status {
		PAGO, PENDENTE, ATRASADO
	}

	public enum FormaPagamento {
		DINHEIRO, PIX, CARTAO
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aluno, dataPagamento, formaPagamento, id, status, valorPago);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(aluno, other.aluno) && Objects.equals(dataPagamento, other.dataPagamento)
				&& formaPagamento == other.formaPagamento && Objects.equals(id, other.id) && status == other.status
				&& Objects.equals(valorPago, other.valorPago);
	}

}