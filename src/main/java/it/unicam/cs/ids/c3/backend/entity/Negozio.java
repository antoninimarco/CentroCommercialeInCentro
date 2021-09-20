package it.unicam.cs.ids.c3.backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
Classe che descrive un negozio associato ad un commerciante
 */
@Entity
public class Negozio extends AbstractEntity implements Serializable {


    private String nomeNegozio;

    private String indirizzo;

    private Categoria categoria;


    @OneToMany(targetEntity = Prodotto.class, mappedBy = "negozio", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval = true)
    // @JoinColumn(name = "prodotto_id")
   /* @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "negozio"
    )*/
    //  @ElementCollection(targetClass = Prodotto.class)
    private List<Prodotto> vetrina = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commerciante_id")
    private Commerciante commerciante;


    public Negozio() {
    }

    public Negozio(String nomeNegozio, String indirizzo, ArrayList<Prodotto> vetrina, Commerciante commerciante, Categoria categoria) {
        this.nomeNegozio = nomeNegozio;
        this.indirizzo = indirizzo;
        this.vetrina = vetrina;
        this.commerciante = commerciante;
        this.categoria = categoria;
    }

    public Negozio(String nomeNegozio, String indirizzo, Commerciante commerciante, Categoria categoria) {
        this.nomeNegozio = nomeNegozio;
        this.indirizzo = indirizzo;
        this.commerciante = commerciante;
        this.categoria = categoria;
    }

    public String getNomeNegozio() {
        return nomeNegozio;
    }

    public void setNomeNegozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Prodotto> getVetrina() {
        return vetrina;
    }

    public void setVetrina(ArrayList<Prodotto> vetrina) { this.vetrina = vetrina; }

    public Commerciante getCommerciante() {
        return commerciante;
    }

    public void setCommerciante(Commerciante commerciante) {
        this.commerciante = commerciante;
    }

    public Categoria getCategoria() { return categoria; }

    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public void setVetrina(List<Prodotto> vetrina) { this.vetrina = vetrina; }
}
