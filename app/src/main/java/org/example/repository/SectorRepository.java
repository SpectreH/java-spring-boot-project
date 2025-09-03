package org.example.repository;

import org.example.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAllByOrderByIdAsc();

    List<Sector> findBySectorValueIn(Collection<Long> sectorValues);
}