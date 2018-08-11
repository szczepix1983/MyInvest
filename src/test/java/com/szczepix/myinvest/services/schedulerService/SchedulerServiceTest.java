package com.szczepix.myinvest.services.schedulerService;

import com.szczepix.myinvest.jobs.BaseJobMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = SchedulerServiceTest.SchedulerServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class SchedulerServiceTest {

    @Autowired
    private SchedulerService schedulerService;

    @Test
    public void scheduleTasks() {
        BaseJobMock testJob = new BaseJobMock(1);
        schedulerService.addJob(testJob);
        int i;
        for (i = 0; i < 10; i++) {
            schedulerService.scheduleTasks();
        }
        assertEquals(testJob.counter, i);
    }

    @Test
    public void addJob() {
        BaseJobMock testJob = new BaseJobMock(1);
        schedulerService.addJob(testJob);
        schedulerService.scheduleTasks();
        assertEquals(testJob.counter, 1);
    }

    @Configuration
    static class SchedulerServiceTestConfiguration {

        @Bean
        public SchedulerService schedulerService() {
            return new SchedulerService();
        }
    }
}