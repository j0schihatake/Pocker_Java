package Util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;

public class ImageUtil {

    /**
     * Метод создает скриншот экрана и сохраняет в Image.
     * @throws Exception
     */
    public static BufferedImage createScreenImage(String patchName) throws Exception
    {
        Robot robot = new Robot();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(screenShot, "JPG", new File(patchName));
        return screenShot;
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
        Robot robot = new Robot();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        BufferedImage dest = screenShot.getSubimage(rect.x, rect.y, rect.width, rect.height);
        ImageIO.write(dest, "JPG", new File(patchName));
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
     * Преобразование изображения в массив byte[]
     * @param file
     * @param section
     * @throws IOException
     */
    /**
    public static void imageToMassive(String file, Rectangle section) throws IOException {
        File f = new File(file);
        BufferedImage img2 = ImageIO.read(f);
        BufferedImage img = cropImage(img2, section);

        int width = img.getWidth();
        int height = img.getHeight();
        int bpp = getBitPerPixel(img);

        System.out.print("Input image size: " + width + " x " + height + " (bpp = " + bpp + ")\n");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        //byte[] imageInByte = ((DataBufferByte) img.getData().getDataBuffer()).getData();
        baos.close();
        dump(imageInByte);
    }

    public static int getBitPerPixel(BufferedImage img){

        return img.getColorModel().getPixelSize()/Byte.SIZE;
    }

    private static void dump(byte[] raw){
        for(int i = 0; i < raw.length; i++ ){
            System.out.print(raw[i] + " ");
        }
        System.out.println();
        System.out.print("Lenght = " + raw.length);
    }
     **/

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
}
