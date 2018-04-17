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


        for (int i = 0; i <flip ; i++) {
            SolutionSAT s = solution.clone();
            for (int j = 0; j <k ; j++) {

               if (j*flip+i<instance.getNombreVariable())s.inverse(j*flip+i);
            }

            bees.add(new Bee(instance,s,nbRecherchesLocales));
        }
            return bees;
      }

    public SolutionSAT runBSO(){
    int cpt =0;
    SolutionSAT sRef = Bee.beeInit(instance,nbRecherchesLocales).getSolutionBee();
    int quality =sRef.quality(instance);
    SolutionSAT bestSol=sRef;

    boolean solutionFound = false;
        long testFinish , testTime  ;

        long startingTest = System.currentTimeMillis();
        while (cpt<maxIterations && !solutionFound){
        taboo.add(sRef);
        // to get from Dance The best Solution of bees ..
        sRef= searchPoint(sRef)
                .stream()
                .map(beeDance->beeDance.localSearch1(taboo))
                .filter(x -> !taboo.contains(x))
                .max(Comparator.comparingInt(solution->solution.quality(instance))).get();

        if (quality<sRef.quality(instance)){
            quality =sRef.quality(instance);
            bestSol=sRef;

        }
        System.out.println("\tsRef.quality :"+quality+"  ---->  Iteration: "+cpt);
        cpt++;
        if (bestSol.quality(instance)==instance.getNombreClauses()){
            solutionFound = true;
        }
    }
    testFinish =System.currentTimeMillis();
        testTime = (testFinish-startingTest)/1000;

        double taux = (bestSol.quality(instance)/instance.getNombreClauses())*100;

        System.out.println("\n\t\tTime            : "+testTime+" second ");
        System.out.println("\t\tbest quality    :      "+bestSol.quality(instance)+
                "\n\t\tTaux            :   "+taux+"%"
                +"\n\t\tnombre of itération   :  "+cpt+"\n\t\t");
        //System.out.println("best solution:\n"+bestSol);

        bestSol.display();
        bestSol.quality=bestSol.quality(instance);
        return bestSol;
    }


    }


