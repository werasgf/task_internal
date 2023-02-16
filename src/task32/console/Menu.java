package task.console;

import task.service.ProductService;

import java.util.Scanner;

public class Menu {
    private static final int MENU_SIZE = 4;
    private final Scanner scanner;
    private final ProductService service;
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.service = new ProductService();
    }
    public void mainMenu() {
        int choice;
        while (true) {
            String startMessage = "\n1. Получить набор предметов\n" +
                    "2. Сортировать товары по весу\n" +
                    "3. Заполнить склад по максимому\n" +
                    "4. Выход\n";
            choice = getAnswerFromMenu(startMessage);
            switch (choice) {
                case 1:
                    service.printItemList();
                    break;
                case 2:
                    service.printSortedListByWeight(service.sortByWeight());
                    break;
                case 3:
                    setAModelOfTheSafe();
                    service.printItemInTheWarehouseWithMaxWeight(service.putItemInTheWarehouseWithMaxWeight
                            (service.sortByWeight(), getVolumeFromConsole()));
                    break;
                case 4:
                    return;
            }
        }
    }
    private boolean isNumber1(String str) {
        if (str.isBlank()) {//  if (str == null || str.isEmpty())
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    private void errorMenu(String errorMessage) {
        System.out.println(errorMessage);
    }
    private void setAModelOfTheSafe() {
        System.out.println("Введите номер склада: ");
        scanner.nextLine();
    }
    private int getAnswerFromMenu(String message) {
        String answer;
        int result;
        while (true) {
            System.out.println(message);
            answer = scanner.nextLine();
            if (isNumber1(answer)) {
                result = Integer.parseInt(answer);
                if (result <= Menu.MENU_SIZE && result > 0) {
                    return result;
                } else {
                    errorMenu("Пожалуйста, введите число от 1 до "
                            + Menu.MENU_SIZE + "\n");
                }
            } else {
                errorMenu("Пожалуйста введите число\n");
            }
        }
    }
    private int getVolumeFromConsole() {
        int volumeInt;
        String volumeStr;
        while (true) {
            System.out.println("Введите вместимость склада: ");
            volumeStr = scanner.nextLine();
            if (isNumber1(volumeStr)) {
                volumeInt = Integer.parseInt(volumeStr);

                if (volumeInt >= service.findMinVolumeFromItemList() && volumeInt > 0) {
                    return volumeInt;
                } else {
                    errorMenu("Введите объем склада превышающий " + service.findMinVolumeFromItemList() + "\n");
                }
            } else {
                errorMenu("Пожалуйста, введите число.\n Введите объем склада превышающий " +
                        service.findMinVolumeFromItemList() + "\n");
            }
        }
    }
}
