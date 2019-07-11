package PockerRoom;

import java.util.HashMap;

/**
 * Game это виртуальная симуляция игры.
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
     * Новая игра
     * @param unitCount - число игроков
     */
    public Game(int unitCount){
        this.unitCount = unitCount;
    }

    public HashMap<Integer, Unit> gameUnit;

    /**
     * Метод запускает игру:
     */
    public void play(){

        gameUnit = new HashMap<Integer, Unit>();

        while(unitCount > 0){

            // Игровые этапы:
            switch(gameState){

                // Инициализация игры:
                case 0:
                    initGame();
                    break;

                // Первый ход:
                case 1:
                    break;

                // Последующие ходы:
                case 2:
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
        gameUnit.put(0,diller);

        // SB
        Unit sb = new Unit();
        sb.unitRole = Unit.role.sb;
        gameUnit.put(1,sb);

        // BB
        Unit bb = new Unit();
        bb.unitRole = Unit.role.bb;
        gameUnit.put(2, bb);

        // Добавляем остальных игроков:
        for(int i = 3; i < unitCount; i++){
            Unit otherUnit = new Unit();
            otherUnit.unitRole = Unit.role.empty;
            gameUnit.put(i, otherUnit);
        }
    }
}
