package Util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.PixelGrabber;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import javax.imageio.ImageIO;

public class ImageUtil {

    public static Rectangle starWindow = new Rectangle(339,65, 1321,940);

    /**
     * Метод возвращает BufferedImage открытого стола.
     * @return
     * @throws AWTException
     */
    public static BufferedImage getStarWindow() throws AWTException {
        // Получаем скрин всего экрана:
        Robot robot = new Robot();
        BufferedImage fullScreen = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        // Выделяем окно приложения
        BufferedImage starWindow = ImageUtil.cropImage(fullScreen, ImageUtil.starWindow);
        return starWindow;
    }

    /**
     * Метод создает скриншот экрана и сохраняет в Image.
     * @throws Exception
     */
    public static BufferedImage createScreenImage() throws Exception
    {
        Robot robot = new Robot();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        return screenShot;
    }

    public static void saveImageAs(BufferedImage image, String patchName) throws IOException {
        ImageIO.write(image, "JPG", new File(patchName));
    }

    /**
     * Метод возвращает участок изображения
     * @param src - исходное изображение(кусок которого требуется вернуть)
     * @param rect - прямоугольник на екране заданный 4-мя координатами
     * @return
     */
    public static BufferedImage cropImage(BufferedImage src, Rectangle rect) {
        BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
        return dest;
    }

    /**
     * Метод возвращает участок от скрина всего экрана и сохраняет его в файл
     * @param patchName название файла в который будет записан указанный кусок
     * @param rect - прямоугольник на екране заданный 4-мя координатами
     * @return
     */
    public static BufferedImage cropAndSaveImage(String patchName, Rectangle rect) throws AWTException, IOException {
        BufferedImage dest = ImageUtil.getStarWindow().getSubimage(rect.x, rect.y, rect.width, rect.height);
        ImageIO.write(ImageUtil.getBonusContrast(dest), "JPG", new File(patchName));
        return dest;
    }

    /**
     * Преобразование изображения в массив пикселей(пример: -13948117)
     * @param
     */
    public static int[] grabPixels(String file, Rectangle section) throws IOException {

        File f = new File(file);
        BufferedImage img2 = ImageIO.read(f);
        BufferedImage image = cropImage(img2, section);

        int width = image.getWidth();
        int height = image.getHeight();

        int[] pixels = new int[0];
        PixelGrabber pgb;

        try {
            pixels = new int[width * height];

            pgb = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);

            pgb.grabPixels();
        } catch (InterruptedException exc) {
            System.out.println("Interrupted: " + exc.getMessage());
        }

        // Выводим содержимое массива:
        for(int i  = 0; i < pixels.length; i++) {
            System.out.print(pixels[i] + " ");
            System.out.print("int = " + getDecimal(pixels[i]) + " ");
            System.out.print("итоговый float = " + 1f/(getDecimal(pixels[i])) + "; ");
        }
        System.out.print(" Lenght = " + pixels.length + " ");
        return pixels;
    }

    /**
     * Метод преобразует изображение в черно белое:
     * @param image
     * @return
     */
    public static BufferedImage getWhiteBlackImage(BufferedImage image){
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        return op.filter(image, null);
    }

    public static BufferedImage getWhiteBalckTwoColorImage(BufferedImage img){
        return new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
    }

    public static BufferedImage getBonusContrast(BufferedImage image){
        RescaleOp rescaleOp = new RescaleOp(1.2f, 15, null);
        return rescaleOp.filter(image, image);
    }

    /**
     * Метод преобразует бинарное значение в int:
     * @param binary
     * @return
     */
    public static int getDecimal(int binary){
        int decimal = 0;
        int n = 0;
        while(true){
            if(binary == 0){
                break;
            } else {
                int temp = binary%10;
                decimal += temp*Math.pow(2, n);
                binary = binary/10;
                n++;
            }
        }
        return decimal;
    }

    /**
     * Метод возвращает рандомизированное за счет даты название
     * @param patch пример: "d:\\Pocker\\Example0\\"
     * @return
     */
    public static String getRandomName(String patch){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
        Calendar now = Calendar.getInstance();
        String patchName = patch+formatter.format(now.getTime())+".jpg";
        return patchName;
    }

    public static String recognition(BufferedImage image) throws TesseractException {
        String text;

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata/");
        text = tesseract.doOCR(image);
        System.out.print(text);

        return text;
    }
}
