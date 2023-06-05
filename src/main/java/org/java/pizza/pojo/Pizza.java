package org.java.pizza.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

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

    @ManyToMany
    @JoinTable(name = "pizza_ingrediente",
               joinColumns = @JoinColumn(name = "pizza_id"),
               inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    private List<Ingrediente> ingredienti;

    @OneToMany(mappedBy = "pizza")
    private List<OffertaSpeciale> offerteSpeciali;

    public Pizza() {
        
    }

    public Pizza(String nome, String descrizione, String foto, double prezzo) {
        setNome(nome);
        setDescrizione(descrizione);
        setFoto(foto);
        setPrezzo(prezzo);
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

    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public List<OffertaSpeciale> getOfferteSpeciali() {
        return offerteSpeciali;
    }

    public void setOfferteSpeciali(List<OffertaSpeciale> offerteSpeciali) {
        this.offerteSpeciali = offerteSpeciali;
    }
}
