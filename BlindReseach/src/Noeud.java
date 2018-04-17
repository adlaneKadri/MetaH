import java.util.ArrayList;
import java.util.LinkedList;



public class Noeud implements Comparable {
    static int generatedNode = -1;
    int indice;
    int parent, prof, ClauseSatisfy, bool;
    int frequence ;
    static boolean isAstar;

    ArrayList<Integer> clauseSatisfy = new ArrayList<Integer>();
    LinkedList<Integer> solution = new LinkedList<>();

    public Noeud(int parent, int clSat) {
        generatedNode++;
        this.indice = generatedNode;
        this.parent = parent;
        this.ClauseSatisfy = clSat;
    }

    public int F(){
        if (this.isAstar) return ClauseSatisfy -frequence ;
        else return ClauseSatisfy;

    }


    @Override
    public int compareTo(Object ob) {
        int nb = (( Noeud ) ob).F();
        return nb - this.F();
    }

    private boolean searchClauseSatisfy(ArrayList<Integer> cl, int i) {
        if (!cl.isEmpty()) {
            int taille = cl.size();
            int j = 0;
            while (j < taille && cl.get(j) != i)
                j++;
            if (j < taille) return true;
            else return false;
        }
        return false;
    }

    public void treatNode(Litteral clause[][], int nbcla, Litteral v) {
        for (int i = 0; i < nbcla; i++)
            if (!searchClauseSatisfy(this.clauseSatisfy, i)) {
                int k = 0;
                while (k < 3 && (clause[i][k].num != v.num || clause[i][k].val != v.val))
                    k++;
                if (k < 3) {
                    this.ClauseSatisfy++;
                    this.clauseSatisfy.add(i);
                }
            }

    }

}
