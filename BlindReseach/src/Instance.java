import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Instance {

    private static int p;  //  nimber  of  variables of this instance
    private List<Clause> clauses;

    public Instance(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public Instance() {
    }

    public static Instance transformationOfCnfFile(String file) throws IOException {
        return new Instance(Files
                .readAllLines(Paths.get(file))
                .stream()
                .filter(line -> !line.startsWith("c") && !line.startsWith("p") && !line.startsWith("%") && !line.startsWith("0"))
                .map(line -> {
                    if (line.startsWith(" ")) line = line.substring(1);
                    String[] literals = line.split("\\s+");
                    HashSet<Integer> l = new HashSet<>();
                    for (int i = 0; i < literals.length - 1; i++) {
                        l.add(Integer.parseInt(literals[i]));
                    }
                    return new Clause(l);
                })
                .collect(Collectors.toList()));
    }

    public List<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public int getP() {
        return p;
    }

    public void setP(int px) {
        p = px;
    }

    // instance methods

    public boolean isSatisfy(Node node) {
        return clauses
                .stream()
                .map(clause -> clause.isSatisfy(node))
                .reduce((x, y) -> x && y).get();
    }

    public int literalNumber(File file) {
        //     br file is the input
        BufferedReader br = null;
        FileReader fr = null;
        try {
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine = br.readLine();
            int p = Integer.parseInt(this.getWords(sCurrentLine)[this.getWords(sCurrentLine).length - 2]);
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    private String[] getWords(String text) {
        return text.split("\\s+");
    }

    public long clauseSatisfyWithThisSolution(Node node) {
        //return number of clause satisfy  this node
        return clauses.stream().filter(clause -> clause.isSatisfy(node)).count();
    }


    // to get instance from a file
    public static Litteral[][] getInstance(String file) {
        Litteral[][] instance = null;

        BufferedReader br = null;
        FileReader fr = null;
        File f;
        int d;

        try {
            f = new File(file);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String sCurrentLine;
            do {
                sCurrentLine = br.readLine();
            } while (sCurrentLine.charAt(0) != 'p');

            sCurrentLine = sCurrentLine.replaceAll("\\s+", " ");
            String[] s = sCurrentLine.split(" ");

            int nbvar = Integer.parseInt(s[2]);
            int nbcla = Integer.parseInt(s[3]);

            instance = new Litteral[nbcla][3];

            int ind = 0;

            while ((sCurrentLine = br.readLine()) != null && ind != nbcla) {
                sCurrentLine = sCurrentLine.replaceAll("\\s+", " ");
                sCurrentLine = sCurrentLine.replaceAll("^ ", "");
                s = sCurrentLine.split(" ");
                int[] nume = new int[s.length];

                for (int i = 0; i < s.length; i++) {
                    nume[i] = Integer.parseInt(s[i]);
                    //System.out.print(nume[i] + " ");
                }  //System.out.println();

                if (ind != nbcla) {
                    Litteral v;
                    for (int j = 0; j < 3; j++) {
                        if (nume[j] < 0) {
                            v = new Litteral(Math.abs(nume[j]), 0);
                            instance[ind][j] = v;
                        } else {
                            v = new Litteral(nume[j], 1);
                            instance[ind][j] = v;
                        }
                    }
                }
                ind++;
            }
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static HashMap<Integer,Integer> literalFrequence(Litteral[][] instance,int line){
        HashMap<Integer,Integer> literalfrq = new HashMap<>();

        for (int i = 0; i <line ; i++) {
            for (int j = 0; j <3 ; j++) {
                if(!literalfrq.containsKey(instance[i][j].getLiteral())){
                     literalfrq.put(instance[i][j].getLiteral(),1);
                }else {
                    Integer l = literalfrq.get(instance[i][j].getLiteral());
                    literalfrq.remove(instance[i][j].getLiteral());
                   literalfrq.put(instance[i][j].getLiteral() ,l+1);
                }
            }
        }

        return  literalfrq;
    }

}
