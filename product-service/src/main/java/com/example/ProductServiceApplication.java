package com.example;

import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
class Product{
	@Id
	private Long id;
	private String name;
}

interface ProductRepository extends JpaRepository<Product, Long> {
}

@RequiredArgsConstructor
@SpringBootApplication
@RestController
public class ProductServiceApplication {

	private final ProductRepository productRepository;

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productRepository.findAll().stream().toList();
	}

	//Intialize the database with some data
	@PostConstruct
	public void init() {
		productRepository.save(new Product(1L, "Product 1"));
		productRepository.save(new Product(2L, "Product 2"));
		productRepository.save(new Product(3L, "Product 3"));
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
