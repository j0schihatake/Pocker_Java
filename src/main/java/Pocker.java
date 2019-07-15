import Util.ImageUtil;
import PockerRoom.*;
import Util.InputUtil;

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

        // В целях тестирования можно запустить тестовую(виртуальную) игру:
        Game testGame = new Game(10);

        // Запуск тестовой игры:
        testGame.play();

        //---------------------------------Эксперименты с распознаванием изображения-------------------------------
        //screen();

        //---------------------------------Эксперименты с имитацией управления-------------------------------------
        inputTest();
    }

    /**
     * Выполняет создание изображения скрина каждые 10 сек:
     * @throws Exception
     */
    public static void screen() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
        Calendar now = Calendar.getInstance();
        String patchName = "d:\\test\\"+formatter.format(now.getTime())+".jpg";

        ImageUtil s2i = new ImageUtil();
        while(1==1)
        {
            s2i.createScreenImage(patchName);
            Thread.sleep(10000);
        }
    }

    /**
     * Выполняет тестовое нажатие клавиш с клавиатуры и тестовое управение мышью:
     * @throws AWTException
     */
    public static void inputTest() throws AWTException {
        InputUtil.klickMouseToCoordinats(10,20, InputEvent.BUTTON1_DOWN_MASK);
        InputUtil.pressKey(KeyEvent.VK_A);
        InputUtil.pressKeys("alt+tab");
    }

}
