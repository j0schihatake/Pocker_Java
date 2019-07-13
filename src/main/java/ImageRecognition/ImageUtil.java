package ImageRecognition;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ImageUtil {

    /**
     * Метод создает скриншот экрана и сохраняет в Image.
     * @throws Exception
     */
    public static void createScreenImage(String patchName) throws Exception
    {
        Robot robot = new Robot();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(screenShot, "JPG", new File(patchName));
    }
}
