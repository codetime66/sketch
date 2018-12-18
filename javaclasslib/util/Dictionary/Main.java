import java.util.*;

class Main {
    static public void main(String[] args) {
        Tree t = new Tree();

        // Add 20 random numbers.
        for (int i=0; i<20; i++) {
            double r = Math.random();
            t.put(""+Math.floor(r * 10), new Double(r));
        }
        // Display the elements in the tree.
        String key;
        for (Enumeration e = t.keys(); e.hasMoreElements(); ) {
            key = (String)e.nextElement();
            System.out.print(key + ": ");
            System.out.println(t.get(key));
        }
    }
}

// This class implements a node in the binary tree.
// The node contains a reference to the key and element.
// The node also contains a reference to left and right branch
// of the tree.
class TreeNode {
    // 0 -> left, 1 -> right
    TreeNode[] branch = new TreeNode[2];
    String key;
    Object value;
    TreeNode(String k, Object v) {
        key = k;
        value = v;
    }
}

class Tree extends Dictionary {
    TreeNode root;
    int count;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Enumeration keys() {
        return new TreeEnumerator(this, true);
    }

    public Enumeration elements() {
        return new TreeEnumerator(this, false);
    }

    
    // Recurse the tree, looking for 'key'.
    public Object get2(TreeNode n, String key) {
        if (n == null) return null;
        int cmp = key.compareTo(n.key);

        if (cmp == 0) return n.value;
        return get2(n.branch[cmp = Math.min(1, Math.max(0, cmp))], key);
    }
    public Object get(Object key) {
        return get2(root, (String)key);
    }

    // n is never null.  Smaller elements are added to the left branch.
    public Object put2(TreeNode n, String key, Object value) {
        int cmp = key.compareTo(n.key);
        if (cmp == 0) {
            Object old = n.value;
            n.value = value;
            return old;
        } 
        cmp = Math.min(1, Math.max(0, cmp));
        if (n.branch[cmp] != null) {
            return put2(n.branch[cmp], key, value);
        } else {
            n.branch[cmp] = new TreeNode(key, value);
            count++;
            return null;
        }
    }
    public Object put(Object key, Object value) {
        if (root == null) {
            root = new TreeNode((String)key, value);
            count++;
            return null;
        } else {
            return put2(root, (String)key, value);
        }
    }

    public Object remove(Object key) {
        // not implemented
        return null;
    }
}

// The enumerator create a list of tree nodes, large enough
// to hold all the tree nodes in the tree.  The enumerator
// then recurses the tree and places all tree nodes in the list.
class TreeEnumerator implements Enumeration {
    boolean keys;
    int index;
    TreeNode[] list;

    void traverse(TreeNode n) {
        if (n == null) return;
        traverse(n.branch[0]);
        list[index++] = n;
        traverse(n.branch[1]);
    }
    TreeEnumerator(Tree tree, boolean keys) {
        this.keys = keys;
        list = new TreeNode[tree.count];
        traverse(tree.root);
        index = 0;
    }
    public boolean hasMoreElements() {
        return index < list.length;
    }
    public Object nextElement() {
        if (keys) {
            return list[index++].key;
        } else {
            return list[index++].value;
        }
    }
}
