package it.unicam.cs.ids.c3.backend.repository;

import it.unicam.cs.ids.c3.backend.entity.Negozio;
import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
Interfaccia per l'accesso al database per l'entita commerciante
 */
public interface CommercianteRepository extends JpaRepository<Commerciante,Long>{

    //Ricerca commercianti filtrandoli per nome
    @Query("select c from Commerciante c " +
            "where lower(c.nomeCommerciante) like lower(concat('%', :searchTerm, '%')) " )
    List<Commerciante> search(@Param("searchTerm") String searchTerm);
}
