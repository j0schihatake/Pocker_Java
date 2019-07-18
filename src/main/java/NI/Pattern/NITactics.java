package NI.Pattern;

import java.util.ArrayList;
import java.util.Random;

// Итак данный класс будет выполнять настройку NI командиров,
// а так-же предоставлять всемозможные действия в виде state машины.
public class NITactics{

    /*

    //Выключатель дебага:
    public boolean debug = false;

    //Данный пример является основой. С него создаются клоны.
    public NISample sample = null;

    public MyStrategy strategy = null;

    //Прямая ссылка на сеть:
    public NINetwork tactikal_officer = null;

    //Действие или условное обозначение нескольких действий, или же целое тактическое решение:
    public int action = 0;

    //Список имеющихся отчетов:
    public ArrayList<Report> allReports = new ArrayList<>();

    //Первоначальная настройка, тут формирую сети и примеры к ним, а так-же запускаю обучение.
    void start(){
        //Подготавливаем тактического офицера:
        tacticalOfficer();
    }

    //Подготавливаем тактического оффицера:
    void tacticalOfficer(){
        //-----------------------Формирую пример данных для тактического офицера:---------------------------------------
        sample = new NISample();

        //-----------------------Формируем входной слой:
        sample.description = "По данному примеру будет сформирована сеть, " +
                "и с него будут создаваться клоны для процесса обучения";

        //Данные нейроны представляют собой сектора(x, y):
        //Верхний, первый ряд:
        NINeuron sector_0_0 = new NINeuron(0);
        NINeuron sector_1_0 = new NINeuron(0);
        NINeuron sector_2_0 = new NINeuron(0);
        NINeuron sector_3_0 = new NINeuron(0);
        NINeuron sector_4_0 = new NINeuron(0);
        //Второй ряд:
        NINeuron sector_0_1 = new NINeuron(0);
        NINeuron sector_1_1 = new NINeuron(0);
        NINeuron sector_2_1 = new NINeuron(0);
        NINeuron sector_3_1 = new NINeuron(0);
        NINeuron sector_4_1 = new NINeuron(0);
        //Третий ряд:
        NINeuron sector_0_2 = new NINeuron(0);
        NINeuron sector_1_2 = new NINeuron(0);
        NINeuron sector_2_2 = new NINeuron(0);
        NINeuron sector_3_2 = new NINeuron(0);
        NINeuron sector_4_2 = new NINeuron(0);
        //Четвертый ряд:
        NINeuron sector_0_3 = new NINeuron(0);
        NINeuron sector_1_3 = new NINeuron(0);
        NINeuron sector_2_3 = new NINeuron(0);
        NINeuron sector_3_3 = new NINeuron(0);
        NINeuron sector_4_3 = new NINeuron(0);
        //Пятый отряд:
        NINeuron sector_0_4 = new NINeuron(0);
        NINeuron sector_1_4 = new NINeuron(0);
        NINeuron sector_2_4 = new NINeuron(0);
        NINeuron sector_3_4 = new NINeuron(0);
        NINeuron sector_4_4 = new NINeuron(0);

        //Так-же информация о состоянии отрядов:
        NINeuron squad_0 = new NINeuron(0);
        NINeuron squad_1 = new NINeuron(0);
        NINeuron squad_2 = new NINeuron(0);
        NINeuron squad_3 = new NINeuron(0);
        NINeuron squad_4 = new NINeuron(0);

        //Наполняем входной слой нейронами:
        sample.input.add(sector_0_0);
        sample.input.add(sector_1_0);
        sample.input.add(sector_2_0);
        sample.input.add(sector_3_0);
        sample.input.add(sector_4_0);

        sample.input.add(sector_0_1);
        sample.input.add(sector_1_1);
        sample.input.add(sector_2_1);
        sample.input.add(sector_3_1);
        sample.input.add(sector_4_1);

        sample.input.add(sector_0_2);
        sample.input.add(sector_1_2);
        sample.input.add(sector_2_2);
        sample.input.add(sector_3_2);
        sample.input.add(sector_4_2);

        sample.input.add(sector_0_3);
        sample.input.add(sector_1_3);
        sample.input.add(sector_2_3);
        sample.input.add(sector_3_3);
        sample.input.add(sector_4_3);

        sample.input.add(sector_0_4);
        sample.input.add(sector_1_4);
        sample.input.add(sector_2_4);
        sample.input.add(sector_3_4);
        sample.input.add(sector_4_4);

        //Последние 5 в примере это значения статусов отрядов:
        sample.input.add(squad_0);
        sample.input.add(squad_1);
        sample.input.add(squad_2);
        sample.input.add(squad_3);
        sample.input.add(squad_4);

        //---------------------------------------------ДАЛЕЕ формируем выходной слой:

        NINeuron no_action = new NINeuron(0);
        sample.output.add(no_action);

        //----------Самолеты:

        NINeuron squad_0_up = new NINeuron(0);

        NINeuron squad_0_center = new NINeuron(0);

        NINeuron squad_0_left = new NINeuron(0);

        NINeuron squad_0_right = new NINeuron(0);

        NINeuron squad_0_down = new NINeuron(0);

        NINeuron squad_0_move_to_squad_1 = new NINeuron(0);

        NINeuron squad_0_move_to_squad_2 = new NINeuron(0);

        NINeuron squad_0_move_to_squad_3 = new NINeuron(0);

        NINeuron squad_0_move_to_squad_4 = new NINeuron(0);

        sample.output.add(squad_0_up);
        sample.output.add(squad_0_center);
        sample.output.add(squad_0_left);
        sample.output.add(squad_0_right);
        sample.output.add(squad_0_down);
        sample.output.add(squad_0_move_to_squad_1);
        sample.output.add(squad_0_move_to_squad_2);
        sample.output.add(squad_0_move_to_squad_3);
        sample.output.add(squad_0_move_to_squad_4);

        //----------Вертолеты:

        NINeuron squad_1_up = new NINeuron(0);

        NINeuron squad_1_center = new NINeuron(0);

        NINeuron squad_1_left = new NINeuron(0);

        NINeuron squad_1_right = new NINeuron(0);

        NINeuron squad_1_down = new NINeuron(0);

        NINeuron squad_1_move_to_squad_0 = new NINeuron(0);

        NINeuron squad_1_move_to_squad_2 = new NINeuron(0);

        NINeuron squad_1_move_to_squad_3 = new NINeuron(0);

        NINeuron squad_1_move_to_squad_4 = new NINeuron(0);

        sample.output.add(squad_1_up);
        sample.output.add(squad_1_center);
        sample.output.add(squad_1_left);
        sample.output.add(squad_1_right);
        sample.output.add(squad_1_down);
        sample.output.add(squad_1_move_to_squad_0);
        sample.output.add(squad_1_move_to_squad_2);
        sample.output.add(squad_1_move_to_squad_3);
        sample.output.add(squad_1_move_to_squad_4);

        //----------Танк:

        NINeuron squad_2_up = new NINeuron(0);

        NINeuron squad_2_center = new NINeuron(0);

        NINeuron squad_2_left = new NINeuron(0);

        NINeuron squad_2_right = new NINeuron(0);

        NINeuron squad_2_down = new NINeuron(0);

        NINeuron squad_2_move_to_squad_0 = new NINeuron(0);

        NINeuron squad_2_move_to_squad_1 = new NINeuron(0);

        NINeuron squad_2_move_to_squad_3 = new NINeuron(0);

        NINeuron squad_2_move_to_squad_4 = new NINeuron(0);

        sample.output.add(squad_2_up);
        sample.output.add(squad_2_center);
        sample.output.add(squad_2_left);
        sample.output.add(squad_2_right);
        sample.output.add(squad_2_down);
        sample.output.add(squad_2_move_to_squad_0);
        sample.output.add(squad_2_move_to_squad_1);
        sample.output.add(squad_2_move_to_squad_3);
        sample.output.add(squad_2_move_to_squad_4);

        //----------Бмп:
        NINeuron squad_3_up = new NINeuron(0);

        NINeuron squad_3_center = new NINeuron(0);

        NINeuron squad_3_left = new NINeuron(0);

        NINeuron squad_3_right = new NINeuron(0);

        NINeuron squad_3_down = new NINeuron(0);

        NINeuron squad_3_move_to_squad_0 = new NINeuron(0);

        NINeuron squad_3_move_to_squad_1 = new NINeuron(0);

        NINeuron squad_3_move_to_squad_2 = new NINeuron(0);

        NINeuron squad_3_move_to_squad_4 = new NINeuron(0);

        sample.output.add(squad_3_up);
        sample.output.add(squad_3_center);
        sample.output.add(squad_3_left);
        sample.output.add(squad_3_right);
        sample.output.add(squad_3_down);
        sample.output.add(squad_3_move_to_squad_0);
        sample.output.add(squad_3_move_to_squad_1);
        sample.output.add(squad_3_move_to_squad_2);
        sample.output.add(squad_3_move_to_squad_4);

        //----------РЕМ:
        NINeuron squad_4_up = new NINeuron(0);

        NINeuron squad_4_center = new NINeuron(0);

        NINeuron squad_4_left = new NINeuron(0);

        NINeuron squad_4_right = new NINeuron(0);

        NINeuron squad_4_down = new NINeuron(0);

        NINeuron squad_4_move_to_squad_0 = new NINeuron(0);

        NINeuron squad_4_move_to_squad_1 = new NINeuron(0);

        NINeuron squad_4_move_to_squad_2 = new NINeuron(0);

        NINeuron squad_4_move_to_squad_3 = new NINeuron(0);

        sample.output.add(squad_4_up);
        sample.output.add(squad_4_center);
        sample.output.add(squad_4_left);
        sample.output.add(squad_4_right);
        sample.output.add(squad_4_down);
        sample.output.add(squad_4_move_to_squad_0);
        sample.output.add(squad_4_move_to_squad_1);
        sample.output.add(squad_4_move_to_squad_2);
        sample.output.add(squad_4_move_to_squad_3);

        // Формируем нашего оффицера!
        tactikal_officer = new NINetwork("Тактический оффицер");
        tactikal_officer.inputSample = sample.cloneNISample(sample, "Первоначальный, пустой пример");
        // Далее наполняем примерами
        tactikal_officer.samples = getLearnList();
        // Выполняем инициализацию:
        tactikal_officer.initialize();
    }

    //Метод выполняющий подбор действия в указанной ситуации:
    void work(){
            //Подготавливаем отчет об игровой ситуации, и выполняем принятие решения:
            tactikal_officer.inputSample = getGameInfo();
            int new_action = 0;
            new_action = tactikal_officer.getMaxActual();
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
    }

    //Данный метод подготавливает оценку игровой ситуации в удобный для оффицера формат:
    NISample getGameInfo(){
        NISample gameInfo = tactikal_officer.inputSample;

        //Обновляем в отрядах информацию о каличестве юнитов:
        strategy.squad_0_Fighter.updateSquadInfo();
        strategy.squad_1_Helecopter.updateSquadInfo();
        strategy.squad_2_Tank.updateSquadInfo();
        strategy.squad_3_Bmp.updateSquadInfo();
        strategy.squad_4_REM.updateSquadInfo();

        //Далее наполняем сбор информации об отрядах:
        gameInfo.input.get(strategy.XSectorCount + 1).intCount = getSquadInfo(strategy.squad_0_Fighter);
        gameInfo.input.get(strategy.XSectorCount + 2).intCount = getSquadInfo(strategy.squad_1_Helecopter);
        gameInfo.input.get(strategy.XSectorCount + 3).intCount = getSquadInfo(strategy.squad_2_Tank);
        gameInfo.input.get(strategy.XSectorCount + 4).intCount = getSquadInfo(strategy.squad_3_Bmp);
        gameInfo.input.get(strategy.XSectorCount + 5).intCount = getSquadInfo(strategy.squad_4_REM);

        //Далее добавляем информацию о состоянии секторов:
        for(int i = 0; i < gameInfo.input.size(); i ++){
            // Здесь 5 это смещение на нейроны отрядов:
            if(i < gameInfo.input.size() - 5) {
                Sector selected = strategy.allSectorList.get(i);
                selected.updateSectorInfo(strategy);
                gameInfo.input.get(i).intCount = getSectorInfo(selected);
            }
        }
        return gameInfo;
    }

    // Метод формирует информацию о секторе все сводится к int(очуметь!):
    int getSectorInfo(Sector sector){
        //Карта значений сектора(как ее видит тактический офицер):

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

        //Документация по точному порядку и значению данных приведена в текстовом файле.
        int result = 0;

        if(sector.allVehicleInSector.size() == 0){
            // Сетор пуст.
        }

        if(strategy.game.isFogOfWarEnabled() & sector.allVehicleInSector.size() == 0){

            // Сектор скрыт туманом войны.
            result = 1;
        }

        //Если в секторе есть юниты выполняем более точную оценку:
        if(sector.player_unit_count > 0 && sector.enemy_unit_count > 0){
            //Здесь необходимо оценить какой стороне на данный момент выгоден текущий конфликт;
            //пока будем считать просто чьих юнитов меньше;
            if(sector.player_unit_count > sector.enemy_unit_count){

                // в секторе присутствуют юниты обоих фракций, состояние битвы - численное превосходство игрока
                result = 62;
            }
            if(sector.enemy_unit_count > sector.player_unit_count){

                // в секторе присутствуют юниты обоих фракций, состояние битвы - численное превосходство проитвника
                result = 63;
            }
            if(sector.player_unit_count == sector.enemy_unit_count){

                // в секторе присутствуют юниты обоих фракций, состояние битвы - численное превосходство проитвника
                result = 63;
            }
        }
        //Теперь понятно обозначаем сектора игрока:
        if(sector.player_unit_count > 0 && sector.enemy_unit_count == 0){
            //Значит рассчитываем кто-же из игроков в данном секторе присутствует:

            //Проверяем еденичные отряды:
            if(sector.FIGHTERInSectorCount > 0){
                result = 2;
            }
            if(sector.HELECOPTERInSectorCount > 0){
                result = 3;
            }
            if(sector.TANKInSectorCount > 0){
                result = 4;
            }
            if(sector.BMPInSectorCount > 0){
                result = 5;
            }
            if(sector.REMInSectorCount > 0){
                result = 6;
            }

            //Проверяем парные совпадения;

            if(sector.FIGHTERInSectorCount > 0 && sector.HELECOPTERInSectorCount > 0){
                result = 7;
            }
            if(sector.FIGHTERInSectorCount > 0 && sector.TANKInSectorCount > 0){
                result = 8;
            }
            if(sector.FIGHTERInSectorCount > 0 && sector.BMPInSectorCount > 0){
                result = 9;
            }
            if(sector.FIGHTERInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 10;
            }
            if(sector.HELECOPTERInSectorCount > 0 && sector.TANKInSectorCount > 0){
                result = 11;
            }
            if(sector.HELECOPTERInSectorCount > 0 && sector.BMPInSectorCount > 0){
                result = 12;
            }
            if(sector.HELECOPTERInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 13;
            }
            if(sector.TANKInSectorCount > 0 && sector.BMPInSectorCount > 0){
                result = 14;
            }
            if(sector.TANKInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 15;
            }
            if(sector.BMPInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 16;
            }

                    //Проверяем тройные совпадения:

            if(sector.FIGHTERInSectorCount > 0 && sector.HELECOPTERInSectorCount > 0 && sector.TANKInSectorCount > 0){
                result = 17;
            }
            if(sector.FIGHTERInSectorCount > 0 && sector.TANKInSectorCount > 0 && sector.BMPInSectorCount > 0){
                result = 18;
            }
            if(sector.FIGHTERInSectorCount > 0 && sector.BMPInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 19;
            }
            if(sector.FIGHTERInSectorCount > 0 && sector.HELECOPTERInSectorCount > 0 && sector.BMPInSectorCount > 0){
                result = 20;
            }
            if(sector.FIGHTERInSectorCount > 0 && sector.HELECOPTERInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 21;
            }
            if(sector.HELECOPTERInSectorCount > 0 && sector.TANKInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 22;
            }
            if(sector.HELECOPTERInSectorCount > 0 && sector.TANKInSectorCount > 0 && sector.BMPInSectorCount > 0){
                result = 23;
            }
            if(sector.HELECOPTERInSectorCount > 0 && sector.BMPInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 24;
            }
            if(sector.HELECOPTERInSectorCount > 0 && sector.TANKInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 25;
            }
            if(sector.TANKInSectorCount > 0 && sector.BMPInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 26;
            }

            //Теперь проверяем тройное присутствие:
            if(sector.FIGHTERInSectorCount > 0 && sector.HELECOPTERInSectorCount > 0
                    && sector.TANKInSectorCount > 0 && sector.BMPInSectorCount > 0){
                result = 27;
            }

            if(sector.FIGHTERInSectorCount > 0 && sector.HELECOPTERInSectorCount > 0
                    && sector.TANKInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 28;
            }

            if(sector.FIGHTERInSectorCount > 0 && sector.HELECOPTERInSectorCount > 0
                    && sector.BMPInSectorCount > 0 && sector.REMInSectorCount > 0){
                result = 29;
            }

            if(sector.FIGHTERInSectorCount > 0 && sector.BMPInSectorCount > 0
                    && sector.TANKInSectorCount > 0 && sector.REMInSectorCount > 0 ){
                result = 30;
            }

            if(sector.HELECOPTERInSectorCount > 0 && sector.BMPInSectorCount > 0
                    && sector.TANKInSectorCount > 0 && sector.REMInSectorCount > 0 ){
                result = 31;
            }

            //Проверяем общее присутствие:
            if(sector.HELECOPTERInSectorCount > 0 && sector.BMPInSectorCount > 0
                    && sector.TANKInSectorCount > 0 && sector.REMInSectorCount > 0  && sector.FIGHTERInSectorCount > 0){
                result = 32;
            }
        }
        //Если в секторе только еденицы врага:
        if(sector.enemy_unit_count > 0 && sector.player_unit_count == 0){

                //Проверяем еденичные отряды:
            if(sector.FIGHTEREnemyInSectorCount > 0){
                result = 33;
            }
            if(sector.HELECOPTEREnemyInSectorCount > 0){
                result = 34;
            }
            if(sector.TANKEnemyInSectorCount > 0){
                result = 35;
            }
            if(sector.BMPEnemyInSectorCount > 0){
                result = 36;
            }
            if(sector.REMEnemyInSectorCount > 0){
                result = 37;
            }

            //Проверяем парные совпадения:

            if(sector.FIGHTEREnemyInSectorCount > 0 & sector.HELECOPTEREnemyInSectorCount > 0){
                result = 38;
            }
            if(sector.FIGHTEREnemyInSectorCount > 0 & sector.TANKEnemyInSectorCount > 0){
                result = 39;
            }
            if(sector.FIGHTEREnemyInSectorCount > 0 & sector.BMPEnemyInSectorCount > 0){
                result = 40;
            }
            if(sector.FIGHTEREnemyInSectorCount > 0 & sector.REMEnemyInSectorCount > 0){
                result = 41;
            }
            if(sector.HELECOPTEREnemyInSectorCount > 0 & sector.TANKEnemyInSectorCount > 0){
                result = 42;
            }
            if(sector.HELECOPTEREnemyInSectorCount > 0 & sector.BMPEnemyInSectorCount > 0) {
                result = 43;
            }
            if(sector.HELECOPTEREnemyInSectorCount > 0 & sector.REMEnemyInSectorCount > 0){
                result = 44;
            }
            if(sector.TANKEnemyInSectorCount > 0 & sector.REMEnemyInSectorCount > 0){
                result = 45;
            }
            if(sector.TANKEnemyInSectorCount > 0 & sector.BMPEnemyInSectorCount > 0){
                result = 46;
            }

            //Проверяем тройные совпадения:

            if(sector.FIGHTEREnemyInSectorCount > 0 && sector.HELECOPTEREnemyInSectorCount > 0 && sector.TANKEnemyInSectorCount > 0){
                result = 47;
            }
            if(sector.FIGHTEREnemyInSectorCount > 0 && sector.TANKEnemyInSectorCount > 0 && sector.BMPEnemyInSectorCount > 0){
                result = 48;
            }
            if(sector.FIGHTEREnemyInSectorCount > 0 && sector.BMPEnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0){
                result = 49;
            }
            if(sector.FIGHTEREnemyInSectorCount > 0 && sector.HELECOPTEREnemyInSectorCount > 0 && sector.BMPEnemyInSectorCount > 0){
                result = 50;
            }
            if(sector.FIGHTEREnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0 && sector.BMPEnemyInSectorCount > 0){
                result = 51;
            }
            if(sector.HELECOPTEREnemyInSectorCount > 0 && sector.TANKEnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0){
                result = 52;
            }
            if(sector.HELECOPTEREnemyInSectorCount > 0 && sector.BMPEnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0){
                result = 53;
            }
            if(sector.TANKEnemyInSectorCount > 0 && sector.BMPEnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0){
                result = 54;
            }
            if(sector.HELECOPTEREnemyInSectorCount > 0 && sector.FIGHTEREnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0){
                result = 55;
            }

            //Далее проверяем четверные совпадения:
            if(sector.FIGHTEREnemyInSectorCount > 0 && sector.HELECOPTEREnemyInSectorCount > 0
                   && sector.TANKEnemyInSectorCount > 0 && sector.BMPEnemyInSectorCount > 0){
                result = 56;
            }

            if(sector.FIGHTEREnemyInSectorCount > 0 && sector.HELECOPTEREnemyInSectorCount > 0
                    && sector.TANKEnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0){
                result = 57;
            }

            if(sector.FIGHTEREnemyInSectorCount > 0 && sector.HELECOPTEREnemyInSectorCount > 0
            && sector.BMPEnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0){
                result = 58;
            }

            if(sector.FIGHTEREnemyInSectorCount > 0 && sector.TANKEnemyInSectorCount > 0
                    && sector.BMPEnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0){
                result = 59;
            }

            if(sector.HELECOPTEREnemyInSectorCount > 0 && sector.HELECOPTEREnemyInSectorCount > 0
                    && sector.BMPEnemyInSectorCount > 0 && sector.REMEnemyInSectorCount > 0){
                result = 60;
            }
        }
        return result;
    }

    // Метод формирует информацию об отряде, все сводится к int:
    int getSquadInfo(Squad squad){

        // Карта смысловых обозначений состояния отряда:
        /*
            0. отряд ждет
            1. отряд передвигается
            2. отряд атакован
            3. отряд отступает
            4. предположитеьно данный отряд является целью войска противника(например изза приближения к сектору отряда)
        */
    /**
        int result = 0;

        return result;
    }

    //Метод построчно отображает информацию о ситуации на карте из примера(который после будет передан оффицеру).
    public void debug_map(NISample sample){

        //выводим информацию об отрядах:
        if(debug) {

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
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0,0,0,0, 0);
                strategy.setMoveOrder(0, 0,0);
                break;
            // Cамолеты в центр:
            case 2:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0,0,0,0, 0);
                strategy.setMoveOrder(strategy.allSectorList.get(12).centerPoint.x, strategy.allSectorList.get(12).centerPoint.y,0);
                break;
            // Самолеты влево:
            case 3:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0, 0, 0, 0, 0);
                strategy.setMoveOrder(0, strategy.world.getHeight() / 2,0);
                break;
            // Самолеты вправо:
            case 4:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0, 0, 0, 0, 0);
                strategy.setMoveOrder(strategy.world.getWidth() / 2, 0,0);
                break;
            // Самолеты вниз:
            case 5:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0, 0, 0, 0, 0);
                strategy.setMoveOrder(strategy.world.getWidth(), strategy.world.getHeight(),0);
                break;
            // Самолеты лететь к отряду 1(вертолеты):
            case 6:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0, 0, 0, 0, 0);
                strategy.getCenterHelecopter();
                strategy.setMoveOrder(strategy.HELECOPTERSquadPosition.x, strategy.HELECOPTERSquadPosition.y,0);
                break;
            // Самолеты лететь к отряду 2(Танки):
            case 7:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0, 0, 0, 0, 0);
                strategy.getCenterTank();
                strategy.setMoveOrder(strategy.TANKSquadPosition.x, strategy.TANKSquadPosition.y,0);
                break;
            // Самолеты лететь к отряду 3(БМП):
            case 8:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0, 0, 0, 0, 0);
                strategy.getCenterBmp();
                strategy.setMoveOrder(strategy.BMPSquadPosition.x, strategy.BMPSquadPosition.y,0);
                break;
            // Самолеты лететь к отряду 4(РЕМ):
            case 9:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0, 0, 0, 0, 0);
                strategy.getCenterRem();
                strategy.setMoveOrder(strategy.REMSquadPosition.x, strategy.REMSquadPosition.y,0);
                break;

            //-----------------------------ВЕРТОЛЕТЫ:
            // в вхний угол:
            case 10:
                //strategy.setSelectOrder(4, strategy.getVehicleByID(strategy.FIGHTERList.get(0)), 0, 0, 0, 0, 1);
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.HELECOPTERList.get(0)), 0,0,0,0, 1);
                strategy.setMoveOrder(0, 0,1);
                break;
            // в центр:
            case 11:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.HELECOPTERList.get(0)), 0, 0, 0, 0, 1);
                strategy.setMoveOrder(strategy.world.getWidth() / 2, strategy.world.getHeight() / 2,1);
                break;
            // влево:
            case 13:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.HELECOPTERList.get(0)), 0, 0, 0, 0, 1);
                strategy.setMoveOrder(0, strategy.world.getHeight() / 2,1);
                break;
            // вправо:
            case 14:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.HELECOPTERList.get(0)), 0, 0, 0, 0, 1);
                strategy.setMoveOrder(strategy.world.getWidth() / 2, 0,1);
                break;
            // вниз:
            case 15:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.HELECOPTERList.get(0)), 0, 0, 0, 0, 1);
                strategy.setMoveOrder(strategy.world.getWidth(), strategy.world.getHeight(),1);
                break;
            // ыдвигаться к отряду 1(вертолеты):
            case 16:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.HELECOPTERList.get(0)), 0, 0, 0, 0, 1);
                strategy.getCenterFighter();
                strategy.setMoveOrder(strategy.FIGHTERSquadPosition.x, strategy.FIGHTERSquadPosition.y,1);
                break;
            // выдвигаться к отряду 2(Танки):
            case 17:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.HELECOPTERList.get(0)), 0, 0, 0, 0, 1);
                strategy.getCenterTank();
                strategy.setMoveOrder(strategy.TANKSquadPosition.x, strategy.TANKSquadPosition.y,1);
                break;
            // выдвигаться к отряду 3(БМП):
            case 18:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.HELECOPTERList.get(0)), 0, 0, 0, 0, 1);
                strategy.getCenterBmp();
                strategy.setMoveOrder(strategy.BMPSquadPosition.x, strategy.BMPSquadPosition.y,1);
                break;
            // выдвигаться к отряду 4(РЕМ):
            case 19:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.HELECOPTERList.get(0)), 0, 0, 0, 0, 1);
                strategy.getCenterRem();
                strategy.setMoveOrder(strategy.REMSquadPosition.x, strategy.REMSquadPosition.y,1);
                break;

            //------------------------------------------ТАНКИ
            // в вхний угол:
            case 20:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.TANKList.get(0)), 0, 0, 0, 0, 2);
                strategy.setMoveOrder(0, 0,2);
                break;
            // в центр:
            case 21:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.TANKList.get(0)), 0, 0, 0, 0, 2);
                strategy.setMoveOrder(strategy.world.getWidth() / 2, strategy.world.getHeight() / 2,2);
                break;
            // влево:
            case 22:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.TANKList.get(0)), 0, 0, 0, 0, 2);
                strategy.setMoveOrder(0, strategy.world.getHeight() / 2,2);
                break;
            // вправо:
            case 23:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.TANKList.get(0)), 0, 0, 0, 0, 2);
                strategy.setMoveOrder(strategy.world.getWidth() / 2, 0,2);
                break;
            // вниз:
            case 24:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.TANKList.get(0)), 0, 0, 0, 0, 2);
                strategy.setMoveOrder(strategy.world.getWidth(), strategy.world.getHeight(),2);
                break;
            // выдвигаться к отряду 0(самолеты):
            case 25:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.TANKList.get(0)), 0, 0, 0, 0, 2);
                strategy.getCenterFighter();
                strategy.setMoveOrder(strategy.FIGHTERSquadPosition.x, strategy.FIGHTERSquadPosition.y,2);
                break;
            // выдвигаться к отряду 1(вертолеты):
            case 26:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.TANKList.get(0)), 0, 0, 0, 0, 2);
                strategy.getCenterHelecopter();
                strategy.setMoveOrder(strategy.HELECOPTERSquadPosition.x, strategy.HELECOPTERSquadPosition.y,2);
                break;
            // выдвигаться к отряду 3(БМП):
            case 27:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.TANKList.get(0)), 0, 0, 0, 0, 2);
                strategy.getCenterBmp();
                strategy.setMoveOrder(strategy.BMPSquadPosition.x, strategy.BMPSquadPosition.y,2);
                break;
            // выдвигаться к отряду 4(РЕМ):
            case 28:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.TANKList.get(0)), 0, 0, 0, 0, 2);
                strategy.getCenterRem();
                strategy.setMoveOrder(strategy.REMSquadPosition.x, strategy.REMSquadPosition.y,2);
                break;

            //--------------------------------------------БМП
            // в вхний угол:
            case 29:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.BMPList.get(0)), 0, 0, 0, 0, 3);
                strategy.setMoveOrder(0, 0,3);
                break;
            // в центр:
            case 30:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.BMPList.get(0)), 0, 0, 0, 0, 3);
                strategy.setMoveOrder(strategy.allSectorList.get(12).centerPoint.x, strategy.allSectorList.get(12).centerPoint.y,3);
                break;
            // влево:
            case 31:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.BMPList.get(0)), 0, 0, 0, 0, 3);
                strategy.setMoveOrder(0, strategy.world.getHeight() / 2,3);
                break;
            // вправо:
            case 32:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.BMPList.get(0)), 0, 0, 0, 0, 3);
                strategy.setMoveOrder(strategy.world.getWidth() / 2, 0,3);
                break;
            // вниз:
            case 33:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.BMPList.get(0)), 0, 0, 0, 0, 3);
                strategy.setMoveOrder(strategy.world.getWidth(), strategy.world.getHeight(),3);
                break;
            // выдвигаться к отряду 0(самолеты):
            case 34:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.BMPList.get(0)), 0, 0, 0, 0, 3);
                strategy.getCenterFighter();
                strategy.setMoveOrder(strategy.FIGHTERSquadPosition.x, strategy.FIGHTERSquadPosition.y,3);
                break;
            // выдвигаться к отряду 1(вертолеты):
            case 35:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.BMPList.get(0)), 0, 0, 0, 0, 3);
                strategy.getCenterHelecopter();
                strategy.setMoveOrder(strategy.HELECOPTERSquadPosition.x, strategy.HELECOPTERSquadPosition.y,3);
                break;
            // выдвигаться к отряду 2(Танк):
            case 36:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.BMPList.get(0)), 0, 0, 0, 0, 3);
                strategy.getCenterTank();
                strategy.setMoveOrder(strategy.TANKSquadPosition.x, strategy.TANKSquadPosition.y,3);
                break;
            // выдвигаться к отряду 4(РЕМ):
            case 37:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.BMPList.get(0)), 0, 0, 0, 0, 3);
                strategy.getCenterRem();
                strategy.setMoveOrder(strategy.REMSquadPosition.x, strategy.REMSquadPosition.y,3);
                break;

            //--------------------------------------------REM
            // в вхний угол:
            case 38:
                strategy.setSelectOrder(6, strategy.getVehicleByID(strategy.REMList.get(0)), 0, 0, 0, 0, 4);
                strategy.setMoveOrder(0, 0,4);
                break;
            // в центр:
            case 39:
                strategy.setSelectOrder(6,  strategy.getVehicleByID(strategy.REMList.get(0)), 0, 0, 0, 0, 4);
                strategy.setMoveOrder(strategy.world.getWidth() / 2, strategy.world.getHeight() / 2,4);
                break;
            // влево:
            case 40:
                strategy.setSelectOrder(6,  strategy.getVehicleByID(strategy.REMList.get(0)), 0, 0, 0, 0, 4);
                strategy.setMoveOrder(0, strategy.world.getHeight() / 2,4);
                break;
            // вправо:
            case 41:
                strategy.setSelectOrder(6,  strategy.getVehicleByID(strategy.REMList.get(0)), 0, 0, 0, 0, 4);
                strategy.setMoveOrder(strategy.world.getWidth() / 2, 0,4);
                break;
            // вниз:
            case 42:
                strategy.setSelectOrder(6,  strategy.getVehicleByID(strategy.REMList.get(0)), 0, 0, 0, 0, 4);
                strategy.setMoveOrder(strategy.world.getWidth(), strategy.world.getHeight(),4);
                break;
            // выдвигаться к отряду 0(самолеты):
            case 43:
                strategy.setSelectOrder(6,  strategy.getVehicleByID(strategy.REMList.get(0)), 0, 0, 0, 0, 4);
                strategy.getCenterFighter();
                strategy.setMoveOrder(strategy.FIGHTERSquadPosition.x, strategy.FIGHTERSquadPosition.y,4);
                break;
            // выдвигаться к отряду 1(вертолеты):
            case 44:
                strategy.setSelectOrder(6,  strategy.getVehicleByID(strategy.REMList.get(0)), 0, 0, 0, 0, 4);
                strategy.getCenterHelecopter();
                strategy.setMoveOrder(strategy.HELECOPTERSquadPosition.x, strategy.HELECOPTERSquadPosition.y,4);
                break;
            // выдвигаться к отряду 2(Танк):
            case 45:
                strategy.setSelectOrder(6,  strategy.getVehicleByID(strategy.REMList.get(0)), 0, 0, 0, 0, 4);
                strategy.getCenterTank();
                strategy.setMoveOrder(strategy.TANKSquadPosition.x, strategy.TANKSquadPosition.y,4);
                break;
            // выдвигаться к отряду 3(БМП):
            case 46:
                strategy.setSelectOrder(6,  strategy.getVehicleByID(strategy.REMList.get(0)), 0, 0, 0, 0, 4);
                strategy.getCenterBmp();
                strategy.setMoveOrder(strategy.BMPSquadPosition.x, strategy.BMPSquadPosition.y,4);
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
        ArrayList<NISample> returnedList = new ArrayList<>();

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
     **/
}
