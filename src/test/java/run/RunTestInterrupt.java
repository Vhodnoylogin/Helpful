package run;

import helpful.interrupt.InterruptTimeout;
import helpful.interrupt.exceptions.InterruptionException;
import helpful.interrupt.exceptions.TaskTimeoutException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class RunTestInterrupt {

    @Test
    public void testInterruptTrue() {
        try {
            Integer in = 2;
            Integer out = in;
            out = InterruptTimeout.interruption(() -> {
                TimeUnit.SECONDS.sleep(4);
                return in;
            }, TimeUnit.SECONDS, 5);
            assert in.equals(out);
        } catch (InterruptionException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testInterruptFalse() {
        try {
            Integer in = 2;
            Integer out = in;
            out = InterruptTimeout.interruption(() -> {
                TimeUnit.SECONDS.sleep(7);
                return in;
            }, TimeUnit.SECONDS, 3);
            assert in.equals(out);
        } catch (TaskTimeoutException e) {
            System.out.println("ALL RIGHT!");
            e.printStackTrace();
            assert true;
        } catch (InterruptionException e) {
            e.printStackTrace();
            assert false;
        }
    }
}