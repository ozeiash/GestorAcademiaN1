package com.academia.service;

import com.academia.dto.PlanoDTO;
import com.academia.model.Plano;
import com.academia.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlanoService {
    @Autowired
    private PlanoRepository planoRepository;

    @Transactional
    public PlanoDTO criar(PlanoDTO dto) {
        Plano plano = new Plano();
        plano.setNome(dto.getNome());
        plano.setValorMensal(dto.getValorMensal());
        plano = planoRepository.save(plano);
        dto.setId(plano.getId());
        return dto;
    }

    @Transactional(readOnly = true)
    public Optional<PlanoDTO> buscarPorId(Long id) {
        return planoRepository.findById(id).map(this::toDTO);
    }

    @Transactional
    public PlanoDTO atualizar(Long id, PlanoDTO dto) {
        Plano plano = planoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        plano.setNome(dto.getNome());
        plano.setValorMensal(dto.getValorMensal());
        plano = planoRepository.save(plano);
        return toDTO(plano);
    }

    @Transactional
    public void remover(Long id) {
        planoRepository.deleteById(id);
    }

    private PlanoDTO toDTO(Plano plano) {
        PlanoDTO dto = new PlanoDTO();
        dto.setId(plano.getId());
        dto.setNome(plano.getNome());
        dto.setValorMensal(plano.getValorMensal());
        return dto;
    }
}