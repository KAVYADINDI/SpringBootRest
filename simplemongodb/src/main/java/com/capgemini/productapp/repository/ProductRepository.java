package com.capgemini.productapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capgemini.productapp.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer/*dataType of the primary key*/>{

	@Query("{'productCategory' : ?0}")
	public List<Product> findProductsByCategory(String productCategory);
	
	@Query("{'productName' : ?0}")
	public List<Product> findProductsByName(String productName);
	
	@Query("{'productCategory' :?0, 'productPrice':{$gt : ?1, $lt :?2}}")
	public List<Product> findProductCategoryInRange(String productCategory,int lowerLimit,int upperLimit);
}