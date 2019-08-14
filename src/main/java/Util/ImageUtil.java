package Util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixReadMem;

public class ImageUtil {

    public static Rectangle starWindow = new Rectangle(339,65, 1321,940);

    public static final int OCR_TIME_THRESHOLD_MS = 300;
    public static final int OCR_COLOR_THRESHOLD = 180;

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
        ImageIO.write(convertoBlackAndWhite(dest), "JPG", new File(patchName));
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
     * Метод возвращает цвет пикселя
     * @param image
     */
    public static int getCollor(BufferedImage image, int x, int y){
        return image.getRGB(x,y);
    }

    /**
     * Метод выполняет коррекцию от авторов:
     * @param imgBuff
     * @return
     */
    public static BufferedImage convertoBlackAndWhite(BufferedImage imgBuff){
        // Color image to pure black and white
        for (int x = 0; x < imgBuff.getWidth(); x++) {
            for (int y = 0; y < imgBuff.getHeight(); y++) {
                Color color = new Color(imgBuff.getRGB(x, y));
                int red = color.getRed();
                int green = color.getBlue();
                int blue = color.getGreen();
                if (red + green + blue > OCR_COLOR_THRESHOLD) {
                    red = green = blue = 0; // Black
                } else {
                    red = green = blue = 255; // White
                }
                Color col = new Color(red, green, blue);
                imgBuff.setRGB(x, y, col.getRGB());
            }
        }
        return imgBuff;
    }

    /**
     * Метод выполняет замену цвета в изображении
     */
    public void replaceColorInImage(){
        try {
            BufferedImage img = ImageIO.read(new File("8bit.gif"));
            BufferedImage replaced = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Color match = new Color(209, 167, 86);
            Color with = new Color(0, 255, 0);

            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    int pixel = img.getRGB(x, y);
                    if (pixel == match.getRGB()) {
                        System.out.println("Same Color Detected!");
                        replaced.setRGB(x, y, with.getRGB());
                    } else {
                        replaced.setRGB(x, y, pixel);
                    }
                }
            }

            ImageIO.write(replaced, "png", new File("replaced.png"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
        String text = "";

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata/");
        text = tesseract.doOCR(convertoBlackAndWhite(image));
        //StringUtil.tesCorrect(text);

        return text;
    }

    public static String ocr(BufferedImage imgBuff) {
        String parsedOut = null;

        try {
            // Color image to pure black and white
            for (int x = 0; x < imgBuff.getWidth(); x++) {
                for (int y = 0; y < imgBuff.getHeight(); y++) {
                    Color color = new Color(imgBuff.getRGB(x, y));
                    int red = color.getRed();
                    int green = color.getBlue();
                    int blue = color.getGreen();
                    if (red + green + blue > OCR_COLOR_THRESHOLD) {
                        red = green = blue = 0; // Black
                    } else {
                        red = green = blue = 255; // White
                    }
                    Color col = new Color(red, green, blue);
                    imgBuff.setRGB(x, y, col.getRGB());
                }
            }

            // OCR recognition
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imgBuff, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            tesseract.TessBaseAPI api = new tesseract.TessBaseAPI();
            api.Init(null, "eng");
            ByteBuffer imgBB = ByteBuffer.wrap(imageBytes);

            lept.PIX image = pixReadMem(imgBB, imageBytes.length);
            api.SetImage(image);

            // Get OCR result
            BytePointer outText = api.GetUTF8Text();

            // Destroy used object and release memory
            api.End();
            //api.close();
            outText.deallocate();
            pixDestroy(image);

            // OCR corrections
            parsedOut = outText.getString().replaceAll("l", "1").replaceAll("Z", "2").replaceAll("O", "0")
                    .replaceAll("B", "8").replaceAll("G", "6").replaceAll("S", "8").replaceAll("'", "")
                    .replaceAll("‘", "").replaceAll("\\.", ":").replaceAll("E", "8").replaceAll("o", "0")
                    .replaceAll("ﬂ", "0").replaceAll("ﬁ", "6").replaceAll("§", "5").replaceAll("I", "1")
                    .replaceAll("T", "7").replaceAll("’", "").replaceAll("U", "0").replaceAll("D", "0");
            if (parsedOut.length() > 7) {
                parsedOut = parsedOut.substring(0, 7) + ":" + parsedOut.substring(8, parsedOut.length());
            }
            parsedOut = parsedOut.replaceAll("::", ":");

            // Remove last part (number of frames)
            int iSpace = parsedOut.lastIndexOf(" ");
            if (iSpace != -1) {
                parsedOut = parsedOut.substring(0, iSpace);
            }
        } catch (IOException e) {
            //log.warn("IOException in OCR", e);
        }
        return parsedOut;
    }

    public static ByteBuffer convertImageData(BufferedImage bi)
    {
        byte[] pixelData = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        //        return ByteBuffer.wrap(pixelData);
        ByteBuffer buf = ByteBuffer.allocateDirect(pixelData.length);
        buf.order(ByteOrder.nativeOrder());
        buf.put(pixelData);
        buf.flip();
        return buf;
    }
}
