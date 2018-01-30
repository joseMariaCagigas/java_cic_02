package es.cic.ejemploREST.controladoresSeguros;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import es.cic.ejemploREST.servicios.CineService;

@Controller
@RequestMapping("/sinautowired")
public class AlternativaController {

	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody 
	public String ejemplo() {
		CineService cineService = ContextLoader.getCurrentWebApplicationContext().getBean(CineService.class);
		
		return cineService.dameMensaje() + " ¿qué quiere?";
	}
	
	
}
