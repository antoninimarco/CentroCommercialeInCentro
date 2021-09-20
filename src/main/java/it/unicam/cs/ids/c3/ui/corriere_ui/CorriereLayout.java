package it.unicam.cs.ids.c3.ui.corriere_ui;

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
Menubar dellE GUI del corriere
 */
public class CorriereLayout extends AppLayout {

    private Tabs tabs = new Tabs();
    private Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();

    public CorriereLayout() {
        Anchor logout = new Anchor("logout", "Log out");
        Image img = new Image("images/C3-logos_transparent.png", "Logo");
        img.setHeight("50px");
        addMenuTab("Aggiorna disponibilita", StatusView.class);
        addToNavbar(img, tabs, logout);
    }

    private void addMenuTab(String label, Class<? extends Component> target) {
        Tab tab = new Tab(new RouterLink(label, target));
        navigationTargetToTab.put(target, tab);
        tabs.add(tab);
    }
}
