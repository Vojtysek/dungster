import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Ascii {

    private static final String density = new StringBuilder("Ñ@#W$0?!;:+=-,._ ").reverse().toString();

    public void convertToAscii(String path) throws IOException {
        BufferedImage baseImage = ImageIO.read(new File(path));

        int finalWidth = 450-1;
        int finalHeight = 150;
        double scaleX = (double) baseImage.getWidth() / finalWidth;
        double scaleY = (double) baseImage.getHeight() / finalHeight;

        StringBuilder buffer = new StringBuilder();

        for (int y = 0; y < finalHeight; y++) {
            for (int x = 0; x < finalWidth; x++) {
                int srcX = (int) (x * scaleX);
                int srcY = (int) (y * scaleY);
                int pixel = baseImage.getRGB(srcX, srcY);
                int brightness = ((((pixel >> 16) & 0xff)) + ((pixel >> 8) & 0xff) + (pixel & 0xff)) / 3;
                int index = (int) (brightness / 255.0 * (density.length() - 1));
                buffer.append(density.charAt(index));
            }
            buffer.append("\n");
        }
        System.out.println(buffer.toString());
    }
}