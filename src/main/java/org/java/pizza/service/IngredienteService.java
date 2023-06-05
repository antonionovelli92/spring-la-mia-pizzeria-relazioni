package org.java.pizza.service;

import java.util.List;

import org.java.pizza.pojo.Ingrediente;
import org.java.pizza.repo.IngredienteRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<Ingrediente> getAllIngredienti() {
        return ingredienteRepository.findAll();
    }

    
}
