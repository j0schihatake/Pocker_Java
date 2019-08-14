package PockerRoom;

import Util.ImageUtil;
import Util.SQLiteUtil;
import Util.StringUtil;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Игровой стол.
 * Чтобы облегчить жизнь, привязываемся не к конкретной позиции а к соотношениям(высоты к ширине).
 */
public class Board {

    public static String login = "Hatake";
    public static int maxBoardCount = 3;

    //---------------------------------------------Области наблюдения--------------------------------------------

    /**
     * Цвет масти черви
     */
    public String chervMastCollor = "-3272688";

    /**
     * Цвет масти буби
     */
    public String bubiMastCollor = "-15918685";

    /**
     * Цвет масти крести
     */
    public String krestiMastCollor = "-16747244";

    /**
     * Цвет масти пики
     */
    public String piciMastCollor = "-16777216";

    /**
     * Цвет при наличии карты(белый)
     */
    public String cartEnabledCollor = "-1";

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
     * Максимальный стек
     */
    public String maxStek;

    /**
     * Число игроков на этапе
     */
    public int playersCount;

    /**
     * Позиция на которой игрок начинал игру
     */
    public int startPlayerPosition;

    /**
     * Позиция на которой игрок оказался в этой игре
     */
    public int stagePlayerPpsition;

    /**
     * Размер ставки перед игроком:
     */
    public int betPrePlayer;

    /**
     * Число игроков которые будут ходить после игрока:
     */
    public int postPlayerCount;

    /**
     * Сильнейшая комбинация карт у игрока со столом:
     */
    public int combo;

    /**
     * комбинация действий игрока приведшая к текущему результату:
     */
    public int comboAction;

    /**
     *  рассчитанная эффективность применения (опыт)
     */
    public int exp;

    /**
     * Результат текущей раздачи:
     */
    public int result;

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
     * Этапы игры:
     * 0 торговля
     * 1 второй этап
     * 2 терн
     * 3 ривер
     */
    public int stage = 0;

    /**
     * Карта на столе 0(слева направо):
     */
    public Rectangle cart0Rectangle;
    public String cart0 = "";
    public int cart0Mast = 4;
    public int cart0MastX;
    public int cart0MastY;
    public Boolean cart0Active = false;
    public int cart0ActiveX;
    public int cart0ActiveY;

    /**
     * Карта на столе 1(слева направо):
     */
    public Rectangle cart1Rectangle;
    public String cart1 = "";
    public int cart1Mast = 4;
    public int cart1MastX;
    public int cart1MastY;
    public Boolean cart1Active = false;
    public int cart1ActiveX;
    public int cart1ActiveY;

    /**
     * Карта на столе 2(слева направо):
     */
    public Rectangle cart2Rectangle;
    public String cart2 = "";
    public int cart2Mast = 4;
    public int cart2MastX;
    public int cart2MastY;
    public Boolean cart2Active = false;
    public int cart2ActiveX;
    public int cart2ActiveY;

    /**
     * Карта на столе 3(слева направо):
     */
    public Rectangle cart3Rectangle;
    public String cart3 = "";
    public int cart3Mast = 4;
    public int cart3MastX;
    public int cart3MastY;
    public Boolean cart3Active = false;
    public int cart3ActiveX;
    public int cart3ActiveY;

    /**
     * Карта на столе 4(слева направо):
     */
    public Rectangle cart4Rectangle;
    public String cart4 = "";
    public int cart4Mast = 4;
    public int cart4MastX;
    public int cart4MastY;
    public Boolean cart4Active = false;
    public int cart4ActiveX;
    public int cart4ActiveY;

    /**
     * Карта игрока 0:
     */
    public Rectangle playerCart0Rectangle;
    public String playerCart0 = "";
    public int playerCart0Mast = 4;
    public int playerCart0MastX;
    public int playerCart0MastY;

    /**
     * Карта игрока 1:
     */
    public Rectangle playerCart1Rectangle;
    public String playerCart1 = "";
    public int playerCart1Mast = 4;
    public int playerCart1MastX;
    public int playerCart1MastY;

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

        // Настраиваем координаты центров для определения масти карт на столе:
        this.cart0MastX = 451;
        this.cart0MastY = 392;
        this.cart1MastX = 542;
        this.cart1MastY = 391;
        this.cart2MastX = 633;
        this.cart2MastY = 392;
        this.cart3MastX = 724;
        this.cart3MastY = 391;
        this.cart4MastX = 815;
        this.cart4MastY = 391;
        this.playerCart0MastX = 592;
        this.playerCart0MastY = 657;
        this.playerCart1MastX = 674;
        this.playerCart1MastY = 657;

        // Настройка координат пикселей для определения присутствия карт на столе:
        this.cart0ActiveX = 499;
        this.cart0ActiveY = 349;
        this.cart1ActiveX = 590;
        this.cart1ActiveY = 349;
        this.cart2ActiveX = 680;
        this.cart2ActiveY = 349;
        this.cart3ActiveX = 771;
        this.cart3ActiveY = 349;
        this.cart4ActiveX = 864;
        this.cart4ActiveY = 349;

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
        getBankMoney();
        return this;
    }

    /**
     * Возвращает текущий этап игры:
     *      * Этапы игры:
     *      * 0 Префлоп/торговля
     *      * 1 Флоп
     *      * 2 Терн
     *      * 3 Ривер
     * @return
     */
    public int getGameStage() throws AWTException {
        int result = 0;

        if(isActiveCart0()){
            result = 1;
            if(isActiveCart3()){
                result = 2;
                if(isActiveCart4()){
                    result = 3;
                }
            }
        }
        return stage = result;
    }

    /**
     * Возвращает описание текущего этапа игры:
     * @param stage
     * @return
     */
    public String getStageDescription(int stage){
        String result = "";
        switch(stage){
            case 0:
                result = "Префлоп/торговля";
                break;
            case 1:
                result = "Флоп";
                break;
            case 2:
                result = "Терн";
                break;
            case 3:
                result = "Ривер";
                break;
        }
        return result;
    }

    /**
     * Метод возвращает строчное представление суммы денек в банке на текущем столе
     * @return String - bankMoney
     * @throws AWTException
     * @throws TesseractException
     */
    public String getBankMoney() throws AWTException, TesseractException {
        return bankMoney = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), bankMoneyRectangle));
    }

    /**
     * Метод возвращает насзвание текущего стола:
     * @return String - boardName
     */
    public String getBoardName() throws AWTException, TesseractException {
        return boardName = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), boardNameRectangle));
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
        return cart0 = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart0Rectangle));
    }

    public String getCart1() throws AWTException, TesseractException {
        return cart1 = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart1Rectangle));
    }

    public String getCart2() throws AWTException, TesseractException {
        return cart2 = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart2Rectangle));
    }

    public String getCart3() throws AWTException, TesseractException {
        return cart3 = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart3Rectangle));
    }

    public String getCart4() throws AWTException, TesseractException {
        return cart4 = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), cart4Rectangle));
    }

    public String getPlayerCart0() throws AWTException, TesseractException {
        return playerCart0 = ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), playerCart0Rectangle));
    }

    public String getPlayerCart1() throws AWTException, TesseractException {
        return playerCart1 =  ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), playerCart1Rectangle));
    }

    /**
     * Возвращает масть карты 0(левая на столе)
     * @return
     * @throws AWTException
     */
    public int getCart0Mast() throws AWTException {
        return cart0Mast = getMast(cart0MastX, cart0MastY);
    }

    /**
     * Возвращает масть карты 1
     * @return
     * 3
     * @throws AWTException
     */
    public int getCart1Mast() throws AWTException {
        return cart1Mast = getMast(cart1MastX, cart1MastY);
    }

    /**
     * Возвращает масть карты 2
     * @return
     * @throws AWTException
     */
    public int getCart2Mast() throws AWTException {
        return cart2Mast = getMast(cart2MastX, cart2MastY);
    }

    /**
     * Возвращает масть карты 3
     * @return
     */
    public int getCart3Mast() throws AWTException {
        return cart3Mast = getMast(cart3MastX, cart3MastY);
    }

    /**
     * Возвращает масть карты 4
     * @return
     */
    public int getCart4Mast() throws AWTException {
        return cart4Mast = getMast(cart4MastX, cart4MastY);
    }

    /**
     * Возвращает масть левой карты игрока
     * @return
     * @throws AWTException
     */
    public int getPlayerCart0Mast() throws AWTException {
        return playerCart0Mast = getMast(playerCart0MastX, playerCart0MastY);
    }

    /**
     * Возвращает масть правой карты игрока
     * @return
     * @throws AWTException
     */
    public int getPlayerCart1Mast() throws AWTException {
        return playerCart1Mast = getMast(playerCart1MastX, playerCart1MastY);
    }

    /**
     * Возвращает присутствие карты 0 на столе:
     * @return
     * @throws AWTException
     */
    public boolean isActiveCart0() throws AWTException {
        return cart0Active = isActiveCart(cart0ActiveX, cart0ActiveY);
    }

    /**
     * Возвращает присутствие карты 1 на столе:
     * @return
     * @throws AWTException
     */
    public boolean isActiveCart1() throws AWTException {
        return cart1Active = isActiveCart(cart1ActiveX, cart1ActiveY);
    }

    /**
     * Возвращает присутствие карты 2 на столе:
     * @return
     * @throws AWTException
     */
    public boolean isActiveCart2() throws AWTException {
        return cart2Active = isActiveCart(cart2ActiveX, cart2ActiveY);
    }

    /**
     * Возвращает присутствие карты 3 на столе:
     * @return
     * @throws AWTException
     */
    public boolean isActiveCart3() throws AWTException {
        return cart3Active = isActiveCart(cart3ActiveX, cart3ActiveY);
    }

    /**
     * Возвращает присутствие карты 4 на столе:
     * @return
     * @throws AWTException
     */
    public boolean isActiveCart4() throws AWTException {
        return cart4Active = isActiveCart(cart4ActiveX, cart4ActiveY);
    }

    /**
     * Метод возвражает описание масти:
     * @param mast
     * @return
     */
    public String getMastDescription(int mast) {
        String result = "";
        switch(mast){
            case 0:
                result = "пики";
                break;
            case 1:
                result = "крести";
                break;
            case 2:
                result = "черви";
                break;
            case 3:
                result = "бубны";
                break;
        }
        return result;
    }

    /**
     * Метод возвращает масть пикселя:
     * Масть карты определяет ее итоговое значение value следующим образом(для удобства работы с сетью) mast * 100 + value:
     *  * mast: spades-пики = 0, clubs-трефы = 1, hearts-черви = 2, diamonds-бубны = 3
     * @return
     */
    public int getMast(int x, int y) throws AWTException {
        int mast = 4;

        String pixel = String.valueOf(ImageUtil.getCollor(ImageUtil.getStarWindow(), x, y));

        if(pixel.equals(piciMastCollor)){
            mast = 0;
        }
        if(pixel.equals(krestiMastCollor)){
            mast = 1;
        }
        if(pixel.equals(chervMastCollor)){
            mast = 2;
        }
        if(pixel.equals(bubiMastCollor)){
            mast = 3;
        }

        return mast;
    }

    /**
     * Возвращает активность карты:
     * @throws AWTException
     */
    public Boolean isActiveCart(int x, int y) throws AWTException {
        String pixel = String.valueOf(ImageUtil.getCollor(ImageUtil.getStarWindow(), x, y));
        return  pixel.equals(cartEnabledCollor);
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

        getCart0Mast();
        getCart1Mast();
        getCart2Mast();
        getCart3Mast();
        getCart4Mast();

        getPlayerCart0Mast();
        getPlayerCart1Mast();
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
        downCenterPlayer.playerLoginActionRectangle = new Rectangle(616, 692, 157,27);
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

        // Настройка координат для проверки в игре игрок или нет:
        downLeftPlayer.pInGameX = 135;
        downLeftPlayer.pInGameY = 480;
        downLeftPlayer.playerInGame = "-1644826";

        downCenterPlayer.pInGameX = 610;
        downCenterPlayer.pInGameY = 640;
        downCenterPlayer.playerInGame = "-1644826";

        downRightPlayer.pInGameX = 1115;
        downRightPlayer.pInGameY = 480;
        downRightPlayer.playerInGame = "-1644826";

        upLeftPlayer.pInGameX = 165;
        upLeftPlayer.pInGameY = 214;
        upLeftPlayer.playerInGame = "-1644826";

        upCenterPlayer.pInGameX = 615;
        upCenterPlayer.pInGameY = 83;
        upCenterPlayer.playerInGame = "-1644826";

        upRightPlayer.pInGameX = 1110;
        upRightPlayer.pInGameY = 193;
        upRightPlayer.playerInGame = "-1644826";

        // Настройка координат для проверки активен ли игрок или нет:
        downLeftPlayer.pActiveX = 34;
        downLeftPlayer.pActiveY = 561;
        downLeftPlayer.playerActive = "-7434610";

        downCenterPlayer.pActiveX = 529;
        downCenterPlayer.pActiveY = 722;
        downCenterPlayer.playerActive = "-6052957";

        downRightPlayer.pActiveX = 1027;
        downRightPlayer.pActiveY = 562;
        downRightPlayer.playerActive = "-5460820";

        upLeftPlayer.pActiveX = 35;
        upLeftPlayer.pActiveY = 275;
        upLeftPlayer.playerActive = "-6381922";

        upCenterPlayer.pActiveX = 529;
        upCenterPlayer.pActiveY = 165;
        upCenterPlayer.playerActive = "-6052957";

        upRightPlayer.pActiveX = 1027;
        upRightPlayer.pActiveY = 274;
        upRightPlayer.playerActive = "-6052957";

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
            //System.out.println();
            //System.out.print("Action/Login:" + player.getPlayerLogin());
            //System.out.print("Money:" + player.getPlayerMoney());
            //System.out.print(player.playerLogin + " InGame:" + player.isPlayerInGame());
            //System.out.println();
         }

         /*
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
          */
    }

    /**
     * Метод выполняет запись прочитанных данных в таблицу boardX где X текущая версия набора данных
     */
    public void saveBoard() throws TesseractException, AWTException {

        String sqliteCommand = "INSERT INTO 'board" + SQLiteUtil.bdVersion + "' "
                + "('partyNumber', "                                // номер раздачи
                + "'boardName', "                                   // название стола
                + "'maxStek', "                                     // максимум стека для определения ставок и действий
                + "'playersCount', "                                // число игроков
                + "'bankMoney', "                                   // сумма денег в банке
                + "'stage', "                                       // текущий игровой этап
                + "'cart0', "                                       // самая первая карта слева на столе (конвертированное значение карты в int)
                + "'cart1', "
                + "'cart2', "
                + "'cart3', "
                + "'cart4', "
                + "'playerCart0', "
                + "'playerCart1', "
                + "'startPlayerPosition', "                         // позиция на которой начинал игрок
                + "'stagePlayerPosition', "                         // позиция на которой игрок на этом этапе

                + "'downLeftPlayerLogin', "
                + "'downLeftPlayerMoney', "
                + "'downLeftPlayerRole', "
                + "'downLeftPlayerAction', "

                + "'upLeftPlayerLogin', "
                + "'upLeftPlayerMoney', "
                + "'upLeftPlayerRole', "
                + "'upLeftPlayerAction', "

                + "'upCenterPlayerLogin', "
                + "'upCenterPlayerMoney', "
                + "'upCenterPlayerRole', "
                + "'upCenterPlayerAction', "

                + "'upRightPlayerLogin', "
                + "'upRightPlayerMoney', "
                + "'upRightPlayerRole', "
                + "'upRightPlayerAction', "

                + "'downRightPlayerLogin', "
                + "'downRightPlayerMoney', "
                + "'downRightPlayerRole', "
                + "'downRightPlayerAction', "

                + "'downCenterPlayerLogin', "
                + "'downCenterPlayerMoney', "
                + "'downCenterPlayerRole', "
                + "'downCenterPlayerAction', "

                + "'betPrePlayer', "                                    // величина ставки перед ходом игрока
                + "'postPlayerCount', "                                 // число игроков после игрока
                + "'combo', "                                           // номер самой крупной комбинации которая сложилась у игрока
                + "'comboAction', "
                + "'result', "                                          // результат игры
                + "'exp', "                                             // эффективность данного примера
                + ") VALUES ('"
                + partyNumber
                + "', "
                + boardName
                + "', "
                + maxStek
                + "', "
                + playersCount
                + "', "
                + bankMoney
                + "', "
                + stage
                + "', "
                + cart0
                + "', "
                + cart1
                + "', "
                + cart2
                + "', "
                + cart3
                + "', "
                + cart4
                + "', "
                + playerCart0
                + "', "
                + playerCart1
                + "', ";
                for(Player p : players){
                    sqliteCommand = sqliteCommand
                            + "'" + p.playerLogin + "', "
                            + "'" + p.pMoney + "', "
                            + "'" + p.role + "', "
                            + "'" + p.playerAction + "', ";
                }
                sqliteCommand = sqliteCommand
                        + betPrePlayer
                        + "', "
                        + postPlayerCount
                        + "', "
                        + combo
                        + "', "
                        + comboAction
                        + "', "
                        + result
                        + "', "
                        + exp
                        + "'); ";
    }

    /**
     * Метод возвращает число игроков после указанного игрока на данном этапе игры
     * @param playerRole
     * @return
     */
    public int getPostPlayerCount(int playerRole){
        int result = 0;
        for(Player player : players){
            if(player.role > playerRole){
                result += 1;
            }
        }
        return result;
    }

    /**
     * Метод возвращает число игроков до указанного игрока на данном этапе игры
     * @param playerRole
     * @return
     */
    public int getPrePlayerCount(int playerRole){
        int result = 0;
        for(Player player : players){
            if(player.role < playerRole){
                result += 1;
            }
        }
        return result;
    }

    /**
     * Метод возвращает число активных на данном этапе игроков
     * @return
     */
    public int getActivePlayerCount(){
        int result = 0;
        for(Player player : players){
            if(player.active){
                result += 1;
            }
        }
        return playersCount = result;
    }

    /**
     * Метод рассчитывает проделанные действия игроков начиная с предыдущего сканирования стола
     * @return
     */
    public void сalculatePlayerAction(){

        // Префлоп, нет истории
        if(stage == 0){

        }else{

            // тут получаем предыдущий board с таким-же номером раздачи
            for(Player player : players){}

        }
    }

    //-----------------------------------------------Статические методы-------------------------------------------------
    /**
     * Метод проверяет сидим ли мы за столом(Без полного распознавания стола);
     * @return
     */
    public static Boolean playerInGame() throws TesseractException, AWTException {
        String corLogin = StringUtil.correctTesserractString(ImageUtil.recognition(ImageUtil.cropImage(ImageUtil.getStarWindow(), new Rectangle(616, 692, 154,25))),6);
        Boolean result = Board.login.equals(corLogin);
        System.out.print("corLogin = " + corLogin + ", result = " + Board.login.equals(corLogin));
        System.out.println();
        return result;
    }

    public static Boolean playerActive() throws AWTException {
        String pixel = String.valueOf(ImageUtil.getCollor(ImageUtil.getStarWindow(), 529, 722));
        return  pixel.equals("-6052957");
    }
}
