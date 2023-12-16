package com.example.thriftshop;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.thriftshop.domain.AppUser;
import com.example.thriftshop.domain.AppUserRepository;
import com.example.thriftshop.domain.Category;
import com.example.thriftshop.domain.CategoryRepository;
import com.example.thriftshop.domain.Listing;
import com.example.thriftshop.domain.ListingRepository;

@SpringBootApplication
public class ThriftshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThriftshopApplication.class, args);
	}

@Bean
    public CommandLineRunner expenceDemo(CategoryRepository categoryRepository, ListingRepository listingRepository,
	AppUserRepository appUserRepository) {
        return(args) -> {

			categoryRepository.save(new Category("Fashion"));
			categoryRepository.save(new Category("Sport Equipment"));
			categoryRepository.save(new Category("Furniture"));
			categoryRepository.save(new Category("Toys"));
			categoryRepository.save(new Category("Watches"));
			categoryRepository.save(new Category("Electronics"));

			listingRepository.save(new Listing("Makia Shirt", new BigDecimal("40") , new Date(System.currentTimeMillis()), "PictureData", "Good", "Selling Makia shirt. Good condition. No shipping.",
			categoryRepository.findByName("Fashion").get(0)));

			listingRepository.save(new Listing("Carhart hoodie", new BigDecimal("80"), new Date(System.currentTimeMillis()), "PictureData", "Good", "New carhartt hoodie. Been in my closet for a year. the tag is still on. No shipping.",categoryRepository.findByName("Fashion").get(0)));

			listingRepository.save(new Listing("CCM hockey stick", new BigDecimal("80"), new Date(System.currentTimeMillis()), "PictureData", "Good", "Brand new hockeystick. No shipping",categoryRepository.findByName("Sport Equipment").get(0)));

			listingRepository.save(new Listing("Bauer hockey stick", new BigDecimal("80"), new Date(System.currentTimeMillis()), "PictureData", "Good", "Brand new hockeystick. No shipping",categoryRepository.findByName("Sport Equipment").get(0)));

			listingRepository.save(new Listing("Ikea sofa", new BigDecimal("180"), new Date(System.currentTimeMillis()), "PictureData", "Good", "Selling this sofa. I've had it for 2 years. No shipping. Pickup only.",categoryRepository.findByName("Furniture").get(0)));

			listingRepository.save(new Listing("Ikea Bed 180cm wide", new BigDecimal("280"), new Date(System.currentTimeMillis()), "PictureData", "Good", "Selling. I've had it for 2 years. No shipping. Pickup only.",categoryRepository.findByName("Furniture").get(0)));

			listingRepository.save(new Listing("Pikachu", new BigDecimal("30"), new Date(System.currentTimeMillis()), "PictureData", "Good", "Good condition pikachu looking for new owner.",categoryRepository.findByName("Toys").get(0)));

			listingRepository.save(new Listing("Bulbasaur", new BigDecimal("30"), new Date(System.currentTimeMillis()), "PictureData", "Good", "Good condition Bulbasaur looking for new owner.",categoryRepository.findByName("Toys").get(0)));

			listingRepository.save(new Listing(" Beach Rolex", new BigDecimal("30"), new Date(System.currentTimeMillis()), "PictureData", "Excellent", "Excellent condition (Beach) Rolex for sale.",categoryRepository.findByName("Watches").get(0)));

			listingRepository.save(new Listing("Tommy Hilfiger watch", new BigDecimal("80"), new Date(System.currentTimeMillis()), "PictureData", "Excellent", "Excellent condition Tommy Hilfiger  watch for sale.",categoryRepository.findByName("Watches").get(0)));

			listingRepository.save(new Listing("Iphone 15", new BigDecimal("480"), new Date(System.currentTimeMillis()), "PictureData", "Excellent", "New iphone for sale with cheap prise!!",categoryRepository.findByName("Electronics").get(0)));

			listingRepository.save(new Listing("Iphone 12", new BigDecimal("280"), new Date(System.currentTimeMillis()), "PictureData", "Excellent", "Used iphone for sale with cheap prise!!",categoryRepository.findByName("Electronics").get(0)));


			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");

			appUserRepository.saveAll(Arrays.asList(user1, user2));
        };
    }



}
