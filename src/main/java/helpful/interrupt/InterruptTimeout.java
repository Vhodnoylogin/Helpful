package helpful.interrupt;

import helpful.interrupt.exceptions.InterruptionException;
import helpful.interrupt.exceptions.TaskExecuteException;
import helpful.interrupt.exceptions.TaskTimeoutException;

import java.util.concurrent.*;

public interface InterruptTimeout {
    static <R> R interruption(Callable<R> task, TimeUnit tu, Integer numberOfTime) throws InterruptionException {
        FutureTask<R> ftask = new FutureTask<>(task);
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(ftask);
        try {
            return ftask.get(numberOfTime, tu);
        } catch (TimeoutException | InterruptedException e) {
            throw new TaskTimeoutException(e);
        } catch (ExecutionException e) {
            throw new TaskExecuteException(e);
        } finally {
            ftask.cancel(true);
            service.shutdown();
        }
    }
}