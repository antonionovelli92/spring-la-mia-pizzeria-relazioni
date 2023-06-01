package org.java.pizza.controller;

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
public class OffertaSpecialeController {

    private final OffertaSpecialeService offertaSpecialeService;
    private final PizzaService pizzaService;

    @Autowired
    public OffertaSpecialeController(OffertaSpecialeService offertaSpecialeService, PizzaService pizzaService) {
        this.offertaSpecialeService = offertaSpecialeService;
        this.pizzaService = pizzaService;
    }

    @GetMapping("/offerta/{pizzaId}/nuova")
    public String mostraFormNuovaOfferta(@PathVariable("pizzaId") int pizzaId, Model model) {
        Pizza pizza = pizzaService.findById(pizzaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pizza Id: " + pizzaId));

        OffertaSpeciale offertaSpeciale = new OffertaSpeciale();
        offertaSpeciale.setPizza(pizza);
        model.addAttribute("offertaSpeciale", offertaSpeciale);
        model.addAttribute("pizzaId", pizzaId);

        return "form-nuova-offerta";
    }

    @PostMapping("/offerta/nuova")
    public String salvaOffertaSpeciale(@RequestParam("pizzaId") int pizzaId,
                                       @Valid @ModelAttribute("offertaSpeciale") OffertaSpeciale offertaSpeciale,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "form-nuova-offerta";
        }

        Pizza pizza = pizzaService.findById(pizzaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pizza Id: " + pizzaId));

        offertaSpeciale.setPizza(pizza);
        offertaSpecialeService.save(offertaSpeciale);

        return String.format("redirect:/pizza/%d", pizzaId);
    }

    @GetMapping("/offerta/{offertaId}/modifica")
    public String mostraFormModificaOfferta(@PathVariable("offertaId") int offertaId, Model model) {
        OffertaSpeciale offertaSpeciale = offertaSpecialeService.findById(offertaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid offerta Id: " + offertaId));

        model.addAttribute("offertaSpeciale", offertaSpeciale);
        model.addAttribute("pizzaId", offertaSpeciale.getPizza().getId());

        return "form-modifica-offerta";
    }

    @PostMapping("/offerta/{offertaId}/modifica")
    public String salvaModificheOfferta(@PathVariable("offertaId") int offertaId,
                                        @Valid @ModelAttribute("offertaSpeciale") OffertaSpeciale offertaSpeciale,
                                        BindingResult result) {
        if (result.hasErrors()) {
            return "form-modifica-offerta";
        }

        Pizza pizza = offertaSpeciale.getPizza();
        offertaSpeciale.setId(offertaId);
        offertaSpeciale.setPizza(pizza);
        offertaSpecialeService.save(offertaSpeciale);

        return "redirect:/pizza/" + pizza.getId();
    }

    @GetMapping("/offerta/{offertaId}/offerte")
    public String getOfferteSpeciali(@PathVariable("offertaId") int offertaId, Model model) {
        OffertaSpeciale offertaSpeciale = offertaSpecialeService.findById(offertaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid offerta Id: " + offertaId));

        Pizza pizza = offertaSpeciale.getPizza();
        model.addAttribute("pizza", pizza);

        return "pizza";
    }
}
