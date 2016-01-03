package com.search.api.service;

import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.api.dao.Product;
import com.search.api.dao.ProductsDAO;

/**
 * 
 * @author arefe
 *
 * @version 1.0/2015-12-18
 */
 
@Service("productsService")
public class ProductsService {

	private ProductsDAO productsDAO;

	@Autowired
	private void setProductsDAO(ProductsDAO proDAO) {

		this.productsDAO = proDAO;
	}

	
	/*
	 get all the product categories from the 
	 DAO layer and convert to JSON object 
	 * */
	public String getAllCategories() {

		JSONArray arr = new JSONArray();

		List<String> categories = productsDAO.getAllCategories();

		int count = 0;

		for (String category : categories) {

			JSONObject obj = new JSONObject();
			count++;
			
			obj.put("id", String.valueOf(count));
			obj.put("category", category);
			arr.put(obj);
		}

		return arr.toString();
	}

	
	
	/*
	 * get all product details of certain category from 
	   the DAO layer and convert to JSON object 
	 * */
	public String getGivenCategory(String category) {

		List<Product> products = productsDAO.getGivenCategory(category);
		return makeProductJSON(products); 
		
	}	
	
	
	/*
	 * returns all records in the given category which also match the keyword
	 * */
	public String givenCategoryKeywordSearch(String categoryName, String keywordValue){

		List<Product> products = productsDAO.givenCategoryKeywordSearch(categoryName, keywordValue); 
		return makeProductJSON(products);
	}	
	
	
	/* 
	 * get all keywords from the product description 
	 * */
	public String getAllKeywords(){		
		
		JSONArray arr = new JSONArray();
		Set<String> keywords = productsDAO.getAllKeywords(); 

		int count = 1;  

		for (String str : keywords ) {

			JSONObject obj = new JSONObject();	
			obj.put(String.valueOf(count++), str);
			arr.put(obj);
		}
		
		return arr.toString();		
	}
	
	
	
	/*
	 * provides all records matching the single keyword in any category
	 * */
	public String allMatchingRecords(String word){
	
		List<Product> products = productsDAO.allMatchingRecords(word);
		return makeProductJSON(products);	
	}
	

	
	/*
	 * create product JSON String from the list of products 
	 * */
	
	public String makeProductJSON(List<Product> products){	
		
		if (products.size() == 0)
			return null; 		
		
		JSONArray arr = new JSONArray(); 

		for (Product product: products){
			
			JSONObject obj = new JSONObject();
			
			obj.put("id", products.indexOf(product)); 
			obj.put("createdDate", product.getCreatedDate());
			obj.put("imageUrl", product.getImageUrl());
			obj.put("title", product.getTitle());
			obj.put("category", product.getCategory());

			obj.put("isActive", product.getIsActive());
			obj.put("popularityIndex", product.getPopularityIndex());
			obj.put("itemId", product.getItemId());
			obj.put("parentCategory", product.getParentCategory());

			obj.put("department", product.getDepartment());
			obj.put("upc", product.getUpc());
			obj.put("brand", product.getBrand());
			obj.put("modifiedDate", product.getModifiedDate());
			obj.put("itemHashint64", product.getItemHashint64());

			arr.put(obj);
		}
		
		return arr.toString(); 
	}
	
}
