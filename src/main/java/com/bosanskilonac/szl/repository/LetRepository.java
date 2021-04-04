package com.bosanskilonac.szl.repository;

import dto.LetoviInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bosanskilonac.szl.model.Let;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LetRepository extends JpaRepository<Let, Long>, JpaSpecificationExecutor<Let> {
	long countByAvionId(Long avionId);
	void deleteById(Long id);
	@Query(value = "Select MIN(cena) FROM Let l")
	BigDecimal findMinCena();
	@Query(value = "Select MAX(cena) FROM Let l")
	BigDecimal findMaxCena();
	@Query(value = "Select MIN(duzina) FROM Let l")
	Integer findMinDuzina();
	@Query(value = "Select MAX(duzina) FROM Let l")
	Integer findMaxDuzina();
	@Query(value = "SELECT pocetna_destinacija, krajnja_destinacija FROM Let let")
	List<String> findDestinacije();
}
