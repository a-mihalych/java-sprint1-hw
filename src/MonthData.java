// исправлено пожелание, этот класс вынесен из класса StepTracker
public class MonthData {
    private int[] stepsEveryDayMonth;   // массив дней в месяце (30)

    public MonthData() {
        stepsEveryDayMonth = new int[30];
    }

    public int[] getStepsEveryDayMonth() {
        return stepsEveryDayMonth.clone();
    }
    public void setStepsEveryDayMonth(int[] stepsEveryDayMonth) {
        this.stepsEveryDayMonth = stepsEveryDayMonth;
    }
}
