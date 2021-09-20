package it.unicam.cs.ids.c3.ui.commerciante_ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.*;
import it.unicam.cs.ids.c3.backend.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/*
GUI che mostra un form per l'aggiornamento dei dati di un ordine già creato
 */
@Route(value = "aggiornaordine", layout = CommercianteLayout.class)
@PageTitle("Aggiorna ordine")
public class AggiornaOrdineView extends HorizontalLayout {

    private OrdineService ordineService;
    private CorriereService corriereService;
    private CommercianteService commercianteService;
    private ProdottoService prodottoService;
    private NegozioService negozioService;
    private Grid<Ordine> ordineGrid = new Grid<>(Ordine.class);
    private TextField filterText = new TextField();
    private Select<StatusOrdine> statusOrdineSelect = new Select<>();
    private Select<Corriere> corriereSelect = new Select<>();
    private Select<Prodotto> prodottoSelect = new Select<>();
    private ArrayList<Prodotto> productList = new ArrayList<>();
    private Grid<Prodotto> prodottoGrid = new Grid<>(Prodotto.class);
    private IntegerField qty = new IntegerField();
    private Negozio negozio;
    private Button saveButton = new Button("Save");

    public AggiornaOrdineView(OrdineService ordineService, CorriereService corriereService, CommercianteService commercianteService, NegozioService negozioService, ProdottoService prodottoService) {
        this.ordineService = ordineService;
        this.corriereService = corriereService;
        this.commercianteService = commercianteService;
        this.prodottoService = prodottoService;
        this.negozioService = negozioService;
        configureGrid();
        findNegozio();
        add(setComponents(), setProductsGrid());
    }


    //Setting della griglia e dei componenti per l'aggiunta di ulteriori prodotti
    private VerticalLayout setProductsGrid() {
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        prodottoSelect.setLabel("Prodotti");
        prodottoSelect.setItemLabelGenerator(Prodotto::getNomeProdotto);
      if(negozio!=null) {prodottoSelect.setItems(negozio.getVetrina());} else {
          Notification notification = new Notification("Crea un negozio prima!");
          notification.setDuration(3000);
          notification.open();
      }
        Button btn = new Button(new Icon(VaadinIcon.PLUS_CIRCLE));
        btn.addClickListener(event -> {
            Prodotto prodotto = prodottoSelect.getValue();
            prodotto.setQuantita(prodotto.getQuantita() - qty.getValue());
            Prodotto p = null;
            try {
                p = prodotto.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            p.setQuantita(qty.getValue());
            productList.add(p);
            prodottoGrid.setItems(productList);


        });
        prodottoSelect.addToPrefix(btn);
        qty.setHasControls(true);
        qty.setMin(1);
        if (prodottoSelect.isEmpty() == false || prodottoSelect.getValue() != null) {
            qty.setMax(prodottoSelect.getValue().getQuantita());
        }
        qty.setValue(1);
        qty.setLabel("Quantità");
        prodottoGrid.setColumns("descrizione.nomeProdotto", "quantita");
        saveButton.addClickListener(event -> modifyOrder());
        horizontalLayout.add(prodottoSelect, qty);
        layout.add(horizontalLayout, prodottoGrid, saveButton);
        return layout;
    }

    //Registra le modiche effettuate
    private void modifyOrder() {
        Ordine ordine = ordineGrid.getSelectionModel().getFirstSelectedItem().get();
        ordine.setStatus(statusOrdineSelect.getValue());
        ordine.setCorriere(corriereSelect.getValue());
        productList.addAll(ordine.getProdotti());
        ordine.setProdotti(productList);
        Iterator<Prodotto> iter = productList.iterator();
        while (iter.hasNext()) {
            iter.next().setOrdine(ordine);
        }
        ordineService.save(ordine);
        prodottoService.saveAll(productList);
        Notification notification = new Notification("Ordine modificato");
        notification.setDuration(3000);
        notification.open();
    }

    //Setting dei componenti della GUI
    private VerticalLayout setComponents() {
        VerticalLayout layout = new VerticalLayout();
        filterText.setLabel("Cliente");
        filterText.setSuffixComponent(new Icon(VaadinIcon.SEARCH));
        filterText.setPlaceholder("Cerca per nome...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList(e.getValue()));
        ordineGrid.setHeight("250px");
        ordineGrid.setWidth("350px");
        statusOrdineSelect.setLabel("Status Ordine");
        statusOrdineSelect.setItems(StatusOrdine.values());
        corriereSelect.setLabel("Corrieri disponibili");
        List<Corriere> corrieriDisponibili = corriereService.findAll().stream()
                .filter(c -> c.getStatusDisponibilita().equals(StausCorriere.DISPONIBILE))
                .collect(Collectors.toList());
        corriereSelect.setItemLabelGenerator(Corriere::getNomeCorriere);
        corriereSelect.setItems(corrieriDisponibili);
        layout.add(filterText, ordineGrid, statusOrdineSelect, corriereSelect);
        return layout;
    }

    //Aggiorna la lista degli ordini mostrati in griglia
    private void updateList(String value) {
        ordineGrid.setItems(ordineService.findAll(value));
    }

    //Configura la griglia
    private void configureGrid() {
        ordineGrid.setColumns("cliente.nomeCliente", "status");
        ordineGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        ordineGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
    }

    //Ritorna il negozio associato al commerciante che sta effettuando le modifiche
    private void findNegozio() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Commerciante commerciante = commercianteService.search(username).get(0);
        String finalUsername = username;
        negozio = negozioService.findAll().stream()
                .filter(n -> n.getCommerciante().getNomeCommerciante().equals(finalUsername))
                .findAny().orElse(null);
    }
}
