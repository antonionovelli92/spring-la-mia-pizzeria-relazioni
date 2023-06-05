package org.java.pizza.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome dell'ingrediente non può essere vuoto")
    @Size(min = 1, max = 50, message = "Il nome dell'ingrediente deve avere una lunghezza compresa tra 1 e 50 caratteri")
    private String nome;

    public Ingrediente() {
    }

    public Ingrediente(Integer id, String nome) {
        setId(id);
        setNome(nome);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Ingrediente [id=" + id + ", nome=" + nome + "]";
    }
}
