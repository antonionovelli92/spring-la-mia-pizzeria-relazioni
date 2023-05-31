package org.java.pizza.controller;

import java.util.List;

import org.java.pizza.pojo.OffertaSpeciale;
import org.java.pizza.pojo.Pizza;
import org.java.pizza.service.OffertaSpecialeService;
import org.java.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
    private final PizzaService pizzaService;
    private final OffertaSpecialeService offertaSpecialeService;

    @Autowired
    public PizzaController(PizzaService pizzaService, OffertaSpecialeService offertaSpecialeService) {
        this.pizzaService = pizzaService;
        this.offertaSpecialeService = offertaSpecialeService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Pizza> pizze = pizzaService.getAllPizze();
        model.addAttribute("pizze", pizze);
        return "index";
    }

    @GetMapping("/{id}")
    public String getPizza(@PathVariable("id") int id, Model model) {
        Pizza pizza = pizzaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pizza Id: " + id));

        List<OffertaSpeciale> offerteSpeciali = offertaSpecialeService.getOfferteSpecialiByPizza(pizza);
        model.addAttribute("pizza", pizza);
        model.addAttribute("offerteSpeciali", offerteSpeciali);

        return "pizza";
    }

    @GetMapping("/new")
    public String showCreatePizzaForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "createPizzaForm";
    }

    @PostMapping("/create")
    public String createPizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createPizzaForm";
        }

        pizzaService.save(pizza);
        return "redirect:/pizza";
    }

    @PostMapping("/")
    public String indexWithFilter(Model model, @RequestParam("filtro") String filtro) {
        List<Pizza> pizze = pizzaService.getAllPizzeByNome(filtro);
        model.addAttribute("pizze", pizze);
        model.addAttribute("filtro", filtro);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showEditPizzaForm(@PathVariable("id") int id, Model model) {
        Pizza pizza = pizzaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pizza Id: " + id));

        model.addAttribute("pizza", pizza);
        return "editPizzaForm";
    }

    @PostMapping("/update/{id}")
    public String updatePizza(@PathVariable("id") int id, @ModelAttribute("pizza") @Valid Pizza updatedPizza,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
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
        return "redirect:/pizza";
    }

    @GetMapping("/delete/{id}")
    public String deletePizza(@PathVariable("id") int id) {
        pizzaService.deleteById(id);
        return "redirect:/pizza";
    }
}
