package helpful.tree.iterator;

import helpful.tree.nodes.TreeNode;
import helpful.tree.nodes.TreeNodeInterface;

import java.util.Iterator;

public class TreeIterator<T> extends TreeNode<TreeNode<T>> implements Iterator<TreeNodeInterface<T>> {
    protected VISIT flag;
    protected TreeNodeInterface<T> node;

    public TreeIterator(TreeNodeInterface<T> node) {
        this.node = node;
        //this.setData(this.node.getData());
        //this.setRight(this.);
//        if(!this.node.getLeft().isEmpty()){
//            this.flag = VISIT.NEW;
//        } else if(!this.node.isRemoved()){
//            this.flag = VISIT.GET;
//        } else if(!this.node.getRight().isEmpty()){
//            this.flag = VISIT.RIGHT;
//        } else {
//            this.flag = VISIT.FINISH;
//        }
    }

    @Override
    public boolean hasNext() {
        return this.flag != VISIT.FINISH;
    }

    @Override
    public TreeNodeInterface<T> next() {
        if (this.flag == VISIT.NEW) {
            //this.getRight().
        }
        return null;
    }

    protected enum VISIT {
        NEW, GET, RIGHT, FINISH {
            @Override
            public VISIT next() {
                return null; // see below for options for this line
            }
        };

        public VISIT next() {
            return values()[ordinal() + 1];
        }
    }
}