package com.capgemini.productapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductByNameNotFoundException;
import com.capgemini.productapp.service.ProductService;
import com.capgemini.productapp.service.ProductsNotFoundInRangeException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(int productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isPresent())
			return optionalProduct.get();
		throw new ProductNotFoundException("Product does not exists");
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public List<Product> findProductsByCategory(String productCategory) throws CategoryNotFoundException {
		// return productRepository.findProductsByCategory(productCategory);
		List<Product> list = productRepository.findProductsByCategory(productCategory);
		if (list.isEmpty())
			throw new CategoryNotFoundException("Category does not exists");
		return list;
	}

	@Override
	public List<Product> findProductsByName(String productName) throws ProductByNameNotFoundException {
		List<Product> list = productRepository.findProductsByName(productName);
		if (list.isEmpty())
			throw new ProductByNameNotFoundException("Product by specified name does not exists");
		return list;
	}

	@Override
	public List<Product> findProductCategoryInRange(String productCategory, int lowerLimit, int upperLimit)
			throws ProductsNotFoundInRangeException {
		List<Product> list = productRepository.findProductCategoryInRange(productCategory,lowerLimit,upperLimit);
		if (list.isEmpty())
			throw new ProductsNotFoundInRangeException("Products by category not found in specified range");
		return list;
	}

}
