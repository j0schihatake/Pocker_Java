import Util.ImageUtil;
import PockerRoom.*;
import Util.InputUtil;
import Util.VirtualDesktop.Table;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Pocker - Покер.
 */
public class Pocker {

    public static void main(String[] arrgs) throws Exception {

        // В целях тестирования/обучения можно запустить тестовую(виртуальную) игру:
        Game testGame = new Game(10);

        // Запуск тестовой игры:
        testGame.play();

        //---------------------------------Эксперименты с распознаванием изображения-------------------------------
        screen();

        //---------------------------------Эксперименты с имитацией управления-------------------------------------
        //inputTest();

    }

    /**
     * Выполняет создание изображения скрина каждые 10 сек:
     * @throws Exception
     */
    public static void screen() throws Exception {

        // String patchName = ImageUtil.getRandomName("d:\\Pocker\\Example0\\");

        // Rectangle subImage = new Rectangle(0,0,128,128);

        // ImageUtil.createScreenImage(patchName);

        /**
         * 1) с этим все более менее понятно каждый пиксель это -15593193
         * 2) не буду учитывать минус при декодированиии,
         * 3) а так - же для преобразованияв значения от 0 до 1 / 1
         */
        // ImageUtil.grabPixels(patchName,subImage);

        // с этим методом получается нихрена мне непонятный массив(там и заголовки и ещехерь всякая)
        // ImageUtil.imageToMassive(patchName, subImage);

        // тестирую создание файла кусочка изображения
        // ImageUtil.cropAndSaveImage(patchName,subImage);

        // Thread.sleep(10000);
    }

    /**
     * Выполняет тестовое нажатие клавиш с клавиатуры и тестовое управение мышью:
     * @throws AWTException
     */
    public static void inputTest() throws AWTException, InterruptedException {
        InputUtil.klickMouseToCoordinats(10,20, InputEvent.BUTTON1_DOWN_MASK);
        InputUtil.pressKey(KeyEvent.VK_A);
        InputUtil.pressKeys("alt+tab");
    }

}
