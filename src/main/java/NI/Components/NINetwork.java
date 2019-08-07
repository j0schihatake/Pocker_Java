package NI.Components;

import java.util.ArrayList;

// Универсальная нейронная сеть.
public class NINetwork {
    // name - УНИКАЛЬНОЕ название нейронной сети.
    public String name = "Новая нейронная сеть";

    // Количество учавствующих:
    private ArrayList<NINeuron> INPUT_NEURONS = new ArrayList<NINeuron>();

    // Количество скрытых слоев(по умолчанию 1):
    private int LAYER_HIDDEN_NEURONS = 1;

    // Число скрытых нейронов равно числу входных нейронов:
    private int HIDDEN_NEURONS = 1;
    private ArrayList<NINeuron> OUTPUT_NEURONS = new ArrayList<NINeuron>();

    // Коэффициент обучения:
    private float LEARN_RATE = 0.2f;

    // Случайные веса:
    private float RAND_WEIGHT = 0f;
    private float RAND_MAX = 0.5f;

    /**
     * Список имеющихся примеров(набор входных данных + известный "правильный результат")
     */
    public ArrayList<NISample> samples = new ArrayList<NISample>();

    // Входной слой, данные от пользователя
    public NISample inputSample = null;

    //--------------------------------------------Веса
    // Вход скрытых ячеек(со смещением)
    public float[][] wih;

    // Вход выходных ячеек(со смещением)
    public float[][] who;

    // Активаторы
    public float[] inputs;
    public float[] hidden;
    public float[] target;
    public float[] actual;

    // Ошибки
    public float[] erro;
    public float[] errh;
    public float err = 0f;
    public int sum = 0;

    public int iterations = 1000;

    /// <summary>
    /// Пример нейронной сети, по параметрам которого была создана текущая сеть.
    /// </summary>
    public NIInput input = null;

    /// <summary>
    /// Осмысленное название нейронной сети.
    /// </summary>
    public String description = "Новая нейронная сеть";

    /// <summary>
    /// Список "входных" нейронов сети.
    /// </summary>
    public ArrayList<NINeuron> inputNeuronList = new ArrayList<NINeuron>();

    /// <summary>
    /// Список "выходных, результативных" нейронов сети.
    /// </summary>
    public ArrayList<NINeuron> outputNeuronList = new ArrayList<NINeuron>();

    //----------------------------------------Доступные функции-----------------------------------------

    /// <summary>
    /// Конструктор используется в NINeuroCenter
    /// </summary>
    /// <param name="first_sample"></param>
    /// <param name="name"></param>
    /// <param name="ni_center"></param>
    public NINetwork(NIInput input, NIResult result, String name) { }

    // Еще один конструктор:
    public NINetwork(String name)
    {
        this.name = name;
    }

    /// <summary>
    /// Задать коэффициент обучения:
    /// </summary>
    /// <param name="rate"></param>
    public void setLearnRate(float rate)
    {
        this.LEARN_RATE = rate;
    }

    /// <summary>
    /// Запуск первоначальной настройки сети
    /// (рандомные веса, и скрытый слой, массивы ошибок)
    /// </summary>
    public void initialize()
    {
        NISample initSample = null;
        // В зависимости от установленного источника примеров
        initSample = inputSample;

        //Получаем количество входов и выходов на основе первого примера.
        INPUT_NEURONS = initSample.input;
        HIDDEN_NEURONS = INPUT_NEURONS.size();
        OUTPUT_NEURONS = initSample.output;

        // Вход скрытых ячеек(смещение удаляю)
        wih = new float[INPUT_NEURONS.size()][HIDDEN_NEURONS];

        // Вход выходных ячеек(смещение удаляю)
        who = new float[HIDDEN_NEURONS][OUTPUT_NEURONS.size()];

        // Активаторы
        inputs = new float[INPUT_NEURONS.size()];
        hidden = new float[HIDDEN_NEURONS];
        target = new float[OUTPUT_NEURONS.size()];
        actual = new float[OUTPUT_NEURONS.size()];

        // Ошибки
        erro = new float[OUTPUT_NEURONS.size()];
        errh = new float[HIDDEN_NEURONS];

        // Инициализировать генератор случайных чисел
        assignRandomWeights();

        if(samples.contains(initSample))
        {
            samples.remove(initSample);
        }
        // Если в списке примеров имеются примеры то выполняем обучение:
        if(samples.size() > 0)
        {
            learn();
        }
    }

    /// <summary>
    /// Выполнить обучение нейронной сети: return bool;
    /// </summary>
    /// <returns></returns>
    public void learn()
    {
        int iter_Count = 0;
        //Обучить сеть
        while(iter_Count < iterations)
        {
            // Пробегаем каждый пример:
            for(int i = 0; i < samples.size(); i++)
            {
                NISample current = samples.get(i);
                // Пробегаем каждый входной нейрон
                for(int j = 0; j < current.input.size(); j++)
                {
                    // Наполняем входной слой примерами:
                    switch(current.input.get(j).type)
                    {
                        case int_:
                            inputs[j] = (float)current.input.get(j).intCount;
                            break;
                        case float_:
                            inputs[j] = (float)current.input.get(j).floatCount;
                            break;
                        case bool_:
                            if(current.input.get(j).boolCount)
                            {
                                inputs[j] = (float)1;
                            }
                            else
                            {
                                inputs[j] = (float)0;
                            }
                            break;
                    }
                    // Наполняем выходной слой известными результатами:
                    switch(current.output.get(j).type)
                    {
                        case int_:
                            target[j] = (float)current.output.get(j).intCount;
                            break;
                        case float_:
                            target[j] = (float)current.output.get(j).floatCount;
                            break;
                        case bool_:
                            if(current.output.get(j).boolCount)
                            {
                                target[j] = (float)1;
                            }
                            else
                            {
                                target[j] = (float)0;
                            }
                            break;
                    }
                }

                feedForward();

                err = 0.0f;
                //Квадратичная ошибка для каждого из выходов:
                for(int k = 0; k < current.output.size(); k++)
                {
                    err += (float)Math.sqrt((target[k] - actual[k]));
                }

                err = 0.5f * err;
                iter_Count++;
                // собственно выполняем обучение
                backPropagate();
            }
            // System.out.println("Всего итераций: " + iterations + ", текущая итерация обучения: " + iter_Count );
        }
    }

    /// <summary>
    /// FeedForward - прямое распространение(обработка информации)
    /// </summary>
    public void feedForward()
    {
        int inp, hid, outs;
        float sum;

        // Вычислить вход в скрытый слой
        for(hid = 0; hid < HIDDEN_NEURONS; hid++)
        {
            sum = 0f;
            for(inp = 0; inp < INPUT_NEURONS.size(); inp++)
            {
                sum += inputs[inp] * wih[inp][hid];
            }
            // Добавить смещение
            // sum += wih[INPUT_NEURONS, hid];
            hidden[hid] = sigmoid(sum);
        }

        // Вычислить вход в выходной слой
        for(outs = 0; outs < OUTPUT_NEURONS.size(); outs++)
        {
            sum = 0.0f;
            for(hid = 0; hid < HIDDEN_NEURONS; hid++)
            {
                sum += hidden[hid] * who[hid][outs];
            }
            // Добавить смещение
            // sum += who[HIDDEN_NEURONS, outs];
            actual[outs] = sigmoid(sum);
        }
    }

    // Метод возвращает максимальное значение(или index из списка):
    public int getMaxActual()
    {

        int result = 0;
        float min = 0;

        // выполняем собственно обработку информации:
        feedForward();

        for(int i = 0; i < actual.length; i++)
        {
            if(min < actual[i])
            {
                min = actual[i];
                result = i;
            }
        }
        // Возвращаем индекс правильного действия:
        return result;
    }

    /// <summary>
    /// BackPropagate - обратное распространение(обучение)
    /// </summary>
    void backPropagate()
    {
        int inp, hid, outs;

        // Вычислить ошибку выходного слоя (шаг 3 для выходных ячеек)
        for(outs = 0; outs < OUTPUT_NEURONS.size(); outs++)
        {
            erro[outs] = (target[outs] - actual[outs]) * sigmoidDerivative(actual[outs]);
        }
        // Вычислить ошибку скрытого слоя (шаг 3 для скрытого слоя)
        for(hid = 0; hid < HIDDEN_NEURONS; hid++)
        {
            errh[hid] = 0.0f;
            for(outs = 0; outs < OUTPUT_NEURONS.size(); outs++)
            {
                errh[hid] += erro[outs] * who[hid][outs];
            }
            errh[hid] *= sigmoidDerivative(hidden[hid]);
        }
        // Обновить веса для выходного слоя(шаг 4 для выходных ячеек)
        for(outs = 0; outs < OUTPUT_NEURONS.size(); outs++)
        {
            for(hid = 0; hid < HIDDEN_NEURONS; hid++)
            {
                who[hid][outs] += (LEARN_RATE * erro[outs] * hidden[hid]);
            }
            // Обновить смещение
            //who[HIDDEN_NEURONS][outs] += (LEARN_RATE * erro[outs]);
        }
        // Обновить веса для скрытого слоя (шаг 4 для скрытого слоя)
        for(hid = 0; hid < HIDDEN_NEURONS; hid++)
        {
            for(inp = 0; inp < INPUT_NEURONS.size(); inp++)
            {
                wih[inp][hid] += (LEARN_RATE * errh[hid] * inputs[inp]);
            }
            // Обновить смещение
            //wih[INPUT_NEURONS.size()][hid] += (LEARN_RATE * errh[hid]);
        }
    }

    /// <summary>
    /// Вернуть список(пример решения) результативных значений: return float[];
    /// </summary>
    /// <returns></returns>
    public float[] getResultList()
    {
        return actual;
    }

    /// <summary>
    /// функция вычисления рандома.
    /// </summary>
    /// <returns></returns>
    private float getRandomWEIGHT()
    {
        float rand = 0f;
        //float rand = new Random()..Range(-0.5f, 0.5f);
        return rand;
    }

    /// <summary>
    /// Назначение случайных весов(например при иницилизации)
    /// </summary>
    public void assignRandomWeights()
    {
        int hid, inp, outs;
        // Назначаем случайные веса(по идее только первый раз)
        for(inp = 0; inp < INPUT_NEURONS.size(); inp++)
        {
            for(hid = 0; hid < HIDDEN_NEURONS; hid++)
            {
                RAND_WEIGHT = getRandomWEIGHT();
                wih[inp][hid] = RAND_WEIGHT;
            }
        }
        for(hid = 0; hid < HIDDEN_NEURONS; hid++)
        {
            for(outs = 0; outs < HIDDEN_NEURONS; outs++)
            {
                who[hid][outs] = RAND_WEIGHT;
            }
        }
    }

    /// <summary>
    /// Sigmoid - функция сжатия.
    /// </summary>
    /// <param name="val"></param>
    /// <returns></returns>
    private float sigmoid(float val)
    {
        return (1.0f / (1.0f + (float)Math.exp((-val))));
    }

    /// <summary>
    /// sigmoidDerigative - функция сжатия.
    /// </summary>
    /// <param name="val"></param>
    /// <returns></returns>
    private float sigmoidDerivative(float val)
    {
        return (val * (1.0f - val));
    }

    /// <summary>
    /// Данный метод проверяет входящий пример на корректность, и применяет различные методики для предотвращения ошибок,
    /// например при большем количестве данных во входящем примере он игнорирует, отсутствующие данные.
    /// </summary>
    private NISample sample_Loader(NISample loadSample)
    {
        NISample returned = loadSample;
        return returned;
    }
}
