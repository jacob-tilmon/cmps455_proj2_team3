import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class UserThreadT3 implements Runnable{

    ArrayList<ArrayList<String>> domains;
    ArrayList<AccessProtectedObject> objects;
    int id;
    int curDomain;
    int M;
    int N;
    Random random = new Random();

    public UserThreadT3(ArrayList<ArrayList<String>> domains,int id, ArrayList<AccessProtectedObject> objs, int M,int N){
        this.domains = domains;
        this.id = id;
        this.objects = objs;
        curDomain = id;
        this.M = M;
        this.N = N;
    }

    @Override
    public void run(){

        int attempts =5;
        while (attempts > 0) {

            int X = random.nextInt(M);
            ArrayList<String> myCurrentCapabilityList = domains.get(curDomain);
            String accessReq = "";
            if (X < M) {
                int requestInt = random.nextInt(2);
                if (requestInt == 0) {
                    accessReq = "R";
                    System.out.println("Thread[" + id + "] in domain "+curDomain+" is making a read request on object " + X);
                }
                if (requestInt == 1) {
                    accessReq = "W";
                    System.out.println("Thread[" + id + "] in domain "+curDomain+" is making a write request on object " + X);
                }
            } else {
                if((N + M) - X - 1 == curDomain){
                    continue;
                }
                accessReq = "allow";
                System.out.println("Thread[" + id + "] in domain "+curDomain+" is making a domain switch to domain " + ((N + M) - X - 1));
            }

            if (myCurrentCapabilityList.get(X).contains(accessReq) ||
                    myCurrentCapabilityList.get(X).equals(accessReq)) {
                if (X < M) {
                    if (accessReq.equals("R")) {
                        objects.get(X).read(id, curDomain, X);
                    }
                    if (accessReq.equals("W")) {
                        System.out.println("Thread[" + id + "] is writing to object " + X);
                        objects.get(X).write("NewWord");
                    }
                } else
                    curDomain = (N + M) - X - 1;
            } else {
                System.out.println("Thread[" + id + "] permission was denied.");
                int waitTime = random.nextInt(5)+3;
                for (int i = 0; i < waitTime; i++) Thread.yield();
            }
            int waitTime = random.nextInt(5)+3;
            for (int i = 0; i < waitTime; i++) Thread.yield();
            attempts--;
        }
    }
}
