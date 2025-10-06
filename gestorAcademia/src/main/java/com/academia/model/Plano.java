package com.academia.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "plano")
public class Plano {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "valor_mensal", nullable = false)
    private BigDecimal valorMensal;

    @OneToMany(mappedBy = "plano", fetch = FetchType.LAZY)
    private Set<Aluno> alunos;

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

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alunos, id, nome, valorMensal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plano other = (Plano) obj;
		return Objects.equals(alunos, other.alunos) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(valorMensal, other.valorMensal);
	}

}