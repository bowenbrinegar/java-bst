package BST_A2;

public class BST_Node {
    String data;
    BST_Node left;
    BST_Node right;

    BST_Node(String data){ this.data=data; }

    // --- used for testing  ----------------------------------------------
    //
    // leave these 3 methods in, as is

    public String getData(){ return data; }
    public BST_Node getLeft(){ return left; }
    public BST_Node getRight(){ return right; }

    // --- end used for testing -------------------------------------------


    // --- fill in these methods ------------------------------------------
    //
    // at the moment, they are stubs returning false
    // or some appropriate "fake" value
    //
    // you make them work properly
    // add the meat of correct implementation logic to them

    // you MAY change the signatures if you wish...
    // make the take more or different parameters
    // have them return different types
    //
    // you may use recursive or iterative implementations

    public boolean containsNode(String s, BST_Node root){
        return recursiveContains(root, s);
    }

    public boolean recursiveContains(BST_Node curr, String s) {
        boolean is_right = isRight(s, curr.data,0);
        boolean is_right_again;
        if (is_right && curr.right != null) {
            if (curr.right.data.equals(s)) {
                return true;
            } else {
                is_right_again = isRight(s, curr.right.data,0);
                return is_right_again ? recursiveContains(curr.right, s)
                        : recursiveContains(curr.left, s);
            }
        } else if (!is_right && curr.left != null) {
            if (curr.left.data.equals(s)) {
                return true;
            } else {
                is_right_again = isRight(s, curr.left.data,0);
                return is_right_again ? recursiveContains(curr.right, s)
                        : recursiveContains(curr.left, s);
            }
        } else {
            return false;
        }
    }

    public boolean insertNode(String s, BST_Node root) {
        if (s.equals(data) || containsNode(s, root)) {
            return false;
        } else {
            BST_Node node = new BST_Node(s);
            return recursiveInsert(node, root);
        }
    }

    public boolean recursiveInsert(BST_Node node, BST_Node curr) {
        boolean is_right = isRight(node.data, curr.data,0);

        BST_Node next_right = curr.right;
        BST_Node next_left = curr.left;

        if (is_right && next_right == null) {
            curr.right = node;
            return true;
        } else if (!is_right && next_left == null) {
            curr.left = node;
            return true;
        } else if (is_right) {
            return recursiveInsert(node, curr.right);
        } else {
            return recursiveInsert(node, curr.left);
        }
    }

    private static boolean isGreaterThan(BST_Node incoming, BST_Node next) {
        return isRight(incoming.data, next.data, 0);
    }

    public BST_Node removeNode(String s){
        BST_Node temp = recursiveRemove(this,this, s);
        return temp != null ? temp : this;
    }

    public static BST_Node replaceTempInTree(BST_Node result, BST_Node curr, boolean isRight) {
        if (isRight) {
            BST_Node temp = curr.right;
            BST_Node temp_right = temp.right;
            BST_Node temp_left = temp.left;

            if (temp.left == null) {
                curr.right = temp_right;
            } else {
                BST_Node replacement = findGreatestValueLeft(temp.left);
                replacement.left = temp_left;
                replacement.right = temp_right;
                curr.right = replacement;
            }
        } else {
            BST_Node temp = curr.left;
            BST_Node temp_right = temp.right;
            BST_Node temp_left = temp.left;

            if (temp.left == null) {
                curr.left = temp_right;
            } else {
                BST_Node replacement = findGreatestValueLeft(temp.left);
                replacement.left = temp_left;
                replacement.right = temp_right;
                curr.left = replacement;
            }
        }
        return result;
    }

    public static BST_Node findGreatestValueLeft(BST_Node curr) {
        if (curr.left != null) {
            return findGreatestValueLeft(curr.right);
        } else {
            return curr;
        }
    }

    public BST_Node recursiveRemove(BST_Node result, BST_Node curr, String s) {
        boolean is_right = isRight(s, curr.data,0);
        boolean is_right_again;

        if (is_right && curr.right != null) {
            if (curr.right.data.equals(s)) {
                return replaceTempInTree(result, curr, true);
            } else {
                is_right_again = isRight(s, curr.right.data,0);
                return is_right_again ? recursiveRemove(result, curr.right, s)
                        : recursiveRemove(result, curr.left, s);
            }
        } else if (!is_right && curr.left != null) {
            if (curr.left.data.equals(s)) {
                return replaceTempInTree(result, curr, false);
            } else {
                is_right_again = isRight(s, curr.left.data,0);
                return is_right_again ? recursiveRemove(result, curr.right, s)
                        : recursiveRemove(result, curr.left, s);
            }
        } else {
            return null;
        }

    }

    private static BST_Node findLeftMost(BST_Node curr, String s) {
        boolean is_greater_than = isRight(s, curr.data, 0);

        if (!is_greater_than) {
            return curr.left;
        } else {
            return findLeftMost(curr.left, s);
        }
    }

    public BST_Node findMin(){
        BST_Node curr = this;

        if (this.left != null) {
            while (true) {
                BST_Node temp = curr.left;
                if (temp == null) {
                    break;
                } else {
                    curr = temp;
                }
            }
        }

        return curr;
    }

    public BST_Node findMax(){
        BST_Node curr = this;

        if (this.right != null) {
            while (true) {
                BST_Node temp = curr.right;
                if (temp == null) {
                    break;
                } else {
                    curr = temp;
                }
            }
        }

        return curr;
    }

    public int getHeight(){
        BST_Node curr_left = this.left;
        BST_Node curr_right = this.right;

        int left_depth = 1;
        int right_depth = 1;

        while (curr_left != null) {
            curr_left = curr_left.left == null ? curr_left.right : curr_left.left;

            if (curr_left != null) {
                left_depth += 1;
            }
        }

        while (curr_right != null) {
            curr_right = curr_right.right == null ? curr_right.left : curr_right.right;

            if (curr_right != null) {
                right_depth += 1;
            }
        }

        return Math.max(left_depth, right_depth);
    }

    // --- end fill in these methods --------------------------------------
    private static boolean isRight(String incoming, String curr, int i) {
        int incoming_len = incoming.length();
        int current__len = curr.length();

        if (incoming_len == i || current__len == i) {
            return incoming_len > current__len;
        } else {
            int first_incoming = (int) incoming.charAt(i);
            int first_current = (int) curr.charAt(i);

            if (first_incoming > first_current) {
                return true;
            } else if (first_incoming < first_current) {
                return false;
            } else {
                return isRight(incoming, curr,i + 1);
            }
        }
    }

    // --------------------------------------------------------------------
    // you may add any other methods you want to get the job done
    // --------------------------------------------------------------------

    public String toString(){
        return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
                +",Right: "+((this.right!=null)?right.data:"null");
    }
}
