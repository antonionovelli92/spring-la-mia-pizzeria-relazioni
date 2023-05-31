package org.java.pizza.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Il campo Nome è obbligatorio")
    @Size(max = 50, message = "Il campo Nome deve essere lungo al massimo 50 caratteri")
    private String nome;

    @NotBlank(message = "Il campo Descrizione è obbligatorio")
    @Size(max = 200, message = "Il campo Descrizione deve essere lungo al massimo 200 caratteri")
    private String descrizione;

    @NotBlank(message = "Il campo Foto è obbligatorio")
    private String foto;

    @NotNull(message = "Il campo Prezzo è obbligatorio")
 
    private double prezzo;

    public Pizza() {
        // Costruttore di default 
    }

    public Pizza(String nome, String descrizione, String foto, double prezzo) {
        setNome(nome);
        setDescrizione(descrizione);
        setFoto(foto);
        setPrezzo(prezzo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
