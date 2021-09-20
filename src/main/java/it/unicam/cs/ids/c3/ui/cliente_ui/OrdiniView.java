package it.unicam.cs.ids.c3.ui.cliente_ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.Cliente;
import it.unicam.cs.ids.c3.backend.entity.Ordine;
import it.unicam.cs.ids.c3.backend.entity.Prodotto;
import it.unicam.cs.ids.c3.backend.service.ClienteService;
import it.unicam.cs.ids.c3.backend.service.OrdineService;
import it.unicam.cs.ids.c3.ui.commerciante_ui.CommercianteLayout;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

/*
GUI che mostra gli ordini associati ad un cliente
 */
@Route(value = "ordini", layout = ClienteLayout.class)
@PageTitle("Ordini")
public class OrdiniView extends VerticalLayout {

    private Grid<Ordine> grid = new Grid<>(Ordine.class);
    private OrdineService ordineService;
    private ClienteService clienteService;
    private List<Ordine> ordineList;

    public OrdiniView(OrdineService ordineService, ClienteService clienteService) {
        this.ordineService = ordineService;
        this.clienteService = clienteService;
        findOrdini();
        configureGrid();
        grid.setItems(ordineList);
        add(grid);
    }

    //Configura le colonne della griglia
    private void configureGrid() {

        grid.setColumns("status", "puntoRitiro");
        grid.addColumn(new ComponentRenderer<>(ordine -> showProdotti(ordine)));
    }

    //Mostra i prodotti contenuti nell'ordine
    private Component showProdotti(Ordine ordine) {
        String text = "";
        for (int i = 0; i < ordine.getProdotti().size(); i++) {
            text = text + ordine.getProdotti().get(i).getDescrizione().getNomeProdotto() + " ";
            text = text + "n." + ordine.getProdotti().get(i).getQuantita();
            text = text + "\n";
        }
        Details component = new Details("Prodotti", new Text(text));
        return component;
    }

    //Cerca gli ordini associati al cliente
    private void findOrdini() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Cliente cliente = clienteService.search(username).get(0);

        ordineList = ordineService.findAll().stream()
                .filter(o -> o.getCliente().getId().equals(cliente.getId()))
                .collect(Collectors.toList());
    }
}
