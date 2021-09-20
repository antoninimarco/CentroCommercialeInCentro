package it.unicam.cs.ids.c3.backend.entity;

import javax.persistence.Entity;

/*
Classe che descrive l'attore cliente
 */
@Entity
public class Cliente extends AbstractEntity implements Cloneable{

    private String nomeCliente;
    private String indirizzo;

    public Cliente(){}

    public Cliente(String nomeCliente, String indirizzo) {
        this.nomeCliente = nomeCliente;
        this.indirizzo = indirizzo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
