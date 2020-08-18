package tree.nodes;

import exceptioned.functions.FunctionWithException;
import exceptioned.functions.FunctionWithException2Params;
import exceptioned.functions.FunctionWithException3Params;

public class TreeNode<T> implements TreeNodeInterface<T> {
    TreeNodeInterface<T> left, right;
    T data;
    boolean removed;

    public TreeNode() {
        this.removed = false;
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
        this.data = data;
    }

    @Override
    public void addData(T data, FunctionWithException2Params<T, T, Boolean> isLower) throws Exception {
        if (this.isEmpty()) {
            this.setData(data);
        } else if (
                this.isRemoved()
                        && isLower.accept(data, this.getRight().getData())
                        && isLower.accept(this.getLeft().getData(), data)
        ) {
            this.data = data;
            this.removed = false;
        } else {
            (isLower.accept(data, this.data) ? this.left : this.right).addData(data, isLower);
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