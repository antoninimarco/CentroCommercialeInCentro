package it.unicam.cs.ids.c3.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*
Classe che descrive una specifica istanza di un prodotto che verrà associato ad un negozio
o ad un ordine
 */
@Entity
public class Prodotto extends AbstractEntity implements Serializable,Cloneable {
	

	 @ManyToOne
	  @JoinColumn(name="descrizioneProdotto_id")
	  private DescrizioneProdotto descrizione;

	@ManyToOne
	@JoinColumn(name="ordine_id")
	 private Ordine ordine;

	  private int quantita;
	 
	 @ManyToOne(targetEntity = Negozio.class)
	 // @JoinColumn(name="negozio_id")
	/*@ManyToOne(targetEntity = Negozio.class)
	 @MapsId("negozioId")
	 @JoinColumn(
			 name = "negozio_id",
			 foreignKey = @ForeignKey(
					 name = "negozio_prodotto_id_fk"
			 )
	 )*/
	  private Negozio negozio;

	public Prodotto() {}


	public Prodotto(DescrizioneProdotto descrizione, int quantita) {
		this.descrizione = descrizione;
		this.quantita = quantita;
	}


	public DescrizioneProdotto getDescrizione() {
		return descrizione;
	}

	public String getNomeProdotto(){return descrizione.getNomeProdotto();}

	public void setDescrizione(DescrizioneProdotto descrizione) {
		this.descrizione = descrizione;
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Negozio getNegozio() {
		return negozio;
	}

	public void setNegozio(Negozio negozio) {
		this.negozio = negozio;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	@Override
	public Prodotto clone() throws CloneNotSupportedException {
		return (Prodotto) super.clone();
	}

	@Override
	public String toString() {
		return "Prodotto{" +
				"descrizione=" + descrizione +
				", quantita=" + quantita +
				'}';
	}
}
