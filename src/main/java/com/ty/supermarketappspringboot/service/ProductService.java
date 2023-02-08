package com.ty.supermarketappspringboot.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ty.supermarketappspringboot.dao.ProductDao;
import com.ty.supermarketappspringboot.dao.StockDao;
import com.ty.supermarketappspringboot.dto.Product;
import com.ty.supermarketappspringboot.dto.ProductImage;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.dto.Stock;
import com.ty.supermarketappspringboot.exception.DataNotFoundException;
import com.ty.supermarketappspringboot.exception.IdNotFoundException;
import com.ty.supermarketappspringboot.util.ImageUtils;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private StockDao stockDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(String stockId, Product product) {

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		Stock stock = stockDao.getStockById(stockId);
		if (stock != null) {
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Successfully Saved Product");
			product.setStock(stockDao.getStockById(stockId));
			responseStructure.setData(productDao.saveProduct(product));
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundException("Stock Id " + stockId + " Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(String id) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		Product product = productDao.getProduct(id);
		if (product != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Successfully Fetched Product By Id");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "ID " + id + " not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<Product>> deleteProduct(String id) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		Product product = productDao.getProduct(id);
		if (product != null) {
			productDao.deleteProduct(product);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Product Deleted Successfully");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "ID " + id + " not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct() {
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
		List<Product> list = productDao.getAllProduct();
		if (list.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Successfully Fetched All Product");
			responseStructure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("Product Data Not Present");
		}
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(String id, Product product) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		Product product2 = productDao.getProduct(product.getId());
		if (product2 != null) {
			Stock stock = stockDao.getStockById(id);
			if (stock != null) {
				product.setStock(stockDao.getStockById(id));
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Successfully Updated Product");
				responseStructure.setData(productDao.saveProduct(product));
				return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
			} else {
				String msg = "Stock ID " + id + " Not Found";
				throw new IdNotFoundException(msg);
			}
		} else {
			String msg = "Product ID " + product.getId() + " Not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getProductByBrand(String brand) {
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		List<Product> product = productDao.getProductByBrand(brand);
		if (product != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Got The Product By Brand");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "Brand " + brand + " Not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getProductByTypeOfProduct(String typeOfProduct) {
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		List<Product> product = productDao.getProductByTypeOfProduct(typeOfProduct);
		if (product != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Got The Product By Type");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.FOUND);
		} else {
			String msg = "typeOfProduct " + typeOfProduct + " not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getProductByPriceRange(double startPrice, double endPrice) {
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		List<Product> products = productDao.getProductByPriceRange(startPrice, endPrice);
		if (products != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Got The Product By Price Range");
			responseStructure.setData(products);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.FOUND);
		} else {
			String msg = "Price Range Of Product " + products + " not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<String>> uploadProductImage(String productId, MultipartFile file) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		Product product = productDao.getProduct(productId);
		if (product != null) {
			if (product.getProductImage() != null) {
				try {
					product.setProductImage(ProductImage.builder().name(file.getOriginalFilename())
							.type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes()))
							.id(product.getProductImage().getId()).build());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					product.setProductImage(ProductImage.builder().name(file.getOriginalFilename())
							.type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			productDao.saveProduct(product);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("ProductImage Saved Successfully");
			responseStructure.setData("Image Saved Successfully For Product of Id -> " + productId);
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "ID " + productId + " not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<?> downloadProductImageByProductId(String productId) {
		Product product = productDao.getProduct(productId);
		if (product != null) {
			ProductImage productImage = product.getProductImage();
			if (productImage != null) {
				productImage.setImageData(ImageUtils.decompressImage(productImage.getImageData()));
				byte[] img = productImage.getImageData();
				return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(img);
			} else {
				String msg = "Product Image is Not Present" + productId;
				throw new IdNotFoundException(msg);
			}
		} else {
			String msg = "Product Id is Not Present" + productId;
			throw new IdNotFoundException(msg);
		}
	}

}
