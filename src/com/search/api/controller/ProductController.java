package com.search.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.search.api.service.ProductsService;

/**
 * 
 * @author arefe
 *
 * @version 1.0/2015-12-18
 */

@Controller
public class ProductController {

	@Autowired
	ProductsService productsService;		
	
	
	@RequestMapping("/")
	public String showHome(){
		
		return "home";
	}	
	
	/*
	 * Get info about all the categories 
	 * */
	@RequestMapping("/category")
    public @ResponseBody ResponseEntity<String> getAllCategories(){
		
        HttpHeaders ResultHeader = new HttpHeaders();
        ResultHeader.set("Access-Control-Allow-Origin", "*");
        ResultHeader.set("Content-Type", "application/json");
        String result = productsService.getAllCategories();
       
        if( null ==  result){

        	return new ResponseEntity<String>( "{}", ResultHeader, HttpStatus.EXPECTATION_FAILED);
         }

        return new ResponseEntity<String>(result, ResultHeader, HttpStatus.OK); 
     }	
	
	
	/*
	 * Get all products info for certain category 
	 * */
	@RequestMapping(value = "/category/{category}", method =  RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> getGivenCategory(@PathVariable String category){
		
		// System.out.println("My Category = "+ category);

		HttpHeaders ResultHeader = new HttpHeaders();
        ResultHeader.set("Access-Control-Allow-Origin", "*");
        ResultHeader.set("Content-Type", "application/json");
        String result = productsService.getGivenCategory(category);  
       
        if(null ==  result){
        	
        	return new ResponseEntity<String>( "{}", ResultHeader, HttpStatus.EXPECTATION_FAILED);
         }
        
         return new ResponseEntity<String>(result, ResultHeader, HttpStatus.OK); 
     }

	
	/*
	 * returns all records in the given category which also match the keyword
	 * */
	@RequestMapping(value = "/category/{category}/keyword/{word}", method =  RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> givenCategoryKeywordSearch (@PathVariable String category, 
    		@PathVariable String word){
		
		HttpHeaders ResultHeader = new HttpHeaders();
        ResultHeader.set("Access-Control-Allow-Origin", "*");
        ResultHeader.set("Content-Type", "application/json");

        String result = productsService.givenCategoryKeywordSearch(category, word);
       
        if( null ==  result){
        	
        	return new ResponseEntity<String>( "{}", ResultHeader, HttpStatus.EXPECTATION_FAILED);
         }
        
         return new ResponseEntity<String>(result, ResultHeader, HttpStatus.OK); 
     }
	
	
	/*
	 * get all keywords from the products description 
	 * */
	@RequestMapping("/keyword")
    public @ResponseBody ResponseEntity<String> getAllKeywords(){
		
        HttpHeaders ResultHeader = new HttpHeaders();
        ResultHeader.set("Access-Control-Allow-Origin", "*");
        ResultHeader.set("Content-Type", "application/json");
        String result = productsService.getAllKeywords();
       
        if(null ==  result){

        	return new ResponseEntity<String>( "{}", ResultHeader, HttpStatus.EXPECTATION_FAILED);
         }

        return new ResponseEntity<String>(result, ResultHeader, HttpStatus.OK); 
     }		
	
	

	/*
	 * provides all records matching the single keyword in any category
	 * */	
	@RequestMapping(value = "/keyword/{word}", method = RequestMethod.GET )
    public @ResponseBody ResponseEntity<String> allMatchingRecords(@PathVariable String word){
		
        HttpHeaders ResultHeader = new HttpHeaders();
        ResultHeader.set("Access-Control-Allow-Origin", "*");
        ResultHeader.set("Content-Type", "application/json");

        String result = productsService.allMatchingRecords(word);
       
        if( null ==  result){

        	return new ResponseEntity<String>( "{}", ResultHeader, HttpStatus.EXPECTATION_FAILED);
         }

        return new ResponseEntity<String>(result, ResultHeader, HttpStatus.OK); 
     }	

}
