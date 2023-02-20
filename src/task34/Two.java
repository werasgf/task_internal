package task.app.task34;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Two {
    public static void main(String[] args) {
        List<Integer> listNumber = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 15; i++) {
            int number = -15 + random.nextInt(15 + 30);
            listNumber.add(number);
        }
        System.out.println(listNumber);
        List<Integer> resultList = listNumber.stream().sorted((o1, o2) -> o2).collect(Collectors.toList());
        System.out.println(resultList);
    }
}
