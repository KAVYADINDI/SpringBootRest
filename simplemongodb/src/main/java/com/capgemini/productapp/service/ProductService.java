package com.capgemini.productapp.service;

import java.util.List;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.service.impl.CategoryNotFoundException;

public interface ProductService {

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public Product findProductById(int productId) throws ProductNotFoundException;

	public void deleteProduct(Product product);

	public List<Product> findProductsByCategory(String productCategory) throws CategoryNotFoundException;

	public List<Product> findProductsByName(String productName) throws ProductByNameNotFoundException;

	public List<Product> findProductCategoryInRange(String productCategory, int lowerLimit, int upperLimit) throws ProductsNotFoundInRangeException;
}
