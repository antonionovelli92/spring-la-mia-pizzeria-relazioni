package org.java.pizza.repo;

import org.java.pizza.pojo.OffertaSpeciale;
import org.java.pizza.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffertaSpecialeRepository extends JpaRepository<OffertaSpeciale, Integer> {

    List<OffertaSpeciale> findByPizzaId(int pizzaId);

	List<OffertaSpeciale> findByPizza(Pizza pizza);
}
