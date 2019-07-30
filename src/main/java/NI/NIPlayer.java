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
        //-----------------------Формируем пример данных для сети:---------------------------------------
        sample = new NISample();

        //-----------------------Формируем входной слой:
        sample.description = "По данному примеру будет сформирована сеть, " +
                "и с него будут создаваться клоны для процесса обучения";

        // Верхний, первый ряд:
        NINeuron neuron = new NINeuron(0);

        // Наполняем входной слой нейронами:
        sample.input.add(neuron);

        //---------------------------------------------ДАЛЕЕ формируем выходной слой:

        NINeuron no_action = new NINeuron(0);

        // Наполняем выходной слой нейронами:
        sample.output.add(no_action);

        // Формируем нашего оффицера!
        network = new NINetwork("Тактический оффицер");
        network.inputSample = sample.cloneNISample(sample, "Первоначальный, пустой пример");
        // Далее наполняем примерами
        //network.samples = getLearnList();
        // Выполняем инициализацию:
        network.initialize();
    }

    //Метод выполняющий подбор действия в указанной ситуации:
    void work(){
            /*
            //Подготавливаем отчет об игровой ситуации, и выполняем принятие решения:
            network.inputSample = getGameInfo();
            int new_action = 0;
            new_action = network.getMaxActual();
            //Теперь чтоб не зацикливаться на одной идее...
            if(action != new_action) {
                if (strategy.debug) {
                    System.out.println("тактический оффицер принял решение под номером: " + new_action);
                }
                action = new_action;
                //Отдаем цепочку приказов:
                actionGOO();
            }else{
                //В случае если наш умный оффицер зациклился, выдаем какуюнибудь муть:
                actionRandomGOO();
            }
            */
    }

    //Данный метод подготавливает оценку игровой ситуации в удобный для оффицера формат:
    NISample getGameInfo(){
        NISample gameInfo = network.inputSample;

        /*
        //Обновляем в отрядах информацию о каличестве юнитов:
        strategy.squad_0_Fighter.updateSquadInfo();

        //Далее наполняем сбор информации об отрядах:
        gameInfo.input.get(strategy.XSectorCount + 1).intCount = getSquadInfo(strategy.squad_0_Fighter);

        //Далее добавляем информацию о состоянии секторов:
        for(int i = 0; i < gameInfo.input.size(); i ++){
            // Здесь 5 это смещение на нейроны отрядов:
            if(i < gameInfo.input.size() - 5) {
                Sector selected = strategy.allSectorList.get(i);
                selected.updateSectorInfo(strategy);
                gameInfo.input.get(i).intCount = getSectorInfo(selected);
            }
        }
         */
        return gameInfo;
    }

    // Метод формирует информацию о секторе все сводится к int(очуметь!):
    //int getSectorInfo(Sector sector){
        //Карта значений сектора(как ее видит тактический офицер):

        /*
        //В целях дебага выводим подробную информацию о каждом секторе в лог:
        if(debug){
            if(strategy.mapLevel_debug) {
                System.out.println("Ключ сектора: sector.sector_key.x = " + sector.sector_key.x + ", sector.sector_key.y = " + sector.sector_key.y);
                System.out.println("В секторе имеется следующее число едениц техники: " + sector.allVehicleInSector.size());

                System.out.println("Из них техника игрока составляет: " + sector.player_unit_count);
                System.out.println("Общее число вертолетов игрока: " + sector.HELECOPTERInSectorCount);
                System.out.println("Общее число самолетов игрока: " + sector.FIGHTERInSectorCount);
                System.out.println("Общее число танков игрока: " + sector.TANKInSectorCount);
                System.out.println("Общее число бмп игрока: " + sector.BMPInSectorCount);
                System.out.println("Общее число рем игрока: " + sector.REMInSectorCount);
                System.out.println("А вражеских едениц следующее число: " + sector.enemy_unit_count);
                System.out.println("Общее число вертолетов противника: " + sector.HELECOPTEREnemyInSectorCount);
                System.out.println("Общее число самолетов противника: " + sector.FIGHTEREnemyInSectorCount);
                System.out.println("Общее число танков противника: " + sector.TANKEnemyInSectorCount);
                System.out.println("Общее число бмп противника: " + sector.BMPEnemyInSectorCount);
                System.out.println("Общее число рем противника: " + sector.REMEnemyInSectorCount);
            }
        }
        */

        //Документация по точному порядку и значению данных приведена в текстовом файле.
        //int result = 0;
        //return result;
    //}

    //Метод построчно отображает информацию о ситуации на карте из примера(который после будет передан оффицеру).
    public void debug_map(NISample sample){

        //выводим информацию об отрядах:
        if(debug) {

            /*
            if(strategy.mapLevel_debug) {
                System.out.println("Состояние отряда 0 - Самолеты: " + sample.input.get(0).intCount);
                System.out.println("Состояние отряда 1 - Вертолеты: " + sample.input.get(1).intCount);
                System.out.println("Состояние отряда 2 - Танки: " + sample.input.get(2).intCount);
                System.out.println("Состояние отряда 3 - БМП: " + sample.input.get(3).intCount);
                System.out.println("Состояние отряда 4 - REM: " + sample.input.get(4).intCount);
            }

            String debugLine = "";

            ArrayList<String> allLineGen = new ArrayList<>();
            allLineGen.add("NISample sample_ = sample.cloneNISample(sample, Новое состояние игрового мира);");

            //Выводим информативную карту:
            for (int i = 0; i < sample.input.size(); i++) {
                NINeuron selected = sample.input.get(i);
                //По идее каждые 5 знаков:
                if (i % strategy.XSectorCount == 0) {
                    System.out.println(debugLine);
                    debugLine = "" + selected.intCount;
                } else {
                    debugLine = debugLine + "  " + selected.intCount;
                }

                //Формируем автоматически инпут код примера:
                if(selected.intCount > 0){
                    allLineGen.add(newInputStringGEN(i, selected.intCount));
                }
            }

            //Теперь построчно печатаем пример:
            System.out.println("");
            for(int j = 0; j < allLineGen.size(); j++){
                String selected = allLineGen.get(j);
                System.out.println(selected);
            }
            //Не забываем добавить необходимое действие, и добавление примера в список:
            System.out.println("sample_.output.get(  ).intCount =  ;");
            System.out.println("returnedList.add(sample_);");
            */
        }
    }

    //Метод описывает цепочки поведения:
    public void actionGOO(){
        //------------------------------------------ЦЕПОЧКИ ДЕЙСТВИЙ------------------------------------------------
        //В зависимости от текущего принятого тактического решения отдаем новый приказ или последовательность приказов:
        switch(action) {
            //Первое тактическое решение, как нестранно, ничего не делать:
            case 0:
                break;
            // Далее идут решения на отправку отрядов каждого рода войск в 5 зон на карте:

            //---------------------------------------САМОЛЕТЫ:
            // Самолеты в вхний угол:
            case 1:
                //strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0,0,0,0, 0);
                //strategy.setMoveOrder(0, 0,0);
                break;
            // Cамолеты в центр:
            case 2:
                //strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0,0,0,0, 0);
                //strategy.setMoveOrder(strategy.allSectorList.get(12).centerPoint.x, strategy.allSectorList.get(12).centerPoint.y,0);
                break;
            // Самолеты влево:
            case 3:
                //strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0, 0, 0, 0, 0);
                //strategy.setMoveOrder(0, strategy.world.getHeight() / 2,0);
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

        //------------------------------------ПРИМЕРЫ управления САМОЛЕТАМИ:
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

        //Третий пример:
        NISample sample_2 = sample.cloneNISample(sample, "Самолеты противника приблизились уже" +
                " на середину карты, мои самолеты находятся ближе остальной техники к ним, " +
                "возможно стоит выполнить засаду для нападения на них сзади, отлетев в сторону и переждав их перемещение");

        //Далее обозначаем конкретные индивидуальные для данного примера условия:
        sample_2.input.get(0).intCount = 4;
        sample_2.input.get(6).intCount = 2;
        sample_2.input.get(18).intCount = 33;
        sample_2.input.get(23).intCount = 35;
        sample_2.input.get(24).intCount = 60;

        sample_2.output.get(3).intCount = 1;

        returnedList.add(sample_2);

        //Четвертый пример:
        NISample sample_3 = sample.cloneNISample(sample, "" +
                "Итак, мои войска: " +
                "Самолеты расположиись слева, вся остальная техника на месте вверху" +
                "Враги:" +
                "Все войска в основном внизу но похоже движутся в центр," +
                "самолеты уже приближаются к моим войскам, отправлю свои самолеты к нашим вертолетам");

        //Далее обозначаем конкретные индивидуальные для данного примера условия:
        sample_3.input.get(0).intCount = 4;
        sample_3.input.get(15).intCount = 2;
        sample_3.input.get(12).intCount = 33;
        sample_3.input.get(18).intCount = 35;
        sample_3.input.get(24).intCount = 60;

        sample_3.output.get(2).intCount = 1;

        returnedList.add(sample_3);

        //-----------------------------------------ПРИМЕРЫ управления ВЕРТОЛЕТАМИ
        //Первый пример:
        NISample sample_0 = sample.cloneNISample(sample, "Враг далеко, но его самолеты похоже, " +
                "выдвинулись на нас. Будем перестраиваться на более удобные позиции: отправим вертолеты вправо.");

        //Далее обозначаем конкретные индивидуальные для данного примера условия:
        sample_0.input.get(0).intCount = 15;
        sample_0.input.get(18).intCount = 33;
        sample_0.input.get(19).intCount = 33;
        sample_0.input.get(23).intCount = 60;

        sample_0.output.get(12).intCount = 1;

        returnedList.add(sample_0);

        //пятый пример:
        NISample sample_4 = sample.cloneNISample(sample, "" +
                "Итак, мои войска: " +
                "Самолеты расположиись слева, вся остальная техника на месте вверху" +
                "Враги:" +
                "Все войска в основном внизу но похоже движутся в центр," +
                "самолеты уже приближаются к моим войскам, отправлю вертолеты к РЕМ(чтобы расположились над)");

        //Далее обозначаем конкретные индивидуальные для данного примера условия:
        sample_4.input.get(0).intCount = 4;
        sample_4.input.get(15).intCount = 2;
        sample_4.input.get(12).intCount = 33;
        sample_4.input.get(18).intCount = 35;
        sample_4.input.get(24).intCount = 60;

        sample_4.output.get(17).intCount = 1;

        returnedList.add(sample_4);

        //------------------------------------------ПРИМЕРЫ управления ТАНКАМИ

        //------------------------------------------ПРИМЕРЫ управления БМП

        //------------------------------------------ПРИМЕРЫ управления РЕМ
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
