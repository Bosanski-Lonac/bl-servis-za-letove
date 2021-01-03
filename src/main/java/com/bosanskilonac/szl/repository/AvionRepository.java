package com.bosanskilonac.szl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosanskilonac.szl.model.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {
	Page<Avion> findAll(Pageable pageable);
	void deleteById(Long id);
}
