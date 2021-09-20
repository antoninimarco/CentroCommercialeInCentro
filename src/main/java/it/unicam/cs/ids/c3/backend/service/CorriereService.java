package it.unicam.cs.ids.c3.backend.service;

import it.unicam.cs.ids.c3.backend.entity.Cliente;
import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import it.unicam.cs.ids.c3.backend.entity.Corriere;
import it.unicam.cs.ids.c3.backend.entity.StausCorriere;
import it.unicam.cs.ids.c3.backend.repository.CorriereRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/*
Classe service che effettua operazioni nel database per l'entita corriere
 */
@Service
public class CorriereService {

    private CorriereRepository corriereRepository;

    public CorriereService(CorriereRepository corriereRepository) {
        this.corriereRepository = corriereRepository;
    }

    //Restituisce tutti i corrieri presenti nel db
    public List<Corriere> findAll() {
        return corriereRepository.findAll();
    }

    //Salva un corriere nel db
    public Corriere save(Corriere corriere) {
        return corriereRepository.save(corriere);
    }

    //Ricerca corrieri secondo una stringa di ricerca
    public List<Corriere> search(String searchTerm) {
        return corriereRepository.search(searchTerm);
    }

    @PostConstruct
    public void populateTestData() {
        Corriere corriere = new Corriere("Luca Verdi", StausCorriere.DISPONIBILE);
        corriereRepository.save(corriere);
    }
}
