import java.util.ArrayList;
import java.util.Random;

public class Main {
    static Random rand = new Random();

    public static void main(String[] args) {

        // NEED TWO OBJECT TYPES, THREADS AND OBJECTS, BOTH NEED A FEW CONSTRUCTORS
        // IN ORDER TO BE COMPATIBLE WITH EACH TASK. - JACOB

        if (args[0].equals("-S")) {

            int N = 0;
            int M = 0;
            N = rand.nextInt(4) + 3;
            M = rand.nextInt(4) + 3;
            if (args.length == 1) {
                System.out.println("Missing Argument, Please include 1, 2, or 3 after -S");
            } else if (args[1].equals("1")) {
                //access matrix
                accessMatrix(N, M);
            } else if (args[1].equals("2")) {
                //access list
                accessList(N, M);
            } else if (args[1].equals("3")) {
                //capability list
                capabilityList(N, M);
            } else {
                System.out.println("Unexpected Argument. Please try again.");
            }
        } else {
            System.out.println("Unexpected Tag. Please try again.");
        }
    }

    public static void accessMatrix(int N, int M) {
        System.out.println(N + " Domains");
        System.out.println(M + " Objects");

        String[][] matrix = new String[N][N + M];

        //GENERATING THE MATRIX
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M + N; j++) {
                if (j < M) {
                    int gen = rand.nextInt(4);
                    if (gen == 0) {
                        matrix[i][j] = "-";
                    }
                    if (gen == 1) {
                        matrix[i][j] = "R";
                    }
                    if (gen == 2) {
                        matrix[i][j] = "W";
                    }
                    if (gen == 3) {
                        matrix[i][j] = "R/W";
                    }
                } else {
                    int gen = rand.nextInt(2);
                    if (gen == 0) {
                        matrix[i][j] = "-";
                    }
                    if (gen == 1) {
                        matrix[i][j] = "allow";
                    }
                }
            }
        }
        //printing out the objects title
        for (int i = 0; i < M; i++) {
            System.out.format("%10s", "F" + i + "\t");
        }
        //printing out the domains allow or - title
        for (int i = 0; i < N; i++) {
            System.out.format("%10s", "D" + i + "\t");
        }
        System.out.println();
        //printing out the domain column on the left
        for (int i = 0; i < N; i++) {
            System.out.print("D" + i);
            for (int j = 0; j < M + N; j++) {
                System.out.format("%10s", matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        ArrayList<AccessProtectedObject> objects = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            AccessProtectedObject obj = new AccessProtectedObject();
            objects.add(obj);
        }

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Runnable t1 = new UserThread(matrix, objects, i, N, M);
            Thread t2 = new Thread(t1);
            threads.add(t2);
        }
        for (Thread t : threads) t.start();

    }

    public static void accessList(int N, int M) {
        System.out.println(N + " Domains");
        System.out.println(M + " Objects");
        System.out.println( );
        ArrayList<ArrayList<String>> acl = new ArrayList<>(N + M);
        int domainInRow = 0;
        int domainInColumn = 0;
        for (int i = 0; i < N + M; i++) {
            ArrayList<String> objects = new ArrayList<>(N);
            for (int j = 0; j < N; j++) {
                if (i < M) {
                    int gen = rand.nextInt(4);
                    if (gen == 0) {objects.add("-");}
                    if (gen == 1) {objects.add("R");}
                    if (gen == 2) {objects.add("W");}
                    if (gen == 3) {objects.add("R/W");}
                } else {
                    int gen = rand.nextInt(2);
                    //System.out.print( " Row : "+ domainInRow+", Column : "+ domainInColumn);
                    if (domainInColumn==domainInRow++) {objects.add("-");}
                    else if (gen == 0) {objects.add("-");}
                    else if (gen == 1) {objects.add("allow");}
                }
            }
            if(i>=M){domainInColumn++;}
            domainInRow = 0;
            acl.add(objects);
        }
        int index = 0; // to keep track of domain index
        for (int i = 0; i < acl.size(); i++) {
            if (i<M){
                System.out.print("Object "+i+ " : ");
            }
            else{
                System.out.print("Domain "+index+++ " : ");
            }
            for (int j = 0; j<acl.get(i).size(); j++){
                if (j==acl.get(i).size()-1){
                    System.out.print("(D"+j +" , "+ acl.get(i).get(j)+ ")");
                }
                else{
                    System.out.print("(D"+j +" , "+ acl.get(i).get(j)+ ") --> ");
                }
            }
            System.out.println();
        }
        System.out.println();
        ArrayList<AccessProtectedObject> objects = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            AccessProtectedObject obj = new AccessProtectedObject();
            objects.add(obj);
        }

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Runnable t1 = new UserThreadTask2(acl, objects, i, N, M);
            Thread t2 = new Thread(t1);
            threads.add(t2);
        }
        for (Thread t : threads) t.start();
    }
    public static void capabilityList(int N, int M){
        ArrayList<ArrayList<String>> domains = new ArrayList<>();
        for (int i = 0; i < N; i++){
            ArrayList<String> newDomain = new ArrayList<>();
            for (int j = 0; j < M+N; j++){
                if (j < M){
                    int gen = rand.nextInt(4);
                    if (gen == 0) {newDomain.add("-"); }
                    if (gen == 1) { newDomain.add("R"); }
                    if (gen == 2) { newDomain.add("W"); }
                    if (gen == 3) { newDomain.add("R/W"); }
                }
                else{
                    int gen = rand.nextInt(2);
                    if (gen == 0) { newDomain.add("-"); }
                    if (gen == 1) { newDomain.add("allow"); }
                }
            }
            domains.add(newDomain);
        }

        System.out.println("Number of Domains " +N);
        System.out.println("Number of Objects: " + (M+N));
        for(int i = 0; i<N; i++){
            System.out.print("Domain " + i );
            System.out.println();
            for(int j = 0; j<(N+M); j++){
                System.out.print(" " +domains.get(i).get(j)+"\t");
            }
            System.out.println();
        }

        ArrayList<AccessProtectedObject> objects = new ArrayList<>();
        for (int i = 0; i <M+N;i++){
            AccessProtectedObject newObj = new AccessProtectedObject();
            objects.add(newObj);
        }
        //print capability lists
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i<N; i++){
            Runnable t1 = new UserThreadT3(domains,i,objects,M,N);
            Thread t2 = new Thread(t1);
            threads.add(t2);
        }
        for(Thread t : threads) t.start();
    }
}