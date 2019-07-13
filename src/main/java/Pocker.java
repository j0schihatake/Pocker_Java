import ImageRecognition.ImageUtil;
import PockerRoom.*;

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
        screen();
    }

    /**
     * Выполняет создание изображения скрина каждые 10 сек:
     * @throws Exception
     */
    public static void screen() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
        Calendar now = Calendar.getInstance();
        String patchName = "d:\\"+formatter.format(now.getTime())+".jpg";

        ImageUtil s2i = new ImageUtil();
        while(1==1)
        {
            s2i.createScreenImage(patchName);
            Thread.sleep(10000);
        }
    }

}
