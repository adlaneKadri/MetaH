import java.util.*;

public class Node {

    private HashSet<Integer> variables ;

    public Node(HashSet<Integer> variables) {
        this.variables = new HashSet<>(variables);
    }

    public Node() {
    this.variables = new HashSet<>();
    }

    public HashSet<Integer> getVariables() {
        return variables;
    }

    public void setVariables(HashSet<Integer> variables) {
        this.variables = variables;
    }


    /*here it has been supposed that the set of works has sucessful values,
     and each time we choose a literal which has the smallest value and which has not been chosen already.
     there is another method : using Random();
    */
    public void choseNextLiteral(int p, Collection<Node> open) {
        OptionalInt optionalInt = variables.stream().map(Math::abs).mapToInt(Integer::intValue).max();

        int max = optionalInt.isPresent() ?  optionalInt.getAsInt(): 0 ;

        if (max <= p) {
            Node node = new Node(variables);
            node.variables.add(-(max + 1));
            open.add(node);
            Node node2 = new Node(variables);
            node2.variables.add(max + 1);
            open.add(node2);
        }
    }
    Random ran = new Random();

    public int getNext(ArrayList<Integer> solutionSAT, Collection<Node> open) {
        int max ;
        do {
            max = this.NextLiteral(ran);
        }while (this.variables.contains(max)||this.variables.contains(-max));

        Node node = new Node(variables);
        node.variables.add(-(max + 1));
        open.add(node);
        Node node2 = new Node(variables);
        node2.variables.add(max + 1);
        open.add(node2);

       return max;

    }

    public int NextLiteral(Random random) {
        int  n;
           n = random.nextInt(75) + 1;
        return  n ;
    }

    @Override
    public String toString() {
        return "Node{" +
                "variables=" + variables +
                '}';
    }
}
