
package bat;

import java.io.IOException;

public class GenerarScriptNewMan {

    public static void main(String[] args) throws IOException {
    	/** Modificar solo esto*/
        int fase = 1;
        int numPruebas = 18;
        /** Modificar solo esto*/
        
        
        String entornoDB2 = "eDb2Estable.json";
        String entornoSQL = "eSQLEstable.json";
        String [] entornoComparacion = new String[2];
        entornoComparacion[0] = entornoDB2;
        entornoComparacion[1] = entornoSQL;

        for (int entorno = 0; entorno < 2; entorno++) {
        	 StringBuffer sfRespuesta = new StringBuffer();
             String carpetaResultados = "resultados"+entornoComparacion[entorno].substring(1, 4);
             String carpetaGlobales = "globales"+entornoComparacion[entorno].substring(1, 4);

             for (int prueba = 0; prueba < numPruebas; prueba++) {
                 sfRespuesta = new StringBuffer();
                 sfRespuesta.append("mkdir "+carpetaResultados+"\n");
                 sfRespuesta.append("mkdir "+carpetaGlobales+"\n");
                 String l1 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                         + ".json -e ..\\eUrsaePru.json --reporters cli,json --reporter-json-export "+carpetaGlobales+"\\"
                         + prueba + ".json\n";
                 sfRespuesta.append(l1);
                 String l2 = "call newman run ..\\Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                         + ".json -e ..\\" + entornoComparacion[0] + " -g "+carpetaGlobales+"\\" + prueba
                         + ".json -d "+carpetaGlobales+"\\" + prueba
                         + ".json --reporters cli,json --reporter-json-export "+carpetaResultados+"\\respuesta_"
                         + prueba + ".json\n";
                 sfRespuesta.append(l2);

                 sfRespuesta.append("echo * * * * FIN DEL PROCESO. * * * * \n");
                 // sfRespuesta.append(":end\n");
                 // sfRespuesta.append("echo \n");
                 // sfRespuesta
                 // .append("echo * * * * * * * * * * * * * FIN DEL PROCESO!! * * * * * * * \n");
                 // sfRespuesta.append("timeout /t 1\n");
                 // sfRespuesta.append("cls\n");
                 // sfRespuesta.append("goto end\n");
                  sfRespuesta.append("pause\n");

                 GrabarFichero grabarFichero = new GrabarFichero();
                 grabarFichero.crearFichero("ScriptFase"+fase+"_"+entornoComparacion[entorno].substring(0, 11)+"/scriptPruebasFase" + fase + "_p_" + prueba + ".bat", true);

                 grabarFichero.agregarAFichero(sfRespuesta.toString());
                 grabarFichero.cerrarFichero();
                 System.out.println("Fin de generacion bat " +entornoComparacion[entorno].substring(1, 4) + " fase " + fase);
             }
		}

       
    }

}
