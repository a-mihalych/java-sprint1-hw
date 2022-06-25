import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // для начального меню
        String[] menuBeginNumber = {"1", "2", "3", "0"};
        String[] menuBeginLine = {"Ввод количества шагов за определённый день",
                                  "Посмотреть статистику за определённый месяц",
                                  "Изменить цель по количеству шагов за день",
                                  "Выход из приложения"};
        String[][] menuBegin = {menuBeginNumber, menuBeginLine};
        // меню для месяца
        String[] menuMonthNumber = {"Число от 1 до 12", "0"};
        String[] menuMonthLine = {"Ввод месяца (число, номер месяца)",
                                  "Возврат в начальное меню"};
        String[][] menuMonth = {menuMonthNumber, menuMonthLine};
        // меню для дня
        String[] menuDayNumber = {"Число от 1 до 30", "0"};
        String[] menuDayLine = {"Ввод дня (число)",
                                "Возврат в начальное меню"};
        String[][] menuDay = {menuDayNumber, menuDayLine};
        // меню для шагов
        String[] menuStepsNumber = {"Неотрицательное число от 0", "-1"};
        String[] menuStepsLine = {"Ввод количества шагов",
                                  "Возврат в начальное меню"};
        String[][] menuSteps = {menuStepsNumber, menuStepsLine};

        Scanner scanner = new Scanner(System.in);
        int userInput;
        int day;
        int month;
        int steps;
        StepTracker stepTracker = new StepTracker();
        do {
            printMenu("-= Счётчик калорий =-", menuBegin);
            userInput = getNumber(scanner);
            switch (userInput) {
                case 1: // Ввод количества шагов за определённый день
                    System.out.println(menuBegin[1][0] + "]");
                    printMenu("- Ввод пройденных шагов -", menuSteps);
                    steps = getNumber(scanner);
                    if (steps != -1) {
                        System.out.println(menuSteps[1][0] + "]");
                        printMenu("- Ввод месяца -", menuMonth);
                        month = getNumber(scanner);
                        if (month != 0) {
                            System.out.println(menuMonth[1][0] + "]");
                            printMenu("- Ввод дня -", menuDay);
                            day = getNumber(scanner);
                            if (day != 0) {
                                System.out.println(menuDay[1][0] + "]");
                                System.out.println(stepTracker.saveStepsDay(steps, month, day));
                            } else {
                                System.out.println(menuDay[1][1] + "]");
                            }
                        } else {
                            System.out.println(menuMonth[1][1] + "]");
                        }
                    } else {
                        System.out.println(menuSteps[1][1] + "]");
                    }
                    break;
                case 2: // Посмотреть статистику за определённый месяц
                    System.out.println(menuBegin[1][1] + "]");
                    printMenu("- Ввод месяца -", menuMonth);
                    month = getNumber(scanner);
                    if (month != 0) {
                        System.out.println(menuMonth[1][0] + "]");
                        System.out.println(stepTracker.showStatistics(month));
                    } else {
                        System.out.println(menuMonth[1][1] + "]");
                    }
                    break;
                case 3: // Изменить цель по количеству шагов за день
                    System.out.println(menuBegin[1][2] + "]");
                    printMenu("- Ввод цели на день (пройденные шаги) -", menuSteps);
                    steps = getNumber(scanner);
                    if (steps != -1) {
                        System.out.println(menuSteps[1][0] + "]");
                        System.out.println(stepTracker.setGoalStepsDay(steps));
                    } else {
                        System.out.println(menuSteps[1][1] + "]");
                    }
                    break;
                case 0: // Выйти из приложения
                    System.out.println(menuBegin[1][3] + "]");
                    break;
                default: // число вне границ (0 - 3)
                    System.out.println("Неверный ввод! Нужно ввести число из меню]");
            }
        } while (userInput != 0);
        System.out.println("Программа завершена");
    }

    // печать меню
    private static void printMenu(String head, String[][] menu) {
        System.out.println("\n\n" + head + "\n");
        for (int i = 0; i < menu[0].length; i++) {
            System.out.println(menu[0][i] + " [" + menu[1][i] + "]");
        }
        System.out.print("\nОжидание ввода: ");
    }

    // проверка типа, на целое число
    private static int getNumber(Scanner scanner) {
        int result;
        while (!scanner.hasNextInt()) {
            System.out.print("Нужно ввести целое число: ");
            scanner.next();
        }
        result = scanner.nextInt();
        System.out.print("\nВаш ввод: " + result + " [");
        return result;
    }
}
