package Util;

import Util.Input.VirtualKeyboard;

import java.awt.*;
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
}
