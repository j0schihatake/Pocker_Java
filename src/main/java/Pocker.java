import PockerRoom.*;
import Util.ImageUtil;
import Util.InputUtil;
import Util.StringUtil;
import net.sourceforge.tess4j.TesseractException;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;

import java.awt.*;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

/**
 * Pocker - Покер.
 */
public class Pocker {

    public static void main(String[] arrgs) throws Exception {

        // В целях тестирования/обучения можно запустить тестовую(виртуальную) игру:
        //Game testGame = new Game(10);

        // Запуск тестовой игры:
        //testGame.play();

        //---------------------------------Эксперименты с распознаванием изображения-------------------------------
        //screen();

        //---------------------------------Эксперименты с имитацией управления-------------------------------------
        //inputTest();

        //--------------------------------------------Рабочий режим------------------------------------------------
        Thread.sleep(10000);


        start();
    }

    /**
     * Выполняет создание изображения скрина каждые 10 сек:
     * @throws Exception
     */
    public static void screen() throws Exception {

        //Thread.sleep(1000);

        //String patchName = ImageUtil.getRandomName("d:\\");

        //Rectangle subImage = new Rectangle(0,0,0,0);
        //subImage.setRect(270,240,130,35);

        // Сохранять все анализируемые области в виде изображений в папку тест
        //ImageUtil.saveImageAs(ImageUtil.getStarWindow(),"d:\\test.jpg");

        /**
         * 1) с этим все более менее понятно каждый пиксель это -15593193
         * 2) не буду учитывать минус при декодированиии,
         * 3) а так - же для преобразованияв значения от 0 до 1 / 1
         */
        // ImageUtil.grabPixels(patchName,subImage);

        //SQLiteUtil.Conn();
        //SQLiteUtil.CreateDB();
        //SQLiteUtil.CloseDB();

        // с этим методом получается нихрена мне непонятный массив(там и заголовки и ещехерь всякая)
        // ImageUtil.imageToMassive(patchName, subImage);

        // тестирую создание файла кусочка изображения
         //ImageUtil.cropAndSaveImage(patchName,subImage);

        // Thread.sleep(10000);

        Board one = new Board();
        //Thread.sleep(1000);
        //inputTest();

        //InputUtil.clikOnLobbi();

        while(1==1) {
            //one.debugBoardLogInfo();
            //one.makeExampleFolder();

            //System.out.print(one.upRightPlayer.isActive());
            //System.out.println();

            // Выводим цвет пикселя:
            //System.out.print(ImageUtil.getCollor(ImageUtil.getStarWindow(), one.cart1ActiveX,one.cart1ActiveY));
            System.out.print(one.getStageDescription(one.getGameStage()));
            //System.out.println();
            ImageUtil.cropAndSaveImage(ImageUtil.getRandomName("d:\\test\\"), new Rectangle(616, 692, 157,27));

            //InputUtil.down();
            Thread.sleep(1000);
        }

    }

    /**
     * Выполняет тестовое нажатие клавиш с клавиатуры и тестовое управение мышью:
     * @throws AWTException
     */
    public static void inputTest() throws AWTException, InterruptedException {
        //InputUtil.klickMouseToCoordinats(10,20, InputEvent.BUTTON1_DOWN_MASK);
        //InputUtil.pressKey(KeyEvent.VK_2);
        //InputUtil.pressKeys("alt+tab");

        //InputUtil.enter();
        //Thread.sleep(1000);
        InputUtil.sittAll();
        //InputUtil.clikOnLobbi();

        //InputUtil.closeBoard();
    }

    /**
     * Метод приступает к игре
     * для старта открыть лобби со списком столов
     */
    public static void start() throws TesseractException, AWTException, InterruptedException {

        /**
        InputUtil.enter();

        // Открываем необходимое число столов:
        for (int i = 0; i < Board.maxBoardCount; i++) {

            // Открываем столы по порядку и садимся за них:
            //InputUtil.addBoard();
        }
        */

        Boolean observ = true;

        // Теперь работаем со всеми столами:
        while(1==1){
            if(!observ){
                //inGame();
            }else{
                observer();
            }
        }
    }

    /**
     * Метод выполняет действия для участия в игре
     */
    static void inGame() throws TesseractException, AWTException, InterruptedException {
        //InputUtil.cursorToSaveZone();
        // Проверяем сидим ли мы за этим столом:
        System.out.println(Board.playerInGame());
        if(Board.playerInGame()){

            System.out.print(" Игрок сидит за столом: выполняю анализ стола:");
            System.out.println();

            // В целях теста всегда делаем чек и fold если ход
            if(Board.playerActive()) {
                System.out.print(" Игрок в Активен: выполняю анализ стола:");
                System.out.println();

                // Раз мы сидим за данным столом Считываем всю информацию о столе:
                Board nextOpenBoard = new Board();

                //InputUtil.chek();
                InputUtil.fold();
            }
            InputUtil.nextBoard();
        }
        // Мы не сидим за этим столом пробуем сесть:
        InputUtil.sittAll();
        //
        // wInputUtil.cursorToSaveZone();

        //Переключаемся на следующий стол:
        InputUtil.nextBoard();
    }

    /**
     * Метод ведет наблюдение за игрой
     */
    static void observer() throws TesseractException, AWTException {

        // Считываем всю информацию о столе:
        Board board = new Board();

        if(board.stage == 0){

            // Выполняем конвертацию данных в нужный формат:
            String party = board.partyNumber;
            String boardName = board.boardName;
            String login = board.downCenterPlayer.playerLogin;
            int maxSteck = StringUtil.correctTesseractInt(board.downCenterPlayer.pMoney);
            int playerCount = board.playersCount;
            int bankMoney = StringUtil.correctTesseractInt(board.bankMoney);
            int stage = board.stage;
            int cart0 = StringUtil.convertTesseractCartToInt(board.cart0, board.cart0Mast);

            System.out.print("party = " + party);
            System.out.println();
            System.out.print("boardName = " + boardName);
            System.out.println();
            System.out.print("bankMoney = " + bankMoney);
            System.out.println();
            System.out.print("login = " + login);




            // Создаем пример начала игры:
            // board.saveBoard();

            while(board.stage == 0){

            }
        }
    }
}


