import java.util.*;

public class BreadthFirstSearch {
    ArrayList<Instance> instances = new ArrayList<>();
    public  ArrayList<Integer> solutionGot = new ArrayList<>();


    public void treatBFS_Statistic(Instance instance, long durationOfTest , int timeDisplay){
        for (int i=1;i<=timeDisplay;i++){
            treatBFS(instance,durationOfTest);
        }
    }

    public Node treatBFS(Instance instance, long durationOfTest){
            solutionGot = new ArrayList<>();
        long startingTest = System.currentTimeMillis();
        long testFinish = startingTest + durationOfTest ;

       //File open : to store nodes that have not yet been processed .
        Stack<Node> open = new Stack<>();

        Node node = new Node();
        long taux_max = 0 ;
        long taux = 0 ;

        //node.choseNextLiteral(instance.getP(), open);
       int x=  node.getNext(solutionGot, open);
       x=x+1;
       int x1 = -1*x;
        solutionGot.add(x);
        solutionGot.add(x1);
        Node current = null;
        while (!open.isEmpty() && System.currentTimeMillis()<=testFinish) {
            current = open.get(0);
            open.remove(0);
            if (instance.isSatisfy(current)) return current;
            else {
                taux = instance.clauseSatisfyWithThisSolution(current);
                if (taux > taux_max) taux_max = taux ;

                //current.choseNextLiteral(instance.getP(), open);
              x =  current.getNext(solutionGot, open);
              x=x+1;
              x1=-1*x;
              solutionGot.add(x);
              solutionGot.add(x1);
                //long taux = instance.clauseSatisfyWithThisSolution(current);
            }
        }

        System.out.println("Taux: "+((float)taux_max/325)*100+" %");

        System.out.println("Clause number: "+taux_max);
        System.out.println(current);
        System.out.println("\n");
        return null;
    }

    public ArrayList<Integer> solutionGot(){
        int  n;
        ArrayList<Integer> sol = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <=75 ; i++) {
           do {
               n = random.nextInt(75) + 1;
           }while (sol.contains((Integer)n));
            sol.add(n);
           // System.out.print(" "+ sol.get(i-1));
        }
        //System.out.println("\n"+sol.size());
        //Collections.sort(sol);

       // System.out.println(sol.stream().sorted().collect(Collectors.toList()));
       return sol;
    }

}
