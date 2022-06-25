public class StepTracker {

    private MonthData[] monthToData;    // массив массивов MonthData[12]
                                        // конечный массив int[30], заполнен 0
    private int goalStepsDay;   // цель на день, количество шагов (10 000)

    public StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
        goalStepsDay = 10000;
    }

    class MonthData {
        private int[] stepsEveryDayMonth;   // массив дней в месяце (30)
        public MonthData() {
            stepsEveryDayMonth = new int[30];
        }
    }

    // изменение цели на день, количество шагов (не отрицательное)
    public String setGoalStepsDay(int goalStepsDay) {
        // проверка на неотрицательное число
        if (goalStepsDay >= 0) {
            this.goalStepsDay = goalStepsDay;
            return "\nИзменена цель на день, новая цель - " + goalStepsDay + " шагов в день";
        }
        return "\nНе удалось изменить цель на день!\nЧисло шагов не должно быть отрицательным [" + goalStepsDay + "]";
    }

    // запись пройденных шагов за день:
    // шаги (не отрицательное), месяц(1 - 12), день(1 - 30)
    public String saveStepsDay(int stepsDays, int month, int day) {
        String data = day + "." + (month < 10 ? 0 : "") + month;
        // проверка, верны ли аргументы
        if ((month > 0) && (month < 13) && (day > 0) && (day < 31) && (stepsDays >= 0)) {
            month--;
            day--;
            monthToData[month].stepsEveryDayMonth[day] = stepsDays;
            return "\nШаги (" + stepsDays + "), пройденые " + data + " сохранены";
        }
        // фомируется строка с описанием ошибки
        String result = "\nНе удалось записать количество шагов за день!";
        if (stepsDays < 0) {
            result += ("\nЧисло шагов не должно быть отрицательным [" + stepsDays + "]");
        }
        if (!((month > 0) && (month < 13) && (day > 0) && (day < 31))) {
            result += ("\nНе верная дата [" + data + "]");
        }
        return result;
    }

    // показ статистики за месяц (1 - 12)
    public String showStatistics(int month) {
        String result;
        // проверка аргумента месяца
        if ((month > 0) && (month < 13)) {
            result = "\n";
            month--;
            Converter converter = new Converter();
            int sumStepsMonth = 0;  // шаги за месяц
            int maxStepsDey = 0;    // максимум в день
            int seriesMoreGoalsMax = 0; // серия подряд больше цели за день max
            int seriesMoreGoalsCurrent = 0; // серия подряд больше цели за день текущая
            for (int i = 0; i < monthToData[month].stepsEveryDayMonth.length; i++) {
                // вывод пройденых шагов по дням
                result += ((i + 1) + " день: " + monthToData[month].stepsEveryDayMonth[i]);
                if (i < (monthToData[month].stepsEveryDayMonth.length - 1)) {
                    result += ", ";
                }
                // суммирование шагов за месяц
                sumStepsMonth += monthToData[month].stepsEveryDayMonth[i];
                // поиск максимума в день
                if (maxStepsDey < monthToData[month].stepsEveryDayMonth[i]) {
                    maxStepsDey = monthToData[month].stepsEveryDayMonth[i];
                }
                // поиск серий, больше цели за день
                if (monthToData[month].stepsEveryDayMonth[i] >= goalStepsDay) {
                    seriesMoreGoalsCurrent++;
                    if (seriesMoreGoalsCurrent > seriesMoreGoalsMax) {
                        seriesMoreGoalsMax = seriesMoreGoalsCurrent;
                    }
                } else {
                    seriesMoreGoalsCurrent = 0;
                }
            }
            result += ("\nОбщее количество шагов за месяц: " + sumStepsMonth);
            result += ("\nМаксимальное пройденное количество шагов в месяце: " + maxStepsDey);
            result += ("\nСреднее количество шагов: " + (sumStepsMonth / monthToData[month].stepsEveryDayMonth.length));
            result += ("\nПройденная дистанция: " + converter.stepsInKm(sumStepsMonth) + " км");
            result += ("\nКоличество сожжённых килокалорий: " + converter.stepsInKCalories(sumStepsMonth));
            result += ("\nЛучшая серия: максимальное количество подряд идущих дней,");
            result += ("\nв течение которых количество шагов за день было равно или выше целевого: " + seriesMoreGoalsMax);
        } else { // вывод, не правильно введён месяц
            result = "\nНе удалось записать количество шагов за день!\nНе верно введён месяц (1 - 12): [" + month + "]";
        }
        return result;
    }
}
