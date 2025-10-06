package com.academia.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "aluno_treino_vinculo")
public class AlunoTreinoVinculo {
	
	@EmbeddedId
	private AlunoTreinoKey id = new AlunoTreinoKey();

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("alunoId")
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("treinoId")
	private Treino treino;

	@Column(name = "data_associacao", nullable = false)
	private LocalDateTime dataAssociacao = LocalDateTime.now();

	public AlunoTreinoKey getId() {
		return id;
	}

	public void setId(AlunoTreinoKey id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}

	public LocalDateTime getDataAssociacao() {
		return dataAssociacao;
	}

	public void setDataAssociacao(LocalDateTime dataAssociacao) {
		this.dataAssociacao = dataAssociacao;
	}

	@Embeddable
	public static class AlunoTreinoKey implements Serializable {
		private Long alunoId;
		private Long treinoId;

		// GETTERS E SETTERS
		public Long getAlunoId() {
			return alunoId;
		}

		public void setAlunoId(Long alunoId) {
			this.alunoId = alunoId;
		}

		public Long getTreinoId() {
			return treinoId;
		}

		public void setTreinoId(Long treinoId) {
			this.treinoId = treinoId;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			AlunoTreinoKey that = (AlunoTreinoKey) o;
			return alunoId.equals(that.alunoId) && treinoId.equals(that.treinoId);
		}

		@Override
		public int hashCode() {
			return java.util.Objects.hash(alunoId, treinoId);
		}
	}
}