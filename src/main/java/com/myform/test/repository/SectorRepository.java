package com.myform.test.repository;

import com.myform.test.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAllByParentSectorNameNull();
    Optional<Sector> findById(Integer id);
}

