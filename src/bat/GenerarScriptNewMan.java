
package bat;

import java.io.IOException;

public class GenerarScriptNewMan {

    public static void main(String[] args) throws IOException {

        int fase = 1;
        int numPruebas = 18;
        String entornoDB2 = "eUrsaePru.json";
        String entornoSQL = "eSQL.json";
        String entornoComparacion = entornoSQL;

        StringBuffer sfRespuesta = new StringBuffer();

        for (int prueba = 0; prueba < numPruebas; prueba++) {
            sfRespuesta = new StringBuffer();
            sfRespuesta.append("mkdir resultados\n");
            sfRespuesta.append("mkdir globales\n");
            String l1 = "call newman run Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                    + ".json -e eUrsaePru.json --reporters cli,json --reporter-json-export globales\\globales"
                    + prueba + ".json\n";
            sfRespuesta.append(l1);
            String l2 = "call newman run Colecciones\\ColeccionPostman_fase_" + fase + "_" + prueba
                    + ".json -e " + entornoComparacion + " -g globales\\globales" + prueba
                    + ".json -d globales\\globales" + prueba
                    + ".json --reporters cli,json --reporter-json-export resultados\\respuesta_"
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
            // sfRespuesta.append("pause\n");

            GrabarFichero grabarFichero = new GrabarFichero();
            grabarFichero.crearFichero("scriptPruebasFase" + fase + "_p_" + prueba + ".bat", false);

            grabarFichero.agregarAFichero(sfRespuesta.toString());
            grabarFichero.cerrarFichero();
            System.out.println("Fin de generacion bat");
        }
    }

}
