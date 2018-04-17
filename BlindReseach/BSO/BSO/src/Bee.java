import java.util.ArrayList;
import java.util.Random;

public class Bee {
    private Instance instance;
    private SolutionSAT solutionBee;
    private int maxLocalIterations ;

    /********Constructor************/

    public Bee(Instance instance, SolutionSAT solutionBee, int maxIterations) {
        this.instance = instance;
        this.solutionBee = solutionBee;
        this.maxLocalIterations = maxIterations;
    }

    /****************Fucntion ***************/
    public static Bee beeInit(Instance instance,int maxLocalIterations){
        ArrayList<Integer> sRef= new ArrayList<>();
        for (int i = 0; i <75 ; i++) {
            if(Math.random()> 0.5){
                sRef.add(1);
            }else {
                sRef.add(0);
            }
        }

        Bee bee = new Bee(instance,new SolutionSAT(sRef),maxLocalIterations);
        return bee;
    }
    public SolutionSAT localSearch( ArrayList<SolutionSAT> taboo){
        SolutionSAT best = solutionBee.clone();
        taboo.add(best);
        for (int i = 0; i <maxLocalIterations ; i++) {
            //System.out.println("best: "+best.quality(instance));
            //System.out.println("taboo = " + taboo);
            SolutionSAT max = solutionBee.clone();
            //System.out.println("best = " + best);
            max.inverse(new Random().nextInt(75));
            taboo.add(max);
            //  System.out.println("max: "+max.quality(instance));
            //System.out.println("max = " + max);
            if(best.quality(instance)<max.quality(instance)){
                best=max;
            }
        }
        //System.out.println("best: "+best.quality(instance));

        return best;
    }


    /***********************GETTER AND SETTER BEE***********************/


    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public SolutionSAT getSolutionBee() {
        return solutionBee;
    }

    public void setSolutionBee(SolutionSAT solutionBee) {
        this.solutionBee = solutionBee;
    }

    public int getMaxLocalIterations() {
        return maxLocalIterations;
    }

    public void setMaxLocalIterations(int maxLocalIterations) {
        this.maxLocalIterations = maxLocalIterations;
    }
}
