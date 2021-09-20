package it.unicam.cs.ids.c3.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/*
GUI per il form di login
 */
@Route("login")
@PageTitle("Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login = new LoginForm();

    public LoginView(){
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        login.setAction("login");
        Image img = new Image("images/C3-logo.png","Logo");
        H1 h1 = new H1("Centro Commerciale in Centro");
        add(img,h1, login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
// inform the user about an authentication error
        if (beforeEnterEvent.getLocation().getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
