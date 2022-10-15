import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserThread implements Runnable {
    //getting basics for user threads --> implementing runnable, override

    //2D array
    //array list of access protection type (access to objects)
    //domain as a string or integer

    String[][] accessMatrix; //passed in main
    ArrayList<Object> accessRights;
    int domainNumber;

    public UserThread(String[][] accessMatrix, ArrayList accessRights, int domainNumber){
        this.accessMatrix = accessMatrix;
        this.accessRights = accessRights;
        this.domainNumber = domainNumber;
    }


    @Override
    public void run(){
        //develop our algorithm how it will check access matrix and handle if permission is denied



    }
}

