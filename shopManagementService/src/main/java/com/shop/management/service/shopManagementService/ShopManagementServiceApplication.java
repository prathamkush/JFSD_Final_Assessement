package com.shop.management.service.shopManagementService;

import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class ShopManagementServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShopManagementServiceApplication.class, args);
	}

	private final CategoryRepository categoryRepository;


	public ShopManagementServiceApplication(CategoryRepository userRepository) {
		this.categoryRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		categoryRepository.save(new Category("Electronics", "Electronic items"));
		categoryRepository.save(new Category("Clothing", "Clothes"));
		categoryRepository.save(new Category("Household", "Household items"));


		categoryRepository.findAll().forEach(System.out::println);
	}
}
