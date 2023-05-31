package org.java.pizza.service;

import org.java.pizza.pojo.OffertaSpeciale;
import org.java.pizza.pojo.Pizza;
import org.java.pizza.repo.OffertaSpecialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffertaSpecialeService {

    private final OffertaSpecialeRepository offertaSpecialeRepository;

    @Autowired
    public OffertaSpecialeService(OffertaSpecialeRepository offertaSpecialeRepository) {
        this.offertaSpecialeRepository = offertaSpecialeRepository;
    }

    public List<OffertaSpeciale> findAll() {
        return offertaSpecialeRepository.findAll();
    }

    public Optional<OffertaSpeciale> findById(int offertaId) {
        return offertaSpecialeRepository.findById(offertaId);
    }

    public List<OffertaSpeciale> findByPizzaId(int pizzaId) {
        return offertaSpecialeRepository.findByPizzaId(pizzaId);
    }

    public OffertaSpeciale save(OffertaSpeciale offertaSpeciale) {
        return offertaSpecialeRepository.save(offertaSpeciale);
    }

    public void deleteById(int offertaId) {
        offertaSpecialeRepository.deleteById(offertaId);
    }

    public OffertaSpeciale getOffertaSpecialeById(int offertaId) {
        return offertaSpecialeRepository.findById(offertaId)
                .orElseThrow(() -> new IllegalArgumentException("Offerta speciale non trovata con ID: " + offertaId));
    }

    public List<OffertaSpeciale> getOfferteSpecialiByPizza(Pizza pizza) {
        return offertaSpecialeRepository.findByPizza(pizza);
    }
}
