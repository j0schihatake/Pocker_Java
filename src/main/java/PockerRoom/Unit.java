package PockerRoom;

import java.util.ArrayList;

/**
 * Участник игры.
 */
public class Unit {

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
     * Первоначальная подготовка игрока:
     */
    public void init(){

    }

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
