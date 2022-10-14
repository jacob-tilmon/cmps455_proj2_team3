import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // NEED TWO OBJECT TYPES, THREADS AND OBJECTS, BOTH NEED A FEW CONSTRUCTORS
        // IN ORDER TO BE COMPATIBLE WITH EACH TASK. - JACOB

        if(args[0].equals("-S")){
            if (args.length == 1) {
                System.out.println("Missing Argument, Please include 1, 2, or 3 after -S");
            }
            else if(args[1].equals("1")){
                //access matrix
                accessMatrix();
            }
            else if(args[1].equals("2")){
                //access list
                accessList();
            }
            else if(args[1].equals("3")){
                //capability list
                capabilityList();
            }
            else{
                System.out.println("Unexpected Argument. Please try again.");
            }
        }
        else  {
            System.out.println("Unexpected Tag. Please try again.");
        }
    }
    public static void accessMatrix(){
        Random random = new Random();
        int n = 3+random.nextInt(5); // number of row
        int m = 3+ random.nextInt(5); // nums of columns
        String [] [] matrix = new String [n][n+m];
        for (int i = 0; i< n ; i++){
            for (int j = 0; j<n+m; j++){
                int accessForObject= random.nextInt(4);
                int accessForDomain = random.nextInt(2);
                if (j<m){
                    if (accessForObject==0){
                        matrix[i][j]= "None";

                    }
                    else if (accessForObject == 1){
                        matrix[i][j] = "Read";

                    }
                    else if (accessForObject==2){
                        matrix[i][j] = "Write";

                    }
                    else if (accessForObject==3){
                        matrix[i][j] = "R/W";

                    }
                }
                else{
                    if (accessForDomain==0){
                        matrix [i][j] = " - ";
                    }
                    else{
                        matrix[i][j] = "allow";
                    }


                }

            }
        }
        for (int i = 0; i<n; i++){
            for (int j = 0; j<m+n; j++){
                System.out.print(matrix[i][j] + "     ");
            }
            System.out.println();
        }



    }
    public static void accessList(){

    }
    public static void capabilityList(){

    }
}