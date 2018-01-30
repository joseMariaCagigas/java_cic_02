package es.cic.curso;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.NumberRenderer;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI implements crearMenu {
	

    @Override
    protected void init(VaadinRequest vaadinRequest) {

    	HorizontalLayout layout1 = new HorizontalLayout();
    	layout1.setWidth("50%");
    	layout1.setHeight("100%");
        //layout.setId(id);
    	
    	HorizontalLayout layout2 = new HorizontalLayout();
    	layout1.setWidth("50%");
    	layout1.setHeight("100%");
        //layout.setId(id);
        
        crearPanel(layout1);
        MenuBar barmenu = crearMenu();
        Form(layout1);
        ComboBox listaCiudades = ComboBox();
        
        TextArea area1 = new TextArea("Wrapping");
        area1.setWordwrap(true); // The default
        area1.setValue("A quick brown fox jumps over the lazy dog");

        TextArea area2 = new TextArea("Nonwrapping");
        area2.setWordwrap(false);
        area2.setValue("Victor jagt zw&ouml;lf Boxk&auml;mpfer quer "+
                       "&uuml;ber den Sylter Deich");
        
        
        final TextField name = new TextField();
        name.setCaption("Escribe tu nombre aquí:");

        Button button = new Button("Haz Click");
        button.addClickListener( e -> {
            layout1.addComponent(new Label("Gracias por hacer click " + name.getValue() 
                    + ", sigue trabajando!"));
        });
        
        Calendar cal = new Calendar("My Calendar");
        cal.setWidth("600px");
        cal.setHeight("300px");
        
        Button button2 = new Button("Haz Click");
        button2.addClickListener( e -> {
            layout1.addComponent(new Label("Gracias por hacer click " + name.getValue() 
                    + ", sigue trabajando!"));
        });
        

        
        ProgressBar bar = new ProgressBar(0.0f);
        layout1.addComponent(bar);


        layout1.addComponent(area1);
        layout1.addComponent(area2);
        layout1.addComponent(barmenu);
        layout1.addComponent(cal);
        layout1.addComponent(bar);
        layout2.addComponents(name, button);
        layout2.addComponents(name, button2);
        layout2.addComponent(listaCiudades);
        layout2.setMargin(true);
        layout2.setSpacing(true);
        
        //Define el contenedor del layout de la aplicacion
        setContent(layout1);
        setContent(layout2);
        
    }



	public void crearPanel(final HorizontalLayout layout) {
		Panel panel = new Panel("Panel de Logueo");
        panel.addStyleName("mypanelexample");
        panel.setSizeUndefined(); // Shrink to fit content
        layout.addComponent(panel);

        // Create the content
        FormLayout content = new FormLayout();
        content.addStyleName("mypanelcontent");
        content.addComponent(new TextField("Usuario"));
        content.addComponent(new TextField("Password"));
        content.setSizeUndefined(); // Shrink to fit
        content.setMargin(true);
        panel.setContent(content);
	}



	public ComboBox ComboBox() {
		ComboBox listaCiudades = new ComboBox();
        
        listaCiudades.addItem("Trocomocho");
        listaCiudades.addItem("Somorrostro");
        listaCiudades.addItem("Ramales");
        listaCiudades.addItem("Wisconsin");
        
        listaCiudades.setNullSelectionAllowed(false);
        listaCiudades.setValue("Somorrostro");
		return listaCiudades;
	}



	public void Form(final HorizontalLayout layout) {
		FormLayout form = new FormLayout();
        TextField tf1 = new TextField("Name");
        tf1.setIcon(FontAwesome.USER);
        tf1.setRequired(true);
        tf1.addValidator(new NullValidator("Must be given", false));
        form.addComponent(tf1);

        TextField tf2 = new TextField("Street address");
        tf2.setIcon(FontAwesome.ROAD);
        form.addComponent(tf2);

        TextField tf3 = new TextField("Postal code");
        tf3.setIcon(FontAwesome.ENVELOPE);
        tf3.addValidator(new IntegerRangeValidator("Doh!", 1, 99999));
        layout.addComponent(form);
	}



	public MenuBar crearMenu() {
		MenuBar barmenu = new MenuBar();
        
        // A top-level menu item that opens a submenu
        MenuItem drinks = barmenu.addItem("Whisky", null, null);

        // Submenu item with a sub-submenu
        MenuItem hots = drinks.addItem("Hot", null, null);
        hots.addItem("Tea",null, null);
        hots.addItem("Coffee",null, null);

        // Another submenu item with a sub-submenu
        MenuItem colds = drinks.addItem("Cold", null, null);
        colds.addItem("Milk",      null, null);
        colds.addItem("Weissbier", null, null);

        // Another top-level item
        MenuItem snacks = barmenu.addItem("Snacks", null, null);
        snacks.addItem("Weisswurst", null, null);
        snacks.addItem("Bratwurst",  null, null);
        snacks.addItem("Currywurst", null, null);

        // Yet another top-level item
        MenuItem servs = barmenu.addItem("Services", null, null);
        servs.addItem("Car Service", null, null);
		return barmenu;
	}



	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
