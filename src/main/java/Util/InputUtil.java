package Util;

import java.awt.*;
import Util.Input.VirtualKeyboard;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Предоставляет инструменты для имитации инпута в системе.
 * https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html
 */
public class InputUtil {

    private static VirtualKeyboard keyboard;

    static {
        try {
            keyboard = new VirtualKeyboard();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------Управление мышкой-----------------------------------------------------

    public static void klickMouseToCoordinats(int x, int y, int buttonType) throws AWTException {
        Robot bot = new Robot();
        int mask = buttonType;
        bot.mouseMove(x, y);
        bot.mousePress(mask);
        bot.mouseRelease(mask);
    }

    //------------------------------------------------Клавиатура-------------------------------------------------------

    /**
     * Метод реализует нажатие в системе одной клавиши с клавиатуры
     */
    public static void pressKey(int keyValue){
        keyboard.pressKey(keyValue);
    }

    /**
     * Метод реализует нажатие в системе комбинации клавиш с клавиатуры
     */
    public static void pressKeys(String keyCombination) throws AWTException {
        keyboard.pressKeys(keyCombination);
    }

    //----------------------------------------------Хардкодим горячие клавиши-------------------------------------------
    /**
     * Переключиться на следующий стол:
     */
    public static void nextBoard(){
        InputUtil.pressKey(KeyEvent.VK_W);
    }

    /**
     * Переключиться на предыдущий стол:
     */
    public static void prevBoard(){
        InputUtil.pressKey(KeyEvent.VK_2);
    }

    /**
     * Переключиться на первый стол:
     */
    public static void firstBoard(){
        InputUtil.pressKey(KeyEvent.VK_1);
    }

    /**
     * Выполняет Bet/Rise
     */
    public static void betRise(){
        InputUtil.pressKey(KeyEvent.VK_B);
    }

    /**
     * Выполняет Koll
     */
    public static void koll(){
        InputUtil.pressKey(KeyEvent.VK_K);
    }

    /**
     * Выполняет Fold
     */
    public static void fold(){
        InputUtil.pressKey(KeyEvent.VK_F);
    }

    /**
     * Выполняет Chek
     */
    public static void chek(){
        InputUtil.pressKey(KeyEvent.VK_C);
    }

    /**
     * Метод нажимает стрелку вниз:
     */
    public static void down(){
        InputUtil.pressKey(KeyEvent.VK_DOWN);
    }

    /**
     * Метод нажимает стрелку вверх:
     */
    public static void up(){
        InputUtil.pressKey(KeyEvent.VK_UP);
    }

    /**
     * Метод нажимает enter:
     */
    public static void enter(){
        InputUtil.pressKey(KeyEvent.VK_ENTER);
    }

    /**
     * Метод закрывает стол и выходит из игры:
     * @throws AWTException
     */
    public static void closeBoard() throws AWTException {
        InputUtil.pressKeys("alt+F4");
        InputUtil.enter();
    }

    /**
     * Выполняет щелчек по кнопке сесть за похожий стол
     */
    public static void sittInOtherBoard() throws AWTException {
        InputUtil.klickMouseToCoordinats(788,845, InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Помещаем курсор в безопасное место чтобы не мешал распознаванию:
     */
    public static void cursorToSaveZone() throws AWTException {
        InputUtil.klickMouseToCoordinats(1375,211, InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Метод выполняет клик по кнопке Lobbi:
     */
    public static void clikOnLobbi() throws AWTException {
        InputUtil.klickMouseToCoordinats(1555,117, InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Открыть стол(вызывать на открытом столе)
     */
    public static void addBoard() throws InterruptedException, AWTException {
        InputUtil.clikOnLobbi();
        Thread.sleep(1000);
        InputUtil.down();
        InputUtil.enter();
    }

    /**
     * Метод кликает по всем кнопкам сесть:
     */
    public static void sittAll() throws AWTException, InterruptedException {
        InputUtil.klickMouseToCoordinats(545,639, InputEvent.BUTTON1_DOWN_MASK);
        InputUtil.klickMouseToCoordinats(540,369, InputEvent.BUTTON1_DOWN_MASK);
        InputUtil.klickMouseToCoordinats(1003,230, InputEvent.BUTTON1_DOWN_MASK);
        InputUtil.klickMouseToCoordinats(1478,366, InputEvent.BUTTON1_DOWN_MASK);
        InputUtil.klickMouseToCoordinats(1498,625, InputEvent.BUTTON1_DOWN_MASK);
        InputUtil.klickMouseToCoordinats(1007,778, InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(1000);
        InputUtil.enter();
        InputUtil.cursorToSaveZone();
    }
}
