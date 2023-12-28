package com.example.thriftshop.web;

import java.io.IOException;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.example.thriftshop.domain.ListingRepository;
import com.example.thriftshop.domain.Listing;
import com.example.thriftshop.domain.CategoryRepository;
import com.example.thriftshop.domain.AppUserRepository;
import com.example.thriftshop.domain.Category;
import com.example.thriftshop.domain.AppUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ThriftshopController {
    @Autowired
    public ObjectMapper objectMapper;

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

	//GET REST endpoint for fetching listing with id as json.
	@CrossOrigin
	 @RequestMapping(value="/listings/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Listing>> findListingRest(@PathVariable("id")Long listingId){
        Optional<Listing> listing = listingRepository.findById(listingId);
        return ResponseEntity.ok().body(listing);
    }

     @CrossOrigin
	 @RequestMapping(value="/listings", method = RequestMethod.PUT)
    public ResponseEntity<Listing> modifyListingRest(@RequestBody Listing listing){
        Long listingId = listing.getId();
        if (listingRepository.existsById(listingId)) {
            Listing modifiedListing = listingRepository.save(listing);
            return ResponseEntity.ok().body(modifiedListing);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

     // POST REST endpoint for saving a new listing. This takes multipart data from frontEnd and then the listing is constructed from there and file is converted to byte[]
      @CrossOrigin
    @PostMapping(value = "/listings", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public Listing addListing(
        @RequestPart("name") String name,
        @RequestPart("price") String price,
        @RequestPart("date") String date,
        @RequestPart("condition") String condition,
        @RequestPart("details") String details,
        @RequestPart("category") String categoryJson,
        @RequestPart("appUser") String appUserJson,
        @RequestPart("pictureData") MultipartFile pictureData
) {
  Category category = new Category();
  AppUser appUser = new AppUser();
        try {
             category = objectMapper.readValue(categoryJson, Category.class);
             appUser = objectMapper.readValue(appUserJson, AppUser.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); 
        }
 

  byte[] pictureDataBytes = null;
    try {
        pictureDataBytes = pictureData.getBytes();
    } catch (IOException e) {
        e.printStackTrace(); 
    }
  
    Listing listing = new Listing();
    listing.setName(name);
    listing.setPrice(new BigDecimal(price));
    listing.setDate(new Date()); 
    listing.setCondition(condition);
    listing.setDetails(details);
    listing.setCategory(category);
    listing.setAppUser(appUser);
    listing.setPictureData(pictureDataBytes);
    
    return listingRepository.save(listing);
}

     @CrossOrigin
	 @RequestMapping(value="/listings/{id}", method = RequestMethod.DELETE)
     public ResponseEntity<String> deleteListing(@PathVariable Long id) {
        try {
            listingRepository.deleteById(id);
            return new ResponseEntity<>("Listing deleted successfully", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            
            return new ResponseEntity<>("Listing not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting listing", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //GET REST endpoint for fetching users listings with userid as json.
	@CrossOrigin
	 @RequestMapping(value="/listings/users/{id}", method = RequestMethod.GET)
    public @ResponseBody List <Listing> findusersListingsRest(@PathVariable("id")Long userId){
        List<Listing> listings = listingRepository.findByAppUserUserId(userId);
        System.out.print(listings);
        return listings;
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
    // PUT REST endpoint for updating the userData
    @CrossOrigin
	 @RequestMapping(value="/users", method = RequestMethod.PUT)
    public ResponseEntity<AppUser> modifyUserRest(@RequestBody AppUser appUser){
        Long userId = appUser.getUserId();
        if (appUserRepository.existsById(userId)) {
            AppUser modifiedUser = appUserRepository.save(appUser);
            return ResponseEntity.ok().body(modifiedUser);
        } else {
            return ResponseEntity.notFound().build();
        }

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
