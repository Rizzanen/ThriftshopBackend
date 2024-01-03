package com.example.thriftshop.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

      private static byte[] downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        Path tempFile = Files.createTempFile("tempImage", ".tmp");

        try {
            Files.copy(url.openStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            return Files.readAllBytes(tempFile);
        } finally {
            Files.deleteIfExists(tempFile);
        }
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

    @CrossOrigin
	 @RequestMapping(value="/users", method = RequestMethod.POST)
    public AppUser AddUsersRest(@RequestBody AppUser appUser){
        AppUser newUser = new AppUser(appUser.getUsername(), appUser.getPassword(), appUser.getRole(), appUser.getEmail(), appUser.getPhone(), appUser.getAddress(), appUser.getPostcode(), appUser.getCity() );

        appUserRepository.save(newUser);
        
     
        return newUser;
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
     
  @CrossOrigin
	 @RequestMapping(value="/reset", method = RequestMethod.GET)
    public String ResetDb(){
        listingRepository.deleteAll();
        categoryRepository.deleteAll();
        appUserRepository.deleteAll();

        String imageUrl1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVs6xgO-hoQpiit6ibXDLntVTvB_-NEVpe8g&usqp=CAU";
			String imageUrl2 = "https://storagemedia.corporategear.com/storagemedia/1/mastercatalog/attributeimages/ctk288-brn.jpg";
			String imageUrl3 = "https://i.ebayimg.com/images/g/EhgAAOSwn2xdE7AK/s-l1200.webp";
			String imageUrl4 = "https://playitagainsports.imgix.net/images/11307-S000234580-1?auto=compress,format&fit=clip&w=800&orient=6";
			String imageUrl5 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTR13z0CJ3FlnKKilXIsC3Z8q-r3ooZFelLa4BR1GnPtrhdnFo3mawoL04MZLB72Av_46g&usqp=CAU";
			String imageUrl6 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIETJZ4xLlFAJTUJ7QSXGqya6l0MFNTCc_cA&usqp=CAU";
			String imageUrl7 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJXp12Pf0kmQJwk_T0v6cp7vHb32xxMpntpA&usqp=CAU";
			String imageUrl8 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzI30N7IvtRFuRVEbL_q-7YlqRCqiZyDXznluqsNEElpd2hlkQrIUqXNLYDvFBuF9Ta-4&usqp=CAU";
			String imageUrl9 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZeSvvRuqBmfczNwDlCpCzZJ1PWCLxtFa9yZRvbe8pJAw1e7VVWNEu3j5RR-r3cmHg86o&usqp=CAU";
			String imageUrl10 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpLLNcQEazh9wXtWgxYf5kMK2BqKnRDM3q2w&usqp=CAU";
			String imageUrl11 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXoKpM9kdoKgyJ4c177uAzlP7pVsdMwJePx47SEy7mvb2uxYeUZBgR-MNmVLJslyB-Fg8&usqp=CAU";
			String imageUrl12 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaImfFi7HBXOsaRl6jACfQLfP7RwdEX8DzDQ&usqp=CAU";
try {
			byte[] imageBytes1 = downloadImage(imageUrl1);
            byte[] imageBytes2 = downloadImage(imageUrl2);
			byte[] imageBytes3 = downloadImage(imageUrl3);
            byte[] imageBytes4 = downloadImage(imageUrl4);
			byte[] imageBytes5 = downloadImage(imageUrl5);
            byte[] imageBytes6 = downloadImage(imageUrl6);
			byte[] imageBytes7 = downloadImage(imageUrl7);
            byte[] imageBytes8 = downloadImage(imageUrl8);
			byte[] imageBytes9 = downloadImage(imageUrl9);
            byte[] imageBytes10 = downloadImage(imageUrl10);
			byte[] imageBytes11 = downloadImage(imageUrl11);
            byte[] imageBytes12 = downloadImage(imageUrl12);
     

			categoryRepository.save(new Category("Fashion"));
			categoryRepository.save(new Category("Sport Equipment"));
			categoryRepository.save(new Category("Furniture"));
			categoryRepository.save(new Category("Toys"));
			categoryRepository.save(new Category("Watches"));
			categoryRepository.save(new Category("Electronics"));

			AppUser user1 = new AppUser("user", "user", "USER", "user@mail.com", "1231231231", "katu 12", "00100", "Helsinki");
			AppUser user2 = new AppUser("admin", "admin", "ADMIN","admin@mail.com", "1231231231", "katu 13", "00110", "Helsinki");

			appUserRepository.saveAll(Arrays.asList(user1, user2));

			

			

			listingRepository.save(new Listing("Makia Longsleeve XL", new BigDecimal("80"), new Date(System.currentTimeMillis()),
			imageBytes1, "Good", "white makia longsleeve 100% cotton.",
			categoryRepository.findByName("Fashion").get(0), appUserRepository.findByUsername("user")));

			listingRepository.save(new Listing("Carhart hoodie XL", new BigDecimal("120"), new Date(System.currentTimeMillis()), imageBytes2, "Good", "New carhartt hoodie. Been in my closet for a year. the tag is still on. ",categoryRepository.findByName("Fashion").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing("CCM hockey stick", new BigDecimal("5"), new Date(System.currentTimeMillis()), imageBytes3, "Good", "Brand new hockeystick. ",categoryRepository.findByName("Sport Equipment").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Bauer hockey stick", new BigDecimal("20"), new Date(System.currentTimeMillis()), imageBytes4, "Good", "Brand new hockeystick. ",categoryRepository.findByName("Sport Equipment").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing("Ikea sofa", new BigDecimal("180"), new Date(System.currentTimeMillis()), imageBytes5, "Good", "Selling this sofa. I've had it for 2 years.",categoryRepository.findByName("Furniture").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Ikea Bed 180cm wide", new BigDecimal("280"), new Date(System.currentTimeMillis()), imageBytes6, "Good", "Selling. I've had it for 2 years. ",categoryRepository.findByName("Furniture").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing("Pikachu", new BigDecimal("30"), new Date(System.currentTimeMillis()), imageBytes7, "Good", "Good condition pikachu looking for new owner.",categoryRepository.findByName("Toys").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Bulbasaur", new BigDecimal("30"), new Date(System.currentTimeMillis()), imageBytes8, "Good", "Good condition Bulbasaur looking for new owner.",categoryRepository.findByName("Toys").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing(" Beach Rolex", new BigDecimal("30"), new Date(System.currentTimeMillis()), imageBytes9, "Excellent", "Excellent condition (Beach) Rolex for sale.",categoryRepository.findByName("Watches").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Tommy Hilfiger watch", new BigDecimal("80"), new Date(System.currentTimeMillis()), imageBytes10, "Excellent", "Excellent condition Tommy Hilfiger watch for sale.",categoryRepository.findByName("Watches").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing("Iphone 15", new BigDecimal("480"), new Date(System.currentTimeMillis()), imageBytes11, "Excellent", "New iphone for sale with cheap prise!!",categoryRepository.findByName("Electronics").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Iphone 12", new BigDecimal("280"), new Date(System.currentTimeMillis()), imageBytes12, "Excellent", "Used iphone for sale with cheap prise!!",categoryRepository.findByName("Electronics").get(0), appUserRepository.findByUsername("admin")));

   } catch (IOException e) {
            // Handle the exception, e.g., log it or throw a runtime exception
            e.printStackTrace();
            throw new RuntimeException("Error downloading images", e);
        }
        
        return "db reset succesfully.";
    }

  
   
}
