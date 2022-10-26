import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class UserThreadTask2 implements Runnable {
    Random rand = new Random();
    ArrayList<ArrayList<String>> acl;
    ArrayList<AccessProtectedObject> objects;
    int id;
    int domainNumber;
    int N;
    int M;
    String[] randomWords = {"tacos", "chicken", "lobster", "dog", "Lebron"};

    public UserThreadTask2(ArrayList<ArrayList<String>> acl, ArrayList<AccessProtectedObject> objects, int domainNumber,  int N, int M) {
        this.acl = acl;
        this.objects = objects;
        this.domainNumber = domainNumber;
        id = domainNumber;
        this.N = N;
        this.M = M;

    }

    @Override
    public void run() {
        int count = 0;
        while (count < 5) {
            String randomRequest = "";
            int X = rand.nextInt(N + M); // which object/domain to request for a thread
            //System.out.println("X: "+ X);
            if (X < M) {
                int temp = rand.nextInt(2);
                if (temp == 0) {
                    randomRequest = "R";
                    System.out.println("Thread[" + id + "] in Domain " + domainNumber + " is making a read request on object " + X);
                }
                if (temp == 1) {
                    randomRequest = "W";
                    System.out.println("Thread[" + id + "] in Domain " + domainNumber + " is making a write request on object " + X);
                }
            } else { // if X > M, X will be trying to get a domain.
                randomRequest = "allow";
                 if (domainNumber == ((N + M) - X - 1)) {
                //System.out.println("Attempted domain switch to own domain");
                  continue;
                }
                System.out.println("Thread [" + id + "] in Domain " + domainNumber +
                        " is attempting a domain switch to domain " + ((N + M) - X - 1));
            }
            if (acl.get(X).get(domainNumber).contains(randomRequest) || // when access is granted
                    acl.get(X).get(domainNumber).equals(randomRequest)) {
                //System.out.println("Something is possible");
                if (X < M) {
                    if (randomRequest.equals("R")) {
                        objects.get(X).read(id, domainNumber, X);
                    }
                    if (randomRequest.equals("W")) {
                        String someWord = "";
                        someWord = randomWords[rand.nextInt(randomWords.length)];
                        System.out.println("Thread[" + id + "] is writing '" + someWord + "' to Object " + X);
                        objects.get(X).write(someWord);
                    }
                } else {
                    domainNumber = (M + N) - X - 1;
                    System.out.println("Thread[" + id + "]'s new domain is: " + domainNumber);
                }
            } else { // when access is denied
                System.out.println("Thread[" + id + "] does not have permission.");
                int yield = rand.nextInt(5) + 3;
                for (int j = 0; j < yield; j++) {
                    Thread.yield();
                }
            }
            int yield = rand.nextInt(5) + 3;
            for (int j = 0; j < yield; j++) {
                Thread.yield();
            }
            count++;
        }
    }
}
