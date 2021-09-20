package it.unicam.cs.ids.c3.ui.commerciante_ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import it.unicam.cs.ids.c3.backend.entity.DescrizioneProdotto;
import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.entity.Prodotto;
import it.unicam.cs.ids.c3.backend.service.CommercianteService;
import it.unicam.cs.ids.c3.backend.service.DescrizioneProdottoService;
import it.unicam.cs.ids.c3.backend.service.NegozioService;
import it.unicam.cs.ids.c3.ui.commerciante_ui.CommercianteLayout;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/*
GUI che mostra i prodotti venduti da un negozio e ne permette di
aggiungerne altri
 */
@Route(value = "negozio", layout = CommercianteLayout.class)
@PageTitle("Negozio")
public class NegozioView extends VerticalLayout {
    NegozioService negozioService;
    CommercianteService commercianteService;
    DescrizioneProdottoService descrizioneProdottoService;
    Negozio negozio;
    private Grid<Prodotto> grid = new Grid<>(Prodotto.class);
    private Select<DescrizioneProdotto> prodottiSelect = new Select<>();
    private Button addProductButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE));
    private IntegerField qty = new IntegerField();

    public NegozioView(NegozioService negozioService, CommercianteService commercianteService, DescrizioneProdottoService descrizioneProdottoService) {
        this.negozioService = negozioService;
        this.commercianteService = commercianteService;
        this.descrizioneProdottoService = descrizioneProdottoService;
        addClassName("list-view");
        setSizeFull();
        HorizontalLayout horizontalLayout = new HorizontalLayout(prodottiSelect, addProductButton, qty);
        horizontalLayout.setAlignItems(Alignment.BASELINE);
        addProductButton.addClickListener(e -> updateList());
        setComponents();
        configureGrid();
        showNegozio();
        add(horizontalLayout, grid);
    }

    //Trova il negozio associato al commerciante e ne mostra i prodotti in griglia
    private void showNegozio() {
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
        if (negozio != null) {
            grid.setItems(negozio.getVetrina());
        } else {
            Notification n = new Notification("Nessun negozio associato al tuo account");
            n.setDuration(4000);
            n.open();
        }
    }

    //Aggiorna la lista dei prodotti mostrati in griglia
    private void updateList() {
        Prodotto prodotto = new Prodotto(prodottiSelect.getValue(), qty.getValue());
        prodotto.setNegozio(negozio);
        negozioService.addProdotto(negozio, prodotto);
        showNegozio();

    }


    //Setting dei componenti della GUI
    private void setComponents() {
        prodottiSelect.setLabel("Prodotti");
        prodottiSelect.setItemLabelGenerator(DescrizioneProdotto::getNomeProdotto);
        prodottiSelect.setItems(descrizioneProdottoService.findAll());
        qty.setHasControls(true);
        qty.setMin(1);
        qty.setValue(1);
    }


    private void configureGrid() {
        grid.addClassName("vetrina-grid");
        grid.setSizeFull();
        grid.setColumns("descrizione.codiceProdotto", "descrizione.nomeProdotto", "descrizione.descrizioneProdotto", "quantita");
    }
}
