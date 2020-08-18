package run;

import tree.Tree;

public class Run3 {
    public static void main(String[] args) {
        Tree<Integer> testTree = new Tree<>();
        testTree.setComparator((x, y) -> x < y);
        testTree.add(4);
        testTree.forEach(System.out::println);
    }
}