package org.java.pizza.controller;

import java.util.List;
import java.util.Optional;


import org.java.pizza.pojo.Pizza;
import org.java.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Pizza> pizze = pizzaService.getAllPizze();
        model.addAttribute("pizze", pizze);
        return "index";
    }
    @GetMapping("/pizze/{id}")
	public String getPizza(
			Model model,
			@PathVariable("id") int id
	) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza";
	}
//    Creazione 
    @GetMapping("/new")
    public String showCreatePizzaForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "createPizzaForm";
    }

    @PostMapping("/create")
    public String createPizza(
            Model model,
            @Valid @ModelAttribute("pizza") Pizza pizza,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            for (ObjectError err : bindingResult.getAllErrors()) {
                System.err.println("error: " + err.getDefaultMessage());
            }
            model.addAttribute("pizza", pizza);
            model.addAttribute("errors", bindingResult);
            return "createPizzaForm";
        }

        pizzaService.save(pizza);
        return "redirect:/";
    }

    
//    Filtro
    @PostMapping("/")
    public String indexWithFilter(Model model, @RequestParam("filtro") String filtro) {
        List<Pizza> pizze = pizzaService.getAllPizzeByNome(filtro);
        model.addAttribute("pizze", pizze);
        model.addAttribute("filtro", filtro);
        return "index";
    }

//    Edit
    @GetMapping("/edit/{id}")
    public String showEditPizzaForm(@PathVariable("id") int id, Model model) {
        Optional<Pizza> optPizza = pizzaService.findById(id);
        Pizza pizza = optPizza.orElseThrow(() -> new IllegalArgumentException("Invalid pizza Id: " + id));
        model.addAttribute("pizza", pizza);
        return "editPizzaForm";
    }
// Update
    @PostMapping("/update/{id}")
    public String updatePizza(@PathVariable("id") int id, Model model, @ModelAttribute("pizza") @Validated Pizza updatedPizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //  ci sono errori di validazione return
            return "editPizzaForm";
        }

        pizzaService.findById(id)
                .ifPresent(pizza -> {
                    pizza.setNome(updatedPizza.getNome());
                    pizza.setDescrizione(updatedPizza.getDescrizione());
                    pizza.setFoto(updatedPizza.getFoto());
                    pizza.setPrezzo(updatedPizza.getPrezzo());
                    pizzaService.save(pizza);
                });

        model.addAttribute("message", "Pizza aggiornata con successo!");

        return "redirect:/";
    }



//    Delete
    @GetMapping("/delete/{id}")
    public String deletePizza(@PathVariable("id") int id) {
        pizzaService.deleteById(id);
        return "redirect:/";
    }

   


}