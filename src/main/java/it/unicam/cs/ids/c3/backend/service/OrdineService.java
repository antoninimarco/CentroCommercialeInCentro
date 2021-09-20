package it.unicam.cs.ids.c3.backend.service;

import it.unicam.cs.ids.c3.backend.entity.Cliente;
import it.unicam.cs.ids.c3.backend.entity.Ordine;
import it.unicam.cs.ids.c3.backend.repository.OrdineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Classe service che effettua operazioni nel database per l'entita ordine
 */
@Service
public class OrdineService {


    private OrdineRepository ordineRepository;

    public OrdineService(OrdineRepository ordineRepository) {
        this.ordineRepository = ordineRepository;
    }

    //Restituisce tutti gli ordini nel db
    public List<Ordine> findAll() {
        return ordineRepository.findAll();
    }

    //Restituisce tutti gli ordini nel db in base ad una stringa di ricerca
    public List<Ordine> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return ordineRepository.findAll();
        } else {
            return ordineRepository.searchForCliente(stringFilter);
        }
    }

    //Ricerca ordini per cliente
    public List<Ordine> searchForCliente(String searchTerm) {
        return ordineRepository.searchForCliente(searchTerm);
    }

    //Salva un ordine nel db
    public Ordine save(Ordine ordine) {
        return ordineRepository.save(ordine);
    }
}
