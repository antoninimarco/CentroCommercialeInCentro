package it.unicam.cs.ids.c3.backend.service;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.flow.component.notification.Notification;
import it.unicam.cs.ids.c3.backend.entity.*;
import it.unicam.cs.ids.c3.backend.repository.CommercianteRepository;
import it.unicam.cs.ids.c3.backend.repository.DescrizioneProdottoRepository;
import it.unicam.cs.ids.c3.backend.repository.ProdottoRepository;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.repository.NegozioRepository;

import javax.annotation.PostConstruct;

/*
Classe service che effettua operazioni nel database per l'entita negozio
 */
@Service
public class NegozioService {

    private NegozioRepository negozioRepository;
    private CommercianteRepository commerianteRepository;
    private ProdottoRepository prodottoRepository;
    private DescrizioneProdottoRepository descrizioneProdottoRepository;
    private ProdottoService prodottoService;

    public NegozioService(NegozioRepository negozioRepository, CommercianteRepository commerianteRepository, ProdottoRepository prodottoRepository, DescrizioneProdottoRepository descrizioneProdottoRepository, ProdottoService prodottoService) {
        this.negozioRepository = negozioRepository;
        this.commerianteRepository = commerianteRepository;
        this.prodottoRepository = prodottoRepository;
        this.descrizioneProdottoRepository = descrizioneProdottoRepository;
        this.prodottoService = prodottoService;
    }

    //Salva un negozio nel db
    public Negozio save(Negozio negozio) {
        return negozioRepository.save(negozio);
    }

    //Restituisce tutti i negozi nel db
    public List<Negozio> findAll() {
        return negozioRepository.findAll();
    }

    //Restituisce tutti i negozi nel db in base a una stringa di ricerca
    public List<Negozio> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return negozioRepository.findAll();
        } else {
            return negozioRepository.search(stringFilter);
        }
    }

    //Restituisce tutti i negozi nel db appartenenti ad una determinata categoria
    public List<Negozio> findAll(Categoria categoria) {
        return negozioRepository.searchByCategoria(categoria);
    }

    public Negozio addNegozio(Negozio negozio) {
        return negozioRepository.save(negozio);
    }


    //Ricerca un negozio per il suo ID
    public Negozio getById(Long id) {
        return negozioRepository.getById(id);
    }

    //Aggiunge un prodotto nella vetrina del negozio
    public Negozio addProdotto(Negozio negozio, Prodotto prodotto) {
        negozio.getVetrina().add(prodotto);
        return save(negozio);
    }

    //Ricerca negozi in base ad una stringa di ricerca
    public List<Negozio> search(String searchTerm) {
        return negozioRepository.search(searchTerm);
    }


}
