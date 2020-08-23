package run;

import helpful.stream.builder.StreamBuilder;

import java.util.Arrays;
import java.util.List;

public class Run {
    public static void main(String[] args) {
        System.out.println("TEST");

        List<Integer> in = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        try {
            Integer out = StreamBuilder
                    .collectionToStream(in)
                    .filter(x -> x % 4 != 0)
                    .map(x -> x * 10 + x)
                    .map(x -> x.toString() + "qql" + x.toString())
                    .filter(x -> x.contains("2") || x.contains("5") || x.contains("7"))
                    .map(String::toUpperCase)
                    .map(x -> x.replace("QQL", ""))
                    .map(Integer::valueOf)
                    .reduce(7000000, Integer::sum);
            System.out.println(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}