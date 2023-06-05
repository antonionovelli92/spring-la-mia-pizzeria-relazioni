package org.java.pizza.controller;

import java.util.List;

import org.java.pizza.pojo.Ingrediente;
import org.java.pizza.pojo.OffertaSpeciale;
import org.java.pizza.pojo.Pizza;
import org.java.pizza.service.IngredienteService;
import org.java.pizza.service.OffertaSpecialeService;
import org.java.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
public class PizzaController {
    private final PizzaService pizzaService;
    private final OffertaSpecialeService offertaSpecialeService;
    private final IngredienteService ingredienteService;

    @Autowired
    public PizzaController(PizzaService pizzaService, OffertaSpecialeService offertaSpecialeService,
                           IngredienteService ingredienteService) {
        this.pizzaService = pizzaService;
        this.offertaSpecialeService = offertaSpecialeService;
        this.ingredienteService = ingredienteService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Pizza> pizze = pizzaService.getAllPizze();
        model.addAttribute("pizze", pizze);
        return "index";
    }

    @GetMapping("/pizza/{id}")
    public String getPizza(@PathVariable("id") int id, Model model) {
        Pizza pizza = pizzaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pizza Id: " + id));

        List<OffertaSpeciale> offerteSpeciali = offertaSpecialeService.getOfferteSpecialiByPizza(pizza);
        model.addAttribute("pizza", pizza);
        model.addAttribute("offerteSpeciali", offerteSpeciali);

        return "pizza";
    }

    @GetMapping("/pizza/new")
    public String showCreatePizzaForm(Model model) {
        List<Ingrediente> ingredienti = ingredienteService.getAllIngredienti();
        model.addAttribute("ingredienti", ingredienti);
        model.addAttribute("pizza", new Pizza());
        return "createPizzaForm";
    }

    @PostMapping("/pizza/create")
    public String createPizza(@Valid @ModelAttribute("pizza") Pizza pizza,
                              @RequestParam("ingredienti") List<Integer> ingredientiIds,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createPizzaForm";
        }

        List<Ingrediente> ingredienti = ingredienteService.getIngredientiByIds(ingredientiIds);
        pizza.setIngredienti(ingredienti);

        pizzaService.save(pizza);
        return "redirect:/";
    }

    @PostMapping("/pizza")
    public String indexWithFilter(Model model, @RequestParam("filtro") String filtro) {
        List<Pizza> pizze = pizzaService.getAllPizzeByNome(filtro);
        model.addAttribute("pizze", pizze);
        model.addAttribute("filtro", filtro);
        return "index";
    }

    @GetMapping("/pizza/edit/{id}")
    public String showEditPizzaForm(@PathVariable("id") int id, Model model) {
        Pizza pizza = pizzaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pizza Id: " + id));

        List<Ingrediente> ingredienti = ingredienteService.getAllIngredienti();
        model.addAttribute("ingredienti", ingredienti);
        model.addAttribute("pizza", pizza);
        return "editPizzaForm";
    }

    @PostMapping("/pizza/update/{id}")
    public String updatePizza(@PathVariable("id") int id, @ModelAttribute("pizza") @Valid Pizza updatedPizza,
                              @RequestParam("ingredienti") List<Integer> ingredientiIds,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editPizzaForm";
        }

        List<Ingrediente> ingredienti = ingredienteService.getIngredientiByIds(ingredientiIds);

        pizzaService.findById(id)
                .ifPresent(pizza -> {
                    pizza.setNome(updatedPizza.getNome());
                    pizza.setDescrizione(updatedPizza.getDescrizione());
                    pizza.setFoto(updatedPizza.getFoto());
                    pizza.setPrezzo(updatedPizza.getPrezzo());
                    pizza.setIngredienti(ingredienti);
                    pizzaService.save(pizza);
                });

        model.addAttribute("message", "Pizza aggiornata con successo!");
        return "redirect:/";
    }

    @GetMapping("/pizza/delete/{id}")
    public String deletePizza(@PathVariable("id") int id) {
        pizzaService.deleteById(id);
        return "redirect:/";
    }
}
