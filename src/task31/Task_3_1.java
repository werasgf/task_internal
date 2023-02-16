public class Task_3_1 {
    private static final int LENGTH = 3;
    public static void main(String[] args) {
        int[] arr = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            arr[i] = (int) (100 + Math.random() * 800);
            System.out.println("number " + (i + 1) + ": " + arr[i]);
        }
        for (int i = 0; i < LENGTH; i++) {
            if (i == 2) {
                System.out.println("the difference between number " + LENGTH +  " and " + (LENGTH - i) + " is " + differenceBetweenNumber(arr[2], arr[0]));
            } else System.out.println("the difference between number " + (i + 1) + " and " + (i + 2) + " is " + differenceBetweenNumber(arr[i], arr[i + 1]));
        }
    }
    public static int differenceBetweenNumber(int one, int two) {
        return Math.abs(one - two);
    }
}