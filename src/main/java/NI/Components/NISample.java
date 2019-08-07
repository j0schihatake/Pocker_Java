package NI.Components;

import java.util.ArrayList;

// NISample - пример входной набор данных + заданный(известный) результат.
public class NISample {

    // Уникальное, смысловое название набора входных + заданных выходных данных(пример).
    public String description = "Набор входных данных + заданных выходных данных(пример).";

    // Список входных нейронов со значениями:
    public ArrayList<NINeuron> input = new ArrayList<NINeuron>();

    // Список выходных нейронов со значениями:
    public ArrayList<NINeuron> output = new ArrayList<NINeuron>();

    public NISample cloneNISample(NISample sample, String description_new_sample){

        NISample returned = new NISample();

        returned.description = description_new_sample;

        //Клонируем каждый входной нейрон:
        for(int i = 0; i < sample.input.size(); i++){
            NINeuron next = sample.input.get(i);
            NINeuron nextClone = new NINeuron(next.typeIndex);
            nextClone.intCount = next.intCount;
            nextClone.boolCount = next.boolCount;
            nextClone.floatCount = next.floatCount;
            returned.input.add(nextClone);
        }

        //Клонируем каждый выходной нейрон:
        for(int j = 0; j < sample.output.size(); j++){
            NINeuron next = sample.output.get(j);
            NINeuron nextClone = new NINeuron(next.typeIndex);
            nextClone.intCount = next.intCount;
            nextClone.boolCount = next.boolCount;
            nextClone.floatCount = next.floatCount;
            returned.output.add(nextClone);
        }

        return returned;
    }
}
