package helpful.pair;

public class Pair<L, R> {
    protected final L left;
    protected final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }

    @Override
    public int hashCode() {
        return this.left.hashCode() ^ this.right.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairO = (Pair) o;
        return
                this.left.equals(pairO.getLeft())
                        && this.right.equals(pairO.getRight());
    }
}