package helpful.interrupt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TimeInterval {
    protected final TimeUnit hh = TimeUnit.HOURS;
    protected final TimeUnit mi = TimeUnit.MINUTES;
    protected final TimeUnit ss = TimeUnit.SECONDS;
    protected final TimeUnit ms = TimeUnit.MILLISECONDS;

    protected Integer hhValue;
    protected Integer miValue;
    protected Integer ssValue;
    protected Integer msValue;

    public TimeInterval parse(String time, DateTimeFormatter formatter) {
        LocalDateTime timme = LocalDateTime.parse(time, formatter);
        this.hhValue = timme.getHour();
        this.miValue = timme.getMinute();
        this.ssValue = timme.getSecond();


        return this;
    }
}