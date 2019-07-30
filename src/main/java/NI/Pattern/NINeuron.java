package NI.Pattern;

public class NINeuron {

    // Конструктор с автоматической настройкой типа нейрона:
    public NINeuron(int type){
        switch(type){
            case 0:
                this.type = type_Info.int_;
                break;
            case 1:
                this.type = type_Info.float_;
                break;
            case 2:
                this.type = type_Info.bool_;
                break;
        }
        this.typeIndex = type;
    }

    // Тип информации которую подразумевает под собой нейрон.
    public type_Info type;
    public enum type_Info
    {
        int_,                                   // Тип данного нейрона "Целочисленный"
        float_,                                 // Тип данного нейрона "Число с плавающей точкой"
        bool_,                                  // Тип данного нейрона "Булевый"
    }

    // Понятное пояснение что обозначает данный нейрон.
    public String description = "Новый нейрон";

    // В случае если данный нейрон несет в себе "целочисленный" смысл, хранит текущее значение.
    public int intCount = 0;

    // В случае если данный нейрон несет в себе смысл "числа с плавающей точкой", хранит текущее значение.
    public float floatCount = 0.0f;

    // В случае если данный нейрон несет в себе "булевый" смысл, хранит текущее значение.
    public boolean boolCount = false;

    public int typeIndex = 0;
}
