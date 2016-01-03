package com.search.api.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;


/**
 * 
 * @author arefe
 *
 * @version 1.0/2015-12-18
 */

@Component("productsDAO")
public class ProductsDAO {

	/*
	 * all the data from the products.json file will be stored here
	 */
	private JSONArray jsonArray;
	String[] jsonTAGs;

	/*
	 * get the values of the JSON data and JSON tags 
	 */
	public ProductsDAO() throws FileNotFoundException, IOException, ParseException {

		JSONParser parser = new JSONParser();

		URL path = ProductsDAO.class.getResource("/data/products.json"); 
		File myFile = new File(path.getFile());
		Object obj = parser.parse(new FileReader(myFile));

		JSONObject js = (JSONObject) obj;
		jsonArray = (JSONArray) js.get("products");

		/*
		 * the JSON tags mentioned in the data file
		 */
		jsonTAGs = new String[] { "createdDate", "imageUrl", "title", "category", "isActive", "popularityIndex", "itemId",
				"parentCategory", "department", "upc", "brand", "modifiedDate", "itemHashint64" };

	}
	
	
	
	/*
	 * get informations about all the categories
	 */
	public List<String> getAllCategories() {

		List<String> categories = new ArrayList<String>();

		try {

			for (int j = 0; j < jsonArray.size(); j++) {

				String category = (String) ((JSONObject) jsonArray.get(j)).get("category");
				categories.add(category);
			}
		}

		catch (Exception ex) {

			ex.printStackTrace();
		}

		return categories;
	}

	
	
	/*
	 * Get all product details for a given category
	 */
	public List<Product> getGivenCategory(String categoryName) {

		List<Product> products = new ArrayList<Product>();

		try {

			for (int j = 0; j < jsonArray.size(); j++) {

				String category = (String) ((JSONObject) jsonArray.get(j)).get("category");

				if (category.equals(categoryName)) {

					products.add(getProduct(j));
				}
			}
		}

		catch (Exception ex) {

			ex.printStackTrace();
		}

		return products;
	}

	
	
	/*
	 * returns all records in the given category which also match the keyword
	 */
	public List<Product> givenCategoryKeywordSearch(String categoryName, String keywordValue) {

		List<Product> products = new ArrayList<Product>();

		try {

			for (int j = 0; j < jsonArray.size(); j++) {

				String category = (String) ((JSONObject) jsonArray.get(j)).get("category");

				if (category.equals(categoryName)) {

					List<String> keywordSearch = getProductParameters(j);

					boolean keywordMatch = keywordSearch.stream().anyMatch(temp -> temp.equals(keywordValue));

					if (keywordMatch) {

						products.add(getProduct(j));
					}
				}
			}
		}

		catch (Exception ex) {

			ex.printStackTrace();
		}

		return products;
	}

	
	/*
	 * getAllKeywords get all keywords in the system
	 */
	public Set<String> getAllKeywords() {

		// set will make the unique list of keywords 
		Set<String> set = new HashSet<String>();

		try {

			for (int j = 0; j < jsonArray.size(); j++) {

				for (String str : getProductParameters(j)) {

					set.add(str);
				}
			}
		}

		catch (Exception ex) {

			ex.printStackTrace();
		}

		return set;
	}

	
	
	/*
	 * provides all records matching the single keyword in any category
	 */
	public List<Product> allMatchingRecords(String word) {

		List<Product> products = new ArrayList<Product>();

		try {

			for (int j = 0; j < jsonArray.size(); j++) {

				for (String str : getProductParameters(j)) {

					if (str.equals(word)) {

						products.add(getProduct(j));
						break; 
					}
				}

			}
		}

		catch (Exception ex) {

			ex.printStackTrace();
		}

		return products;
	}

	
	
	
	/*
	 * make a product from the JSON data w/ given index
	 */
	private Product getProduct(int j) {

		Product product = new Product();

		String createdDate = (String) ((JSONObject) jsonArray.get(j)).get("createdDate");
		product.setCreatedDate(createdDate);

		String imageUrl = (String) ((JSONObject) jsonArray.get(j)).get("imageUrl");
		product.setImageUrl(imageUrl);

		String title = (String) ((JSONObject) jsonArray.get(j)).get("title");
		product.setTitle(title);

		String category = (String) ((JSONObject) jsonArray.get(j)).get("category");
		product.setCategory(category);

		String isActive = (String) ((JSONObject) jsonArray.get(j)).get("isActive");
		product.setIsActive(isActive);

		String popularityIndex = (String) ((JSONObject) jsonArray.get(j)).get("popularityIndex");
		product.setPopularityIndex(popularityIndex);

		String itemId = (String) ((JSONObject) jsonArray.get(j)).get("itemId");
		product.setItemId(itemId);

		String parentCategory = (String) ((JSONObject) jsonArray.get(j)).get("parentCategory");
		product.setParentCategory(parentCategory);

		String department = (String) ((JSONObject) jsonArray.get(j)).get("department");
		product.setDepartment(department);

		String upc = (String) ((JSONObject) jsonArray.get(j)).get("upc");
		product.setUpc(upc);

		String brand = (String) ((JSONObject) jsonArray.get(j)).get("brand");
		product.setBrand(brand);

		String modifiedDate = (String) ((JSONObject) jsonArray.get(j)).get("modifiedDate");
		product.setModifiedDate(modifiedDate);

		String itemHashint64 = (String) ((JSONObject) jsonArray.get(j)).get("itemHashint64");
		product.setItemHashint64(itemHashint64);

		return product;
	}

	
	
	/*
	 * get all the keyword parameters of a certain product as list
	 */
	public List<String> getProductParameters(int j) {

		int minKeywordLength = 3;
		List<String> lis = new ArrayList<String>();
		Locale currentLocale = new Locale("en", "US");

		BreakIterator wordIterator = BreakIterator.getWordInstance(currentLocale);

		for (String value : jsonTAGs) {

			// get the String based on the json tag, item index is fixed 
			String temp = (String) ((JSONObject) jsonArray.get(j)).get(value);
			
			// split the string to build keyword storage 
			List<String> splitedWrods = BreakIteratorDemo.extractWords2(temp, wordIterator);			

			// filter and return the final keyword storage 
			for (String s : splitedWrods) {

				if (s.length() >= minKeywordLength && !isStringNumeric(s) && !wordFilter(s)) {

					// print the keywords for that certain product 
					// System.out.println(s);
					lis.add(s);
				}
			}
		}

		return lis;
	}

	
	
	/*
	 * check if the word contains any number 
	 * */
	public static boolean isStringNumeric(String str) {

		for (char c : str.toCharArray()) {

			if (Character.isDigit(c)) {
				return true;
			}
		}

		return false;
	}

	
	/*
	 * filer some unreasonable words for keyword search   
	 * */
	public static boolean wordFilter(String str) {

		String[] sArray = { "http", "jpg", "NULL", "The", "the" };
		String[] punctuation = { ".", "&" };
		
		return  Arrays.stream(sArray).anyMatch(str::equals) ||
				Arrays.stream(punctuation).anyMatch(str::contains);
	}

}
