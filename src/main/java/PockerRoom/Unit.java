package PockerRoom;

/**
 * Участник игры.
 */
public class Unit {

    /**
     * Деньги игрока.
     */
    public int money = 0;

    /**
     * Являемся ли мы данным игроком:
     */
    public boolean isPlayer = false;

    /**
     * Роль участника в игре:
     */
    public role unitRole;                   // роль игрока в игре
    enum role {
        empty,                              // стандартный игрок
        sb,                                 // первый слева от диллера
        d,                                  // диллер
        bb                                  // второй слева от диллера
    }

    // Первоначальная подготовка игрока:
    public void init(){

    }

    // Игрок начинает игру как SB(первая ставка x1)
    public int startSB(){
        this.money = money - Game.deposit;
        return Game.deposit;

    }

    // Игрок начинает игру как BB(вторая ставка 2*x1)
    public int startBB(){
        this.money = money - Game.deposit*2;
        return Game.deposit*2;
    }

    /**
     * Возможные действия игрока в игре:
     * 0) Пропустить, чек (англ. check) – в ситуациях, когда ставка уже была сделана или ставки не были сделаны соперниками
     *    – не добавлять ничего в банк, оставить «как есть»
     * 1) Поставить, бет (англ. bet) – сделать ставку
     * 2) Ответить, колл (англ. call) – поставить столько же, сколько поставил соперник – уравнять
     * 3) Поднять, рейз (англ. raise) – увеличить ставку – поставить больше, чем соперники
     * 4) Cбросить карты, фолд (англ. fold) – отказаться от дальнейшего участия в игре и сбросить карты
     */

    public void check(){}

    public void bet(){}

    public void cell(){}

    public void raise(){}

    public void fold(){}
}
