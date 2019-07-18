package PockerRoom;

import javax.smartcardio.Card;
import java.util.ArrayList;

/**
 * Game это виртуальная симуляция игры(одна запущенная игровая комната).
 */
public class Game {

    /**
     * Число игроков в игре.
     */
    public int unitCount = 0;

    /**
     * Этапы игры.
     */
    public int gameState = 0;

    /**
     * Сумма денег в банке.
     */
    public static int bank = 0;

    /**
     * Сумма стартового депозита:
     */
    public static int deposit = 0;

    /**
     * Новая игра
     * @param unitCount - число игроков
     */
    public Game(int unitCount){
        this.unitCount = unitCount;
    }

    /**
     * Список для доступа к игрокам по порядковому номеру
     */
    public ArrayList<Unit> gameUnit;

    /**
     * Метод запускает игру:
     */
    public void play(){

        gameUnit = new ArrayList<Unit>();

        Deck deck = new Deck(52);

        while(unitCount > 1){

            // Игровые этапы:
            switch(gameState){

                // Инициализация игры:
                case 0:
                    initGame();

                    goToNextStageLevel();
                    break;

                // Первый ход:
                case 1:

                    goToNextStageLevel();
                    break;

                // Второй ход + флоп:
                case 2:

                    goToNextStageLevel();
                    break;

                // Третий ход + терн:
                case 3:

                    goToNextStageLevel();
                    break;

                // Четвертый ход + ривер:
                case 4:

                    goToNextStageLevel();
                    break;

                // Вскрытие:
                case 5:

                    //Конец игры.
                    endGame();

                    break;
            }
        }
    }

    /**
     * Метод подготовки игры:
     */
    void initGame(){

        // Диллер:
        Unit diller = new Unit();
        diller.unitRole = Unit.role.d;
        diller.init();
        gameUnit.add(diller);

        // SB
        Unit sb = new Unit();
        sb.unitRole = Unit.role.sb;
        sb.init();
        setBank(sb.startSB());                                  //платим deposit
        gameUnit.add(sb);

        // BB
        Unit bb = new Unit();
        bb.unitRole = Unit.role.bb;
        bb.init();
        setBank(bb.startBB());                                  //платим 2*deposit
        gameUnit.add(bb);

        // Подготавливаем колоду(наполняем, тусуем):
        Deck deck = new Deck(34);

        // Добавляем остальных игроков:
        for(int i = 3; i < unitCount; i++){
            Unit otherUnit = new Unit();
            otherUnit.unitRole = Unit.role.empty;
            gameUnit.add(otherUnit);
        }
    }

    /**
     * Торг:
     */
    void cake(){

        // Каждый игрок совершает ход по порядку(0 = d, 1 = sb, 2 = bb, 3 = other, n = unitCount):
        for(int i = 0; i < unitCount; i++){

        }
    }

    /**
     * Переход к следующему раунду:
     */
    void goToNextStageLevel(){
        gameState++;
    }

    /**
     * Заканчиваем игру:
     */
    void endGame(){
        unitCount = 1;
    }

    /**
     * Кладем что-то в банк:
     * @param money - сумма вносимых средств
     */
    void setBank(int money){
        this.bank = bank + money;
    }

    //------------------------------------------------Интеграция с NI---------------------------------------------------
}
