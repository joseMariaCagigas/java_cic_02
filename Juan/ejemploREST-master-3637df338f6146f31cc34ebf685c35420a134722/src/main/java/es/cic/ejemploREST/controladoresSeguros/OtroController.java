package es.cic.ejemploREST.controladoresSeguros;

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
public class OtroController {
	
	@Autowired
	private CineService cineService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody 
	public String ejemplo() {
		return cineService.dameMensaje();
	}
}
