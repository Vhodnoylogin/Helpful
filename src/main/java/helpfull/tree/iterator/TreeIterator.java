package helpfull.tree.iterator;

import helpfull.tree.nodes.TreeNode;
import helpfull.tree.nodes.TreeNodeInterface;

import java.util.Iterator;

public class TreeIterator<T> extends TreeNode<T> implements Iterator<TreeNodeInterface<T>> {
    protected boolean flag;
    protected TreeNodeInterface<T> node;

    public boolean isFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public TreeNodeInterface<T> getNode() {
        return this.node;
    }

    public void setNode(TreeNodeInterface<T> node) {
        this.node = node;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public TreeNodeInterface<T> next() {
        return null;
    }
}