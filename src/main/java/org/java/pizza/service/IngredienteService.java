package org.java.pizza.service;


import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Ingrediente;
import org.java.pizza.repo.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
    private final IngredienteRepository ingredienteRepository;

    @Autowired
    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<Ingrediente> getAllIngredienti() {
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> getIngredienteById(int id) {
        return ingredienteRepository.findById(id);
    }

    public List<Ingrediente> getIngredientiByIds(List<Integer> ingredientiIds) {
        return ingredienteRepository.findAllById(ingredientiIds);
    }

    public void save(Ingrediente ingrediente) {
        ingredienteRepository.save(ingrediente);
    }

    public void deleteById(int id) {
        ingredienteRepository.deleteById(id);
    }
}

