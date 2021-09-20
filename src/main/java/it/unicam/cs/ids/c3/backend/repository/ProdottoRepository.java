package it.unicam.cs.ids.c3.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.cs.ids.c3.backend.entity.Prodotto;

/*
Interfaccia per l'accesso al database per l'entita prodotto
 */
public interface ProdottoRepository extends JpaRepository<Prodotto,Long> {

}
