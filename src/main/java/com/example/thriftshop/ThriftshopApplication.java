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

			

			listingRepository.save(new Listing("Makia Shirt", new BigDecimal("40"), new Date(System.currentTimeMillis()),
			"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVs6xgO-hoQpiit6ibXDLntVTvB_-NEVpe8g&usqp=CAU", "Good", "Selling Makia shirt. Good condition. No shipping.",
			categoryRepository.findByName("Fashion").get(0)));

			listingRepository.save(new Listing("Carhart hoodie", new BigDecimal("80"), new Date(System.currentTimeMillis()), "https://storagemedia.corporategear.com/storagemedia/1/mastercatalog/attributeimages/ctk288-brn.jpg", "Good", "New carhartt hoodie. Been in my closet for a year. the tag is still on. No shipping.",categoryRepository.findByName("Fashion").get(0)));

			listingRepository.save(new Listing("CCM hockey stick", new BigDecimal("5"), new Date(System.currentTimeMillis()), "https://i.ebayimg.com/images/g/EhgAAOSwn2xdE7AK/s-l1200.webp", "Good", "Brand new hockeystick. No shipping",categoryRepository.findByName("Sport Equipment").get(0)));

			listingRepository.save(new Listing("Bauer hockey stick", new BigDecimal("20"), new Date(System.currentTimeMillis()), "https://playitagainsports.imgix.net/images/11307-S000234580-1?auto=compress,format&fit=clip&w=800&orient=6", "Good", "Brand new hockeystick. No shipping",categoryRepository.findByName("Sport Equipment").get(0)));

			listingRepository.save(new Listing("Ikea sofa", new BigDecimal("180"), new Date(System.currentTimeMillis()), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTR13z0CJ3FlnKKilXIsC3Z8q-r3ooZFelLa4BR1GnPtrhdnFo3mawoL04MZLB72Av_46g&usqp=CAU", "Good", "Selling this sofa. I've had it for 2 years. No shipping. Pickup only.",categoryRepository.findByName("Furniture").get(0)));

			listingRepository.save(new Listing("Ikea Bed 180cm wide", new BigDecimal("280"), new Date(System.currentTimeMillis()), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIETJZ4xLlFAJTUJ7QSXGqya6l0MFNTCc_cA&usqp=CAU", "Good", "Selling. I've had it for 2 years. No shipping. Pickup only.",categoryRepository.findByName("Furniture").get(0)));

			listingRepository.save(new Listing("Pikachu", new BigDecimal("30"), new Date(System.currentTimeMillis()), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJXp12Pf0kmQJwk_T0v6cp7vHb32xxMpntpA&usqp=CAU", "Good", "Good condition pikachu looking for new owner.",categoryRepository.findByName("Toys").get(0)));

			listingRepository.save(new Listing("Bulbasaur", new BigDecimal("30"), new Date(System.currentTimeMillis()), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzI30N7IvtRFuRVEbL_q-7YlqRCqiZyDXznluqsNEElpd2hlkQrIUqXNLYDvFBuF9Ta-4&usqp=CAU", "Good", "Good condition Bulbasaur looking for new owner.",categoryRepository.findByName("Toys").get(0)));

			listingRepository.save(new Listing(" Beach Rolex", new BigDecimal("30"), new Date(System.currentTimeMillis()), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZeSvvRuqBmfczNwDlCpCzZJ1PWCLxtFa9yZRvbe8pJAw1e7VVWNEu3j5RR-r3cmHg86o&usqp=CAU", "Excellent", "Excellent condition (Beach) Rolex for sale.",categoryRepository.findByName("Watches").get(0)));

			listingRepository.save(new Listing("Tommy Hilfiger watch", new BigDecimal("80"), new Date(System.currentTimeMillis()), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpLLNcQEazh9wXtWgxYf5kMK2BqKnRDM3q2w&usqp=CAU", "Excellent", "Excellent condition Tommy Hilfiger  watch for sale.",categoryRepository.findByName("Watches").get(0)));

			listingRepository.save(new Listing("Iphone 15", new BigDecimal("480"), new Date(System.currentTimeMillis()), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXoKpM9kdoKgyJ4c177uAzlP7pVsdMwJePx47SEy7mvb2uxYeUZBgR-MNmVLJslyB-Fg8&usqp=CAU", "Excellent", "New iphone for sale with cheap prise!!",categoryRepository.findByName("Electronics").get(0)));

			listingRepository.save(new Listing("Iphone 12", new BigDecimal("280"), new Date(System.currentTimeMillis()), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaImfFi7HBXOsaRl6jACfQLfP7RwdEX8DzDQ&usqp=CAU", "Excellent", "Used iphone for sale with cheap prise!!",categoryRepository.findByName("Electronics").get(0)));


			AppUser user1 = new AppUser("user", "user", "USER", "admin@mail.com", "1231231231", "katu 12", "00100", "Helsinki");
			AppUser user2 = new AppUser("admin", "admin", "ADMIN","user@mail.com", "1231231231", "katu 13", "00110", "Helsinki");

			appUserRepository.saveAll(Arrays.asList(user1, user2));
        };
    }


}
