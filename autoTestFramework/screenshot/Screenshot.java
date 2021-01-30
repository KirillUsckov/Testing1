package screenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Screenshot {
    public static void create(String path) {
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(String path) {
        try {
            File screen = new File(path);
            screen.delete();
        } catch (Exception e) {

        }
    }

}
