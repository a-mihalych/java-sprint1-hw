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
            int[] stepsEveryDayMonth = monthToData[month].getStepsEveryDayMonth();
            stepsEveryDayMonth[day] = stepsDays;
            monthToData[month].setStepsEveryDayMonth(stepsEveryDayMonth);
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
            month--;
            Converter converter = new Converter();
            // копия массива с шагами за нужный месяц
            int[] steps = monthToData[month].getStepsEveryDayMonth();
            // добавлена распечатка шагов по дням
            result = getStepsByDay(steps);
            // шаги за месяц
            int sumStepsMonth = getSumStepsMonth(steps);
            result += ("\nОбщее количество шагов за месяц: " + sumStepsMonth);
            // максимальное количество шагов в день за нужный месяц
            int MaxStepsDey = getMaxStepsDey(steps);
            result += ("\nМаксимальное пройденное количество шагов в месяце: " + MaxStepsDey);
            // добавлено среднее количество шагов с точностью до сотых
            result += String.format("\nСреднее количество шагов: %.2f", getAverageNumberSteps(sumStepsMonth, steps.length));
            // добавлено пройденное расстояние в километрах с точностью до сотых
            result += String.format("\nПройденная дистанция: %.2f км", converter.stepsInKm(sumStepsMonth));
            // добавлено потраченные килокалории с точностью до сотых
            result += String.format("\nКоличество сожжённых килокалорий: %.2f", converter.stepsInKCalories(sumStepsMonth));
            // добавлено лучшая серия дней подряд
            // когда количество шагов за день было равно или выше цели
            result += ("\nЛучшая серия: максимальное количество подряд идущих дней,\nв течение которых ");
            result += ("количество шагов за день было равно или выше целевого: " + getSeriesStepsMoreGoals(steps));
        } else { // вывод, не правильно введён месяц
            result = "\nНе удалось записать количество шагов за день!\nНе верно введён месяц (1 - 12): [" + month + "]";
        }
        return result;
    }

    // пройденные шаги по дням (массив шагов за месяц)
    public String getStepsByDay(int[] steps) {
        String result = "\n";
        for (int i = 0; i < steps.length; i++) {
            result += ((i + 1) + " день: " + steps[i]);
            if (i < (steps.length - 1)) {
                result += ", ";
            }
        }
        return result;
    }
    // сумма шагов за месяц (массив шагов за месяц)
    public int getSumStepsMonth(int[] steps) {
        int result = 0;
        for (int i = 0; i < steps.length; i++) {
            result += steps[i];
        }
        return result;
    }
    // максимум шагов за день (массив шагов за месяц)
    public int getMaxStepsDey(int[] steps) {
        int maxStepsDey = 0;
        for (int i = 0; i < steps.length; i++) {
            if (maxStepsDey < steps[i]) {
                maxStepsDey = steps[i];
            }
        }
        return maxStepsDey;
    }
    // Среднее Число Шагов (сумма шагов за месяц) (число дней)
    public double getAverageNumberSteps(int sumSteps, int numberOfDays) {
        return (double)sumSteps / numberOfDays;
    }
    // поиск серий, дни подряд больше цели за день (массив шагов за месяц)
    public int getSeriesStepsMoreGoals(int[] steps) {
        // серия, дни подряд больше цели за день (max)
        int seriesMoreGoalsMax = 0;
        // серия, дни подряд больше цели за день (текущая)
        int seriesMoreGoalsCurrent = 0;
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] >= goalStepsDay) {
                seriesMoreGoalsCurrent++;
                if (seriesMoreGoalsCurrent > seriesMoreGoalsMax) {
                    seriesMoreGoalsMax = seriesMoreGoalsCurrent;
                }
            } else {
                seriesMoreGoalsCurrent = 0;
            }
        }
        return seriesMoreGoalsMax;
    }
}
