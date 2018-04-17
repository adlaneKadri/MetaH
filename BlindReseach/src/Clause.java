import java.util.HashSet;

public class Clause {
    private HashSet<Integer> literals ;

    public Clause(HashSet<Integer> literals) {
        this.literals = literals;
    }

    public Clause() {
        this.literals = new HashSet<>();
    }

    public HashSet<Integer> getIntegers() {
        return literals;
    }

    public void setIntegers(HashSet<Integer> literals) {
        this.literals = literals;
    }

    // Clause Methods :
    public boolean isSatisfy(Node node){
      return !literals
              .stream()
              .map(literal -> !node.getVariables().contains(literal))
              .reduce((x,y)-> x && y)
              .get();
    }

    @Override
    public String toString() {
        return "Clause : {\n" +
                " " + literals +
                "\n}";
    }

    public void displayClause(){
        System.out.println(this);
    }


}
