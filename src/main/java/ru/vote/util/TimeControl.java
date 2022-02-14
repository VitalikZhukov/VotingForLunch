package ru.vote.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vote.service.RestaurantService;
import ru.vote.service.UserService;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

//  https://stackoverflow.com/questions/20387881/how-to-run-certain-task-every-day-at-a-particular-time-using-scheduledexecutorse

public class TimeControl {

    @Autowired
    public UserService userService;

    @Autowired
    public RestaurantService restaurantService;

    private static final int TARGET_HOURS = 14;
    private static final int TARGET_MIN = 0;
    private static final int TARGET_SEC = 0;

    private final Logger log = LoggerFactory.getLogger(TimeControl.class);

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private volatile boolean isBusy = false;
    private volatile ScheduledFuture<?> scheduledTask = null;

    public TimeControl() {
    }

    @PostConstruct
    public void start() {
        log.info("Start TimeControl");
        scheduleNextTask(doTaskWork());
    }

    private Runnable doTaskWork() {
        return () -> {
            //start
            try {
                isBusy = true;
                userService.resetAllRestaurantId();
                restaurantService.resetAllVoteCounter();
                //finish
            } catch (Exception e) {
                log.info("class TimeControl, method doTaskWork, exception:" + e);
            } finally {
                isBusy = false;
            }
            scheduleNextTask(doTaskWork());
            //complete
        };
    }

    private void scheduleNextTask(Runnable task) {
        //schedule
        long delay = computeNextDelay();
        //delay
        scheduledTask = executorService.schedule(task, delay, TimeUnit.SECONDS);
    }

    private static long computeNextDelay() {
        ZonedDateTime zonedNow = minskDateTime();
        ZonedDateTime zonedNextTarget = zonedNow.withHour(TARGET_HOURS).withMinute(TARGET_MIN).withSecond(TARGET_SEC).withNano(0);

        if (zonedNow.compareTo(zonedNextTarget) > 0) {
            zonedNextTarget = zonedNextTarget.plusDays(1);
        }

        Duration duration = Duration.between(zonedNow, zonedNextTarget);
        return duration.getSeconds();
    }

    private static ZonedDateTime minskDateTime() {
        return ZonedDateTime.now(ZoneId.of("Europe/Minsk"));
    }

    public void stop() {
        //stopping
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
        }
        executorService.shutdown();
        //stopped
        try {
            // wait one minute to termination if busy
            if (isBusy) {
                executorService.awaitTermination(1, TimeUnit.MINUTES);
            }
        } catch (InterruptedException e) {
            log.info("class TimeControl, method stop, exception:" + e);
        }
    }


}
