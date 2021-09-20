package it.unicam.cs.ids.c3.backend.service;

import it.unicam.cs.ids.c3.backend.entity.Cliente;
import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/*
Classe service che effettua operazioni nel database per l'entita cliente
 */
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Salva nel db un cliente
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //Ritorna tutti i clienti presenti nel db
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    //Ritorna tutti i clienti presenti nel db in base ad una stringa di ricerca
    public List<Cliente> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return clienteRepository.findAll();
        } else {
            return clienteRepository.search(stringFilter);
        }
    }

    //Cerca i clienti in base ad una stringa di ricerca
    public List<Cliente> search(String searchTerm) {
        return clienteRepository.search(searchTerm);
    }

    @PostConstruct
    public void populateTestData() {
        Cliente cliente = new Cliente("Luigino Bianchi", "Via Nazionale, 12");
        clienteRepository.save(cliente);
    }

}
