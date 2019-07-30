package NI.Pattern;

import java.util.ArrayList;

public class NIResult {

    // Уникальное, смысловое название выходных данных(обычно это результат работы сети).
    public String description = "Набор выходных данных(обычно это результат работы сети).";

    // Список выходных нейронов со значениями:
    public ArrayList<NINeuron> output = new ArrayList<NINeuron>();
}
