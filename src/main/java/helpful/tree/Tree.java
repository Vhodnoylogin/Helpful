package helpful.tree;

import helpful.simplification.CompareFunction;
import helpful.tree.nodes.TreeNode;
import helpful.tree.nodes.TreeNodeInterface;

import java.util.Collection;
import java.util.Iterator;

public class Tree<T> implements Collection<T> {
    TreeNodeInterface<T> head = new TreeNode<>();
    CompareFunction<T> comparator;

    @Override
    public int size() {
        if (this.head == null) return 0;
        try {
            return this.head.drill(x -> x.isRemoved() ? 0 : 1, x -> false, (x, y, z) -> x + y + z);
        } catch (Exception e) {
            return 0;
        }
    }

    public void setComparator(CompareFunction<T> compare) {
        this.comparator = compare;
    }

    @Override
    public boolean isEmpty() {
        return this.size() > 0;
    }

    @Override
    public boolean contains(Object o) {
        try {
            return this.head.drill(o::equals, o::equals, (x, y, z) -> x || y || z);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        try {
            this.head.addData(t, this.comparator);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
    }
}