package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.SectorSelectDto;
import org.example.entity.Sector;
import org.example.mapper.SectorMapper;
import org.example.repository.SectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    public List<SectorSelectDto> getAllForSelect() {
        List<Sector> sectors = sectorRepository.findAllByOrderByIdAsc();
        return sectors.stream().map(sectorMapper::toSelectDto).toList();
    }
}