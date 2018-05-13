package util;

import java.util.Scanner;

/**
 * Ввод в консоль
 * Denis
 * 10.03.2018
 */
public class ConsoleInput {
    private Scanner scanner;
    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    // Ввод числа в диапазоне от min до max
    public int enterNumber(int min, int max) {
        int number;
        while (true) {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number >= min && number <= max) {
                    break;
                }
            } else {
                scanner.nextLine(); // ВсЁ, чТо УгОдНО КрОмЕ чИсЕл
            }
        }
        return number;
    }
}
