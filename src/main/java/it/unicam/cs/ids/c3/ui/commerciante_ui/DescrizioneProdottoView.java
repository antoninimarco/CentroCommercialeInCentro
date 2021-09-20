package it.unicam.cs.ids.c3.ui.commerciante_ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.DescrizioneProdotto;
import it.unicam.cs.ids.c3.backend.service.DescrizioneProdottoService;
import it.unicam.cs.ids.c3.ui.commerciante_ui.CommercianteLayout;

/*
GUI che mostra le descrizioni prodotto presenti nel sistema
 */
@Route(value="prodotti", layout = CommercianteLayout.class)
@PageTitle("Prodotti")
public class DescrizioneProdottoView extends VerticalLayout{

	private DescrizioneProdottoService service;
	private Grid<DescrizioneProdotto> grid = new Grid<>(DescrizioneProdotto.class);
	
	public DescrizioneProdottoView(DescrizioneProdottoService service) {
		this.service = service;
		addClassName("list-view");
		  setSizeFull();
		  configureGrid();
		  add(grid);
		  updateList(); 
	}

	private void updateList() {
		grid.setItems(service.findAll());
	}

	private void configureGrid() {
		grid.addClassName("prodotti-grid");
		  grid.setSizeFull();
		  grid.setColumns("codiceProdotto", "nomeProdotto", "descrizioneProdotto");
	}
	
	
}
