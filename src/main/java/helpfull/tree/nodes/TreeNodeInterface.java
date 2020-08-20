package helpfull.tree.nodes;

import helpfull.exceptioned.functions.FunctionWithException;
import helpfull.exceptioned.functions.FunctionWithException3Params;
import helpfull.simplification.CompareFunction;

public interface TreeNodeInterface<T> {
    TreeNodeInterface<T> getLeft();

    void setLeft(T data);

    TreeNodeInterface<T> getRight();

    void setRight(T data);

    T getData();

    void setData(T data);

    void addData(T data, CompareFunction<T> compare) throws Exception;

    void remove();

    boolean isRemoved();

    boolean isEmpty();

    <R> R drill(
            FunctionWithException<TreeNodeInterface<T>, R> function
            , FunctionWithException<TreeNodeInterface<T>, Boolean> stop
            , FunctionWithException3Params<R, R, R, R> summator
    ) throws Exception;
}