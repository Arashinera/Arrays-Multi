package app;

import java.util.Scanner;
import java.util.Locale;
import java.util.Random;

//Arrays Multi
//--------------
//Лотерейний квиток має чотири рядки та чотири
//стовпчики різних цілочисельних позитивних
//чисел.
//Одне число - є вигришним.
//Створіть двовимірний масив цих чисел.
//Реалізуйте функціонал пошуку виграшного числа
//шляхом введення через Scanner.

public class Main {

    static int[][] numArr;
    static int[] winArr;
    static int winLength;
    static int numRow;
    static int numCol;
    static int numWin;
    static String choiceStr;
    static int choiceNum;
    static double sum;
    static int count;
    public static final String CURRENCY = "EUR";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.ENGLISH);
        Random random = new Random();

        //Вказуємо розміри масивів :
        do {
            System.out.println("Input number of STRING numbers from 3 to 5 : ");
            numRow = input.nextInt();
        } while (numRow < 3 || numRow > 5);
        do {
            System.out.println("Input number of COLUMN numbers from 3 to 5 : ");
            numCol = input.nextInt();
        } while (numCol < 3 || numCol > 5);

        winLength = numRow * numCol;

        //Призначаємо розміри масивам :
        numArr = new int[numRow][numCol];
        winArr = new int[winLength];

        //Заповнюємо масив :
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                numArr[i][j] = random.nextInt(98) + 1;
            }
        }

        System.out.println("\nHere is your lottery ticket : \n");

        //Виводимо масив :
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++)
                System.out.printf("%s\t", numArr[i][j]);
            System.out.println();
        }

        //Розраховуємо дільник для поділу без залишку :
        numWin = (random.nextInt(3) + 3);

        //Створюємо локальний лічильник поза межами циклу :
        int k = 0;

        //Вказівник для зовнішнього циклу :
        Loops:
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                //Передаємо виграшні значення до виграшного одномірного масиву :
                if (numArr[i][j] % numWin == 0) {
                    winArr[k] = numArr[i][j];
                    k++;
                    if (k == winLength) {
                        //Вихід із зовнішнього циклу :
                        break Loops;
                    }
                }
            }
        }

        //Фіксимо сканер :
        input.nextLine();

        //Створюємо контекстне меню :
        do {
            System.out.println("\nWhat do you want to do? :\n- Input \"Clue\" if you want to take a look for a clue :\n- Input \"Choice\" if you wanna try to win :");
            choiceStr = input.nextLine();
            switch (choiceStr) {
                case "Clue", "clue":
                    System.out.printf("%n!!!Winning numbers are divided without remainder by %d!!!%n", numWin);
                    break;
            }
        } while (!choiceStr.equals("Choice"));

        System.out.println("\nOk... So, What is your answer?");
        choiceNum = input.nextInt();

        //Робимо перевірку на виграш :
        for (int i = 0; i < winLength; i++) {
            if (choiceNum == winArr[i]) {
                choiceNum = 5672;
                break;
            }
        }

        //Рахуємо виграш :
        sum = random.nextDouble(50) + 50;

        //Створюємо перевірку :
        if (choiceNum == 5672) {
            System.out.printf("\nYOU WIN %s %.2f!!!", CURRENCY, sum);
        } else {
            System.out.println("\nYou lose....");
        }

        //Виводимо нумерований список виграшних номерів :
        System.out.println("\nHere is the list of winning numbers :");
        for (int i = 0; i < winLength; i++) {
            if (winArr[0] == 0) {
                System.out.println("No matches...");
                break;
            } else if (winArr[i] == 0) {
                break;
            }
            count++;
            System.out.printf("%d) %d%n", count, winArr[i]);
        }

        //Закриваємо сканер :
        input.close();
    }

}
