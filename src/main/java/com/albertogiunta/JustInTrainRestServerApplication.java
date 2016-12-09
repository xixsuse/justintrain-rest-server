package com.albertogiunta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JustInTrainRestServerApplication implements CommandLineRunner {

//	@Autowired
//	private StationRepository repository;

	public static void main(String[] args) {
        SpringApplication.run(JustInTrainRestServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//		// fetch all customers
//		System.out.println("Customers found with findAll():");
//		System.out.println("-------------------------------");
//		for (Stations station : repository.findAll()) {
//			System.out.println(station.toString());
//		}
//		System.out.println();
//
//		// fetch an individual customer
//		System.out.println("Customer found with findByFirstName('Alice'):");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByNameLong("Pesaro"));
    }

}
