package run;

import helpfull.stream.builder.StreamBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Run1 {
    public static void main(String[] args) {
        List<Integer> in = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        try {
            StreamBuilder
                    .collectionToStream(in)
                    .filter(x -> x % 2 == 0)
                    .ifNoExceptions()
                    .filter(x -> {
                        throw new IOException();
                    })
                    //.ifNoExceptions()
                    //.sort((x, y) -> x <= y ? Compare.LESS : Compare.LARGER)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}