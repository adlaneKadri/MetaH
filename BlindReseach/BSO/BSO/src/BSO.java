import java.util.ArrayList;
import java.util.Comparator;

public class BSO {
    private ArrayList<SolutionSAT> danceTable;  //best literals of each localSearch
    private Instance instance;          // our instance or problem data
    private ArrayList<SolutionSAT> taboo;//tableau d'interpretation visitée
    private int flip;           // number of bees
    private int nbRecherchesLocales;    //number of localSearch , -duree du traitement BSO-
    private int maxsChances;
    private long maxIterations;         //duree de vie d'une cellule d'abeille
    public int bestQuality;
    public ArrayList<Integer> literals;


    public BSO(Instance instance, int flip, int nbRecherchesLocales, int maxsChances, long maxIterations) {
        this.instance = instance;
        this.flip = flip;
        this.nbRecherchesLocales = nbRecherchesLocales;
        this.maxsChances = maxsChances;
        this.maxIterations = maxIterations;
        taboo = new ArrayList<>();
    }

    public ArrayList<Bee> searchPoint(SolutionSAT solution) {
        ArrayList<Bee> bees = new ArrayList<>();
        int k = instance.getNombreVariable() / flip;

        //System.out.println("k = " + k);
        for (int i = 0; i <flip ; i++) {
            SolutionSAT s = solution.clone();
            for (int j = 0; j <k ; j++) {
                /*System.out.println("i = " + i);
                System.out.println("j = " + j);
                System.out.println("j*flip+i = " + (j*flip+i));*/
               if (j*flip+i<instance.getNombreVariable())s.inverse(j*flip+i);
            }
           // System.out.println("s = " + s);
            bees.add(new Bee(instance,s,nbRecherchesLocales));
        }
            return bees;
      }

    public SolutionSAT runBSO(){
    int cpt =0;
    SolutionSAT sRef = Bee.beeInit(instance,nbRecherchesLocales).getSolutionBee();
    int quality =sRef.quality(instance);
    SolutionSAT bestSol=sRef;
    while (cpt<maxIterations){

        // to get from Dance The best Solution of bees ..
        sRef= searchPoint(sRef)
                .stream()
                .map(beeDance->beeDance.localSearch(taboo))
                .max(Comparator.comparingInt(solution->solution.quality(instance))).get();

        if (quality<sRef.quality(instance)){
            quality =sRef.quality(instance);
            bestSol=sRef;
        }
        //System.out.println("( "+quality+" , "+sRef.quality(instance)+" )");
        System.out.println("sRef.quality :"+quality);
        cpt++;
    }


        System.out.println("best quality: "+bestSol.quality(instance));
        System.out.println("best solution:\n"+bestSol);
        bestSol.quality=bestSol.quality(instance);
        return bestSol;
    }


    }


