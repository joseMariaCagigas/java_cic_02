package es.cic.ejemploREST.servicios;

import org.springframework.stereotype.Service;

@Service
public class CineService {
	public String dameMensaje() {
		return "Has pasado por aquí";
	}
}
