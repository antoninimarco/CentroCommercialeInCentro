package it.unicam.cs.ids.c3.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.cs.ids.c3.backend.entity.DescrizioneProdotto;

/*
Interfaccia per l'accesso al database per l'entita descrizione prodotto
 */
public interface DescrizioneProdottoRepository extends JpaRepository<DescrizioneProdotto,Long>{

}
