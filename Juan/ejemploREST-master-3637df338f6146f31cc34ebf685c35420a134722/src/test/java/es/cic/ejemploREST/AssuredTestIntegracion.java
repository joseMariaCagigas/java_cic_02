package es.cic.ejemploREST;

import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.cic.ejemploREST.controladores.LoginForm;
import es.cic.ejemploREST.controladores.VentaForm;

public class AssuredTestIntegracion {

	private int puertoValido;
	
	@Before
	public void setUp() throws Exception {
		Properties pr = new Properties();
		pr.load(getClass().getResourceAsStream("jetty.properties"));
		String puerto = pr.getProperty("jetty.port");
		puertoValido = 8080;
		try {
			
			puertoValido = Integer.parseInt(puerto);
		} catch (NumberFormatException nfe) {		
		}		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPost() {
		LoginForm loginForm = new LoginForm();
		loginForm.setUser("5");
		loginForm.setPassword("0");
		VentaForm ventaForm =
			given().port(puertoValido).
				contentType("application/json").
				body(loginForm).
			when().
				post("/ejemploREST/rest/sampleRest").
				getBody().as(VentaForm.class);
		assertEquals(5, ventaForm.getCantidad());
	}

	@Test
	public void testGetVenta() {
		VentaForm ventaForm =
			given().port(puertoValido).
				contentType("application/json").
			when().
				get("/ejemploREST/rest/sampleRest/venta/2").
				getBody().as(VentaForm.class);
		assertEquals(2, ventaForm.getSala());
	}	
}
