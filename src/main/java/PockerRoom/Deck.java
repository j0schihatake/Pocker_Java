package PockerRoom;

import java.util.HashMap;

/**
 * Колода карт:
 *
 *      * Значение карты:
 *      * 2 ... 10
 *      * 11 knave - валет - jack
 *      * 12 queen - королева
 *      * 13 king - король
 *      * 14 ace - туз
 *      * 15 joker - джокер
 *      * + Еще если есть описать
 *
 *      * Масть карты определяет ее итоговое значение value следующим образом(для удобства работы с сетью) mast * 100 + value:
 *      * mast: spades-пики = 0, clubs-трефы = 1, hearts-черви = 2, diamonds-бубны = 3
 *      * И того ТУЗ ПИКИ имеет номер 14, в то время как КОРОЛЬ ЧЕРВИ 213 (запас одной масти 100 карт)
 */

public class Deck {

    /**
     * Число карт в колоде:
     */
    public int cardCount = 0;

    /**
     *  key - значение карты в цифровом эквиваленте:
     *  value - ее местоположение в игровом мире:
     *  0 - в колоде
     *  1 - на столе
     *  2 - Диллер
     *  3 - MB
     *  4 - BB
     *  5..n - другие игроки
     */
    public HashMap<Integer, Integer> carts = new HashMap<Integer, Integer>();

    public Deck(int cardCount){

        this.cardCount = cardCount;

        initColode(cardCount);
    }

    /**
     * Первоначальная настройка колоды карт:
     */
    private void initColode(int cartCount){

        // Создаем карты всех мастей
        for(int i = 0; i < 4; i++){

            // Количество карт одной масти
            int cartMastCount = cartCount/4;

            // Значение самой маленькой карты каждой масти
            int minCartNumber = 2;

            for(int nextCart = 0; nextCart < cartMastCount; nextCart++){

                int nextCartValue = (nextCart + minCartNumber) + i * 100;

                // value = 0 так как все карты в колоде:
                carts.put(nextCartValue, 0);
            }
        }
    }
}
