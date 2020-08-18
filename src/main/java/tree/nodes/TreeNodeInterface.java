package tree.nodes;

import exceptioned.functions.FunctionWithException;
import exceptioned.functions.FunctionWithException2Params;
import exceptioned.functions.FunctionWithException3Params;

public interface TreeNodeInterface<T> {
    TreeNodeInterface<T> getLeft();

    void setLeft(T data);

    TreeNodeInterface<T> getRight();

    void setRight(T data);

    T getData();

    void setData(T data);

    void addData(T data, FunctionWithException2Params<T, T, Boolean> isLower) throws Exception;

    void remove();

    boolean isRemoved();

    boolean isEmpty();

    <R> R drill(
            FunctionWithException<TreeNodeInterface<T>, R> function
            , FunctionWithException<TreeNodeInterface<T>, Boolean> stop
            , FunctionWithException3Params<R, R, R, R> summator
    ) throws Exception;
}