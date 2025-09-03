package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.dto.SubmissionDto;

import org.example.entity.Submission;
import org.example.exception.ApplicationException;
import org.example.mapper.SubmissionMapper;
import org.example.repository.SectorRepository;
import org.example.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final SectorRepository sectorRepository;
    private final SubmissionMapper submissionMapper;

    @Transactional(readOnly = true)
    public Optional<SubmissionDto> loadForSession(String sessionId) {
        return submissionRepository.findBySessionId(sessionId).map(submissionMapper::toDto);
    }

    @Transactional
    public void saveOrUpdate(String sessionId, SubmissionDto dto) {
        var sectors = new HashSet<>(sectorRepository.findBySectorValueIn(dto.getSectorValues()));
        if (sectors.size() != dto.getSectorValues().size()) {
            throw new ApplicationException("One or more selected sectors do not exist");
        }

        Submission sub = submissionRepository.findBySessionId(sessionId)
                .orElseGet(() -> {
                    Submission s = new Submission();
                    s.setSessionId(sessionId);
                    return s;
                });

        submissionMapper.applyDto(sub, dto, sectors);
        submissionRepository.save(sub);
    }
}
