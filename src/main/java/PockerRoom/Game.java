package PockerRoom;

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
    public ArrayList<Player> gamePlayer;

    /**
     * Метод запускает игру:
     */
    public void play(){

        gamePlayer = new ArrayList<Player>();

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

        for(int i = 0; i < unitCount; i++){
            Player nextPlayer = new Player();
            nextPlayer.role = i;
            gamePlayer.add(nextPlayer);
            //nextPlayer.init();

            switch(i){
                case 1:
                    setBank(nextPlayer.startSB());                                  //платим deposit
                    break;
                case 2:
                    setBank(nextPlayer.startBB());                                  //платим 2*deposit
                    break;
            }
        }

        // Подготавливаем колоду(наполняем, тусуем):
        Deck deck = new Deck(34);
        deck.toSchufle();
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
