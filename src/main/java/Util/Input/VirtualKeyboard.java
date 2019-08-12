package Util.Input;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Предоставляет инструменты для имитации инпута клавиатуры в системе.
 * https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html
 */
public class VirtualKeyboard extends Robot {

    public VirtualKeyboard() throws AWTException {
        super();
    }

    //--------------------------------------------Нажатие одной клавиши-------------------------------------------------
    public void pressKey(int keyEvent){
        try {
            Robot rb = new Robot();
            rb.keyPress(keyEvent);
            rb.keyRelease(keyEvent);
        } catch (AWTException ex) {
            System.err.println("Robot error");
        }
    }

    //--------------------------------Реализация нажатия комбинации клавиш в системе------------------------------------
    public void pressKeys(String keysCombination) throws AWTException {
        VirtualKeyboard kb = new VirtualKeyboard();

        //String keyCombination = "control+a"; // select all text on screen
        //String keyCombination = "shift+a+1+c"; // types A!C on screen

        // For your case
        //String keyCombination = "alt+1+2+3";
        this.pressKeys(keysCombination,0);
        this.releaseKeys(keysCombination,0);
    }


    public void pressKeys(String keysCombination, int a) throws IllegalArgumentException {
        for (String key : keysCombination.split("\\+")) {
            try {
                System.out.println(key);
                this.keyPress((int) KeyEvent.class.getField("VK_" + key.toUpperCase()).getInt(null));

            } catch (IllegalAccessException e) {
                e.printStackTrace();

            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException(key.toUpperCase() + " is invalid key\n" + "VK_" + key.toUpperCase() + " is not defined in java.awt.event.KeyEvent");
            }
        }
    }

    public void releaseKeys(String keysConbination, int a) throws IllegalArgumentException {

        for (String key : keysConbination.split("\\+")) {
            try { // KeyRelease method inherited from java.awt.Robot
                this.keyRelease((int) KeyEvent.class.getField("VK_" + key.toUpperCase()).getInt(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException(key.toUpperCase() + " is invalid key\n" + "VK_" + key.toUpperCase() + " is not defined in java.awt.event.KeyEvent");
            }
        }
    }
}