import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
//import java.security.Key;
//import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Map;

public class App {
    
    public static void main(String[] args) throws Exception {

        // faz a coneccao da api, acessa um link

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI enderecoUri = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(enderecoUri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body); // imprimi o link inteiro, coneccao ok

        // preparando os dados brutos

        var parser = new JsonParser(); // aqui foi criado uma nova classe global
        List<Map<String, String>> listaFilmes = parser.parse(body);
        // System.out.println(listaFilmes.size()); // imprime a quantidade de filmes no
        // link, retorno ok

        // organizando para exibir os dados

        for (Map<String, String> filme : listaFilmes) {

            String nomeFilme = filme.get("fullTitle");
            String enderecoImagen = filme.get("image");
            double nota = Double.parseDouble(filme.get("imDbRating")); // string para numero
            int notaInt = (int) nota; // força criar numero com uma casa

            System.out.println();
            System.out.println("➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
            System.out.println("🎬 Filme: " + "\u001b[1m" + nomeFilme + "\u001b[0m");
            System.out.print("\u001b[1m\u001b[30m\u001b[46m Nota no IMDB:\u001b[0m " + nota + " ");

            for (int i = 1; i <= notaInt; i++) { // for para repetir as estrelas

                System.out.print("⭐");
            }

            System.out.println();
            System.out.println(enderecoImagen);
            System.out.println("➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        }

    }
}
