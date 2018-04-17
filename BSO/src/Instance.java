import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by dzcod3r on 3/4/17.
 */
public class Instance {
    private HashSet<Clause> clauses;

    private String cheminDuFichier;
    private int nombreVariables, nombreClauses;


    /***************setters + getters**********************/
    public Instance(String cheminDuFichier) throws IOException {
        this.cheminDuFichier = cheminDuFichier;
        clauses = new HashSet<>();
        //lireLesClauses();
        fileToInstance();


    }

    public Instance(HashSet<Clause> clauses){
        this.clauses= clauses;
    }

    public HashSet<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(HashSet<Clause> clauses) {
        this.clauses = clauses;
    }

    public String getCheminDuFichier() {
        return cheminDuFichier;
    }

    public void setCheminDuFichier(String cheminDuFichier) {
        this.cheminDuFichier = cheminDuFichier;
    }

    public int getNombreVariable() {
        return nombreVariables;
    }

    public void setNombreVariable(int nombreVariables) {
        this.nombreVariables = nombreVariables;
    }

    public int getNombreClauses() {
        return nombreClauses;
    }

    public void setNombreClauses(int nombreClauses) {
        this.nombreClauses = nombreClauses;
    }

    /****************************/ /****Function****/ /********************************/


    @Override
    public String toString() {
        return "Instance{" +
                "clauses=\n" + clauses +
                ",\n cheminDuFichier='" + cheminDuFichier + '\'' +
                ",\n nombreVariable=" + nombreVariables +
                ",\n nombreClauses=" + nombreClauses +
                "\n}";
    }
    public void display() {
        System.out.println(this);
    }

    public void displayInstance(){
        clauses.stream().forEach(e-> System.out.println(e));
        System.out.println("\nnumber Of Clauses: "+clauses.size()+" \nnumber Of variables: "+nombreVariables);
    }

    private void lireLesClauses() throws IOException {

        String headerReg = "p[ \\t\\n\\x0B\\f\\r]+cnf[ \\t\\n\\x0B\\f\\r]+([0-9]+)[ \\t\\n\\x0B\\f\\r]+([0-9]+)";
        String clauseReg = "(.*)[ \\t\\n\\x0B\\f\\r]+0";
        Boolean headerFound = false;

        BufferedReader bufferedReader;

        bufferedReader = new BufferedReader(new FileReader(cheminDuFichier));
        String buffer;

        while (!headerFound && (buffer = bufferedReader.readLine()) != null) {
            //get header:
            Pattern p = Pattern.compile(headerReg);
            Matcher m = p.matcher(buffer);
            if (m.find() && m.groupCount() > 0) {
                headerFound = true;
                nombreVariables = Integer.parseInt(m.group(1));
                nombreClauses = Integer.parseInt(m.group(2));

            }

        }

        if (headerFound) {
            clauses = new HashSet<>();
            int cptClauses = 0;
            while (cptClauses != nombreClauses && (buffer = bufferedReader.readLine()) != null) {
                //get clauses:
                Pattern p = Pattern.compile(clauseReg);
                Matcher m = p.matcher(buffer);
                if (m.find() && m.groupCount() > 0) {
                    cptClauses++;
                    String clause = m.group(1);

                    //get literals:
                    clause = clause.trim();
                    String[] literals = clause.split("[ \\t\\n\\x0B\\f\\r]+");
                    ArrayList<Integer> uneClause = new ArrayList<>();
                    for (int i = 0; i < literals.length; i++) {
                        uneClause.add(Integer.valueOf(literals[i]));
                    }
                    clauses.add(new Clause(uneClause));
                }
            }
        }
        bufferedReader.close();
    }

    public void getInstance() throws IOException {
        //     br file is the input
        BufferedReader br = null;
        FileReader fr = null;

        //we are gonna write on this file :
        BufferedWriter output = null;

        //br = new BufferedReader(new FileReader(FILENAME));
        fr = new FileReader(new File(cheminDuFichier));
        br = new BufferedReader(fr);

        String sCurrentLine;
        sCurrentLine = br.readLine();

        while (sCurrentLine.charAt(0) == 'c') {
            sCurrentLine = br.readLine();
        }
        this.informationsOfInstance();

        String[] l;
        int[] nume;
        ArrayList<Integer> literals;
        System.out.println("p cnf " + nombreVariables + " " + nombreClauses);
        int clauseTraiter = 0;


        while ((sCurrentLine = br.readLine()) != null && sCurrentLine.charAt(0) != '%') {
            // System.out.println(sCurrentLine);
            l = sCurrentLine.split(" ");
            nume = new int[l.length];
            literals = new ArrayList<>();
            for (int i = 0; i < l.length - 1; i++) {
                nume[i] = Integer.parseInt(l[i]);
                // System.out.println(nume[i]);

                literals.add(new Integer(nume[i]));

            }
            clauses.add(new Clause(literals));


        }

        for (Clause c : clauses
                ) {
            c.displayClause();
        }
        br.close();
        fr.close();


    }

    public void fileToInstance() throws IOException {
        informationsOfInstance();
        HashSet<Clause> c = Files.readAllLines(Paths.get(cheminDuFichier))
                .stream()
                .filter(line -> !line.startsWith("c")
                        && !line.startsWith("p")
                        && !line.startsWith("0")
                        && !line.startsWith("%")
                ).map(line->{
                    if (line.startsWith(" ")) line = line.substring(1);
                    String[] literals = line.split("\\s+");
                    ArrayList<Integer> l = new ArrayList<>();
                    for (int i = 0; i < literals.length - 1; i++) {
                        l.add(Integer.parseInt(literals[i]));
                    }
                    clauses.add(new Clause(l));
                    return new Clause(l);
                }).collect(Collectors.toCollection(HashSet::new));


    }
    public void informationsOfInstance() throws IOException {
        String FirstLine = "p[ \\t\\n\\x0B\\f\\r]+cnf[ \\t\\n\\x0B\\f\\r]+([0-9]+)[ \\t\\n\\x0B\\f\\r]+([0-9]+)";
        Boolean FirstLineNotFound = true;

        BufferedReader bufferedReader;
        FileReader r = new FileReader(cheminDuFichier);
        bufferedReader = new BufferedReader(r);
        String buffer;

        while (FirstLineNotFound && (buffer = bufferedReader.readLine()) != null) {

            Pattern pattern = Pattern.compile(FirstLine);
            Matcher matcher = pattern.matcher(buffer);

            if (matcher.find() && matcher.groupCount() > 0) {
                FirstLineNotFound = false;
                nombreVariables = Integer.parseInt(matcher.group(1));
                nombreClauses = Integer.parseInt(matcher.group(2));

            }

        }

    }

    // number of clause satisfy
    public int quality(SolutionSAT solution){
    return ( int ) clauses.stream()
            .filter(clause -> clause.isSatisfy(solution))
            .count();

    }
    public boolean isSatisfy(SolutionSAT solution){
        return  quality(solution)==nombreClauses;
    }

}
