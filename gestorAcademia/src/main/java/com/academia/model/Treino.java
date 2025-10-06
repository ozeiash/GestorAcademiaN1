package com.academia.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "treino")
public class Treino {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Nivel nivel;

	@OneToMany(mappedBy = "treino", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AlunoTreinoVinculo> alunosVinculados;

	public enum Nivel {
		INICIANTE, INTERMEDIARIO, AVANCADO
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Set<AlunoTreinoVinculo> getAlunosVinculados() {
		return alunosVinculados;
	}

	public void setAlunosVinculados(Set<AlunoTreinoVinculo> alunosVinculados) {
		this.alunosVinculados = alunosVinculados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alunosVinculados, descricao, id, nivel, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Treino other = (Treino) obj;
		return Objects.equals(alunosVinculados, other.alunosVinculados) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && nivel == other.nivel && Objects.equals(nome, other.nome);
	}

}