package it.unicam.cs.ids.c3.ui.cliente_ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.Categoria;
import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.service.NegozioService;
import it.unicam.cs.ids.c3.ui.cliente_ui.ClienteLayout;

/*
GUI per la ricerca di punti vendita
 */
@Route(value = "puntivendita", layout = ClienteLayout.class)
@PageTitle("Mostra punti vendita")
public class MostraPuntiVenditaView extends VerticalLayout {

    private NegozioService negozioService;
    private Select<Categoria> categoriaSelect = new Select<>();
    private TextField filterText;
    private Grid<Negozio> negozioGrid = new Grid<>(Negozio.class);

    public MostraPuntiVenditaView(NegozioService negozioService) {
        this.negozioService = negozioService;
        add(setComponents(), configureGrid());
    }

    //Setting dei componenti della GUI
    private HorizontalLayout setComponents() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setAlignItems(Alignment.BASELINE);
        categoriaSelect.setLabel("Categoria");
        categoriaSelect.setItems(Categoria.values());
        categoriaSelect.addValueChangeListener(e -> updateList(e.getValue()));
        filterText = new TextField();
        filterText.setSuffixComponent(new Icon(VaadinIcon.SEARCH));
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList(e.getValue()));
        layout.add(categoriaSelect, filterText);
        return layout;
    }

    //Configura le colonne della griglia
    private Grid<Negozio> configureGrid() {
        negozioGrid.setColumns("nomeNegozio", "indirizzo", "categoria");
        return negozioGrid;
    }

    //Aggiorna gli elementi mostrati nella griglia filtrandoli per nome
    private void updateList(String stringFilter) {
        negozioGrid.setItems(negozioService.findAll(stringFilter));
    }

    //Aggiorna gli elementi mostrati nella griglia filtrandoli per categoria
    private void updateList(Categoria categoria) {
        negozioGrid.setItems(negozioService.findAll(categoria));
    }
}
