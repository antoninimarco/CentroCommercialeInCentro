package it.unicam.cs.ids.c3.backend.entity;

import javax.persistence.Entity;

/*
Classe che descrive l'attore corriere
 */
@Entity
public class Corriere extends AbstractEntity{

    private String nomeCorriere;

    private StausCorriere statusDisponibilita;

    public Corriere() {
    }

    public Corriere(String nomeCorriere, StausCorriere statusDisponibilita) {
        this.nomeCorriere = nomeCorriere;
        this.statusDisponibilita = statusDisponibilita;
    }

    public String getNomeCorriere() {
        return nomeCorriere;
    }

    public void setNomeCorriere(String nomeCorriere) {
        this.nomeCorriere = nomeCorriere;
    }

    public StausCorriere getStatusDisponibilita() {
        return statusDisponibilita;
    }

    public void setStatusDisponibilita(StausCorriere statusDisponibilita) {
        this.statusDisponibilita = statusDisponibilita;
    }
}
