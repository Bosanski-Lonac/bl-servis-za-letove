package com.bosanskilonac.szl.repository;

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
	@Query(value = "Select MIN(let.cena) FROM Let let")
	BigDecimal findMinCena();
	@Query(value = "Select MAX(let.cena) FROM Let let")
	BigDecimal findMaxCena();
	@Query(value = "Select MIN(let.duzina) FROM Let let")
	Integer findMinDuzina();
	@Query(value = "Select MAX(let.duzina) FROM Let let")
	Integer findMaxDuzina();
	@Query(value = "Select MIN(let.milje) FROM Let let")
	Integer findMinDaljina();
	@Query(value = "SELECT MAX(let.milje) FROM Let let")
	Integer findMaxDaljina();
	@Query(value = "SELECT DISTINCT let.pocetnaDestinacija FROM Let let")
	List<String> findPocetneDestinacije();
	@Query(value = "SELECT DISTINCT let.krajnjaDestinacija FROM Let let")
	List<String> findKrajnjeDestinacije();
}
