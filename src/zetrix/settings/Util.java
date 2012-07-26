package zetrix.settings;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Util {        
        public static BufferedImage getRes(String resname) {
            try {
                BufferedImage img = ImageIO.read(zetrix.Desh.class.getResource("/zetrix/img/" + resname));
                System.out.println("Loading resourse: " + resname);
                return img;
            } catch (Exception e) {
                System.out.println("Error loading resource: " + resname);
                System.out.println("Text of Error: " + e);
            } return new BufferedImage(1, 1, 2);
        }
}
