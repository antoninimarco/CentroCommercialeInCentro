package it.unicam.cs.ids.c3.backend.service;

import java.util.List;

import it.unicam.cs.ids.c3.backend.entity.Negozio;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import it.unicam.cs.ids.c3.backend.repository.CommercianteRepository;

import javax.annotation.PostConstruct;

/*
Classe service che effettua operazioni nel database per l'entita commerciante
 */
@Service
public class CommercianteService {

    private CommercianteRepository commercianteRepository;

    public CommercianteService(CommercianteRepository commercianteRepository) {
        this.commercianteRepository = commercianteRepository;
    }

    //Ritorna tutti i commercianti presenti nel db
    public List<Commerciante> findAll() {
        return commercianteRepository.findAll();
    }

    //Ricerca commercianti in base ad una stringa di ricerca
    public List<Commerciante> search(String searchTerm) {
        return commercianteRepository.search(searchTerm);
    }

    @PostConstruct
    public void populateTestData() {
        Commerciante commerciante = new Commerciante("Mario Rossi", "123456");
        commercianteRepository.save(commerciante);
    }
}
