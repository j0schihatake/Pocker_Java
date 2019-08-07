package Util.VirtualDesktop;

import Util.ImageUtil;
import java.awt.*;
import java.io.IOException;

/**
 * Игровой стол.
 * Чтобы облегчить жизнь, привязываемся не к конкретной позиции а к соотношениям(высоты к ширине).
 */
public class Table {

    /**
     * Высота стола(в пикселях)
     */
    public int height;
    /**
     * Ширина стола в пикселях
     */
    public int width;

    //---------------------------------------------Области наблюдения--------------------------------------------

    /**
     * dlp - down left player, игрок слева внизу.
     * Action - область где отражено выполненное игроком действие(надпись)
     * Money - область где отображены средства игрока(цифра)
     * Active - область где отображено что текущий игрок активен(выделение контура)
     * Diller - областьгде отображено что текущий игрок диллер(сердечко)
     */
    public static Rectangle dlpAction;
    public static String dlpExampleActionFilePatch = "d:\\Pocker\\ExampleDLP\\Action\\";
    public static Rectangle dlpMoney;
    public static String dlpExampleMoneyFilePatch = "d:\\Pocker\\ExampleDLP\\Money\\";
    public static Rectangle dlpActive;
    public static String dlpExampleActiveFilePatch = "d:\\Pocker\\ExampleDLP\\Active\\";
    public static Rectangle dlpDiller;
    public static String dlpExampleDillerFilePatch = "d:\\Pocker\\ExampleDLP\\Diller\\";
    // Ставка
    public static Rectangle dlpRect;
    public static String dlpExampleRectFilePatch = "d:\\Pocker\\ExampleDLP\\Rect\\";


    /**
     * dcp - down center player, игрок внизу по центру
     * Action - область где отражено выполненное игроком действие(надпись)
     * Money - область где отображены средства игрока(цифра)
     * Active - область где отображено что текущий игрок активен(выделение контура)
     * Diller - областьгде отображено что текущий игрок диллер(сердечко)
     */
    public static Rectangle dcpAction;
    public static String dcpExampleActionFilePatch = "d:\\Pocker\\ExampleDCP\\Action\\";
    public static Rectangle dcpMoney;
    public static String dcpExampleMoneyFilePatch = "d:\\Pocker\\ExampleDCP\\Money\\";
    public static Rectangle dcpActive;
    public static String dcpExampleActiveFilePatch = "d:\\Pocker\\ExampleDCP\\Active\\";
    public static Rectangle dcpDiller;
    public static String dcpExampleDillerFilePatch = "d:\\Pocker\\ExampleDCP\\Diller\\";
    // Ставка
    public static Rectangle dcpRect;
    public static String dcpExampleRectFilePatch = "d:\\Pocker\\ExampleDCP\\Rect\\";

    /**
     * drp - down right player, игрок справа внизу
     * Action - область где отражено выполненное игроком действие(надпись)
     * Money - область где отображены средства игрока(цифра)
     * Active - область где отображено что текущий игрок активен(выделение контура)
     * Diller - областьгде отображено что текущий игрок диллер(сердечко)
     */
    public static Rectangle drpAction;
    public static String drpExampleActionFilePatch = "d:\\Pocker\\ExampleDRP\\Action\\";
    public static Rectangle drpMoney;
    public static String drpExampleMoneyFilePatch = "d:\\Pocker\\ExampleDRP\\Money\\";
    public static Rectangle drpActive;
    public static String drpExampleActiveFilePatch = "d:\\Pocker\\ExampleDRP\\Active\\";
    public static Rectangle drpDiller;
    public static String drpExampleDillerFilePatch = "d:\\Pocker\\ExampleDRP\\Diller\\";
    // Ставка
    public static Rectangle drpRect;
    public static String drpExampleRectFilePatch = "d:\\Pocker\\ExampleDRP\\Rect\\";

    /**
     * ulp - up left player, игрок слева вверху
     * Action - область где отражено выполненное игроком действие(надпись)
     * Money - область где отображены средства игрока(цифра)
     * Active - область где отображено что текущий игрок активен(выделение контура)
     * Diller - областьгде отображено что текущий игрок диллер(сердечко)
     */
    public static Rectangle ulpAction;
    public static String ulpExampleActionFilePatch = "d:\\Pocker\\ExampleULP\\Action\\";
    public static Rectangle ulpMoney;
    public static String ulpExampleMoneyFilePatch = "d:\\Pocker\\ExampleULP\\Money\\";
    public static Rectangle ulpActive;
    public static String ulpExampleActiveFilePatch = "d:\\Pocker\\ExampleULP\\Active\\";
    public static Rectangle ulpDiller;
    public static String ulpExampleDillerFilePatch = "d:\\Pocker\\ExampleULP\\Diller\\";
    // Ставка
    public static Rectangle ulpRect;
    public static String ulpExampleRectFilePatch = "d:\\Pocker\\ExampleULP\\Rect\\";

    /**
     * ucp - up center player, игрок вверху по центру
     * Action - область где отражено выполненное игроком действие(надпись)
     * Money - область где отображены средства игрока(цифра)
     * Active - область где отображено что текущий игрок активен(выделение контура)
     * Diller - областьгде отображено что текущий игрок диллер(сердечко)
     */
    public static Rectangle ucpAction;
    public static String ucpExampleActionFilePatch = "d:\\Pocker\\ExampleUCP\\Action\\";
    public static Rectangle ucpMoney;
    public static String ucpExampleMoneyFilePatch = "d:\\Pocker\\ExampleUCP\\Money\\";
    public static Rectangle ucpActive;
    public static String ucpExampleActiveFilePatch = "d:\\Pocker\\ExampleUCP\\Active\\";
    public static Rectangle ucpDiller;
    public static String ucpExampleDillerFilePatch = "d:\\Pocker\\ExampleUCP\\Diller\\";
    // Ставка
    public static Rectangle ucpRect;
    public static String ucpExampleRectFilePatch = "d:\\Pocker\\ExampleUCP\\Rect\\";

    /**
     * urp - up right player, игрок справа вверху
     * Action - область где отражено выполненное игроком действие(надпись)
     * Money - область где отображены средства игрока(цифра)
     * Active - область где отображено что текущий игрок активен(выделение контура)
     * Diller - областьгде отображено что текущий игрок диллер(сердечко)
     */
    public static Rectangle urpAction;
    public static String urpExampleActionFilePatch = "d:\\Pocker\\ExampleURP\\Action\\";
    public static Rectangle urpMoney;
    public static String urpExampleMoneyFilePatch = "d:\\Pocker\\ExampleURP\\Money\\";
    public static Rectangle urpActive;
    public static String urpExampleActiveFilePatch = "d:\\Pocker\\ExampleURP\\Active\\";
    public static Rectangle urpDiller;
    public static String urpExampleDillerFilePatch = "d:\\Pocker\\ExampleURP\\Diller\\";
    // Ставка
    public static Rectangle urpRect;
    public static String urpExampleRectFilePatch = "d:\\Pocker\\ExampleURP\\Rect\\";

    //Стол:
    public static String cartExampleFilePatch = "d:\\Pocker\\ExampleCART\\";

    /**
     * Карта на столе 0(слева направо):
     */
    public static Rectangle cart0;

    /**
     * Карта на столе 1(слева направо):
     */
    public static Rectangle cart1;

    /**
     * Карта на столе 2(слева направо):
     */
    public static Rectangle cart2;

    /**
     * Карта на столе 3(слева направо):
     */
    public static Rectangle cart3;

    /**
     * Карта на столе 4(слева направо):
     */
    public static Rectangle cart4;

    /**
     * Карта на столе 5(слева направо):
     */
    public static Rectangle cart5;

    /**
     * Деньги в банке(слева направо)
     */
    public static Rectangle bankMoney;
    public String bankMoneyExampleFilePatch = "d:\\Pocker\\BankMoney\\";

    /**
     * Метод создает примеры указанных участков в указанных папках(потом ручная правка)
     */
    public static void makeExample() throws IOException, AWTException {
        while(1==1){

            // dlp
            ImageUtil.cropAndSaveImage(dlpExampleActionFilePatch, dlpAction);
            ImageUtil.cropAndSaveImage(dlpExampleRectFilePatch, dlpRect);
            ImageUtil.cropAndSaveImage(dlpExampleActiveFilePatch, dlpActive);
            ImageUtil.cropAndSaveImage(dlpExampleDillerFilePatch, dlpDiller);
            ImageUtil.cropAndSaveImage(dlpExampleMoneyFilePatch, dlpMoney);

            // dcp
            ImageUtil.cropAndSaveImage(dcpExampleActionFilePatch, dcpAction);
            ImageUtil.cropAndSaveImage(dcpExampleRectFilePatch, dcpRect);
            ImageUtil.cropAndSaveImage(dcpExampleActiveFilePatch, dcpActive);
            ImageUtil.cropAndSaveImage(dcpExampleDillerFilePatch, dcpDiller);
            ImageUtil.cropAndSaveImage(dcpExampleMoneyFilePatch, dcpMoney);

            // drp
            ImageUtil.cropAndSaveImage(drpExampleActionFilePatch, drpAction);
            ImageUtil.cropAndSaveImage(drpExampleRectFilePatch, drpRect);
            ImageUtil.cropAndSaveImage(drpExampleActiveFilePatch, drpActive);
            ImageUtil.cropAndSaveImage(drpExampleDillerFilePatch, drpDiller);
            ImageUtil.cropAndSaveImage(drpExampleMoneyFilePatch, drpMoney);

            // ulp
            ImageUtil.cropAndSaveImage(ulpExampleActionFilePatch, ulpAction);
            ImageUtil.cropAndSaveImage(ulpExampleRectFilePatch, ulpRect);
            ImageUtil.cropAndSaveImage(ulpExampleActiveFilePatch, ulpActive);
            ImageUtil.cropAndSaveImage(ulpExampleDillerFilePatch, ulpDiller);
            ImageUtil.cropAndSaveImage(ulpExampleMoneyFilePatch, ulpMoney);

            // ucp
            ImageUtil.cropAndSaveImage(ucpExampleActionFilePatch, ucpAction);
            ImageUtil.cropAndSaveImage(ucpExampleRectFilePatch, ucpRect);
            ImageUtil.cropAndSaveImage(ucpExampleActiveFilePatch, ucpActive);
            ImageUtil.cropAndSaveImage(ucpExampleDillerFilePatch, ucpDiller);
            ImageUtil.cropAndSaveImage(ucpExampleMoneyFilePatch, ucpMoney);

            // urp
            ImageUtil.cropAndSaveImage(urpExampleActionFilePatch, urpAction);
            ImageUtil.cropAndSaveImage(urpExampleRectFilePatch, urpRect);
            ImageUtil.cropAndSaveImage(urpExampleActiveFilePatch, urpActive);
            ImageUtil.cropAndSaveImage(urpExampleDillerFilePatch, urpDiller);
            ImageUtil.cropAndSaveImage(urpExampleMoneyFilePatch, urpMoney);

            // cart
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart0);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart1);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart2);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart3);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart4);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart5);
        }
    }
}
