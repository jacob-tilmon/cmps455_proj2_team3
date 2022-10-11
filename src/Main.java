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

    }
    public static void accessList(){

    }
    public static void capabilityList(){

    }
}