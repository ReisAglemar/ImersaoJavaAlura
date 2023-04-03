import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
//import java.security.Key;
//import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Map;
//import java.io.InputStream;
//import java.io.BufferedOutputStream;
//import java.io.InputStream;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//import javax.print.attribute.standard.NumberUpSupported;

public class App1 {
    /**
     * @param args
     * @throws Exception
     */
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

        var parser = new JsonParser1(); // aqui foi criado uma nova classe global
        List<Map<String, String>> listaFilmes = parser.parse(body);
        // System.out.println(listaFilmes.size()); // imprime a quantidade de filmes no
        // link, retorno ok

        // organizando para exibir os dados

        var criadora = new criador();

        for (Map<String, String> filme : listaFilmes) {

            String nomeFilme = filme.get("fullTitle");
            String enderecoImagen = filme.get("image");
            
            InputStream entradaDados = new URL(enderecoImagen).openStream();
            
            String nomeSaida = nomeFilme + ".png";
            criadora.gerador(entradaDados, nomeSaida);
            
            System.out.println(nomeFilme);
            System.out.println();
            
        }

    }
}
