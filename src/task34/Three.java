package task.app.task34;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

public class Three {
    public static void main(String[] args) {
        List<String> list;
        try {
            list = Files.readAllLines(Paths.get("text.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HashSet<String> set = new HashSet<>();
        for (String s : list) {
            String temp = s.toLowerCase();
            String[] str = temp.split("\\W+");
            set.addAll(List.of(str));
        }
        System.out.println(set);
    }
}
