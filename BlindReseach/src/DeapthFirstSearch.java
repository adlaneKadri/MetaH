import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class DeapthFirstSearch {

    public static void treatDFS(Litteral[][] instance, long time, int clauseNumber, int literalNumber) {
        int fonction = 0;
        long startTime = System.currentTimeMillis();
        long testFinish = startTime + time;
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
        while (fonction != clauseNumber && !open.isEmpty() && System.currentTimeMillis() < testFinish) {
           // Collections.sort(open);



            Noeud Noeud = open.pop();
            Litteral litteral;


            if (Noeud.prof < literalNumber) {
                Noeud ng = new Noeud(Noeud.indice, Noeud.ClauseSatisfy);
                ng.prof = Noeud.prof + 1;
                ng.clauseSatisfy.addAll(Noeud.clauseSatisfy);
                ng.solution.addAll(Noeud.solution);
                randomElement = ar.get(rand.nextInt(ar.size()));
                ng.bool = randomElement;
                ar.remove(randomElement);
                litteral = new Litteral(ng.prof, ng.bool);
                ng.solution.add(litteral.print());
                ng.treatNode(instance, clauseNumber, litteral);
                if (ng.ClauseSatisfy > fonction)
                    fonction = ng.ClauseSatisfy;

                Noeud nd = new Noeud(Noeud.indice, Noeud.ClauseSatisfy);
                nd.prof = Noeud.prof + 1;
                nd.clauseSatisfy.addAll(Noeud.clauseSatisfy);
                nd.solution.addAll(Noeud.solution);
                nd.bool = ar.get(0);
                litteral = new Litteral(nd.prof, nd.bool);
                nd.solution.add(litteral.print());
                nd.treatNode(instance, clauseNumber, litteral);
                if (nd.ClauseSatisfy > fonction)
                    fonction = nd.ClauseSatisfy;

                ar.clear();
                ar.add(0);
                ar.add(1);
                open.push(nd);
                open.push(ng);
            }
            //clos.add(a);
        }
        if (fonction == clauseNumber) {
            Noeud node1 = open.pop();
            Noeud node2 = open.pop();
            if (node1.ClauseSatisfy != fonction){node1 = node2;}
            System.out.println("Satisfiable!!");
            for (Integer teger : node1.solution)
                System.out.print(teger + " ");
        } else {
            float b = ( float ) fonction / ( float ) clauseNumber * 100;
            System.out.println(b + " %");
        }
    }

}



