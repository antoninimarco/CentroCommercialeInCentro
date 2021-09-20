package it.unicam.cs.ids.c3.ui.corriere_ui;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.Corriere;
import it.unicam.cs.ids.c3.backend.entity.StausCorriere;
import it.unicam.cs.ids.c3.backend.service.CorriereService;
import it.unicam.cs.ids.c3.ui.commerciante_ui.CommercianteLayout;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


/*
GUI che permette di aggiornare lo status di disponibilita del corriere
 */
@Route(value = "status", layout = CorriereLayout.class)
@PageTitle("Aggiorna Disponibilita")
public class StatusView extends VerticalLayout {

    RadioButtonGroup<StausCorriere> radioGroup = new RadioButtonGroup<>();
    CorriereService corriereService;

    public StatusView(CorriereService corriereService) {
        this.corriereService = corriereService;
        radioGroup.setLabel("Status");
        radioGroup.setItems(StausCorriere.values());
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.addValueChangeListener(e -> changeStatus(e.getValue()));
        add(radioGroup);

    }

    //Registra le modifiche dello status
    private void changeStatus(StausCorriere value) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Corriere corriere = corriereService.search(username).get(0);
        corriere.setStatusDisponibilita(value);
        corriereService.save(corriere);
        Notification n = new Notification("Disponibilita aggiornata");
        n.setDuration(3000);
        n.open();
    }
}
