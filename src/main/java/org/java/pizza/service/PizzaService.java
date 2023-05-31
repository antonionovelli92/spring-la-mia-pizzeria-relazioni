package org.java.pizza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.java.pizza.repo.PizzaRepository;
import org.java.pizza.pojo.Pizza;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizze() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> findById(int id) {
        return pizzaRepository.findById(id);
    }

    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }
    public List<Pizza> getAllPizzeByNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return pizzaRepository.findAll();
        } else {
            return pizzaRepository.findByNomeContainingIgnoreCase(nome);
        }
    }
    public void deleteById(int id) {
        pizzaRepository.deleteById(id);
    }
    public Pizza getPizzaById(int pizzaId) {
        return pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new IllegalArgumentException("Pizza non trovata con ID: " + pizzaId));
    }


}
