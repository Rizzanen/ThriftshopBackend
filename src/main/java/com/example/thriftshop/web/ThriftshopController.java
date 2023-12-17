package com.example.thriftshop.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.thriftshop.domain.ListingRepository;
import com.example.thriftshop.domain.Listing;
import com.example.thriftshop.domain.CategoryRepository;
import com.example.thriftshop.domain.AppUserRepository;
import com.example.thriftshop.domain.Category;
import com.example.thriftshop.domain.AppUser;

@Controller
public class ThriftshopController {

	@Autowired
	private ListingRepository listingRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AppUserRepository appUserRepository;

     @RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = {"/APIDocumentation", "/"})
    public String APIDocumentation() {
        return "APIDocumentation";
    }
	// GET REST endpoint for calling all listings as json.
	@CrossOrigin
    @RequestMapping(value="/listings", method = RequestMethod.GET)
    public @ResponseBody List <Listing> listingsRest(){
        List <Listing> expenses = (List<Listing>) listingRepository.findAll();
        
        return expenses;
    }

	//GET REST endpoint for calling fetching all listings as json.
	@CrossOrigin
	 @RequestMapping(value="/listings/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Listing>> findListingRest(@PathVariable("id")Long listingId){
        Optional<Listing> listing = listingRepository.findById(listingId);
        return ResponseEntity.ok().body(listing);
    }

	// GET REST endpoint for fetching all categorys as json.
	@CrossOrigin
	 @RequestMapping(value="/categorys", method = RequestMethod.GET)
    public @ResponseBody List <Category> categorysRest(){
        List <Category> expenses = (List<Category>) categoryRepository.findAll();
        
        return expenses;
    }

	//GET REST endpoint for calling category by id as json.
	@CrossOrigin
	 @RequestMapping(value="/categorys/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Category>> findCategoryRest(@PathVariable("id")Long categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        return ResponseEntity.ok().body(category);
    }

	//GET REST endpoint for fetching all users as json.
	@CrossOrigin
	 @RequestMapping(value="/users", method = RequestMethod.GET)
    public @ResponseBody List <AppUser> usersRest(){
        List <AppUser> users = (List<AppUser>) appUserRepository.findAll();
        
        return users;
    }

	// GET REST endpoint for calling users by id as json.
	@CrossOrigin
	 @RequestMapping(value="/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<AppUser>> findusersRest(@PathVariable("id")Long userId){
        Optional<AppUser> user = appUserRepository.findById(userId);
        return ResponseEntity.ok().body(user);
    }

    // POST REST endpoint for saving a new listing
    @CrossOrigin
	 @RequestMapping(value="/listings", method = RequestMethod.POST)
     public @ResponseBody Listing addListing(@RequestBody Listing listing){
      
        return listingRepository.save(listing);
     }

     @CrossOrigin
     @PostMapping("/checkLoginRequest")
    public ResponseEntity<AppUser> checkLoginRequest(@RequestBody AppUser user) {
        AppUser appuser = appUserRepository.findByUsername(user.getUsername());
        if (appuser != null) {
            if (user.getPassword().equals(appuser.getPassword()) ) {
                return ResponseEntity.ok(appuser);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else { 
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
     
}
