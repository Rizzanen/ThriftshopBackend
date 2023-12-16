package com.example.thriftshop.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.thriftshop.domain.ListingRepository;
import com.example.thriftshop.domain.Listing;
import com.example.thriftshop.domain.CategoryRepository;
import com.example.thriftshop.domain.Category;

@Controller
public class ThriftshopController {

	@Autowired
	private ListingRepository listingRepository;

	@Autowired
	private CategoryRepository categoryRepository;

     @RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/APIDocumentation")
	public String APIDocumentation() {
		return "APIDocumentation";
	}
	//REST endpoint for calling all listings as json.
    @RequestMapping(value="/listings", method = RequestMethod.GET)
    public @ResponseBody List <Listing> listingsRest(){
        List <Listing> expenses = (List<Listing>) listingRepository.findAll();
        
        return expenses;
    }

	//REST endpoint for calling fetching all listings as json.
	 @RequestMapping(value="/listings/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Listing>> findListingRest(@PathVariable("id")Long listingId){
        Optional<Listing> listing = listingRepository.findById(listingId);
        return ResponseEntity.ok().body(listing);
    }

	//REST endpoint for fetching all categorys as json.
	 @RequestMapping(value="/categorys", method = RequestMethod.GET)
    public @ResponseBody List <Category> categorysRest(){
        List <Category> expenses = (List<Category>) categoryRepository.findAll();
        
        return expenses;
    }

	//REST endpoint for calling category by id as json.
	 @RequestMapping(value="/categorys/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Category>> findCategoryRest(@PathVariable("id")Long categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        return ResponseEntity.ok().body(category);
    }
}
