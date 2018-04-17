public class Literal {

    //Literal representation :
    private int variable;
    private boolean signe;
    private int value;

    public Literal(int variable, boolean signe) {
        this.variable = variable;
        this.signe = signe;
    }

    public Literal(int variable, boolean signe, int value) {
        this.variable = variable;
        this.signe = signe;
        this.value = value;
    }

    public int getVariable() {
        return variable;
    }

    public void setVariable(int variable) {
        this.variable = variable;
    }

    public boolean isSigne() {
        return signe;
    }

    public void setSigne(boolean signe) {
        this.signe = signe;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isTrue(){
        return this.value == -1 && this.signe || this.value == 1 && !this.signe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Literal literal = (Literal) o;

        return variable == literal.variable;
    }

    @Override
    public String toString() {
        return "{" +
                " " + variable +
                " , " + signe +
                ", " + value +
                '}';
    }

    public void displayLiteral(){
        System.out.println(this);
    }
}
