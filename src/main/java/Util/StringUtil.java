package Util;

public class StringUtil {

    /**
     * Странное дело но тессеракт в созданных стрингах добавляет 2  экранированных знака\n и пробелы
     * @param tesString
     * @return
     */
    public static String correctTesserractString(String tesString){
        tesString.replaceAll("\\p{P}", "");
        return tesString.replaceAll("\\s+","");
    }

    public static String tesCorrect(String outText){
        // OCR corrections
        String parsedOut = outText.replaceAll("l", "1").replaceAll("Z", "2").replaceAll("O", "0")
                .replaceAll("B", "8").replaceAll("G", "6").replaceAll("S", "8").replaceAll("'", "")
                .replaceAll("‘", "").replaceAll("\\.", ":").replaceAll("E", "8").replaceAll("o", "0")
                .replaceAll("ﬂ", "0").replaceAll("ﬁ", "6").replaceAll("§", "5").replaceAll("I", "1")
                .replaceAll("T", "7").replaceAll("’", "").replaceAll("U", "0").replaceAll("D", "0");
        if (parsedOut.length() > 7) {
            parsedOut = parsedOut.substring(0, 7) + ":" + parsedOut.substring(8, parsedOut.length());
        }
        parsedOut = parsedOut.replaceAll("::", ":");

        // Remove last part (number of frames)
        int iSpace = parsedOut.lastIndexOf(" ");
        if (iSpace != -1) {
            parsedOut = parsedOut.substring(0, iSpace);
        }
        return parsedOut;
    }

    /**
     * Кастыль для моего логина)
     * @param tesString
     * @param index
     * @return
     */
    public static String correctTesserractString(String tesString, int index){
        if(tesString.length() >= index+2) {
            return tesString.substring(index, tesString.length() - 2);
        }
        return tesString;
    }

    /**
     * Удаляем доллар у распознанных цифр:
     * @param tesString
     * @return
     */
    public static String removeDollar(String tesString){

        return tesString.replaceAll("$", "");
    }

    /**
     * Метод корректирует строку распознанного числа если в ней появилась буква
     * @param number
     * @return
     */
    public static int correctTesseractInt(String number){
        number = StringUtil.correctTesserractString(number);
        // удалится все кроме букв и цифр [^A-Za-zА-Яа-я0-9]
        number.replaceAll("[^A-Za-zА-Яа-я]", "");
        //number = removeDollar(number);
        if(number.length() > 0) {
            System.out.print(number);
            return Integer.parseInt(number);
        }
        return 0;
    }

    /**
     * Метод преобразует 2 полученных параметра о карте в int
     * /**
     *  * Колода карт:
     *  *
     *  *      * Значение карты:
     *  *      * 2 ... 10
     *  *      * 11 knave - валет - jack
     *  *      * 12 queen - королева
     *  *      * 13 king - король
     *  *      * 14 ace - туз
     *  *      * 15 joker - джокер
     *  *      * + Еще если есть описать
     *  *
     *  *      * Масть карты определяет ее итоговое значение value следующим образом(для удобства работы с сетью) mast * 100 + value:
     *  *      * mast: spades-пики = 0, clubs-трефы = 1, hearts-черви = 2, diamonds-бубны = 3
     *  *      * И того ТУЗ ПИКИ имеет номер 14, в то время как КОРОЛЬ ЧЕРВИ 213 (запас одной масти 100 карт)
     *  *
     * @return
     */
    public static int convertTesseractCartToInt(String cart, int mastCart){

        String cartRes = StringUtil.correctTesserractString(cart);
        String cartResult = "" + mastCart;

        String tuz = "A";
        String quen = "Q";
        String king = "K";
        String valet = "J";

        if(cartRes.equals(tuz)){
            cartResult = cartResult + 14;
        }
        if(cartRes.equals(quen)){
            cartResult = cartResult + 12;
        }
        if(cartRes.equals(valet)){
            cartResult = cartResult + 11;
        }
        if(cartRes.equals(king)){
            cartResult = cartResult + 13;
        }
        if(!cartRes.equals(tuz) && !cartRes.equals(king) && !cartRes.equals(quen) && !cartRes.equals(valet)){
            cartResult = cartResult + cart;
        }

        return StringUtil.correctTesseractInt(cartResult);
    }
}
