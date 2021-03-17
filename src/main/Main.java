package main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import bat.GenerarScriptNewMan;

public class Main {
	private static String [] colores = {"1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c"};
    private static String filtro = "";
    static String [] entornoComparacion = obtenerNombresEntornos(); 
    
	public static void main(String[] args) throws IOException {
        GenerarScriptNewMan resultados = new GenerarScriptNewMan();

        
    	/** Modificar solo esto para resultados*/
		boolean generarBatUnico = true;
        int fase = 11;
        int numPruebas = 1;
        String version = "v7";
        filtro = "NoEstable";//Poner NoEstable para generar las 2 pruebas estables, * para todas
        /** Modificar solo esto*/
        
        //resultados.generarScriptResultado(fase, numPruebas, version, generarBatUnico, version, entornoComparacion);
        
    	/** Modificar solo esto para tiempos*/
        int numIteraciones = 10;
		generarBatUnico = true;
        fase = 4;
        numPruebas = 5;
        version = "Tiempos";
        filtro = "NoEstable";//Poner NoEstable para generar las 2 pruebas estables, * para todas
        /** Modificar solo esto para tiempos*/
        
        resultados.generarScriptTiempos(numIteraciones, fase, numPruebas, version, generarBatUnico, version, "eSqlEstable.json");

	}
	
	static FilenameFilter filtrado = new FilenameFilter() {
	    @Override
	    public boolean accept(File dir, String name) {
	        return name.toLowerCase().startsWith("e") && !name.contains(filtro);
	    }
	};
	
	private static String [] obtenerNombresEntornos() {
        File files[] = (new File("entornos/")).listFiles(filtrado);
        String [] entornoComparacion = new String[files.length];
        for (int i = 0; i < entornoComparacion.length; i++) {
			entornoComparacion[i] = files[i].getName();
		}
        return entornoComparacion;
	}
}
