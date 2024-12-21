package com.example.order_service;

//import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
//import io.opentelemetry.api.trace.Tracer;
//import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import org.springframework.web.client.RestTemplate;

@Data
class OrderRequest {
    private String orderId;
    private double amount;
}

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final RestTemplate restTemplate;/*new RestTemplate();*/

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        logger.info("Received order: {}", orderRequest);
        String paymentUrl = "http://localhost:8082/payments";
        ResponseEntity<String> paymentResponse = restTemplate.postForEntity(paymentUrl, orderRequest, String.class);
        logger.info("Payment service response: {}", paymentResponse.getBody());
        if (paymentResponse.getStatusCode().is2xxSuccessful()) {
            logger.info("Order placed successfully: {}", orderRequest.getOrderId());
            return ResponseEntity.ok("Order placed successfully: " + orderRequest.getOrderId());
        }else{
            logger.warn("Order failed: Payment failed for order ID: {}", orderRequest.getOrderId());
            return ResponseEntity.status(400).body("Order failed: Payment failed.");
        }
    }
}

@SpringBootApplication
public class OrderServiceApplication {

//    @Bean
//    OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
//        return OtlpHttpSpanExporter.builder()
//                .setEndpoint(url)
//                .build();
//    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
