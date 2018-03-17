import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class CoutUniform {

    public static void treatCoutUniform(Litteral[][] instance, long time, int nbcla, int nbvar) {
        int fonction = 0;
        long startTime = System.currentTimeMillis();
        long testFinish = startTime + time;
        Noeud.isAstar = false ;
        Noeud racine = new Noeud(-1, 0);
        racine.prof = 0;
        LinkedList<Noeud> open = new LinkedList<>();
        open.push(racine);
        //LinkedList<Noeud> clos = new LinkedList<Noeud>();
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(0);
        ar.add(1);
        Random rand = new Random();
        int randomElement;
        while (fonction != nbcla && !open.isEmpty() && System.currentTimeMillis() < testFinish) {
             Collections.sort(open);



            Noeud Node = open.pop();
            Litteral litteral;

            if (Node.prof < nbvar) {

                Noeud noeud = new Noeud(Node.indice, Node.ClauseSatisfy);
                noeud.prof = Node.prof + 1;
                noeud.clauseSatisfy.addAll(Node.clauseSatisfy);
                noeud.solution.addAll(Node.solution);
                randomElement = ar.get(rand.nextInt(ar.size()));
                noeud.bool = randomElement;
                ar.remove(randomElement);
                litteral = new Litteral(noeud.prof, noeud.bool);
                noeud.solution.add(litteral.print());
                noeud.treatNode(instance, nbcla, litteral);

                if (noeud.ClauseSatisfy > fonction)
                    fonction = noeud.ClauseSatisfy;

                Noeud nd = new Noeud(Node.indice, Node.ClauseSatisfy);
                nd.prof = Node.prof + 1;
                nd.clauseSatisfy.addAll(Node.clauseSatisfy);
                nd.solution.addAll(Node.solution);
                nd.bool = ar.get(0);
                litteral = new Litteral(nd.prof, nd.bool);
                nd.solution.add(litteral.print());
                nd.treatNode(instance, nbcla, litteral);
                if (nd.ClauseSatisfy > fonction)
                    fonction = nd.ClauseSatisfy;

                ar.clear();
                ar.add(0);
                ar.add(1);
                open.push(nd);
                open.push(noeud);
            }

        }
        if (fonction == nbcla) {
            Noeud n1 = open.pop();
            Noeud n2 = open.pop();
            if (n1.ClauseSatisfy != fonction) n1 = n2;
            System.out.println("Satisfiable!!");
            for (Integer teger : n1.solution)
                System.out.print(teger + " ");
        } else {
            float b = ( float ) fonction / ( float ) nbcla * 100;
            System.out.println(b + " %");
        }

    }

}




