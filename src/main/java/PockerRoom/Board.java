package PockerRoom;

import Util.ImageUtil;
import net.sourceforge.tess4j.TesseractException;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Игровой стол.
 * Чтобы облегчить жизнь, привязываемся не к конкретной позиции а к соотношениям(высоты к ширине).
 */
public class Board {

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
     */
    public Player downLeftPlayer;

    /**
     * dcp - down center player, игрок внизу по центру
     */
     public Player downCenterPlayer;

    /**
     * drp - down right player, игрок справа внизу
     */
    public Player downRightPlayer;

    /**
     * ulp - up left player, игрок слева вверху
     */
    public Player upLeftPlayer;

    /**
     * ucp - up center player, игрок вверху по центру
     */
    public Player upCenterPlayer;

    /**
     * urp - up right player, игрок справа вверху
     */
    public Player upRightPlayer;

    /**
     * Название стола:
     */
    public Rectangle boardName;

    /**
     * Путь до деректории в которой сохраняются скрины карт для обучения сети/разпознавателя
     */
    public String cartExampleFilePatch = "d:\\Pocker\\ExampleCART\\";

    /**
     * Карта на столе 0(слева направо):
     */
    public Rectangle cart0;

    /**
     * Карта на столе 1(слева направо):
     */
    public Rectangle cart1;

    /**
     * Карта на столе 2(слева направо):
     */
    public Rectangle cart2;

    /**
     * Карта на столе 3(слева направо):
     */
    public Rectangle cart3;

    /**
     * Карта на столе 4(слева направо):
     */
    public Rectangle cart4;

    /**
     * Карта игрока 0:
     */
    public Rectangle playerCart0;

    /**
     * Карта игрока 1:
     */
    public Rectangle playerCart1;

    /**
     * Деньги в банке(слева направо)
     */
    public Rectangle bankMoney;
    public String bankMoneyExampleFilePatch = "d:\\Pocker\\BankMoney\\";

    /**
     * Номер раздачи:
     */
    public Rectangle numberPeriod;

    /**
     * Список игроков
     */
    private ArrayList<Player> players = new ArrayList<>();

    /**
     * Конструктор со считыванием первоначальной информации со стола
     */
    public Board(){
        this.downLeftPlayer = new Player();
        this.downCenterPlayer = new Player();
        this.downRightPlayer = new Player();
        this.upLeftPlayer = new Player();
        this.upCenterPlayer = new Player();
        this.upRightPlayer = new Player();

        // Настройка downLeftPlayer:
        this.downLeftPlayer.playerLoginAction = new Rectangle(56, 532,154,25);
        this.downLeftPlayer.playerMoney = new Rectangle(56, 566, 154, 25);

        // Настройка upLeftPlayer:
        this.upLeftPlayer.playerLoginAction = new Rectangle(56, 246, 154, 25);
        this.upLeftPlayer.playerMoney = new Rectangle(56, 278, 154, 25);

        // Настройка downCenterPlayer:
        this.downCenterPlayer.playerLoginAction = new Rectangle(616, 692, 154,25);
        this.downCenterPlayer.playerMoney = new Rectangle(616, 726,154, 25);

        // Настройка upCenterPlayer:
        this.upCenterPlayer.playerLoginAction = new Rectangle(552, 136,154, 25);
        this.upCenterPlayer.playerMoney = new Rectangle(552, 170, 154,25);

        // Настройка downRightPlayer:
        this.downRightPlayer.playerLoginAction = new Rectangle(1112, 534,154, 25);
        this.downRightPlayer.playerMoney = new Rectangle(1112, 564, 154,25);

        // Настройка upRightCenter:
        this.upRightPlayer.playerLoginAction = new Rectangle(1112, 244,154, 25);
        this.upRightPlayer.playerMoney = new Rectangle(1112, 280,154, 25);

        // Наполняем игроков в список:
        this.players = new ArrayList<>();
        this.players.add(this.downLeftPlayer);
        this.players.add(this.downCenterPlayer);
        this.players.add(this.downRightPlayer);
        this.players.add(this.upLeftPlayer);
        this.players.add(this.upCenterPlayer);
        this.players.add(this.upRightPlayer);

        // Настраиваем карты:
        this.cart0 = new Rectangle(441, 355, 22,24);
        this.cart1 = new Rectangle(532, 355, 22,24);
        this.cart2 = new Rectangle(622, 355, 22,24);
        this.cart3 = new Rectangle(714, 355, 22,24);
        this.cart4 = new Rectangle(804, 355, 22,24);

        // Деньги в банке:
        this.bankMoney = new Rectangle(656,307,158,26);

        // Название стола:
        this.boardName = new Rectangle(28, 8, 435,20);

        // Номер раздачи:
        this.numberPeriod = new Rectangle(124,75,156,14);

        // Карты игрока:
        this.playerCart0 = new Rectangle(588,632, 22,28);
        this.playerCart1 = new Rectangle(671, 632,22,28);
    }

    /**
     * Метод создает примеры указанных участков в указанных папках(потом ручная правка)
     */
    public static void makeExampleFolder(Board board) throws IOException, AWTException {
        while(1==1){

            /**
            for(Player player : board.players){
                // Скриншот поля логина и действия
                ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), player.playerLoginAction);
                // Cкриншот денег
                ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), player.playerMoney);
            }
            */
            //ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), board.numberPeriod);
            //ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), board.bankMoney);
            //ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), board.boardName);
            //ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), board.cart0);
            //ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), board.cart1);
            //ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), board.cart2);
            //ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), board.cart3);
            //ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), board.playerCart0);
            //ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), board.playerCart1);
        }
    }

    /**
     * Метод выводит в консоль все данные которые удалось получить со стола
     */
    public static void debugBoardLogInfo(Board board) throws AWTException, TesseractException {

        /**
        // Информация по игрокам на столе:
        for(Player player : board.players) {
            System.out.println();
            System.out.print("Action/Login:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), player.playerLoginAction))));
            System.out.print("Money:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), player.playerMoney))));
            System.out.println();
        }
         */
        //System.out.print("Cart0:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.cart0))));
        //System.out.print("Cart1:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.cart1))));
        //System.out.print("Cart2:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.cart2))));
        //System.out.print("Cart3:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.cart3))));
        //System.out.print("Cart4:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.cart4))));

        //System.out.print("BankMoney:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.bankMoney))));
        //System.out.print("BoardName:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.boardName))));
        //System.out.print("NumberPeriod:" + ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.numberPeriod)));

        //System.out.print("PlayerCart0:" + ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.playerCart0)));
        //System.out.print("PlayerCart1:" + ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), board.playerCart1)));
    }
}
