import java.util.ArrayList;

public class Clause {
    private ArrayList<Integer> literals ;

    public Clause(ArrayList<Integer> literals) {
        this.literals = literals;
    }

    public Clause() {
        this.literals = new ArrayList<>();
    }

    public ArrayList<Integer> getIntegers() {
        return literals;
    }

    public void setIntegers(ArrayList<Integer> literals) {
        this.literals = literals;
    }

    @Override
    public String toString() {
        return "\n" +
                " " + literals.toString() +
                "\n";
    }
    public void displayClause(){
        System.out.println(this);
    }
    public boolean contains(int i) {
        return  literals.contains(i);
    }

    public boolean isSatisfy(SolutionSAT solution){
        //System.out.println("this = " + this);
        for (Integer literal : literals) {
            boolean b = true;
            int index = literal;
            if (literal < 0) {
                b = false;
                index = -literal;
            }
            index--;

            boolean b2 = true;
            if (solution.literals.get(index) == 0) b2 = false;
/*
            System.out.println("index = " + index);
            System.out.println("solution = " + solution.literals);
            System.out.println("solution.get(index) = " + solution.literals.get(index));
            System.out.println("b = " + b);
            System.out.println("b2 = " + b2);
*/
            if (b && b2) return true;
            if (!b && !b2) return true;
        }

        return false;
    }

}
