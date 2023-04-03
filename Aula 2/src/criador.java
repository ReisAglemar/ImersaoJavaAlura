//import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class criador{

    
    public void gerador (InputStream entradaDados, String nomeSaida) throws IOException{

        BufferedImage imagenOriginal = ImageIO.read(entradaDados); //carrega imagem na memoria afim de extrair informações

        int largura = imagenOriginal.getWidth(); // estrai a informação de largura
        int altura = imagenOriginal.getHeight(); // estrai a informação de altura
        int novaAltura = altura + 200; //define uma nova altura

        BufferedImage novaImagen = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT); //carrega na memoria um espaco para a nova imagem

        Graphics2D caneta = (Graphics2D) novaImagen.getGraphics();
        caneta.drawImage(imagenOriginal, 0, 0, null);

        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        caneta.setColor(Color.CYAN);
        caneta.setFont(fonte);

        caneta.drawString("AREA DE TEXTO", 100, novaAltura -100);

        ImageIO.write(novaImagen, "png", new File("Aula 2/saida/" + nomeSaida));


    }

}
