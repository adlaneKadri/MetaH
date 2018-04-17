import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Test {

    public static void main(String[] args) throws IOException {
        Instance instance = new Instance("UF75.325.100\\uf75-02.cnf");
/*
        Bee bee = Bee.beeInit(instance,7);
        System.out.println(bee.getSolutionBee().quality(instance));
        ArrayList<SolutionSAT> taboo = new ArrayList<>();
            bee.localSearch(taboo);
*/
        BSO bso = new BSO(instance,7,20,0,10000);
        //System.out.println(bso.searchPoint(new SolutionSAT(zero)));
        //System.out.println(bso.runBSO());
        bso.runBSO();
    }
}
