package es.cic.curso.grupo2.ejercicio024;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;

import es.cic.curso.grupo2.ejercicio024.dominio.Sesion;
import es.cic.curso.grupo2.ejercicio024.servicio.CineServiceImpl.NotEnoughException;

public class VenderForm extends FormLayout
{
	private static final long serialVersionUID = 7854103764337669112L;

	//UI Padre
	@SuppressWarnings("unused")
	private MyUI padre;
	
	@PropertyId("numero_entradas")
	protected NativeSelect selectNumero;
	
	//Botones
	private Button aceptar;
	private Button cancelar;
    private Label importe;
    private double euros;
    
	//Sesion Actual
	private Sesion sesion;

    //Formulario Vender
	public VenderForm(MyUI padre)
	{
		this.padre = padre;
			
        importe = new Label();
        importe.setCaption("Importe:");
        importe.setValue("0 Euros");
   	
	    selectNumero = new NativeSelect("Número de Entradas:");
	    selectNumero.setNullSelectionAllowed(false);
	    selectNumero.setImmediate(true);
        
	    selectNumero.addValueChangeListener(e -> {
	    	if(selectNumero.size()>1){
		    	euros = padre.calcularImporte((int) selectNumero.getValue());
			    importe.setValue(euros+" Euros");
	    	}
		});
	    
		aceptar = new Button("Aceptar");
		aceptar.addClickListener(e -> {
			try {
				sesion=padre.venderEntradas(sesion.getId(), (int)selectNumero.getValue());
			} catch (NotEnoughException e1) {
				e1.printStackTrace();
			}
			padre.cargaGridSesion();
			padre.cargaGridVenta();
			setSesion(sesion);
		});
		
		cancelar = new Button("Cancelar");
		cancelar.addClickListener(e -> {
			padre.cargaGridSesion();
			setSesion(null);
		});
		
		addComponents(importe,selectNumero,aceptar,cancelar);
		setSesion(null);
	}

	public void setSesion(Sesion sesion)
	{
		this.setVisible(sesion != null);
		this.sesion = sesion;
	    importe.setValue("0 Euros");

		selectNumero.setValue(0);;
	    
		if(sesion!=null)
		{
			if(selectNumero.size()>1){
			selectNumero.removeAllItems();
			}
		    for (int i = 0; i < sesion.getAsientosDisponibles()+1; i++) {
		        selectNumero.addItem(i);
		        selectNumero.setItemCaption(i,""+i);
		    }
		    padre.cargaGridSesion();
		    padre.cargaGridVenta();
		    BeanFieldGroup.bindFieldsUnbuffered(sesion, this);			
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Sesion(), this);
		}
	}
}
