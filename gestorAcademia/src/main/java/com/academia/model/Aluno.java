package com.academia.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "aluno")
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String cpf;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

	@Column(nullable = false)
	private Boolean ativo = true;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plano_id")
	private Plano plano;

	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pagamento> pagamentos = new HashSet<>();

	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AlunoTreinoVinculo> treinosVinculados = new HashSet<>();

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Set<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(Set<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public Set<AlunoTreinoVinculo> getTreinosVinculados() {
		return treinosVinculados;
	}

	public void setTreinosVinculados(Set<AlunoTreinoVinculo> treinosVinculados) {
		this.treinosVinculados = treinosVinculados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ativo, cpf, dataNascimento, id, nome, pagamentos, plano, treinosVinculados);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(ativo, other.ativo) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(pagamentos, other.pagamentos)
				&& Objects.equals(plano, other.plano) && Objects.equals(treinosVinculados, other.treinosVinculados);
	}

}