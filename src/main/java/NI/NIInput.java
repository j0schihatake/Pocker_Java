package NI;

import java.util.ArrayList;


// NIInput - данный обьект формирует некоторый набор входных данных.
public class NIInput
{
            // Уникальное, смысловое название набора входных данных(input).
            public String description = "Набор входных данных.";

            // Список входных нейронов со значениями:
            public ArrayList<NINeuron> input = new ArrayList<NINeuron>();
}