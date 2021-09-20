package it.unicam.cs.ids.c3.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
Classe che descrive un ordine associato ad un cliente
 */
@Entity
public class Ordine extends AbstractEntity implements Serializable {

    private StatusOrdine status;

    @OneToMany(targetEntity = Prodotto.class, mappedBy = "ordine", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Prodotto> prodotti;

    private String puntoRitiro;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="corriere_id")
    private Corriere corriere;

    public Ordine() {
    }

    public Ordine(StatusOrdine status, List<Prodotto> prodotti, String puntoRitiro, Cliente cliente) {
        this.status = status;
        this.prodotti = prodotti;
        this.puntoRitiro = puntoRitiro;
        this.cliente = cliente;
        this.corriere= null;
    }

    public StatusOrdine getStatus() {
        return status;
    }

    public void setStatus(StatusOrdine status) {
        this.status = status;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public String getPuntoRitiro() {
        return puntoRitiro;
    }

    public void setPuntoRitiro(String puntoRitiro) {
        this.puntoRitiro = puntoRitiro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public void setCorriere(Corriere corriere) {
        this.corriere = corriere;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "prodotti=" + prodotti.toString() +
                '}';
    }
}
