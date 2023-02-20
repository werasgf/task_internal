package task.app.task34;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class One {

    private static final String poem =
            "But I didn't believe in imagination either\n" +
                    "Scrolled all over again: steps\n" +
                    "From the stage to the hall, suffocation, chills\n" +
                    "And a strange fever, and again this snob\n" +
                    "He got up, and I fell down, but the fault\n" +
                    "Tom was not a pipe, — such a moment\n" +
                    "It's time for a smooth cut off course\n" +
                    "A lame heart, a robot, a fool.";

    private static final Comparator<String> LineComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    };

    public static void main(String[] args) {
        List<String> list = Arrays.asList(poem.split("\n"));
        try (FileWriter writer = new FileWriter("text.txt", false)) {
            for (String s : list) {
                writer.write(s + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("До сортировки\n");
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("text.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String s : lines) {
            System.out.println(s);
        }

        lines.sort(LineComparator);
        System.out.println("\n\nпосле сортировки\n");
        for (String s : lines) {
            System.out.println(s);
        }
    }
}
