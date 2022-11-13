package com.shop.management.service.shopManagementService;

import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.Product;
import com.shop.management.service.shopManagementService.repository.CategoryRepository;
import com.shop.management.service.shopManagementService.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;


@EnableEurekaClient
@SpringBootApplication
public class ShopManagementServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShopManagementServiceApplication.class, args);
	}

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;


	public ShopManagementServiceApplication(CategoryRepository userRepository, ProductRepository productRepository) {
		this.categoryRepository = userRepository;
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		categoryRepository.save(new Category("Electronics", "Electronic items"));
		categoryRepository.save(new Category("Clothing", "Clothes"));
		categoryRepository.save(new Category("Household", "Household items"));

		categoryRepository.findAll().forEach(System.out::println);


		System.out.println("----------------------------------------------------------------------------------");


	}
}
