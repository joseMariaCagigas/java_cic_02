package es.cic.curso;


import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2411403544207587563L;
	private ClienteForm clienteForm;
	private AdminForm adminForm;
	private TabSheet tab;

	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
        final HorizontalLayout base = new HorizontalLayout();
        
        tab = new TabSheet();
        tab.setHeight(100.0f, Unit.PERCENTAGE);
        tab.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        
        final VerticalLayout tab1 = new VerticalLayout(new Label());
        
        tab1.setMargin(true);
        clienteForm = new ClienteForm(this);
        tab1.addComponent(clienteForm);
        tab.addTab(tab1, "Cliente");
        
        
        final HorizontalLayout tab2 = new HorizontalLayout(new Label());
        
        tab2.setMargin(true);    
        adminForm = new AdminForm(this);
        tab2.addComponent(adminForm);
        
        tab.addTab(tab2, "Admin");
        tab.setWidth("100%");
        
        tab.addSelectedTabChangeListener(
                new TabSheet.SelectedTabChangeListener() {
		            public void selectedTabChange(SelectedTabChangeEvent event) {
		                // Encuentra el tabsheet
		                TabSheet tabsheet = event.getTabSheet();
		
		                // Selecciona la tab correspondiente
		                Layout tabb = (Layout) tabsheet.getSelectedTab();
		
		                // Obtiene el titulo de la tab
		                String caption = tabsheet.getTab(tabb).getCaption();
		                
		                //La compara y se recarga la grid indicada
		                if(caption.equalsIgnoreCase("Cliente")){
		                	clienteForm.cargaGrid();
		                }else{
		                	adminForm.cargaGrid();
		                }
            }
        });
        
        base.addComponents(tab);
        base.setMargin(true);
        base.setSpacing(true);
        base.setWidth("100%");
        setContent(base);
        
        adminForm.reiniciarElDia();
        
        clienteForm.cargaGrid();
           
	}
	
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
