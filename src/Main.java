import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {

        String[] products = {"Молоко", "Хлеб", "Гречневая крупа", "Сахар", "Соль"};
        String[] discountedProducts = {"Хлеб", "Гречневая крупа",};
        int[] price = {50, 14, 80, 75, 33};
        int[] basket = new int[products.length];
        int basketSum = 0;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Список возможных товаров для покупки:");

            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + ": " + products[i] + " " + price[i] + " руб/шт.");
            }
            System.out.println("В акции 3 по цене 2-х участвуют товары: "+Arrays.toString(discountedProducts));

            String input = scanner.nextLine();
            if (input.equals("end")) {
                System.out.println("Программа завершена!");
                break;
            }
            String[] stringsInput = input.split(" ");
            if (stringsInput.length != 2) {
                continue;
            }
            String strproductNumber = String.valueOf(stringsInput[0]);
            String strproductCount = String.valueOf(stringsInput[1]);
            int productNumber;
            int productCount;
            try {

                productNumber = Integer.parseInt(strproductNumber) - 1;
                if ((productNumber < 0) || (productNumber + 1 > products.length)) {
                    System.out.println("Некорректное значение выбранного продукта");
                    continue;
                }
                productCount = Integer.parseInt(strproductCount);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                continue;
            }
            if (productCount == 0) {
                basket[productNumber] = 0;
            } else {
                basket[productNumber] += productCount;
            }
        }
        System.out.println("Ваша корзина:");
        for (int i = 0; i < basket.length; i++) {
            boolean discount = false;
            if (basket[i] > 0) {

                for (int j = 0; j < discountedProducts.length; j++) {
                    if (products[i].equals(discountedProducts[j])) {
                        discount = true;
                        int sumDiscounts = ((basket[i] - basket[i] % 3) * price[i] * 2) / 3 + basket[i] % 3 * price[i];
                        basketSum += sumDiscounts;
                        System.out.println(products[i] + " " + basket[i] + " шт " + price[i] + " руб/шт " + sumDiscounts + " рублей в сумме");
                        break;
                    }
                }
                if (!discount) {
                    System.out.println(products[i] + " " + basket[i] + " шт " + price[i] + " руб/шт " + (basket[i] * price[i]) + " рублей в сумме");
                    basketSum += basket[i] * price[i];
                }
            }
        }
        System.out.println("Итого: " + basketSum + " руб.");
    }
}

