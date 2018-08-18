package com.szczepix.myinvest.jobs;

import com.szczepix.myinvest.entities.SettingEntity;
import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.jobs.sync.ResourceSyncJob;
import com.szczepix.myinvest.jobs.wallets.UpdateGoldJob;
import com.szczepix.myinvest.jobs.wallets.UpdateMoneyJob;
import com.szczepix.myinvest.models.SettingModel;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.futureService.FutureService;
import com.szczepix.myinvest.services.requestService.RequestService;
import com.szczepix.myinvest.services.schedulerService.SchedulerService;
import com.szczepix.myinvest.services.settingService.SettingCache;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = JobFactoryTest.JobFactoryTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JobFactoryTest {

    @Autowired
    private JobFactory jobFactory;

    @Autowired
    private SchedulerService schedulerService;

    @After
    public void tearDown() {
        reset(schedulerService);
    }

    @Test
    public void creation() {
        assertThat(jobFactory).isNotNull();
    }

    @Test
    public void createWalletJobForMoneyTargetType() {
        WalletModel model = mock(WalletModel.class);
        when(model.getTargetType()).thenReturn(TargetType.MONEY);

        jobFactory.createWalletJob(model);
        verify(schedulerService).addJob(any(UpdateMoneyJob.class));
    }

    @Test
    public void createWalletJobForGoldTargetType() {
        WalletModel model = mock(WalletModel.class);
        when(model.getTargetType()).thenReturn(TargetType.GOLD);

        jobFactory.createWalletJob(model);
        verify(schedulerService).addJob(any(UpdateGoldJob.class));
    }

    @Test
    public void createWalletJobForSilverTargetType() {
        WalletModel model = mock(WalletModel.class);
        when(model.getTargetType()).thenReturn(TargetType.SILVER);

        jobFactory.createWalletJob(model);
//        verify(schedulerService).addJob(any(UpdateGoldJob.class));
    }

    @Test
    public void createResourceSyncJob() {
        SettingModel model = mock(SettingModel.class);
        SettingEntity entity = new SettingEntity();
        entity.setResourceSyncInterval(10);
        when(model.getEntity()).thenReturn(entity);

        jobFactory.createResourceSyncJob(model);
        verify(schedulerService).addJob(any(ResourceSyncJob.class));
    }

    @Configuration
    static class JobFactoryTestConfiguration {

        @Bean
        public JobFactory jobFactory() {
            return new JobFactory(requestService(), schedulerService());
        }

        @Bean
        public RequestService requestService() {
            return mock(RequestService.class);
        }

        @Bean
        public FutureService futureService() {
            return mock(FutureService.class);
        }

        @Bean
        public SettingCache cache() {
            return mock(SettingCache.class);
        }

        @Bean
        public SchedulerService schedulerService() {
            return mock(SchedulerService.class);
        }
    }
}