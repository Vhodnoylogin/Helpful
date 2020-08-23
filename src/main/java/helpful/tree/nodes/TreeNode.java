package helpful.tree.nodes;

import helpful.exceptioned.functions.FunctionWithException;
import helpful.exceptioned.functions.FunctionWithException3Params;
import helpful.simplification.CompareFunction;

public class TreeNode<T> implements TreeNodeInterface<T> {
    //    TreeNodeInterface<T> parent;
//    TreeNodeInterface<T> left, right;
    TreeNode<T> parent;
    TreeNode<T> left, right;
    boolean removed = false;
    T data;

    public TreeNode() {
    }

    @Override
    public TreeNodeInterface<T> getLeft() {
        return this.left;
    }

    @Override
    public void setLeft(T data) {
        this.left.setData(data);
    }

    @Override
    public TreeNodeInterface<T> getParent() {
        return this.parent;
    }

    //    @Override
//    public void setParent(TreeNodeInterface<T> parent) {
//        this.parent = parent;
//    }
    @Override
    public void setParent(TreeNodeInterface<T> parent) {

    }

    @Override
    public TreeNodeInterface<T> getRight() {
        return this.right;
    }

    @Override
    public void setRight(T data) {
        this.right.setData(data);
    }


    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public void setData(T data) {
        this.left = new TreeNode<>();
        this.right = new TreeNode<>();

        this.left.setParent(this);
        this.right.setParent(this);

        this.data = data;
    }

    @Override
    public void addData(T data, CompareFunction<T> compare) throws Exception {
        if (this.isEmpty()) {
            this.setData(data);
        } else if (
                this.isRemoved()
                        && compare.accept(data, this.getRight().getData()) == CompareFunction.Compare.LESS
                        && compare.accept(this.getLeft().getData(), data) == CompareFunction.Compare.LESS
        ) {
            this.data = data;
            this.removed = false;
        } else {
            (compare.accept(data, this.data) == CompareFunction.Compare.LESS
                    ? this.left
                    : this.right
            ).addData(data, compare);
        }
    }

    @Override
    public void remove() {
        this.removed = true;
    }

    @Override
    public boolean isRemoved() {
        return this.removed;
    }

    @Override
    public boolean isEmpty() {
        return this.data == null;
    }

    @Override
    public <R> R drill(
            FunctionWithException<TreeNodeInterface<T>, R> function
            , FunctionWithException<TreeNodeInterface<T>, Boolean> stop
            , FunctionWithException3Params<R, R, R, R> summator
    ) throws Exception {
        return
                stop.accept(this)
                        ? function.accept(this)
                        : summator.accept(
                        function.accept(this)
                        , this.getLeft() == null ? null : this.getLeft().drill(function, stop, summator)
                        , this.getRight() == null ? null : this.getRight().drill(function, stop, summator)
                );
    }
}