package com.ty.supermarketappspringboot.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.supermarketappspringboot.dao.BillDao;
import com.ty.supermarketappspringboot.dao.CustomerDao;
import com.ty.supermarketappspringboot.dao.ProductDao;
import com.ty.supermarketappspringboot.dto.Bill;
import com.ty.supermarketappspringboot.dto.BillProduct;
import com.ty.supermarketappspringboot.dto.Customer;
import com.ty.supermarketappspringboot.dto.Product;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.exception.DataNotFoundException;
import com.ty.supermarketappspringboot.exception.IdNotFoundException;
import com.ty.supermarketappspringboot.exception.InsufficientProductQuantity;

@Service
public class BillService {

	@Autowired
	private BillDao billDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ProductService productService;

	public ResponseEntity<ResponseStructure<Bill>> saveBill(String customerId, Bill bill) {
		ResponseStructure<Bill> responseStructure = new ResponseStructure<Bill>();

		Customer customer = customerDao.getCustomer(customerId);
		if (customer != null) {
			bill.setCustomer(customer);
			double totalAmt = 0;
			List<Product> products = bill.getProducts();
			List<BillProduct> billProducts = new ArrayList<>();
			for (Product p : products) {
				Product pro = productDao.getProduct(p.getId());
				if (pro != null) {
					if (pro.getQuantityOfProduct() > p.getQuantityOfProduct()) {
						BillProduct billProduct = new BillProduct();
						billProduct.setProductId(p.getId());
						billProduct.setQuantity(p.getQuantityOfProduct());
						totalAmt = totalAmt + p.getPriceOfProduct() * billProduct.getQuantity();
						bill.setTotalCost(totalAmt);
						bill.setLocalDate(LocalDate.now());
						bill.setLocalTime(LocalTime.now());
						billProduct.setBill(bill);
						billProducts.add(billProduct);
						pro.setQuantityOfProduct(pro.getQuantityOfProduct() - p.getQuantityOfProduct());
						productDao.saveProduct(pro);
					} else {
						throw new InsufficientProductQuantity(
								"The Quantity Of The Product In Stock Is Less Than The Quantity Of Product Being Sold");
					}
				} else {
					throw new IdNotFoundException("Product with Id:" + p.getId() + " not found");
				}
			}
			bill.setBillProducts(billProducts);
			Bill b = billDao.saveBill(bill);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Successfully Created");
			responseStructure.setData(b);
			return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);

		} else {
			throw new IdNotFoundException("Customer Id Not Found " + customerId);
		}
	}

	public ResponseEntity<ResponseStructure<Bill>> getBillById(String id) {
		ResponseStructure<Bill> responseStructure = new ResponseStructure<Bill>();
		Bill bill = billDao.getBillById(id);
		if (bill != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Got Bill By Id");
			responseStructure.setData(bill);
			return new ResponseEntity<ResponseStructure<Bill>>(responseStructure, HttpStatus.OK);
		} else {
			String message = "Bill Id " + id + " Not Found";
			throw new IdNotFoundException(message);
		}
	}

	public ResponseEntity<ResponseStructure<Bill>> deleteBill(String id) {
		ResponseStructure<Bill> responseStructure = new ResponseStructure<Bill>();
		Bill bill = billDao.getBillById(id);
		if (bill != null) {
			for (BillProduct billProduct : bill.getBillProducts()) {
				Product product = productDao.getProduct(billProduct.getProductId());
				product.setQuantityOfProduct(billProduct.getQuantity() + product.getQuantityOfProduct());
				productService.updateProduct(product.getStock().getId(), product);
			}

			billDao.deleteBill(bill);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Successfully");
			responseStructure.setData(bill);
			return new ResponseEntity<ResponseStructure<Bill>>(responseStructure, HttpStatus.OK);
		} else {
			String message = "Bill Id " + id + " Not Found";
			throw new IdNotFoundException(message);
		}
	}

	public ResponseEntity<ResponseStructure<List<Bill>>> getAllBills() {
		ResponseStructure<List<Bill>> responseStructure = new ResponseStructure<>();
		List<Bill> list = billDao.getAllBills();
		if (list.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Got All Bill's");
			responseStructure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Bill>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("Bill Data Not Present");
		}
	}

	public ResponseEntity<ResponseStructure<List<Bill>>> getAllBillsByDate(LocalDate date) {
		ResponseStructure<List<Bill>> responseStructure = new ResponseStructure<>();
		List<Bill> bills = billDao.getBillsByDate(date);
		if (bills.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Got Bill By Date");
			responseStructure.setData(bills);
			return new ResponseEntity<ResponseStructure<List<Bill>>>(responseStructure, HttpStatus.OK);
		} else {
			String message = "Date " + date + " Not Found";
			throw new IdNotFoundException(message);
		}
	}

}
