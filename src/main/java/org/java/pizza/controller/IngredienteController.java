package org.java.pizza.controller;

import org.java.pizza.pojo.Ingrediente;
import org.java.pizza.repo.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class IngredienteController {
    private final IngredienteRepository ingredienteRepository;

    @Autowired
    public IngredienteController(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @GetMapping("/ingredienti")
    public String index(Model model) {
        List<Ingrediente> ingredienti = ingredienteRepository.findAll();
        model.addAttribute("ingredienti", ingredienti);
        return "indexIngredienti";
    }

    @GetMapping("/ingrediente/{id}")
    public String getIngrediente(@PathVariable("id") int id, Model model) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ingrediente Id: " + id));

        model.addAttribute("ingrediente", ingrediente);
        return "ingrediente";
    }

    @GetMapping("/ingrediente/new")
    public String showCreateIngredienteForm(Model model) {
        model.addAttribute("ingrediente", new Ingrediente());
        return "createIngredienteForm";
    }

    @PostMapping("/ingrediente/create")
    public String createIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createIngredienteForm";
        }

        ingredienteRepository.save(ingrediente);
        return "redirect:/ingredienti";
    }


    @GetMapping("/ingrediente/edit/{id}")
    public String showEditIngredienteForm(@PathVariable("id") int id, Model model) {
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
        if (ingrediente.isPresent()) {
            model.addAttribute("ingrediente", ingrediente.get());
            return "editIngredienteForm";
        } else {
            return "redirect:/ingredienti";
        }
    }

    @PostMapping("/ingrediente/update/{id}")
    public String updateIngrediente(@PathVariable("id") int id,
                                    @ModelAttribute("ingrediente") @Valid Ingrediente updatedIngrediente,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editIngredienteForm";
        }

        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
        if (ingrediente.isPresent()) {
            Ingrediente ingredienteToUpdate = ingrediente.get();
            ingredienteToUpdate.setNome(updatedIngrediente.getNome());
            ingredienteRepository.save(ingredienteToUpdate);
            model.addAttribute("message", "Ingrediente aggiornato con successo!");
        }
        return "redirect:/ingredienti";
    }

    @GetMapping("/ingrediente/delete/{id}")
    public String deleteIngrediente(@PathVariable("id") int id) {
        ingredienteRepository.deleteById(id);
        return "redirect:/ingredienti";
    }
}
