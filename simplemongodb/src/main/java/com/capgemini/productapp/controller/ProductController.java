package com.capgemini.productapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.service.ProductService;

@RestController
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.addProduct(product),
				HttpStatus.OK);
		logger.info("Added new product :"+ product);
		return responseEntity;
	}

	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		try {
			productService.findProductById(product.getProductId());
			logger.info("Updated product with Id :"+ product.getProductId()+" to : "+product);
			return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable int productId) {
		try {
			Product productFromDb = productService.findProductById(productId);
			logger.info("ID of Product to be found : "+productId);
			return new ResponseEntity<Product>(productFromDb, HttpStatus.OK);
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
		try {
			Product productFromDb = productService.findProductById(productId);
			if (productFromDb != null) {
				productService.deleteProduct(productFromDb);
				logger.info("Deleted product by id: "+productId);
				return new ResponseEntity<Product>(HttpStatus.OK);
			}
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/product/searchbycategory/{productCategory}")
	public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable String productCategory) {
		List<Product> productFromDb = productService.findProductsByCategory(productCategory);
		logger.info("Product by category : "+productCategory);
		return new ResponseEntity<List<Product>>(productFromDb, HttpStatus.OK);
	}
	

	@GetMapping("/product/searchbyname/{productName}")
	public ResponseEntity<List<Product>> findProductsByName(@PathVariable String productName) {
		List<Product> productFromDb = productService.findProductsByName(productName);
		logger.info("Product by name: "+productName);
		return new ResponseEntity<List<Product>>(productFromDb, HttpStatus.OK);
	}
	
	@GetMapping("/product/searchinrange")
	public ResponseEntity<List<Product>> findProductsByCategory(@RequestParam String productCategory,@RequestParam int lowerLimit,@RequestParam int upperLimit) {
		List<Product> productFromDb = productService.findProductCategoryInRange(productCategory,lowerLimit,upperLimit);
		logger.info("Product by category in range: "+productCategory+" "+lowerLimit+"-"+upperLimit);
		return new ResponseEntity<List<Product>>(productFromDb, HttpStatus.OK);
	}
}
