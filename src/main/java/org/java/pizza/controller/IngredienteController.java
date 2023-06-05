package org.java.pizza.controller;

import org.java.pizza.pojo.Ingrediente;
import org.java.pizza.repo.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class IngredienteController {
    @Autowired
    private IngredienteRepository ingredienteRepository;
    
    
    @GetMapping("/ingredienti")
    public String getElencoIngredienti(Model model) {
        List<Ingrediente> ingredienti = ingredienteRepository.findAll();
        model.addAttribute("ingredienti", ingredienti);
        return "elenco-ingredienti";
    }
    
   
}
