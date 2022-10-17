import java.util.ArrayList;
import java.util.Random;

public class Main {
    static Random rand = new Random();
    public static void main(String[] args) {

        // NEED TWO OBJECT TYPES, THREADS AND OBJECTS, BOTH NEED A FEW CONSTRUCTORS
        // IN ORDER TO BE COMPATIBLE WITH EACH TASK. - JACOB

        if(args[0].equals("-S")){

            int N =0;
            int M =0;
            N = rand.nextInt(4)+3;
            M = rand.nextInt(4)+3;
            if (args.length == 1) {
                System.out.println("Missing Argument, Please include 1, 2, or 3 after -S");
            }
            else if(args[1].equals("1")){
                //access matrix
                accessMatrix(N,M);
            }
            else if(args[1].equals("2")){
                //access list
                accessList(N,M);
            }
            else if(args[1].equals("3")){
                //capability list
                capabilityList(N,M);
            }
            else{
                System.out.println("Unexpected Argument. Please try again.");
            }
        }
        else  {
            System.out.println("Unexpected Tag. Please try again.");
        }
    }
    public static void accessMatrix(int N, int M){
        System.out.println(N + " Domains");
        System.out.println(M + " Objects");

        String [][] matrix = new String[N][N+M];

        //GENERATING THE MATRIX
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M+N; j++){
                if (j < M){
                    int gen = rand.nextInt(4);
                    if (gen == 0) { matrix[i][j] = "-"; }
                    if (gen == 1) { matrix[i][j] = "R"; }
                    if (gen == 2) { matrix[i][j] = "W"; }
                    if (gen == 3) { matrix[i][j] = "R/W"; }
                }
                else{
                    int gen = rand.nextInt(2);
                    if (gen == 0) { matrix[i][j] = "-"; }
                    if (gen == 1) { matrix[i][j] = "allow"; }
                }
            }
        }
        for(int i = 0; i < N; i++){
            System.out.print("D"+i);
            for(int j = 0; j < M+N; j++) {
                System.out.format("%10s", matrix[i][j] + "\t");
            }
            System.out.println();
        }

        ArrayList<AccessProtectedObject> objects = new ArrayList<>();
        for(int i = 0; i < M; i++){
            AccessProtectedObject obj = new AccessProtectedObject();
            objects.add(obj);
        }

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < N; i++){
            Runnable t1 = new UserThread(matrix, objects, i,N,M);
            Thread t2 = new Thread(t1);
            threads.add(t2);
        }
        for (Thread t : threads) t.start();

    }
    public static void accessList(int N, int M){

    }
    public static void capabilityList(int N, int M){

    }
}