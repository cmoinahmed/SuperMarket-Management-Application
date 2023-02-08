package com.ty.supermarketappspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ty.supermarketappspringboot.dto.Product;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {
	@Autowired
	private ProductService productService;

	@ApiOperation(value = "Save product", notes = "Input is product Object and return product object")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@PostMapping(value = "/save/{stockId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@PathVariable String stockId,
			@RequestBody Product product) {
		return productService.saveProduct(stockId, product);
	}

	@ApiOperation(value = "get Product by id", notes = "Input is Product Id and return product object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable String id) {
		return productService.getProductById(id);
	}

	@ApiOperation(value = "Delete Product By Id", notes = "Input is Product Id and return  ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Product>> deleteProductId(@PathVariable String id) {
		return productService.deleteProduct(id);
	}

	@ApiOperation(value = "Get All product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProductById() {
		return productService.getAllProduct();
	}

	@ApiOperation(value = "Update Product", notes = "Input is Product Object and return Updated Product object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@PutMapping(value = "/update/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable String id,
			@RequestBody Product product) {
		return productService.updateProduct(id, product);
	}

	@ApiOperation(value = "get Product by brand", notes = "Input is brand and return  Product object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/brand/{brand}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<Product>>> getByBrand(@PathVariable String brand) {
		return productService.getProductByBrand(brand);
	}

	@ApiOperation(value = "get Product by typeOfProduct", notes = "Input is typeOfProduct and return  Product object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/typeOfProduct/{typeOfProduct}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<Product>>> getByTypeOfProduct(@PathVariable String typeOfProduct) {
		return productService.getProductByTypeOfProduct(typeOfProduct);
	}

	@ApiOperation(value = "get Product by priceRange", notes = "Input is price range and return  Product object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/priceRange/{startPrice}/{endPrice}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<Product>>> getByPriceRange(@PathVariable double startPrice,
			@PathVariable double endPrice) {
		return productService.getProductByPriceRange(startPrice, endPrice);
	}

	@ApiOperation(value = "Save ProductImage", notes = "Input is ProductImage file")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@PostMapping(value = "/uploadProductImage", consumes = { MediaType.ALL_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<String>> uploadProductImage(@RequestParam String productId,
			@RequestParam("image") MultipartFile file) {
		return productService.uploadProductImage(productId, file);
	}

	@ApiOperation(value = "Download ProductImage", notes = "Input is Product Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@GetMapping(value = "/downloadProductImage", produces = { MediaType.ALL_VALUE })
	public ResponseEntity<?> downloadProductImage(@RequestParam String productId) {
		return productService.downloadProductImageByProductId(productId);
	}
}
