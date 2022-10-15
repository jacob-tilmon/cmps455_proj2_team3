
import java.util.concurrent.Semaphore;

public class AccessProtectedObject {
    String word="start";
    static Semaphore area = new Semaphore(1);

    public AccessProtectedObject() {
    }

    //public AccessProtectedObject(String newWord) {
    //    this.word = newWord;
    //}

    public void read() {
        try {
            area.acquire();
            System.out.println(word);
            area.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String newWord) {
        try {
            area.acquire();
            this.word = newWord;
            area.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
