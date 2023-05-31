package org.java.pizza.repo;

import java.util.List;

import org.java.pizza.pojo.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

	List<Pizza> findByNomeContainingIgnoreCase(String nome);
   
}
