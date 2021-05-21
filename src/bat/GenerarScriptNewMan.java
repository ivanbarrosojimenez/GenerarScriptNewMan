
package bat;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class GenerarScriptNewMan {
	private static String [] colores = {"1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c"};

    
	public boolean generarScriptResultado(int fase, int numPruebas, String version, boolean generarBatUnico, String filtro,String [] entornoComparacion) throws IOException {

		boolean esSinTest = false;
		
		if (!esSinTest) {
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

	                 GrabarFichero grabarFichero = new GrabarFichero();
	                 grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5)+version+"/scriptPruebasFase" + fase + "_p_" + prueba + ".bat", true);
	                 grabarFichero.agregarAFichero(sfRespuesta.toString());
	                 grabarFichero.cerrarFichero();
	                 sfRespuestaTotal.append(sfRespuesta);                 
	                 System.out.println("Fin de generacion bat " +entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5) + " fase " + fase);
	             }
	             if(generarBatUnico) {
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
			}
		} else {
			//Para generar por separado:
	        for (int entorno = 0; entorno < entornoComparacion.length; entorno++) {
	        	 StringBuffer sfRespuesta = new StringBuffer();
	        	 StringBuffer sfRespuestaTotal = new StringBuffer();	
	        	 sfRespuestaTotal.append(":end\n");
	             String carpetaResultados = "F"+fase+"resultados"+entornoComparacion[entorno].substring(1, 4);
	             String carpetaGlobales = "F"+fase+"globales"+entornoComparacion[entorno].substring(1, 4);

	             for (int prueba = 0; prueba < numPruebas; prueba++) {
	                 sfRespuesta = new StringBuffer();	                 
	                 
	                 String l2 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
	                         + ".json -e ..\\" + entornoComparacion[entorno] + "\n";
	                 sfRespuesta.append(l2);	                 

	                 GrabarFichero grabarFichero = new GrabarFichero();
	                 grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5)+version+"/scriptPruebasFase" + fase + "_p_" + prueba + ".bat", true);
	                 grabarFichero.agregarAFichero(sfRespuesta.toString());
	                 grabarFichero.cerrarFichero();
	                 sfRespuestaTotal.append(sfRespuesta);                 
	                 System.out.println("Fin de generacion bat " +entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5) + " fase " + fase);
	             }
	             
	             if(generarBatUnico) {	           
		             sfRespuestaTotal.append("goto end\n");
		
		             GrabarFichero grabarFichero = new GrabarFichero();
		             grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5)+version+"/scriptPruebasFase" + fase +"_"+ numPruebas + "pruebas.bat", true);
		             grabarFichero.agregarAFichero(sfRespuestaTotal.toString());
		             grabarFichero.cerrarFichero();
	             }
			}
		}

        
        return true;
	}
	
	public boolean generarScriptTiempos(boolean compararPre ,int numIteraciones, int fase, int numPruebas, String version, boolean generarBatUnico, String filtro, String [] entornoComparacion) throws IOException {

        //call newman run ..\Colecciones\ColeccionPostman_fase_4_4.json -e ..\UrsaePru.json --iteration-count 2 --reporters cli,json --reporter-json-export "F4respuestas\prueba4.json"
        
		
        //Para generar por separado:
		
		for (int entorno = 0; entorno < entornoComparacion.length; entorno++) {
			StringBuffer sfRespuesta = new StringBuffer();
       	 	StringBuffer sfRespuestaTotal = new StringBuffer();
            String carpetaResultadosMF = "F"+fase+"resultadosDb2";
            String carpetaResultadosSQL = "F"+fase+"resultadosSQL";
            StringBuffer sfRespuestaTodos = new StringBuffer();

            
            for (int prueba = 0; prueba < numPruebas; prueba++) {
                sfRespuesta = new StringBuffer();
                sfRespuesta.append("title \"ENTORNO "+entornoComparacion[entorno].substring(1, +entornoComparacion[entorno].length()-5)+" COLECCION "+prueba+"/"+(numPruebas-1)+"\"\n");
                sfRespuesta.append("color "+colores[prueba]+"\n");
                sfRespuesta.append("mkdir "+carpetaResultadosMF+"\n");
                sfRespuesta.append("mkdir "+carpetaResultadosSQL+"\n");
                if (compararPre) {
                	String l1 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                            + ".json -e ..\\UrsaePre.json --iteration-count "
                   		 +numIteraciones+" --reporters cli,json --reporter-json-export "+carpetaResultadosMF+"\\prueba"
                            + prueba + ".json\n";
                    sfRespuesta.append(l1);	
                }
                
                String l2 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                        + ".json -e ..\\"+entornoComparacion[entorno]+" --iteration-count "
               		 +numIteraciones+" --reporters cli,json --reporter-json-export "+carpetaResultadosSQL+"\\prueba"
                        + prueba + ".json\n";
                sfRespuesta.append(l2);

                sfRespuesta.append("echo * * * * FIN DEL PROCESO. * * * * \n");

                GrabarFichero grabarFichero = new GrabarFichero();
                grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5)+version+"/scriptPruebasFase" + fase + "_p_" + prueba + ".bat", true);
                grabarFichero.agregarAFichero(sfRespuesta.toString());
                grabarFichero.cerrarFichero();
                sfRespuestaTotal.append(sfRespuesta);
                System.out.println("Fin de generacion bat " +entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5) + " fase " + fase);
            }
            
            sfRespuestaTodos.append(sfRespuestaTotal);
            
            if(generarBatUnico) {
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
            
            GrabarFichero grabarFichero = new GrabarFichero();
            if (fase == 1) {
            	grabarFichero.crearFichero("ScriptFaseTodos_"+entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5)+version+"/scriptPruebasTodos_pruebas.bat", true);	
            } else {
            	grabarFichero.abrirFichero("ScriptFaseTodos_"+entornoComparacion[entorno].substring(1, entornoComparacion[entorno].length()-5)+version+"/scriptPruebasTodos_pruebas.bat", true);            	
            }
            grabarFichero.agregarAFichero(sfRespuestaTodos.toString());
            grabarFichero.cerrarFichero();
		}
		
        return true;
	}


}
