import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            // URL da API do Chuck Norris
            String apiUrl = "https://api.chucknorris.io/jokes/random";

            //Fazendo a requisição GET para a API
            HttpURLConnection conexao = (HttpURLConnection) new URL(apiUrl).openConnection();
            conexao.setRequestMethod("GET");

            //Lendo a resposta do API
            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while((linha = leitor.readLine()) != null){
                resposta.append(linha);
            }
            leitor.close(); // Fecha a entrada de memoria

            String piada = obterPiadaChuckNorris(resposta.toString());
            System.out.println("Piada: " + piada);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * Método para extrair a piada de JSON retornado pela API.
    * @param resposta - Resposta da API no formato String.
    * @return A piada estraida da resposta da API.
    */

    private static String obterPiadaChuckNorris(String resposta) {
        //Extraindo a piada do JSON
        int iniciodoindice = resposta.indexOf("\"value\":") + ("\"value\":".length());
        int fimdoindice = resposta.lastIndexOf("\"");
        return resposta.substring(iniciodoindice, fimdoindice);
    }
}