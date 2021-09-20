package it.unicam.cs.ids.c3.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.entity.Prodotto;
import it.unicam.cs.ids.c3.backend.repository.ProdottoRepository;

import javax.annotation.PostConstruct;

/*
Classe service che effettua operazioni nel database per l'entita ordine
 */
@Service
public class ProdottoService {

    private DescrizioneProdottoService descrizioneProdottoService;
    private ProdottoRepository prodottoRepository;


    public ProdottoService(DescrizioneProdottoService descrizioneProdottoService, ProdottoRepository prodottoRepository) {
        this.descrizioneProdottoService = descrizioneProdottoService;
        this.prodottoRepository = prodottoRepository;
    }

    //Restituisce tutti prodotti nel db
    public List<Prodotto> findAll() {
        return prodottoRepository.findAll();
    }

    //Salva un prodotto nel db
    public Prodotto save(Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }

    //Salva una lista di prodotti nel db
    public List<Prodotto> saveAll(List<Prodotto> prodotti) {
        return prodottoRepository.saveAll(prodotti);
    }

    //Cerca un prodotto per ID
    public Optional<Prodotto> findById(Long id) {
        return prodottoRepository.findById(id);
    }


}
