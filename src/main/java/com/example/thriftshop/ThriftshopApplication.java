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


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


@SpringBootApplication
public class ThriftshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThriftshopApplication.class, args);
	}

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

@Bean
    public CommandLineRunner expenceDemo(CategoryRepository categoryRepository, ListingRepository listingRepository,
	AppUserRepository appUserRepository) {
        return(args) -> {
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

			

			

			listingRepository.save(new Listing("Makia Shirt", new BigDecimal("40"), new Date(System.currentTimeMillis()),
			imageBytes1, "Good", "Selling Makia shirt. Good condition. ",4,
			categoryRepository.findByName("Fashion").get(0), appUserRepository.findByUsername("user")));

			listingRepository.save(new Listing("Carhart hoodie", new BigDecimal("80"), new Date(System.currentTimeMillis()), imageBytes2, "Good", "New carhartt hoodie. Been in my closet for a year. the tag is still on. ",2,categoryRepository.findByName("Fashion").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing("CCM hockey stick", new BigDecimal("5"), new Date(System.currentTimeMillis()), imageBytes3, "Good", "Brand new hockeystick. ",6,categoryRepository.findByName("Sport Equipment").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Bauer hockey stick", new BigDecimal("20"), new Date(System.currentTimeMillis()), imageBytes4, "Good", "Brand new hockeystick. ",8,categoryRepository.findByName("Sport Equipment").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing("Ikea sofa", new BigDecimal("180"), new Date(System.currentTimeMillis()), imageBytes5, "Good", "Selling this sofa. I've had it for 2 years.",1,categoryRepository.findByName("Furniture").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Ikea Bed 180cm wide", new BigDecimal("280"), new Date(System.currentTimeMillis()), imageBytes6, "Good", "Selling. I've had it for 2 years. ",1,categoryRepository.findByName("Furniture").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing("Pikachu", new BigDecimal("30"), new Date(System.currentTimeMillis()), imageBytes7, "Good", "Good condition pikachu looking for new owner.",1,categoryRepository.findByName("Toys").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Bulbasaur", new BigDecimal("30"), new Date(System.currentTimeMillis()), imageBytes8, "Good", "Good condition Bulbasaur looking for new owner.",2,categoryRepository.findByName("Toys").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing(" Beach Rolex", new BigDecimal("30"), new Date(System.currentTimeMillis()), imageBytes9, "Excellent", "Excellent condition (Beach) Rolex for sale.",50,categoryRepository.findByName("Watches").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Tommy Hilfiger watch", new BigDecimal("80"), new Date(System.currentTimeMillis()), imageBytes10, "Excellent", "Excellent condition Tommy Hilfiger watch for sale.",10,categoryRepository.findByName("Watches").get(0), appUserRepository.findByUsername("admin")));


			listingRepository.save(new Listing("Iphone 15", new BigDecimal("480"), new Date(System.currentTimeMillis()), imageBytes11, "Excellent", "New iphone for sale with cheap prise!!",48,categoryRepository.findByName("Electronics").get(0), appUserRepository.findByUsername("user")));


			listingRepository.save(new Listing("Iphone 12", new BigDecimal("280"), new Date(System.currentTimeMillis()), imageBytes12, "Excellent", "Used iphone for sale with cheap prise!!",100,categoryRepository.findByName("Electronics").get(0), appUserRepository.findByUsername("admin")));



			
        };
    }


}
