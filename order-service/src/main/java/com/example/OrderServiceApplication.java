package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
class OrderController {

	private final KafkaTemplate<String, String> kafkaTemplate;

	@PostMapping("/order")
	public String placeOrder(@RequestBody List<Integer> productIds){
		String order = productIds.toString();
		kafkaTemplate.send("orders", order);
		return "Order placed successfully";
	}

}


@SpringBootApplication
@CrossOrigin(origins = "*")
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
