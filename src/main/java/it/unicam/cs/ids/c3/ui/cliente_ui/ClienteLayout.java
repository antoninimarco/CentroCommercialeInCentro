package it.unicam.cs.ids.c3.ui.cliente_ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

import java.util.HashMap;
import java.util.Map;

/*
Menubar per la GUI del cliente
 */
public class ClienteLayout extends AppLayout {
    private Tabs tabs = new Tabs();
    private Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();

    public ClienteLayout() {
        Anchor logout = new Anchor("logout", "Log out");
        Image img = new Image("images/C3-logos_transparent.png", "Logo");
        img.setHeight("50px");
        addMenuTab("Mostra punti vendita", MostraPuntiVenditaView.class);
        addMenuTab("I miei ordini", OrdiniView.class);
        addToNavbar(img, tabs, logout);
    }

    private void addMenuTab(String label, Class<? extends Component> target) {
        Tab tab = new Tab(new RouterLink(label, target));
        navigationTargetToTab.put(target, tab);
        tabs.add(tab);
    }
}
