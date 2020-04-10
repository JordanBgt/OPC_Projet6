package com.openclassrooms.escalade.services;

import com.openclassrooms.escalade.dto.VoieDto;
import com.openclassrooms.escalade.dto.VoieLightDto;
import com.openclassrooms.escalade.dto.VoieSaveDto;
import com.openclassrooms.escalade.model.VoieSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VoieService {

    Page<VoieLightDto> findAll(VoieSearch searchCriteria, Pageable page);
    VoieDto findById(Long id);
    VoieDto create(VoieSaveDto voie);
    VoieDto update(VoieDto voie);
    void delete(Long id);
}
