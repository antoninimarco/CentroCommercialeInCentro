package it.unicam.cs.ids.c3.backend.service;

import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.entity.DescrizioneProdotto;
import it.unicam.cs.ids.c3.backend.repository.DescrizioneProdottoRepository;

/*
Classe service che effettua operazioni nel database per l'entita descrizione prodotto
 */
@Service
public class DescrizioneProdottoService {

    private DescrizioneProdottoRepository descrizioneProdottoRepository;

    public DescrizioneProdottoService(DescrizioneProdottoRepository descrizioneProdottoRepository) {
        this.descrizioneProdottoRepository = descrizioneProdottoRepository;
    }

    //Restituisce tutte le descrizioni prodotto presenti nel db
    public List<DescrizioneProdotto> findAll() {
        return descrizioneProdottoRepository.findAll();
    }

    @PostConstruct
    public void populateTestData() {
        if (descrizioneProdottoRepository.count() == 0) {
            descrizioneProdottoRepository.save(new DescrizioneProdotto("A-0010-Z", "Articolo1", "Descrizione dell' articolo 1."));
            descrizioneProdottoRepository.save(new DescrizioneProdotto("A-0011-Z", "Articolo2", "Descrizione dell' articolo 2."));
            descrizioneProdottoRepository.save(new DescrizioneProdotto("A-0012-Z", "Articolo3", "Descrizione dell' articolo 3."));
        }
    }

}
