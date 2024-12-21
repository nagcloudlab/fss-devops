package com.example.payment_service;

//import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
//import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporterBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
class PaymentRequest {
	private String orderId;
	private double amount;
}

@RestController
@RequestMapping("/payments")
class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        logger.info("Processing payment for order ID: {}", paymentRequest.getOrderId());

        if (paymentRequest.getAmount() > 1000) {
            logger.warn("Payment failed: Amount exceeds limit for order ID: {}", paymentRequest.getOrderId());
            return ResponseEntity.status(400).body("Payment failed: Amount exceeds limit.");
        }

        logger.info("Payment successful for order ID: {}", paymentRequest.getOrderId());
        return ResponseEntity.ok("Payment successful for order ID: " + paymentRequest.getOrderId());
    }

}

@SpringBootApplication
public class PaymentServiceApplication {
//
//    @Bean
//    OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
//        return OtlpHttpSpanExporter.builder()
//                .setEndpoint(url)
//                .build();
//    }


    public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
