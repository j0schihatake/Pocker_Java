import PockerRoom.*;
import Util.ImageUtil;
import Util.InputUtil;
import PockerRoom.Board;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

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

         String patchName = ImageUtil.getRandomName("d:\\");

         Rectangle subImage = new Rectangle(0,0,0,0);
         subImage.setRect(270,240,130,35);

         //ImageUtil.createScreenImage(patchName);

        /**
         * 1) с этим все более менее понятно каждый пиксель это -15593193
         * 2) не буду учитывать минус при декодированиии,
         * 3) а так - же для преобразованияв значения от 0 до 1 / 1
         */
        // ImageUtil.grabPixels(patchName,subImage);

        // с этим методом получается нихрена мне непонятный массив(там и заголовки и ещехерь всякая)
        // ImageUtil.imageToMassive(patchName, subImage);

        // тестирую создание файла кусочка изображения
         //ImageUtil.cropAndSaveImage(patchName,subImage);

        // Thread.sleep(10000);

        while(1==1) {
            Board.debugLogInfo();
            Thread.sleep(10000);
        }
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
