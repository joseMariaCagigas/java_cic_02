package es.cic.curso.curso11.ejercicio017.services;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso11.ejercicio017.repository.figuras.Centro;
import es.cic.curso.curso11.ejercicio017.repository.figuras.Circulo;
import es.cic.curso.curso11.ejercicio017.repository.figuras.Color;
import es.cic.curso.curso11.ejercicio017.repository.figuras.Cuadrado;
import es.cic.curso.curso11.ejercicio017.repository.figuras.Figura;
import es.cic.curso.curso11.ejercicio017.repository.figuras.Linea;
import es.cic.curso.curso11.ejercicio017.services.FiguraService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class FiguraServiceImplTest {

	@Autowired
	private FiguraService serviceUnderTest;
	
	Figura circulo1 = new Circulo( new Centro( Float.parseFloat("3.00"), Float.parseFloat("1.2769") ), Float.parseFloat("3.3"), Float.parseFloat("0.0"), new Color(255, 255, 255) );
	Figura circulo2 = new Circulo( new Centro( Float.parseFloat("0.00"), Float.parseFloat("4.578") ), Float.parseFloat("2.1"), Float.parseFloat("0.0"), new Color(107, 109, 156) );
	Figura cuadrado1 = new Cuadrado( new Centro( Float.parseFloat("8.4534"), Float.parseFloat("-45.453") ), Float.parseFloat("7.5"), Float.parseFloat("45.0"), new Color(0, 0, 0) );
	Figura cuadrado2 = new Cuadrado( new Centro( Float.parseFloat("2.456"), Float.parseFloat("54.7564") ), Float.parseFloat("5.2"), Float.parseFloat("30.0"), new Color(248, 208, 244) );
	Figura linea1 = new Linea( new Centro( Float.parseFloat("0.00"), Float.parseFloat("0.00") ), Float.parseFloat("8.8"), Float.parseFloat("45.0"), new Color(245, 145, 186) );
	Figura linea2 = new Linea( new Centro( Float.parseFloat("-3.2307"), Float.parseFloat("-9.4565") ), Float.parseFloat("6.3"), Float.parseFloat("90.0"), new Color(65, 75, 85) );

	@Test
	public void listarTest() {
		List<Figura> figuras = serviceUnderTest.listar();
		assertTrue(figuras.size() == 0);

		serviceUnderTest.anadir(circulo1);

		figuras = serviceUnderTest.listar();
		assertTrue(figuras.size() == 1);
		
		serviceUnderTest.anadir(linea2);
		
		figuras = serviceUnderTest.listar();
		assertTrue(figuras.size() == 2);
		
		serviceUnderTest.borrar(linea2);
		
		figuras = serviceUnderTest.listar();
		assertTrue(figuras.size() == 1);
	}

}
