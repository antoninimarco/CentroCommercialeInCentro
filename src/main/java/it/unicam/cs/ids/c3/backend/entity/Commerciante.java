package it.unicam.cs.ids.c3.backend.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
Classe che descrive l'attore commerciante
 */
@Entity
public class Commerciante extends AbstractEntity implements Cloneable {


    private String nomeCommerciante;
    private String pIva;

    public Commerciante() {
    }

    public Commerciante(String nomeCommerciante, String pIva) {
        super();
        this.nomeCommerciante = nomeCommerciante;
        this.pIva = pIva;
    }

    public String getNomeCommerciante() {
        return nomeCommerciante;
    }

    public void setNomeCommerciante(String nomeCommerciante) {
        this.nomeCommerciante = nomeCommerciante;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }


}
