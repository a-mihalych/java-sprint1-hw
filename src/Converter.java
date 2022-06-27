public class Converter {
    // исправлено пожелание
    // сделаны константы и без конструктора
    private final double STEP_LENGTH = 0.00075;         // длина шага в км (75 см)
    private final double STEP_IN_KILOCALORIES = 0.050;  // шаг в килокалориях (50 колорий)

    // исправлено пожелание, возврат значения
    // расчёт пройденного расстояния по количеству шагов (км)
    public double stepsInKm(int steps) {
        return steps * STEP_LENGTH;
    }

    // расчёт потраченных килокалорий по количеству шагов
    public double stepsInKCalories(int steps) {
        return steps * STEP_IN_KILOCALORIES;
    }
}