import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] products = {"Молоко", "Хлеб", "Гречневая крупа", "Сахар", "Соль"};
        String[] sale = {"Гречневая крупа", " Сахар"};
        double[] prices = {50, 14, 80, 75, 33};
        int[] basket = new int[products.length];
        double basketSum = 0;

        int productNumber = 0;
        int productCount = 0;

        System.out.println("Список возможных товаров для покупки:");

        for (int i = 0; i < products.length; i++)
            System.out.println(i + 1 + ". " + products[i] + " - " + prices[i] + " руб.за единицу товара.");

        while (true) {

            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("Программа завершена.");
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Некорректный ввод!!! - Введите: номер товара и количество единиц товара через пробел");
                continue;
            }
            try {
                productNumber = Integer.parseInt(parts[0]) - 1;

                if (productNumber < 0 || productNumber > products.length) {
                    System.out.println("Ошибка: Такого товара нет");
                    continue;
                }
            } catch (RuntimeException e) {
                System.out.println("Введена некорректная информация");
            }

            try {
                productCount = Integer.parseInt(parts[1]);

            } catch (NumberFormatException e) {

                System.out.println("Ошибка! Введены некорректные данные!");
                continue;
            }

            if (productCount == 0) {
                basket[productNumber] = 0;
            } else {
                if (basket[productNumber] + productCount < 0) {
                    System.out.println("Количество товаров в корзине не может быть отрицательным!");
                    continue;
                }
                basket[productNumber] += productCount;
            }
        }

        System.out.println("Ваша корзина:");
        for (int i = 0, j = 1; i < products.length; i++) {

            if (basket[i] != 0) {
                if (productNumber == 3 || productNumber == 4 && (productCount / 3) == 0) {
                    double sumDiscounts = ((basket[i] - basket[i] % 3) * prices[i] * 2) / 3 + basket[i] % 3 * prices[i];
                    System.out.println(j + ") Товара № " + (i + 1) + ". " + products[i] + ". " + basket[i] + " ед. Цена: " + prices[i] + " руб. за ед.товара. Всего: " + sumDiscounts + " руб.");
                    basketSum += sumDiscounts;
                } else {
                    System.out.println(j + ") Товара № " + (i + 1) + ". " + products[i] + ". " + basket[i] + " ед. Цена: " + prices[i] + " руб. за ед.товара. Всего: " + (basket[i] * prices[i]) + " руб.");
                    basketSum += prices[i] * basket[i];
                }
                j++;
            }
        }
        System.out.println("Итого " + basketSum + " руб.");
    }
}

