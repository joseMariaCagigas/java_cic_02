package es.cic.curso.curso06.ejercicio014.versionI.datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoOperacion {

	private ArchivoOperacion() {
	}
	
	public static List<String> leerArchivoPorLineas(String rutaArchivoOrigen) throws IOException {

		List<String> listaPalabras = new ArrayList<>();
		
		try ( BufferedReader bufferLectura = new BufferedReader(new FileReader(rutaArchivoOrigen)) ) {
			
			String line;
			
			
			while ( (line = bufferLectura.readLine()) != null) {
				
				line = line.trim();
				
				if (line.length() != 0) {
					
					listaPalabras.add(line);
				}
			}
			
		}
		
		return listaPalabras;
	}
	
	public static void escribirArchivo(String rutaArchivoDestino, Iterable<String> arrayLineas) throws IOException {

		try ( FileWriter fw = new FileWriter(rutaArchivoDestino);
				PrintWriter writer = new PrintWriter(fw) ) {
			
			for(String linea:arrayLineas) {
				
				writer.println(linea);
			}
		} 
	}
	
}
