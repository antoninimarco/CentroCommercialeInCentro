package it.unicam.cs.ids.c3.backend.repository;

import it.unicam.cs.ids.c3.backend.entity.Categoria;
import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.cs.ids.c3.backend.entity.Negozio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/*
Interfaccia per l'accesso al database per l'entita negozio
 */
public interface NegozioRepository extends JpaRepository<Negozio,Long>{


    //Ricerca negozi filtrandoli per nome
  @Query("select c from Negozio c " +
          "where lower(c.nomeNegozio) like lower(concat('%', :searchTerm, '%')) " )
    List<Negozio> search(@Param("searchTerm") String searchTerm);

    //Ricerca negozi filtrandoli per categoria
    @Query("SELECT c FROM Negozio c WHERE c.categoria = :categoria")
    List<Negozio> searchByCategoria(@Param("categoria") Categoria categoria);
}
