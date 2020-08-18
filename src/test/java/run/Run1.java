package run;

import stream.builder.StreamBuilder;

import java.util.Arrays;
import java.util.List;

public class Run1 {
    public static void main(String[] args) {
        List<Integer> in = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        try {
            StreamBuilder
                    .collectionToStream(in)
                    .filter(x -> x % 2 == 0)
                    .filter(x -> {
                        throw new Exception();
                    })
                    //.sort((x, y) -> x <= y ? Compare.LESS : Compare.LARGER)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}