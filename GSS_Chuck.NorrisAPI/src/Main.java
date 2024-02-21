import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Main {
    public static void main(String[] args) {
        try {
            String piada = obterPiadaChuckNorris();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String obterPiadaChuckNorris() throws IOException {
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

        //Extraindo a piada do JSON
        int iniciodoindice = resposta.indexOf("\"value\":") + ("\"value\":".length());
        int fimdoindice = resposta.lastIndexOf("\"");
        return resposta.substring(iniciodoindice, fimdoindice);
    }
}