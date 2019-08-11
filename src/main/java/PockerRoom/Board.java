package PockerRoom;

import Util.ImageUtil;
import net.sourceforge.tess4j.TesseractException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Игровой стол.
 * Чтобы облегчить жизнь, привязываемся не к конкретной позиции а к соотношениям(высоты к ширине).
 */
public class Board {

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
    public Rectangle boardNameRectangle;
    public String boardNameExampleFilePatch = "d:\\Pocker\\BoardNameExample\\";
    public String boardName = "";

    /**
     * Путь до деректории в которой сохраняются скрины карт для обучения сети/разпознавателя
     */
    public String cartExampleFilePatch = "d:\\Pocker\\ExampleCART\\";

    /**
     * Карта на столе 0(слева направо):
     */
    public Rectangle cart0Rectangle;
    public String cart0 = "";

    /**
     * Карта на столе 1(слева направо):
     */
    public Rectangle cart1Rectangle;
    public String cart1 = "";

    /**
     * Карта на столе 2(слева направо):
     */
    public Rectangle cart2Rectangle;
    public String cart2 = "";

    /**
     * Карта на столе 3(слева направо):
     */
    public Rectangle cart3Rectangle;
    public String cart3 = "";

    /**
     * Карта на столе 4(слева направо):
     */
    public Rectangle cart4Rectangle;
    public String cart4 = "";

    /**
     * Карта игрока 0:
     */
    public Rectangle playerCart0Rectangle;
    public String playerCart0 = "";

    /**
     * Карта игрока 1:
     */
    public Rectangle playerCart1Rectangle;
    public String playerCart1 = "";

    /**
     * Деньги в банке(слева направо)
     */
    public Rectangle bankMoneyRectangle;
    public String bankMoneyExampleFilePatch = "d:\\Pocker\\BankMoney\\";
    public String bankMoney = "";

    /**
     * Номер раздачи:
     */
    public Rectangle numberPeriodRectangle;
    public String numberPeriodExampleFilePatch = "d:\\Pocker\\NumberPeriod\\";
    public String partyNumber = "";

    /**
     * Список игроков
     */
    private ArrayList<Player> players = new ArrayList<>();

    /**
     * Конструктор со считыванием первоначальной информации со стола
     */
    public Board() throws TesseractException, AWTException {

        // Настраиваем карты:
        this.cart0Rectangle = new Rectangle(441, 355, 22,24);
        this.cart1Rectangle = new Rectangle(532, 355, 22,24);
        this.cart2Rectangle = new Rectangle(622, 355, 22,24);
        this.cart3Rectangle = new Rectangle(714, 355, 22,24);
        this.cart4Rectangle = new Rectangle(804, 355, 22,24);

        // Деньги в банке:
        this.bankMoneyRectangle = new Rectangle(656,307,158,26);

        // Название стола:
        this.boardNameRectangle = new Rectangle(28, 8, 435,20);

        // Номер раздачи:
        this.numberPeriodRectangle = new Rectangle(124,75,156,14);

        // Карты игрока:
        this.playerCart0Rectangle = new Rectangle(588,632, 22,28);
        this.playerCart1Rectangle = new Rectangle(671, 632,22,28);

        // Обновляем всю информацию остоле:
        updateBoardInfo();
    }

    /**
     * Метод выполняет обновление информации о столе
     * @return
     * @throws TesseractException
     * @throws AWTException
     */
    public Board updateBoardInfo() throws TesseractException, AWTException {
        updateAllPlayers();
        updateAllCart();
        getBoardName();
        getPartyNumber();
        return this;
    }

    /**
     * Метод возвращает строчное представление суммы денек в банке на текущем столе
     * @return String - bankMoney
     * @throws AWTException
     * @throws TesseractException
     */
    public String getBankMoney() throws AWTException, TesseractException {
        return bankMoney = ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), bankMoneyRectangle)));
    }

    /**
     * Метод возвращает насзвание текущего стола:
     * @return String - boardName
     */
    public String getBoardName() throws AWTException, TesseractException {
        return boardName = ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), boardNameRectangle)));
    }

    /**
     * Метод возвращает номер текущей раздачи с строковом представлении:
     * @return String - partyNumber
     * @throws AWTException
     * @throws TesseractException
     */
    public String getPartyNumber() throws AWTException, TesseractException {
        return partyNumber = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), numberPeriodRectangle));
    }

    /**
     * Метод возвращает значение карты cart0
     * @return
     * @throws AWTException
     * @throws TesseractException
     */
    public String getCart0() throws AWTException, TesseractException {
        return cart0 = ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart0Rectangle)));
    }

    public String getCart1() throws AWTException, TesseractException {
        return cart1 = ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart1Rectangle)));
    }

    public String getCart2() throws AWTException, TesseractException {
        return cart2 = ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart2Rectangle)));
    }

    public String getCart3() throws AWTException, TesseractException {
        return cart3 = ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart3Rectangle)));
    }

    public String getCart4() throws AWTException, TesseractException {
        return cart4 = ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart4Rectangle)));
    }

    public String getPlayerCart0() throws AWTException, TesseractException {
        return playerCart0 = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), playerCart0Rectangle));
    }

    public String getPlayerCart1() throws AWTException, TesseractException {
        return playerCart1 =  ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), playerCart1Rectangle));
    }

    /**
     * Метод выполняет обновление всех известных нас толе карт включая карты игрока.
     * @throws AWTException
     * @throws TesseractException
     */
    public void updateAllCart() throws AWTException, TesseractException {
        getCart0();
        getCart1();
        getCart2();
        getCart3();
        getCart4();

        getPlayerCart0();
        getPlayerCart1();
    }

    /**
     * Метод возвращает игрока который является диллером:
     * @return
     * @throws AWTException
     */
    public Player getDillerBoard() throws AWTException {
        Player result = null;
        for(Player player : players){
            if(player.isDiller()){
                result = player;
            }
        }
        return result;
    }

    /**
     * Метод обновляет роли игроков(по часовой стрелке от диллера)
     *      * Роль участника в игре:
     *      *      * 0 - диллер
     *      *      * 1 - MB
     *      *      * 2 - BB
     *      *      * 3 - n...
     * @throws AWTException
     */
    public void updatePlayerRole() throws AWTException {
        Player diller = getDillerBoard();

        if(diller == downLeftPlayer){
            downLeftPlayer.role = 0;
            upLeftPlayer.role = 1;
            upCenterPlayer.role = 2;
            upRightPlayer.role = 3;
            downRightPlayer.role = 4;
            downCenterPlayer.role = 5;
        }

        if(diller == upLeftPlayer){
            upLeftPlayer.role = 0;
            upCenterPlayer.role = 1;
            upRightPlayer.role = 2;
            downRightPlayer.role = 3;
            downCenterPlayer.role = 4;
            downLeftPlayer.role = 5;
        }

        if(diller == upCenterPlayer){
            upCenterPlayer.role = 0;
            upRightPlayer.role = 1;
            downRightPlayer.role = 2;
            downCenterPlayer.role = 3;
            downLeftPlayer.role = 4;
            upLeftPlayer.role = 5;
        }

        if(diller == upRightPlayer){
            upRightPlayer.role = 0;
            downRightPlayer.role = 1;
            downCenterPlayer.role = 2;
            downLeftPlayer.role = 3;
            upLeftPlayer.role = 4;
            upCenterPlayer.role = 5;
        }

        if(diller == downRightPlayer){
            downRightPlayer.role = 0;
            downCenterPlayer.role = 1;
            downLeftPlayer.role = 2;
            upLeftPlayer.role = 3;
            upCenterPlayer.role = 4;
            upRightPlayer.role = 5;
        }

        if(diller == downCenterPlayer){
            downCenterPlayer.role = 0;
            downLeftPlayer.role = 1;
            upLeftPlayer.role = 2;
            upCenterPlayer.role = 3;
            upRightPlayer.role = 4;
            downRightPlayer.role = 5;
        }


    }

    /**
     * Метод возвращает игрока который является MB:
     * @return
     */
    public Player getMBBoard() throws AWTException {
        updatePlayerRole();
        Player result = null;
        for(Player player : players){
            if(player.isMB()){
                result = player;
            }
        }
        return result;
    }

    /**
     * Метод возвращает игрока который является BB:
     * @return
     */
    public Player getBBBoard() throws AWTException {
        updatePlayerRole();
        Player result = null;
        for(Player player : players){
            if(player.isBB()){
                result = player;
            }
        }
        return result;
    }

    /**
     * Метод возвращает текущую роль нейронной сети в раздаче
     * @return
     */
    public int getPlayerRole() throws AWTException {
        updatePlayerRole();
        return downCenterPlayer.role;
    }

    /**
     * Получаем значения игроков со стола и сетим их игрокам:
     */
    public void updateAllPlayers() throws AWTException, TesseractException {

        downLeftPlayer = new Player();
        downCenterPlayer = new Player();
        downRightPlayer = new Player();
        upLeftPlayer = new Player();
        upCenterPlayer = new Player();
        upRightPlayer = new Player();

        // Настройка downLeftPlayer:
        downLeftPlayer.playerLoginActionRectangle = new Rectangle(56, 532,154,25);
        downLeftPlayer.playerMoneyRectangle = new Rectangle(56, 566, 154, 25);

        // Настройка upLeftPlayer:
        upLeftPlayer.playerLoginActionRectangle = new Rectangle(56, 246, 154, 25);
        upLeftPlayer.playerMoneyRectangle = new Rectangle(56, 278, 154, 25);

        // Настройка downCenterPlayer:
        downCenterPlayer.playerLoginActionRectangle = new Rectangle(616, 692, 154,25);
        downCenterPlayer.playerMoneyRectangle = new Rectangle(616, 726,154, 25);

        // Настройка upCenterPlayer:
        upCenterPlayer.playerLoginActionRectangle = new Rectangle(552, 136,154, 25);
        upCenterPlayer.playerMoneyRectangle = new Rectangle(552, 170, 154,25);

        // Настройка downRightPlayer:
        downRightPlayer.playerLoginActionRectangle = new Rectangle(1112, 534,154, 25);
        downRightPlayer.playerMoneyRectangle = new Rectangle(1112, 564, 154,25);

        // Настройка upRightCenter:
        upRightPlayer.playerLoginActionRectangle = new Rectangle(1112, 244,154, 25);
        upRightPlayer.playerMoneyRectangle = new Rectangle(1112, 280,154, 25);

        // Настройка координат диллер не диллер( 2 координаты искомого белого пикселя в центре сердечка):
        downLeftPlayer.pDillerX = 340;
        downLeftPlayer.pDillerY = 547;

        downCenterPlayer.pDillerX = 774;
        downCenterPlayer.pDillerY = 607;

        downRightPlayer.pDillerX = 984;
        downRightPlayer.pDillerY = 547;

        upLeftPlayer.pDillerX = 298;
        upLeftPlayer.pDillerY = 328;

        upCenterPlayer.pDillerX = 564;
        upCenterPlayer.pDillerY = 240;

        upRightPlayer.pDillerX = 1040;
        upRightPlayer.pDillerY = 336;

        // Наполняем игроков в список:
        players = new ArrayList<>();
        players.add(downLeftPlayer);
        players.add(downCenterPlayer);
        players.add(downRightPlayer);
        players.add(upLeftPlayer);
        players.add(upCenterPlayer);
        players.add(upRightPlayer);

        // Получаем имена и денежные суммы игроков со стола:
        for(Player player : players){
            player.getPlayerLogin();
            player.getPlayerMoney();
            player.isPlayerInGame();
        }

        updatePlayerRole();
    }

    /**
     * Метод создает примеры указанных участков в указанных папках(потом ручная правка)
     */
    public void makeExampleFolder() throws IOException, AWTException {
        while(1==1){

            for(Player player : players){
                // Скриншот поля логина и действия
                ImageUtil.cropAndSaveImage(player.playerExampleLoginActionFilePatch, player.playerLoginActionRectangle);
                // Cкриншот денег
                ImageUtil.cropAndSaveImage(player.playerExampleMoneyFilePatch, player.playerMoneyRectangle);
            }
            ImageUtil.cropAndSaveImage(numberPeriodExampleFilePatch, numberPeriodRectangle);
            ImageUtil.cropAndSaveImage(bankMoneyExampleFilePatch, bankMoneyRectangle);
            ImageUtil.cropAndSaveImage(boardNameExampleFilePatch, boardNameRectangle);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart0Rectangle);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart1Rectangle);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart2Rectangle);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, cart3Rectangle);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, playerCart0Rectangle);
            ImageUtil.cropAndSaveImage(cartExampleFilePatch, playerCart1Rectangle);
        }
    }

    /**
     * Метод выводит в консоль все данные которые удалось получить со стола
     */
    public void debugBoardLogInfo() throws AWTException, TesseractException {

         // Информация по игрокам на столе:
         for(Player player : players) {
            System.out.println();
            System.out.print("Action/Login:" + player.getPlayerLogin());
            System.out.print("Money:" + player.getPlayerMoney());
            System.out.println();
         }

        System.out.print("Cart0:" + getCart0());
        System.out.print("Cart1:" + getCart2());
        System.out.print("Cart2:" + getCart3());
        System.out.print("Cart3:" + getCart3());
        System.out.print("Cart4:" + getCart4());

        System.out.print("BankMoney:" + getBankMoney());
        System.out.print("BoardName:" + getBoardName());
        System.out.print("NumberPeriod:" + getPartyNumber());

        System.out.print("PlayerCart0:" + getPlayerCart0());
        System.out.print("PlayerCart1:" + getPlayerCart1());

        Player diller = getDillerBoard();
        if (diller != null) {
            System.out.print("Diller: " + diller.playerLogin);
            System.out.println();
        }
    }
}
