package Util;

public class StringUtil {

    /**
     * Странное дело но тессеракт в созданных стрингах добавляет 2  экранированных знака\n
     * @param tesString
     * @return
     */
    public static String correctTesserractString(String tesString){
            if(tesString.length() > 2) {
                return tesString.substring(0, tesString.length() - 2);
            }
            return tesString;
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
        return tesString.substring( 1, tesString.length());
    }

    /**
     * Метод корректирует строку распознанного числа если в ней появилась буква
     * @param number
     * @return
     */
    public static String correctTesseractInt(String number){
        return number;
    }
}
