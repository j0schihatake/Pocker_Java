package PockerRoom;

/**
 * Колода карт:
 */
public class Deck {

    /**
     * Число карт в колоде:
     */
    public int cardCount = 0;

    public Deck(int cardCount){

        this.cardCount = cardCount;

        initColode();
    }

    /**
     * Первоначальная настройка колоды карт:
     */
    private void initColode(){

        switch(cardCount){
            case 32:
                break;

            case 36:
                break;

            case 52:
                break;

            case 54:
                break;
        }
    }
}
