
import java.util.Random;
import java.util.concurrent.Semaphore;

public class AccessProtectedObject {
    String word="start";
    Semaphore area = new Semaphore(1);
    Random random = new Random();

    public AccessProtectedObject() {
    }

    public void read(int threadId, int domain, int obj) {
        try {
            area.acquire();
            int yield = random.nextInt(5)+3;
            for( int i = 0; i<yield; i++){ Thread.yield(); }
            System.out.println("Thread["+threadId+"] from Domain "+domain+" has read '"+word+"' from Object "+obj);
            area.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String newWord) {
        try {
            area.acquire();
            int yield = random.nextInt(5)+3;
            for( int i = 0; i<yield; i++){ Thread.yield(); }
            this.word = newWord;
            area.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
