import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] products = {"Молоко", "Хлеб", "Гречневая крупа"};
        int[] prices = {50, 14, 80}; // для сумм с копейками можно ввести double
        int[] basket = new int[products.length];
        int sumProducts = 0;

        while (true) {
            System.out.println("Список возможных товаров для покупки:");
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + ": " + products[i] + " " + prices[i] + " руб/шт.");
            }


            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("Программа завершена.");
                break;
            }
            String[] purchases = input.split(" ");
            int productNumber;
            int productCount;
            if (purchases.length != 2) {
                System.out.println("Некорректный ввод!!! - введите: номер товара от 1 до 3 и количество товара");
                continue;
            }

            try {

                productNumber = (Integer.parseInt(purchases[0])) - 1;
                productCount = Integer.parseInt(purchases[1]);

                if (productNumber < 0 || productNumber > products.length) {
                    System.out.println("Ошибка: Такого товара нет");
                    continue;
                } else if (productCount < 0) {
                    System.out.println("Ошибка: Введено некорректное количество товара");
                    continue;
                }
                sumProducts += prices[productNumber] * productCount;
                basket[productNumber] += productCount;
            } catch (RuntimeException e) {
                System.out.println("Введена некорректная информация");
            }
        }
        System.out.println("Ваша корзина:");
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] != 0) {
                System.out.println(products[i] + " " + basket[i] + " шт " + prices[i] + " руб/шт " + (basket[i] * prices[i]) + " рублей в сумме");

            }
        }

        System.out.println("Итого: " + sumProducts);

    }
}



