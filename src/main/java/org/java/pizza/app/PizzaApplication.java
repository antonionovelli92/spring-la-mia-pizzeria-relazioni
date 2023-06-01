package org.java.pizza.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Pizza;
import org.java.pizza.pojo.OffertaSpeciale;
import org.java.pizza.service.PizzaService;
import org.java.pizza.service.OffertaSpecialeService;

@SpringBootApplication
public class PizzaApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OffertaSpecialeService offertaSpecialeService;

	public PizzaApplication(PizzaService pizzaService, OffertaSpecialeService offertaSpecialeService) {
		this.pizzaService = pizzaService;
		this.offertaSpecialeService = offertaSpecialeService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		Pizza p = new Pizza("Margherita", "Sugo e mozzarella", "www.fotomiapizza.ciro", 10);
		Pizza p2 = new Pizza("Capricciosa", "Sugo e mozzarella", "www.fotomiapizza.ciro", 10);
		Pizza p3 = new Pizza("Quattro stagioni", "Sugo e mozzarella", "www.fotomiapizza.ciro", 10);
		Pizza p4 = new Pizza("Gigi d'alessio", "Napoletana", "www.fotomiapizzawewe.ciro", 10);
		
		System.out.println(p);
		pizzaService.save(p);
		
		System.out.println(p2);
		pizzaService.save(p2);
		
		System.out.println(p3);
		pizzaService.save(p3);
		
		System.out.println(p4);
		pizzaService.save(p4);

		List<Pizza> pizze = pizzaService.findAll();
		System.out.println(pizze);
		
		Optional<Pizza> optPizza = pizzaService.findById(1);
		
		if (optPizza.isPresent()) {
			Pizza dbPizza = optPizza.get();
			System.out.println("Pizza con id 1");
			System.out.println("--------------");
			System.out.println(dbPizza);
		} else {
			System.out.println("Pizza con id 1 non trovata :-(");
		}
		
		OffertaSpeciale offerta1 = new OffertaSpeciale(p, LocalDate.now(), LocalDate.now().plusDays(7), "Offerta del giorno", 5);
		OffertaSpeciale offerta2 = new OffertaSpeciale(p2, LocalDate.now(), LocalDate.now().plusDays(7), "Offerta speciale", 8);
		OffertaSpeciale offerta3 = new OffertaSpeciale(p3, LocalDate.now(), LocalDate.now().plusDays(7), "Speciale", 8);
		OffertaSpeciale offerta4 = new OffertaSpeciale(p4, LocalDate.now(), LocalDate.now().plusDays(7), "Offerta", 8);

		offertaSpecialeService.save(offerta1);
		offertaSpecialeService.save(offerta2);
		offertaSpecialeService.save(offerta3);
		offertaSpecialeService.save(offerta4);
		
		List<OffertaSpeciale> offerte = offertaSpecialeService.findAll();
		System.out.println(offerte);

		Optional<OffertaSpeciale> optOfferta = offertaSpecialeService.findById(1);
		
		if (optOfferta.isPresent()) {
			OffertaSpeciale dbOfferta = optOfferta.get();
			System.out.println("Offerta speciale con id 1");
			System.out.println("--------------------------");
			System.out.println(dbOfferta);
		} else {
			System.out.println("Offerta speciale con id 1 non trovata :-(");
		}
	}
}
