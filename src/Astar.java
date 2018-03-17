import java.util.*;

public class Astar {

    public static void treatAstar(Litteral[][] instance, long time, int nbcla, int nbvar) {
        HashMap<Integer,Integer> literals = Instance.literalFrequence(instance,325);

/*
        for (Map.Entry<Integer, Integer> entry : literals.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(" "+key+" : "+value);
        }
*/
        int fonction = 0;
        long startTime = System.currentTimeMillis();
        long testFinish = startTime + time;
        Noeud.isAstar = true;
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

                Noeud node = new Noeud(Node.indice, Node.ClauseSatisfy);
                node.prof = Node.prof + 1;
                node.clauseSatisfy.addAll(Node.clauseSatisfy);
                node.solution.addAll(Node.solution);
                randomElement = ar.get(rand.nextInt(ar.size()));
                node.bool = randomElement;
                ar.remove(randomElement);
                litteral = new Litteral(node.prof, node.bool);
                Integer l = litteral.getLiteral();
                node.frequence = literals.get(l);
                node.solution.add(litteral.print());
                node.treatNode(instance, nbcla, litteral);

                if (node.ClauseSatisfy > fonction)
                {fonction = node.ClauseSatisfy;}

                Noeud nd = new Noeud(Node.indice, Node.ClauseSatisfy);
                nd.prof = Node.prof + 1;
                nd.clauseSatisfy.addAll(Node.clauseSatisfy);
                nd.solution.addAll(Node.solution);
                nd.bool = ar.get(0);
                litteral = new Litteral(nd.prof, nd.bool);
                Integer l1 = litteral.getLiteral();
                node.frequence = literals.get(l1);
                nd.solution.add(litteral.print());
                nd.treatNode(instance, nbcla, litteral);
                if (nd.ClauseSatisfy > fonction)
                    fonction = nd.ClauseSatisfy;

                ar.clear();
                ar.add(0);
                ar.add(1);
                open.push(nd);
                open.push(node);
            }

        }
        if (fonction == nbcla) {
            Noeud n1 = open.pop();
            Noeud n2 = open.pop();
            if (n1.ClauseSatisfy != fonction)n1 = n2;
            System.out.println("Satisfiable!!");
            for (Integer teger : n1.solution){
                System.out.print(teger + " ");
            }
        } else {
            float b = ( float ) fonction / ( float ) nbcla * 100;
            System.out.println(b + " %");
        }

    }


}
