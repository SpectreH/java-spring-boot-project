package org.example.repository;

import org.example.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAllByOrderByIdAsc();

    Optional<Sector> findBySectorValue(Long sectorValue);

    List<Sector> findBySectorValueIn(Collection<Long> sectorValues);
}