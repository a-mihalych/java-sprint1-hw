public class Converter {
    private int stepLength;     // длина в см (75)
    private int stepInCalories; // шаг в калориях (50)

    public Converter() {
        stepLength = 75;
        stepInCalories = 50;
    }

    // расчёт пройденного расстояния по количеству шагов (км)
    public double stepsInKm(int steps) {
        return steps * stepLength / 100000;
    }

    // расчёт потраченных килокалорий по количеству шагов
    public double stepsInKCalories(int steps) {
        return steps * stepInCalories / 1000;
    }
}