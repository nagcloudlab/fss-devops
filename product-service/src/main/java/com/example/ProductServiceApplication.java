package com.example;

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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
class Product implements Serializable {
	@Id
	private int id;
	private String name;
	private int quantity;
	private double price;
}

interface ProductRepository extends JpaRepository<Product, Integer> {
}

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
class ProductController {

	private final ProductRepository repository;

	@PostConstruct
	void init(){
		Product p1 = new Product(1, "Laptop", 100, 50000);
		Product p2 = new Product(2, "Mobile", 200, 20000);
		Product p3 = new Product(3, "Tablet", 300, 10000);
		repository.save(p1);
		repository.save(p2);
		repository.save(p3);
	}

	@Cacheable("products")
	@GetMapping("/products")
	public List<Product> getProducts(){
		return repository.findAll();
	}
}


@SpringBootApplication
@EnableCaching
@CrossOrigin(origins = "*")
public class ProductServiceApplication {

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(1)) // Cache TTL
				.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
				);

		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(config)
				.build();
	}


	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
