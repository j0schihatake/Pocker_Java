package PockerRoom;

/**
 * Игровая карта.
 */
public class Cart {

    /**
     * Значение карты:
     * туз-ace
     * король-king
     * дама - queen
     * валет-knave, Jack
     * джокер -joker
     */

    /**
     * Значение специальной карты.
     */
    public String specialValue;

    /**
     * значение цифровой карты(цифровая).
     */
    public int value;


    public enum typeCart{
        number,
        special
    }

    /**
     * Масть карты:
     * spades-пики, clubs-трефы,hearts-черви,diamonds-бубны...
     */
    public cartMast mast;
    public enum cartMast{
        spades,
        clubs,
        hearts,
        diamonds
    }
}
