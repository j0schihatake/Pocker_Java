package NI;

import NI.Pattern.NINetwork;
import NI.Pattern.NINeuron;
import NI.Pattern.NISample;

import java.util.ArrayList;
import java.util.Random;

// Итак данный класс будет выполнять настройку NI,
public class NIPlayer {

    /**
     * То о чем я говорил нейропрограммирование:
     */

    //Выключатель дебага:
    public boolean debug = false;

    //Данный пример является основой. С него создаются клоны(полный вектор принимаемых во внимание деталей).
    public NISample sample = null;

    //Прямая ссылка на сеть:
    public NINetwork network = null;

    //Действие или условное обозначение нескольких действий, или же целое тактическое решение:
    public int action = 0;

    //Подготавливаем новую нейронную сеть:
    public void createNetworkPattern(){
        //-----------------------Формируем пример данных для сети:
        sample = new NISample();

        //-----------------------Формируем входной слой:
        sample.description = "Игрок";

        /**
         * 0) Роль игрока(Дилер, MB, BB, n++...):
         */
        NINeuron role = new NINeuron(0);
        role.description = "Вход: Роль игрока(Дилер, MB, BB, n++...)";

        /**
         * 1) Число игроков:
         */
        NINeuron playersCount = new NINeuron(0);
        playersCount.description = "Вход: Число игроков";

        /**
         * 2) Текущий шанс на улучшение:
         */
        NINeuron bonusChance = new NINeuron(0);
        bonusChance.description = "Вход: Текущий шанс на улучшение";

        /**
         * 3) Размер стека:
         */
        NINeuron steckSize = new NINeuron(0);
        steckSize.description = "Вход: Размер стека";

        /**
         * 4) Номер текущего раунда:
         */
        NINeuron level = new NINeuron(0);
        level.description = "Вход: Номер текущего раунда";

        /**
         * 5) Стоимость ставки:
         */
        NINeuron cost = new NINeuron(0);
        cost.description = "Вход: Стоимость ставки";

        /**
         * 6) Шанс:
         */
        NINeuron chance = new NINeuron(0);
        chance.description = "Вход: Шанс";

        // Наполняем входной слой нейронами:
        sample.input.add(role);
        sample.input.add(playersCount);
        sample.input.add(bonusChance);
        sample.input.add(steckSize);
        sample.input.add(level);
        sample.input.add(cost);
        sample.input.add(chance);

        //-----------------------ДАЛЕЕ формируем выходной слой(например возможные действия):
        /**
         * 0) Пропустить, чек (англ. check) – в ситуациях, когда ставка уже была сделана или ставки не были сделаны соперниками
         *    – не добавлять ничего в банк, оставить «как есть»
         */
        NINeuron chek = new NINeuron(0);
        chek.description = "Выход: Пропустить";

        /**
         * 1) Поставить, бет (англ. bet) – сделать ставку
         */
        NINeuron bet = new NINeuron(0);
        bet.description = "Выход: Пропустить";

        /**
         * 2) Ответить, колл (англ. call) – поставить столько же, сколько поставил соперник – уравнять
         */
        NINeuron cell = new NINeuron(0);
        cell.description = "Выход: Ответить, колл";

        /**
         * 3) Поднять, рейз (англ. raise) – увеличить ставку – поставить больше, чем соперники
         */
        NINeuron raise = new NINeuron(0);
        raise.description = "Выход: Поднять, рейз";

        /**
         * 4) Cбросить карты, фолд (англ. fold) – отказаться от дальнейшего участия в игре и сбросить карты
         */
        NINeuron fold = new NINeuron(0);
        fold.description = "Выход: Cбросить карты, фолд";

        // Наполняем выходной слой нейронами:
        sample.output.add(chek);
        sample.output.add(bet);
        sample.output.add(cell);
        sample.output.add(raise);
        sample.output.add(fold);

        // Формируем нашего оффицера!
        network = new NINetwork("Игрок");
        network.inputSample = sample.cloneNISample(sample, "Первоначальный, пустой пример");

        // Далее наполняем примерами (вручную захардкоженными)
        //network.samples = getLearnList();

        // Выполняем инициализацию:
        network.initialize();
    }

    /**
     * Метод собственно выполняет подбор действия(сеть уже обучена)
     * @param gameStatus - информация об игровой ситуации, по сути пример но без заведомого результата.
     * @return
     */
    int work(NISample gameStatus){

        //описание текущей игровой ситуации в цифровом виде:
        network.inputSample = gameStatus;

        int new_action = 0;

        // getMaxActual(внутри себя вызывает прямое распространение у сетки)
        new_action = network.getMaxActual();

        if (debug) {
            debug_log(gameStatus, new_action);
        }

        /*
        ВНИМАНИЕ: Старый код пока удалять не стал, зачемтож он был, какминимум до устранения в коде ошибок)
        //Теперь чтоб не зацикливаться на одной идее...
        if(action != new_action) {
           action = new_action;
           //Отдаем цепочку приказов:
               actionGOO();
        }else{
           //В случае если наш умный игрок зациклился, выдаем какуюнибудь муть(шаг в неизвестность):
           actionRandomGOO();
        }
        */
        return new_action;
    }

    /**
     * Метод построчно отображает информацию обо всех этапах работы сети.
     * @param sample
     */
    public void debug_log(NISample sample, int action){

        System.out.println("ВНИМАНИЕ!!! Обращение к сети.");

        // Выводим поданную на вход информацию:
        for(int i = 0; i < sample.input.size(); i++){
            System.out.println(sample.input.get(i).description
                    + " = "
                    + sample.input.get(i).intCount);
        }

        System.out.println(network.description
                + " принято следующее решение: "
                + sample.output.get(action).description
                + "("
                + sample.output.get(action).intCount
                + ")");
    }

    /**
     * Метод для подбора стратегии(когда точно известны несколько дальнейших самых выгодных реакций)
     */
    public void actionGOO(){
        //------------------------------------------ЦЕПОЧКИ ДЕЙСТВИЙ------------------------------------------------
        switch(action) {
            case 0:
                break;
            case 1:
                break;

        }
    }

    //Метод случайно выбирает действия:
    public void actionRandomGOO(){
        action = genRandom(0,46);
        actionGOO();
    }

    // Метод формирует из захардкоженных примеров набор для обучения сети.
    ArrayList<NISample> getLearnList(){
        ArrayList<NISample> returnedList = new ArrayList<NISample>();

        /**
        //------------------------------------ПРИМЕР управления САМОЛЕТАМИ:
        //Второй пример:
        NISample sample_1 = sample.cloneNISample(sample, "Самолеты противника приблизились уже" +
                " на середину карты, необходимо выслать им на перехват наши самолеты(2), " +
                "если по схеме заметно что они не ближе к самолетам игрока. Отправляем самолеты к вертолетам");

        //Далее обозначаем конкретные индивидуальные для данного примера условия:
        sample_1.input.get(0).intCount = 15;
        sample_1.input.get(12).intCount = 33;
        sample_1.input.get(18).intCount = 45;
        sample_1.input.get(23).intCount = 37;
        sample_1.input.get(24).intCount = 43;

        sample_1.output.get(5).intCount = 1;

        returnedList.add(sample_1);
        */

        return  returnedList;
    }

    //Метод выдает рандом между значениями min и max.
    public int genRandom(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + 1;
    }

    //---------------------------------------------МЕТОДЫ для ГЕНЕРАЦИИ кода примеров:
    private String newInputStringGEN(int index, int intCount){
        String gen_return = "";
        gen_return =  "sample_.input.get(" + index + ").intCount = " + intCount + ";";
        return gen_return;
    }
}
