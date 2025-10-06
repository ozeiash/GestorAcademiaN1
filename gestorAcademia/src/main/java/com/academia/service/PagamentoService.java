package com.academia.service;

import com.academia.dto.PagamentoDTO;
import com.academia.model.Aluno;
import com.academia.model.Pagamento;
import com.academia.repository.AlunoRepository;
import com.academia.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PagamentoService {
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private AlunoRepository alunoRepository;

	@Transactional
	public PagamentoDTO registrar(PagamentoDTO dto) {
		Aluno aluno = alunoRepository.findById(dto.getAlunoId())
				.orElseThrow(() -> new RuntimeException("Aluno não encontrado" + dto.getAlunoId()));
		Pagamento pagamento = new Pagamento();
		pagamento.setAluno(aluno);
		pagamento.setValorPago(dto.getValorPago());
		pagamento.setFormaPagamento(Pagamento.FormaPagamento.valueOf(dto.getFormaPagamento()));
		pagamento.setDataPagamento(LocalDateTime.now());

		if (pagamento.getValorPago().compareTo(aluno.getPlano().getValorMensal()) < 0) {
			pagamento.setStatus(Pagamento.Status.ATRASADO);
		} else {
			pagamento.setStatus(Pagamento.Status.PAGO);
		}
		pagamento = pagamentoRepository.save(pagamento);
		dto.setId(pagamento.getId());
		dto.setDataPagamento(pagamento.getDataPagamento());
		dto.setStatus(pagamento.getStatus().name());
		return dto;
	}

	@Transactional(readOnly = true)
	public Optional<PagamentoDTO> buscarPorId(Long id) {
		return pagamentoRepository.findById(id).map(this::toDTO);
	}

	private PagamentoDTO toDTO(Pagamento pg) {
		PagamentoDTO dto = new PagamentoDTO();
		dto.setId(pg.getId());
		dto.setAlunoId(pg.getAluno().getId());
		dto.setDataPagamento(pg.getDataPagamento());
		dto.setValorPago(pg.getValorPago());
		dto.setStatus(pg.getStatus().name());
		dto.setFormaPagamento(pg.getFormaPagamento().name());
		return dto;
	}
}