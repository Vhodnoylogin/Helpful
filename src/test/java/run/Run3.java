package run;

import helpfull.simplification.CompareFunction;
import helpfull.tree.Tree;

public class Run3 {
    public static void main(String[] args) {
        Tree<Integer> testTree = new Tree<>();
        testTree.setComparator((x, y) -> {
            if (x < y) {
                return CompareFunction.Compare.LESS;
            } else if (y < x) {
                return CompareFunction.Compare.LARGER;
            } else {
                return CompareFunction.Compare.EQUAL;
            }
        });
        testTree.add(4);
        testTree.forEach(System.out::println);
    }
}