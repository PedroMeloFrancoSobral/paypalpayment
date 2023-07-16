package com.paypalpaymentassignment.paypalpayment;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.paypalpaymentassignment.paypalpayment.domain.Product;
import com.paypalpaymentassignment.paypalpayment.repositories.ProductRepository;


@SpringBootApplication
public class PaypalpaymentApplication implements CommandLineRunner{


	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(PaypalpaymentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Product product1 = new Product(null, "Mousepad", 50.00);
		productRepository.save(product1);

	}
}
