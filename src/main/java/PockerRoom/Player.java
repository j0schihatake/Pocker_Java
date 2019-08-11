package PockerRoom;

import Util.ImageUtil;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Участник игры.
 */
public class Player {

    /**
     * Деньги игрока.
     */
    public int money = 0;

    /**
     * Роль участника в игре:
     *      * 0 - диллер
     *      * 1 - MB
     *      * 2 - BB
     *      * 3 - n...
     */
    public int role = 0;

    /**
     * Action - область где отражено выполненное игроком действие(надпись)/Login
     */
    public Rectangle playerLoginActionRectangle;

    public String playerLogin;

    public String playerExampleLoginActionFilePatch = "d:\\Pocker\\ExampleDLP\\Action\\";

    /**
     * Money - область где отображены средства игрока(цифра)
     */
    public Rectangle playerMoneyRectangle;

    public String pMoney;

    public String playerExampleMoneyFilePatch = "d:\\Pocker\\ExampleDLP\\Money\\";

    /**
     * Active - область где отображено что текущий игрок активен(выделение контура)
     */
    public Rectangle playerActiveRectangle;

    public String playerExampleActiveFilePatch = "d:\\Pocker\\ExampleDLP\\Active\\";

    /**
     * Diller - областьгде отображено что текущий игрок диллер(сердечко)
     */
    public String playerDiller = "-1";
    public int pDillerX;
    public int pDillerY;

    /**
     * Проверяем наличие карт в руках игрока:
     */
    public String playerInGame = "-1";
    public int pInGameX;
    public int pInGameY;

    public String playerExampleDillerFilePatch = "d:\\Pocker\\ExampleDLP\\Diller\\";

    /**
     * Ставка игрока
     */
    public Rectangle playerBetRectangle;

    public String playerExampleBetFilePatch = "d:\\Pocker\\ExampleDLP\\Bet\\";

    /**
     * Игрок начинает игру как SB(первая ставка x1)
     * @return сумма вносимая в банк
     */
    public int startSB(){
        this.money = money - Game.deposit;
        return Game.deposit;
    }

    /**
     * Игрок начинает игру как BB(вторая ставка 2*x1)
     * @return сумма вносимая в банк
     */
    public int startBB(){
        this.money = money - Game.deposit*2;
        return Game.deposit*2;
    }

    /**
     * Метод определяет является ли игрок диллером по цвету пикселя в центре значка:
     * @return true = да
     */
    public Boolean isDiller() throws AWTException {
        String pixel = String.valueOf(ImageUtil.getCollor(ImageUtil.getStarWindow(), pDillerX, pDillerY));
        return  pixel.compareTo(playerDiller) == 0 ? true : false;
    }

    /**
     * Метод возвращает true если игрок является MB:
     * @return
     */
    public Boolean isMB(){
        return role == 1;
    }

    /**
     * Метод возвращает true если игрок является BB:
     * @return
     */
    public Boolean isBB(){
        return role == 2;
    }

    /**
     * Метод проверяет учавствует ли игрок в игре(по наличию карт в его руках):
     * @return true = да
     * @throws AWTException
     */
    public Boolean isPlayerInGame() throws AWTException {
        String pixel = String.valueOf(ImageUtil.getCollor(ImageUtil.getStarWindow(), pInGameX, pInGameY));
        return  pixel.compareTo(playerInGame) == 0 ? true : false;
    }

    /**
     * Метод обновляет значение логина игрока(считывая со стола)
     * @return
     */
    public String getPlayerLogin() throws AWTException, TesseractException {
        return playerLogin = ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), playerLoginActionRectangle)));
    }

    public String getPlayerMoney() throws AWTException, TesseractException {
        return  pMoney = ImageUtil.recognition(ImageUtil.getBonusContrast(ImageUtil.cropImage(ImageUtil.getStarWindow(), playerMoneyRectangle)));
    }

    /**
     * Проверка на комбинацию:
     */
    public void chekToCombo(){}

    //-------------------------------------Возможные действия игрока в игре:--------------------------------------------

    /**
     * 0) Пропустить, чек (англ. check) – в ситуациях, когда ставка уже была сделана или ставки не были сделаны соперниками
     *    – не добавлять ничего в банк, оставить «как есть»
     */
    public void check(){}

    /**
     * 1) Поставить, бет (англ. bet) – сделать ставку
     */
    public void bet(){}

    /**
     * 2) Ответить, колл (англ. call) – поставить столько же, сколько поставил соперник – уравнять
     */
    public void cell(){}

    /**
     * 3) Поднять, рейз (англ. raise) – увеличить ставку – поставить больше, чем соперники
     */
    public void raise(){}

    /**
     * 4) Cбросить карты, фолд (англ. fold) – отказаться от дальнейшего участия в игре и сбросить карты
     */
    public void fold(){}
}
