package es.cic.ejemploREST.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.cic.ejemploREST.servicios.CineService;

@Controller
@RequestMapping("/sampleRest")
public class SampleRestController {
	
	@Autowired
	private CineService cineService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody 
	public VentaForm ejemplo() {
		VentaForm ventaForm = new VentaForm();
		ventaForm.setMensaje(cineService.dameMensaje());
		return ventaForm;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody 
	public VentaForm ejemplo(@RequestBody LoginForm loginForm) {
		VentaForm ventaForm = new VentaForm();
		ventaForm.setMensaje(cineService.dameMensaje());
		ventaForm.setCantidad(Integer.parseInt(loginForm.getUser()));
		return ventaForm;
	}
	
	@RequestMapping(value="/venta/{salaId}" , method=RequestMethod.GET)
	@ResponseBody
	public VentaForm getVenta(@PathVariable String salaId) {
		VentaForm ventaForm = new VentaForm();
		ventaForm.setSala(Integer.parseInt(salaId));
		return ventaForm;		
	}
}
