package com.bosanskilonac.szak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosanskilonac.szl.model.Let;

@Repository
public interface LetRepository extends JpaRepository<Let, Long> {

}
