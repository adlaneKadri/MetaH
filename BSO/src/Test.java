import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Test {

    public static void main(String[] args) throws IOException {
        Instance instance = new Instance("UF75.325.100\\uf75-01.cnf");
        // Instance instance = new Instance("UUF75.325.100\\uuf75-01.cnf");

        BSO bso = new BSO(instance,7,20,6,1000);
        bso.runBSO();


/*
        Bee bee = Bee.beeInit(instance,7);
        System.out.println(bee.getSolutionBee().quality(instance));
        ArrayList<SolutionSAT> taboo = new ArrayList<>();
            bee.localSearch(taboo);
*/

/*        SolutionSAT s1 = new SolutionSAT();
        for (int i = 0; i <75 ; i++) {
            s1.literals.add(0);
        }

        ArrayList<SolutionSAT> ss = ( ArrayList<SolutionSAT> ) s1.getNeighbor();
        for (int i = 0; i < ss.size() ; i++) {

            System.out.println(ss.get(i));
        }

        SolutionSAT s2 = new SolutionSAT();
        for (int i = 0; i <75 ; i++) {
            if (i == 2){

                s2.literals.add(1);
            }else {
                s2.literals.add(0);
            }
        }

        System.out.println(s2.equals(s1));
*/
        //System.out.println(bso.searchPoint(new SolutionSAT(zero)));
        //System.out.println(bso.runBSO());
    }

}
