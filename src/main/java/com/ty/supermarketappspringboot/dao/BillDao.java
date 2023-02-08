package com.ty.supermarketappspringboot.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.supermarketappspringboot.dto.Bill;
import com.ty.supermarketappspringboot.repository.BillRepository;

@Repository
public class BillDao {

	@Autowired
	private BillRepository billRepository;

	public Bill saveBill(Bill bill) {

		return billRepository.save(bill);
	}

	public Bill getBillById(String id) {

		return billRepository.getBillById(id);
	}

	public List<Bill> getAllBills() {

		return billRepository.findAll();
	}

	public Bill deleteBill(Bill bill) {

		billRepository.delete(bill);
		return bill;
	}

	public List<Bill> getBillsByDate(LocalDate date) {

		return billRepository.getBillByDate(date);
	}

	public Bill updateBill(String id, Bill bill) {

		Bill bill2 = billRepository.getBillById(id);
		if (bill2 != null) {
			return bill2;
		}
		return null;
	}

}
