package com.ty.supermarketappspringboot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.supermarketappspringboot.dto.Bill;

public interface BillRepository extends JpaRepository<Bill, String> {

	Bill getBillById(String id);

	@Query(nativeQuery = true, value = ("select *,0 as clazz from bill b where b.local_date = ?"))
	List<Bill> getBillByDate(LocalDate date);
}
