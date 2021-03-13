
package bat;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class GenerarScriptNewMan {
	private static String [] colores = {"1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c"};
    private static String filtro = "";
	public static void main(String[] args) throws IOException {
    	/** Modificar solo esto*/
		boolean generarBatUnico = true;
        int fase = 1;
        int numPruebas = 30;
        String version = "v7";
        filtro = "NoEstable";//Poner NoEstable para generar las 4 pruebas, * para todas
        /** Modificar solo esto*/

        
        String [] entornoComparacion = obtenerNombresEntornos(); 

        //Para generar por separado:
        for (int entorno = 0; entorno < entornoComparacion.length; entorno++) {
        	 StringBuffer sfRespuesta = new StringBuffer();
        	 StringBuffer sfRespuestaTotal = new StringBuffer();
             String carpetaResultados = "F"+fase+"resultados"+entornoComparacion[entorno].substring(1, 4);
             String carpetaGlobales = "F"+fase+"globales"+entornoComparacion[entorno].substring(1, 4);

             for (int prueba = 0; prueba < numPruebas; prueba++) {
                 sfRespuesta = new StringBuffer();
                 sfRespuesta.append("title \"ENTORNO "+entornoComparacion[entorno].substring(1, +entornoComparacion[entorno].length()-5)+" COLECCION "+prueba+"/"+(numPruebas-1)+"\"\n");
                 sfRespuesta.append("color "+colores[prueba]+"\n");
                 sfRespuesta.append("mkdir "+carpetaResultados+"\n");
                 sfRespuesta.append("mkdir "+carpetaGlobales+"\n");
                 String l1 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                         + ".json -e ..\\UrsaePru.json --reporters cli,json --reporter-json-export "+carpetaGlobales+"\\"
                         + prueba + ".json\n";
                 sfRespuesta.append(l1);
                 String l2 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                         + ".json -e ..\\" + entornoComparacion[entorno] + " -g "+carpetaGlobales+"\\" + prueba
                         + ".json -d "+carpetaGlobales+"\\" + prueba
                         + ".json --reporters cli,json --reporter-json-export "+carpetaResultados+"\\respuesta_"
                         + prueba + ".json\n";
                 sfRespuesta.append(l2);

                 sfRespuesta.append("echo * * * * FIN DEL PROCESO. * * * * \n");

                 //sfRespuesta.append("pause\n");

                 GrabarFichero grabarFichero = new GrabarFichero();
                 grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5)+version+"/scriptPruebasFase" + fase + "_p_" + prueba + ".bat", true);
                 grabarFichero.agregarAFichero(sfRespuesta.toString());
                 grabarFichero.cerrarFichero();
                 sfRespuestaTotal.append(sfRespuesta);
                 //Poner aqui grabar para generar por separado
                 System.out.println("Fin de generacion bat " +entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5) + " fase " + fase);
             }
             sfRespuestaTotal.append(":end\n");
             sfRespuestaTotal.append("echo \n");
             sfRespuestaTotal.append("cls\n");
             sfRespuestaTotal
              .append("echo * * * * * * * * * * * * * FIN DEL PROCESO!! * * * * * * * \n");
             sfRespuestaTotal.append("timeout /t 7\n");
             
             sfRespuestaTotal.append("goto end\n");

             GrabarFichero grabarFichero = new GrabarFichero();
             grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5)+version+"/scriptPruebasFase" + fase +"_"+ numPruebas + "pruebas.bat", true);
             grabarFichero.agregarAFichero(sfRespuestaTotal.toString());
             grabarFichero.cerrarFichero();
		}
        //Fin de generar por separado
      /*  
      //Para generar un unico fichero:
        for (int entorno = 0; entorno < entornoComparacion.length; entorno++) {
        	 StringBuffer sfRespuesta = new StringBuffer();
             String carpetaResultados = "F"+fase+"resultados"+entornoComparacion[entorno].substring(1, 4);
             String carpetaGlobales = "F"+fase+"globales"+entornoComparacion[entorno].substring(1, 4);
             //comentar para generar por separado
            
             sfRespuesta = new StringBuffer();
             sfRespuesta.append("mkdir "+carpetaResultados+"\n");
             sfRespuesta.append("mkdir "+carpetaGlobales+"\n");
             for (int prueba = 0; prueba < numPruebas; prueba++) {
                 sfRespuesta.append("title \"ENTORNO "+entornoComparacion[entorno].substring(1, +entornoComparacion[entorno].length()-5)+" COLECCION "+prueba+"/"+(numPruebas-1)+"\"\n");
                 sfRespuesta.append("color "+colores[prueba]+"\n");
                 String l1 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                         + ".json -e ..\\UrsaePru.json --reporters cli,json --reporter-json-export "+carpetaGlobales+"\\"
                         + prueba + ".json\n";
                 sfRespuesta.append(l1);
                 String l2 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                         + ".json -e ..\\" + entornoComparacion[entorno] + " -g "+carpetaGlobales+"\\" + prueba
                         + ".json -d "+carpetaGlobales+"\\" + prueba
                         + ".json --reporters cli,json --reporter-json-export "+carpetaResultados+"\\respuesta_"
                         + prueba + ".json\n";
                 sfRespuesta.append(l2);

                 
                 // sfRespuesta.append(":end\n");
                 // sfRespuesta.append("echo \n");
                 // sfRespuesta
                 // .append("echo * * * * * * * * * * * * * FIN DEL PROCESO!! * * * * * * * \n");
                 // sfRespuesta.append("timeout /t 1\n");
                 // sfRespuesta.append("cls\n");
                 // sfRespuesta.append("goto end\n");
                 //sfRespuesta.append("pause\n");

//                 GrabarFichero grabarFichero = new GrabarFichero();
//                 grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(0, 11)+"/scriptPruebasFase" + fase + "_p_" + prueba + ".bat", true);

                 //Poner aqui grabar para generar por separado
                 System.out.println("Fin de generacion bat " +entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5) + " fase " + fase);
             }
             sfRespuesta.append("pause\n");
             GrabarFichero grabarFichero = new GrabarFichero();
             grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5)+version+"/scriptPruebasFase" + fase +"_"+ numPruebas + "pruebas.bat", true);
             grabarFichero.agregarAFichero(sfRespuesta.toString());
             grabarFichero.cerrarFichero();
		}
        //Fin de generar un unico fichero
       */
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
