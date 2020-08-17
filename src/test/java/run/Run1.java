package run;

import stream.StreamBuilder;
import stream.nodes.Compare;

import java.util.Arrays;
import java.util.List;

public class Run1 {
    public static void main(String[] args) {
        List<Integer> in = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        StreamBuilder
                .collectionToStream(in)
                .filter(x -> x % 2 == 0)
                .sort((x, y) -> x <= y ? Compare.LESS : Compare.LARGER)
                .forEach(System.out::println);
    }
}