package PockerRoom;

import Util.ImageUtil;
import net.sourceforge.tess4j.TesseractException;

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
    public static Rectangle bankMoney  = new Rectangle(1004,352, 198,33);
    public String bankMoneyExampleFilePatch = "d:\\Pocker\\BankMoney\\";

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

        // Наполняем игроков в список:
        ArrayList<Player> players = new ArrayList<>();
        players.add(downLeftPlayer);
        players.add(downCenterPlayer);
        players.add(downRightPlayer);
        players.add(upLeftPlayer);
        players.add(upCenterPlayer);
        players.add(upRightPlayer);
    }

    /**
     * Метод создает примеры указанных участков в указанных папках(потом ручная правка)
     */
    public static void makeExampleFolder(Board board) throws IOException, AWTException {
        while(1==1){
            for(Player player : board.players){
                // Скриншот поля логина и действия
                ImageUtil.cropAndSaveImage(player.playerExampleActionFilePatch, player.playerLoginAction);
                // Скриншот ставки
                ImageUtil.cropAndSaveImage(player.playerExampleBetFilePatch, player.playerBet);
                // Cкриншот денег
                ImageUtil.cropAndSaveImage(player.playerExampleMoneyFilePatch, player.playerMoney);
            }
        }
    }

    /**
     * Метод выводит в консоль все данные которые удалось получить со стола
     */
    public static void debugLogInfo(Board board) throws AWTException, TesseractException {

        // Получаем скрин всего эерана:
        Robot robot = new Robot();
        BufferedImage fullScreen = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        // Информация по игрокам на столе:
        for(Player player : board.players) {
            System.out.println();
            System.out.print("downLeftPlayer Action/Login:" + ImageUtil.recognition(ImageUtil.cropImage(fullScreen, player.playerLoginAction)));
            System.out.println();
            System.out.print("downLeftPlayer Money:" + ImageUtil.recognition(ImageUtil.cropImage(fullScreen, player.playerMoney)));
            System.out.println();
        }

    }
}
