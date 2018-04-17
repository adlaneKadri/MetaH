import java.io.IOException;
import java.util.ArrayList;

public class Lunch {
    private static ArrayList<String> good = new ArrayList<>();
    private static ArrayList<String> notGood = new ArrayList<>();

    public  Lunch() {
        good.add("UF75.325.100\\uf75-01.cnf");
        good.add("UF75.325.100\\uf75-02.cnf");
        good.add("UF75.325.100\\uf75-03.cnf");
        good.add("UF75.325.100\\uf75-04.cnf");
        good.add("UF75.325.100\\uf75-05.cnf");

        good.add("UF75.325.100\\uf75-07.cnf");
        good.add("UF75.325.100\\uf75-08.cnf");
        good.add("UF75.325.100\\uf75-09.cnf");
        good.add("UF75.325.100\\uf75-010.cnf");

        //Instance i = Instance.transformationOfCnfFile("G:\\repCon\\test3.cnf");
        // Instance i = Instance.transformationOfCnfFile("UF75.325.100\\uf75-05.cnf");
        //Instance i = Instance.transformationOfCnfFile("FILENAME");
        //i.treatFile(new File(FILENAME));

        notGood.add("UUF75.325.100\\uuf75-01.cnf");
        notGood.add("UUF75.325.100\\uuf75-02.cnf");
        notGood.add("UUF75.325.100\\uuf75-03.cnf");
        notGood.add("UUF75.325.100\\uuf75-04.cnf");
        notGood.add("UUF75.325.100\\uuf75-05.cnf");

        notGood.add("UUF75.325.100\\uuf75-06.cnf");
        notGood.add("UUF75.325.100\\uuf75-07.cnf");
        notGood.add("UUF75.325.100\\uuf75-08.cnf");
        notGood.add("UUF75.325.100\\uuf75-09.cnf");
        notGood.add("UUF75.325.100\\uuf75-010.cnf");


        //System.out.println(bfs.treatBFS(i,20*1000));
        //bfs.treatBFS_Statistic(i,20*1000,1);
        // bfs.treatBFS_Statistic(i,20*60*1000,6);

    }

    /*****************  BFS   *********************/

    public static void BFS_start() throws IOException {
        Lunch lunch = new Lunch();
        System.out.println("********************************* good *********************************");
        System.out.println("**************************************************************************");
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        for (int sat = 0; sat <good.size() ; sat++) {
            Instance i = Instance.transformationOfCnfFile(good.get(sat));
            i.setP(75);
            System.out.println("\t\t------------"+good.get(sat)+"------------\n");

            for (int j = 0; j < 10; j++) {
                bfs.treatBFS(i,10*60*1000);
            }
        }

        System.out.println("**************************************************************************");
        System.out.println("******************************* not good **********************************");
        System.out.println("**************************************************************************");

        // BreadthFirstSearch bfs = new BreadthFirstSearch();
        for (String aNotGood : notGood) {
            Instance i = Instance.transformationOfCnfFile(aNotGood);
            i.setP(75);
            System.out.println("\t\t---------------------" + aNotGood + "---------------------\n");
            for (int j = 0; j < 10; j++) {
                bfs.treatBFS(i, 10*1000);
            }
        }
        System.out.println("*********************************************************");

    }

    public static void BFS_startOneFile() throws IOException {
        Lunch lunch = new Lunch();
        BreadthFirstSearch bfs = new BreadthFirstSearch();

            Instance i = Instance.transformationOfCnfFile("UUF75.325.100\\uuf75-011.cnf");
            i.setP(75);
                     bfs.treatBFS(i,10*1000);
    }

    /*****************  DFS   *********************/

    public static void DFS_start() throws IOException {
        Lunch lunch = new Lunch();
        System.out.println("********************************* good *********************************");
        System.out.println("**************************************************************************");
        DeapthFirstSearch dfs = new DeapthFirstSearch();

        for (int sat = 0; sat <good.size() ; sat++) {
           Litteral[][] instance = Instance.getInstance(good.get(sat));

            System.out.println("\t\t------------"+good.get(sat)+"------------\n");

            for (int j = 0; j < 10; j++) {
                dfs.treatDFS(instance,10*1000,325,75);
            }
        }

        System.out.println("**************************************************************************");
        System.out.println("******************************* not good **********************************");
        System.out.println("**************************************************************************");

        // BreadthFirstSearch bfs = new BreadthFirstSearch();
        for (String aNotGood : notGood) {
            Litteral[][] instance = Instance.getInstance(aNotGood);

            System.out.println("\t\t---------------------" + aNotGood + "---------------------\n");
            for (int j = 0; j < 10; j++) {
                dfs.treatDFS(instance,10*1000,325,75);
            }
        }
        System.out.println("*********************************************************");

    }

    public static void DFS_startOneFile() throws IOException {
        Lunch lunch = new Lunch();
        DeapthFirstSearch dfs = new DeapthFirstSearch();
         String FILENAME = "UF75.325.100\\uf75-05.cnf";

        Litteral[][] instance = Instance.getInstance(FILENAME);

        dfs.treatDFS(instance,10*1000,325,75);

    }

    /**************COUT  UNIFORM****************/

    public static void CU_startOneFile() throws IOException {
        Lunch lunch = new Lunch();
        CoutUniform cu = new CoutUniform();
        String FILENAME = "UF75.325.100\\uf75-05.cnf";

        Litteral[][] instance = Instance.getInstance(FILENAME);

        cu.treatCoutUniform(instance,10*1000,325,75);

    }

    public static void CU_tart() throws IOException {
        Lunch lunch = new Lunch();
        System.out.println("********************************* good *********************************");
        System.out.println("**************************************************************************");
        CoutUniform cu = new CoutUniform();

        for (int sat = 0; sat <good.size() ; sat++) {
            Litteral[][] instance = Instance.getInstance(good.get(sat));

            System.out.println("\t\t------------"+good.get(sat)+"------------\n");

            for (int j = 0; j < 10; j++) {
                cu.treatCoutUniform(instance,10*1000,325,75);
            }
        }

        System.out.println("**************************************************************************");
        System.out.println("******************************* not good **********************************");
        System.out.println("**************************************************************************");

        // BreadthFirstSearch bfs = new BreadthFirstSearch();
        for (String aNotGood : notGood) {
            Litteral[][] instance = Instance.getInstance(aNotGood);

            System.out.println("\t\t---------------------" + aNotGood + "---------------------\n");
            for (int j = 0; j < 10; j++) {
                cu.treatCoutUniform(instance,10*1000,325,75);
            }
        }
        System.out.println("*********************************************************");

    }

    /**************COUT  UNIFORM****************/

    public static void Astar_startOneFile() throws IOException {
        Lunch lunch = new Lunch();
        Astar aStar = new Astar();
        String FILENAME = "UF75.325.100\\uf75-05.cnf";

        Litteral[][] instance = Instance.getInstance(FILENAME);

        aStar.treatAstar(instance,5*60*1000,325,75);

    }

    public static void Astar_tart() throws IOException {
        Lunch lunch = new Lunch();
        System.out.println("********************************* good *********************************");
        System.out.println("**************************************************************************");
        Astar aStar = new Astar();

        for (int sat = 0; sat <good.size() ; sat++) {
            Litteral[][] instance = Instance.getInstance(good.get(sat));

            System.out.println("\t\t------------"+good.get(sat)+"------------\n");

            for (int j = 0; j < 10; j++) {
                aStar.treatAstar(instance,10*1000,325,75);
            }
        }

        System.out.println("**************************************************************************");
        System.out.println("******************************* not good **********************************");
        System.out.println("**************************************************************************");

        // BreadthFirstSearch bfs = new BreadthFirstSearch();
        for (String aNotGood : notGood) {
            Litteral[][] instance = Instance.getInstance(aNotGood);

            System.out.println("\t\t---------------------" + aNotGood + "---------------------\n");
            for (int j = 0; j < 10; j++) {
                aStar.treatAstar(instance,10*1000,325,75);
            }
        }
        System.out.println("*********************************************************");

    }


}
