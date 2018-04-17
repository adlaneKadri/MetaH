import java.util.ArrayList;
import java.util.List;

public class SolutionSAT {

    ArrayList<Integer> literals;
    Instance instance;
    int quality;
    private int nbrClauseSatisfy;

    public SolutionSAT(ArrayList<Integer> solution) {
        this.literals = solution;
        //this.quality=this.quality(instance);
    }


    public SolutionSAT() {
        this.literals = new ArrayList<>();
        nbrClauseSatisfy = 0;
    }

    public SolutionSAT(Instance instance) {
        this.literals = new ArrayList<>();
        this.instance = instance;
        nbrClauseSatisfy = this.nbrClauseSatisfy(instance);
    }

    public int nbrClauseSatisfy(Instance instance) {
        this.nbrClauseSatisfy = instance.quality(this);
        return nbrClauseSatisfy;
    }

    public void inverse(int i) {
        //t= {1,0,0,0,1,1,0} --> inverse(2) =>{1,0,1,0,1,1,0}
        int x = this.literals.get(i);
        if (x == 1) x = 0;
        else x = 1;
        this.literals.set(i, x);
    }

    public SolutionSAT clone() {
        ArrayList<Integer> s = ( ArrayList<Integer> ) this.literals.clone();
        return new SolutionSAT(s);
    }

    public int quality(Instance instance) {
        this.instance=instance;
        return ( int ) instance.getClauses().stream()
                .filter(clause -> clause.isSatisfy(this))
                .count();

    }

    public List<SolutionSAT> getNeighbor(){
       List<SolutionSAT> l = new ArrayList<>();

        for (int i = 0; i <this.literals.size() ; i++) {
            SolutionSAT s = new SolutionSAT(this.clone().literals);
            s.inverse(i);
            l.add(s);
        }



        return l;
    }
    @Override
    public String toString() {
        return "" + literals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SolutionSAT)) return false;

        SolutionSAT that = ( SolutionSAT ) o;

        for (int i = 0; i < that.literals.size(); i++) {
            if (that.literals.get(i) != this.literals.get(i)) return false;
        }

        return true;
    }

    public void display(){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i <this.literals.size() ; i++) {
            if (this.literals.get(i)==1) result.add(i+1);
            else                         result.add(-(i+1));
        }
        System.out.println("best solution:");
        System.out.println(result);
    }

}