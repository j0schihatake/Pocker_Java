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
    public Rectangle cart0 = new Rectangle(780,410, 28,25);

    /**
     * Карта на столе 1(слева направо):
     */
    public Rectangle cart1 = new Rectangle(876,410, 28,25);

    /**
     * Карта на столе 2(слева направо):
     */
    public Rectangle cart2 = new Rectangle(944,410, 28,25);

    /**
     * Карта на столе 3(слева направо):
     */
    public Rectangle cart3 = new Rectangle(1030,410, 28,25);

    /**
     * Карта на столе 4(слева направо):
     */
    public Rectangle cart4 = new Rectangle(1098,410, 28,25);

    /**
     * Карта на столе 5(слева направо):
     */
    public Rectangle cart5 = new Rectangle(1166,410, 28,25);

    /**
     * Деньги в банке(слева направо)
     */
    public Rectangle bankMoney  = new Rectangle(1004,352, 198,33);
    public String bankMoneyExampleFilePatch = "d:\\Pocker\\BankMoney\\";

    public Rectangle numberPeriod = new Rectangle(124,74,108,15);

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
    }

    /**
     * Метод создает примеры указанных участков в указанных папках(потом ручная правка)
     */
    public static void makeExampleFolder(Board board) throws IOException, AWTException {
        while(1==1){
            for(Player player : board.players){
                // Скриншот поля логина и действия
                ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), player.playerLoginAction);
                // Cкриншот денег
                ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), player.playerMoney);
            }
        }
    }

    /**
     * Метод выводит в консоль все данные которые удалось получить со стола
     */
    public static void debugBoardLogInfo(Board board) throws AWTException, TesseractException {

        // Информация по игрокам на столе:
        for(Player player : board.players) {
            System.out.println();
            System.out.print("Action/Login:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), player.playerLoginAction))));
            System.out.print("Money:" + ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), player.playerMoney))));
            System.out.println();
        }

    }
}
