package PockerRoom;

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
     * Являемся ли мы данным игроком.
     */
    public boolean isPlayer = false;

    /**
     * Карты игрока:
     */
    public ArrayList<Integer> unitCard;

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
    public Rectangle playerLoginAction;

    public String playerExampleActionFilePatch = "d:\\Pocker\\ExampleDLP\\Action\\";

    /**
     * Money - область где отображены средства игрока(цифра)
     */
    public Rectangle playerMoney;

    public String playerExampleMoneyFilePatch = "d:\\Pocker\\ExampleDLP\\Money\\";

    /**
     * Active - область где отображено что текущий игрок активен(выделение контура)
     */
    public Rectangle playerActive;

    public String playerExampleActiveFilePatch = "d:\\Pocker\\ExampleDLP\\Active\\";

    /**
     * Diller - областьгде отображено что текущий игрок диллер(сердечко)
     */
    public Rectangle playerDiller;

    public String playerExampleDillerFilePatch = "d:\\Pocker\\ExampleDLP\\Diller\\";

    /**
     * Ставка игрока
     */
    public static Rectangle playerBet;

    public static String playerExampleBetFilePatch = "d:\\Pocker\\ExampleDLP\\Rect\\";

    /**
     * Первоначальная подготовка игрока:
     */
    public void init(){}

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
