package com.bosanskilonac.szak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosanskilonac.szl.model.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {

}
