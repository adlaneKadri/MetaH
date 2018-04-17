import java.util.ArrayList;

public class SolutionSAT {

    ArrayList<Integer> literals;
    private int nbrClauseSatisfy;
    Instance instance;
    int quality;

    public SolutionSAT(ArrayList<Integer> solution) {
        this.literals = solution;
    }


    public SolutionSAT() {
        this.literals = new ArrayList<>();
        nbrClauseSatisfy = 0;
    }

    public SolutionSAT(Instance instance) {
        this.literals = new ArrayList<>();
        this.instance=instance;
        nbrClauseSatisfy = this.nbrClauseSatisfy(instance);
    }

    public int nbrClauseSatisfy(Instance instance){
        this.nbrClauseSatisfy = instance.quality(this);
        return nbrClauseSatisfy;
    }

    public void inverse(int i){
        //t= {1,0,0,0,1,1,0} --> inverse(2) =>{1,0,1,0,1,1,0}
        int x = this.literals.get(i);
        if (x==1)x=0;
        else x=1;
        this.literals.set(i,x);
    }

    public SolutionSAT clone(){
        ArrayList<Integer> s = ( ArrayList<Integer> ) this.literals.clone();
        return new SolutionSAT(s);
    }

    public int quality(Instance instance){
        return ( int ) instance.getClauses().stream()
                .filter(clause -> clause.isSatisfy(this))
                .count();

    }

    @Override
    public String toString() {
        return "" + literals ;
                    }
}