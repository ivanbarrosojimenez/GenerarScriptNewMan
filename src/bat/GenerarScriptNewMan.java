
package bat;

import java.io.IOException;

public class GenerarScriptNewMan {
	static String [] colores = {"1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c","1f","26","37","48","59","60","7a","8b","9c","ad","be","cf","d1","e2","af","c9","9c"};
    public static void main(String[] args) throws IOException {
    	/** Modificar solo esto*/
        int fase = 2;
        int numPruebas = 7;
        /** Modificar solo esto*/
        
        
        
        String entornoDB2 = "eDb2Estable.json";
        String entornoSQL = "eSQLEstable.json";
        String [] entornoComparacion = new String[2];
        entornoComparacion[0] = entornoDB2;
        entornoComparacion[1] = entornoSQL;
        
        //Para generar por separado:
        for (int entorno = 0; entorno < 2; entorno++) {
        	 StringBuffer sfRespuesta = new StringBuffer();
             String carpetaResultados = "resultados"+entornoComparacion[entorno].substring(1, 4);
             String carpetaGlobales = "globales"+entornoComparacion[entorno].substring(1, 4);

             for (int prueba = 0; prueba < numPruebas; prueba++) {
                 sfRespuesta = new StringBuffer();
                 sfRespuesta.append("title \"ENTORNO "+entornoComparacion[entorno].substring(1, 4)+" COLECCION "+prueba+"/"+(numPruebas-1)+"\"\n");
                 sfRespuesta.append("color "+colores[prueba]+"\n");
                 sfRespuesta.append("mkdir "+carpetaResultados+"\n");
                 sfRespuesta.append("mkdir "+carpetaGlobales+"\n");
                 String l1 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                         + ".json -e ..\\eUrsaePru.json --reporters cli,json --reporter-json-export "+carpetaGlobales+"\\"
                         + prueba + ".json\n";
                 sfRespuesta.append(l1);
                 String l2 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                         + ".json -e ..\\" + entornoComparacion[entorno] + " -g "+carpetaGlobales+"\\" + prueba
                         + ".json -d "+carpetaGlobales+"\\" + prueba
                         + ".json --reporters cli,json --reporter-json-export "+carpetaResultados+"\\respuesta_"
                         + prueba + ".json\n";
                 sfRespuesta.append(l2);

                 sfRespuesta.append("echo * * * * FIN DEL PROCESO. * * * * \n");
                 // sfRespuesta.append(":end\n");
                 // sfRespuesta.append("echo \n");
                 // sfRespuesta
                 // .append("echo * * * * * * * * * * * * * FIN DEL PROCESO!! * * * * * * * \n");
                 // sfRespuesta.append("timeout /t 5\n");
                 // sfRespuesta.append("cls\n");
                 // sfRespuesta.append("goto end\n");
                 sfRespuesta.append("pause\n");

                 GrabarFichero grabarFichero = new GrabarFichero();
                 grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(0, 11)+"/scriptPruebasFase" + fase + "_p_" + prueba + ".bat", true);
                 grabarFichero.agregarAFichero(sfRespuesta.toString());
                 grabarFichero.cerrarFichero();
                 //Poner aqui grabar para generar por separado
                 System.out.println("Fin de generacion bat " +entornoComparacion[entorno].substring(1, 4) + " fase " + fase);
             }
		}
        //Fin de generar por separado
        
      //Para generar un unico fichero:
        for (int entorno = 0; entorno < 2; entorno++) {
        	 StringBuffer sfRespuesta = new StringBuffer();
             String carpetaResultados = "resultados"+entornoComparacion[entorno].substring(1, 4);
             String carpetaGlobales = "globales"+entornoComparacion[entorno].substring(1, 4);
             //comentar para generar por separado
            
             sfRespuesta = new StringBuffer();
             sfRespuesta.append("mkdir "+carpetaResultados+"\n");
             sfRespuesta.append("mkdir "+carpetaGlobales+"\n");
             for (int prueba = 0; prueba < numPruebas; prueba++) {
                 sfRespuesta.append("title \"ENTORNO "+entornoComparacion[entorno].substring(1, 4)+" COLECCION "+prueba+"/"+(numPruebas-1)+"\"\n");
                 sfRespuesta.append("color "+colores[prueba]+"\n");
                 String l1 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                         + ".json -e ..\\eUrsaePru.json --reporters cli,json --reporter-json-export "+carpetaGlobales+"\\"
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
                 System.out.println("Fin de generacion bat " +entornoComparacion[entorno].substring(1, 4) + " fase " + fase);
             }
             sfRespuesta.append("pause\n");
             GrabarFichero grabarFichero = new GrabarFichero();
             grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(0, 11)+"/scriptPruebasFase" + fase +"_"+ numPruebas + "pruebas.bat", true);
             grabarFichero.agregarAFichero(sfRespuesta.toString());
             grabarFichero.cerrarFichero();
		}
        //Fin de generar un unico fichero

       
    }

}
