package org.java.pizza.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class OffertaSpeciale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @NotNull(message = "La data di inizio è obbligatoria")
    private LocalDate dataInizio;

    @NotNull(message = "La data di fine è obbligatoria")
    private LocalDate dataFine;

    @NotBlank(message = "Il titolo è obbligatorio")
    private String titolo;

    @NotNull(message = "La percentuale di sconto è obbligatoria")
    @Positive(message = "La percentuale di sconto deve essere un numero positivo")
    private Integer percentualeSconto;

    public OffertaSpeciale() {
        
    }

    public OffertaSpeciale(Pizza pizza, LocalDate dataInizio, LocalDate dataFine, String titolo, Integer percentualeSconto) {
        setPizza(pizza);
        setDataInizio(dataInizio);
        setDataFine(dataFine);
        setTitolo(titolo);
        setPercentualeSconto(percentualeSconto);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getPercentualeSconto() {
        return percentualeSconto;
    }

    public void setPercentualeSconto(Integer percentualeSconto) {
        this.percentualeSconto = percentualeSconto;
    }
}
