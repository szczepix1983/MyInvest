package com.szczepix.myinvest.services.schedulerService;

import com.szczepix.myinvest.jobs.IBaseJob;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SchedulerService extends ConcurrentTaskScheduler {

    private static final Logger LOG = Logger.getLogger("SchedulerService");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private List<IBaseJob> jobs = new ArrayList<>();

    public SchedulerService() {
        super();
    }

    @Scheduled(fixedRate = 1000, initialDelay = 5000)
    public void scheduleTasks() {
        jobs.stream().filter(IBaseJob::isReady).forEach(this::executeJob);
    }

    public void addJob(final IBaseJob baseJob) {
        jobs.add(baseJob);
    }

    private void executeJob(final IBaseJob baseJob) {
        try {
            LOG.info("Execute job (" + dateTimeFormatter.format(LocalDateTime.now()) + ") - " + baseJob.toString());
            baseJob.submit();
        } catch (Exception e) {
            LOG.warning("Execute job exception - " + baseJob.toString());
        }
    }
}