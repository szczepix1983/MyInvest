package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.dao.WalletsRepository;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.EventService;
import com.szczepix.myinvest.services.schedulerService.SchedulerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = WalletServiceTest.WalletServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class WalletServiceTest {

    @Autowired
    private WalletServiceMock walletService;

    @Autowired
    private WalletsRepository repository;

    @Test
    public void init() {
        assertThat(walletService.postConstructInitialized).isTrue();
    }

    @Test
    public void create() {
        assertThat(walletService.create()).isNotNull();
    }

    @Test
    public void save() {
        WalletModel model = walletService.create();
        walletService.save(model);
        verify(repository, times(1)).save(eq(model.getEntity()));
    }

    @Test
    public void getWallet() {
        assertThat(walletService.getWallet(0)).isNull();
    }

    @Test
    public void getWallets() {
        assertThat(walletService.getWallets()).isNotNull();
        assertThat(walletService.getWallets().size()).isEqualTo(0);
    }

    @Configuration
    static class WalletServiceTestConfiguration {

        @Bean
        public WalletsRepository getRepository() {
            WalletsRepository repository = mock(WalletsRepository.class);
            Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
            return repository;
        }

        @Bean
        public EventService eventService(){
            return mock(EventService.class);
        }

        @Bean
        public SchedulerService schedulerService() { return mock(SchedulerService.class); }

        @Bean
        public WalletCache cache() {
            return mock(WalletCache.class);
        }

        @Bean
        public WalletService getWalletService() {
            return new WalletServiceMock();
        }
    }

    static class WalletServiceMock extends WalletService {

        boolean postConstructInitialized;

        @Override
        public void init() {
            postConstructInitialized = true;
            super.init();
        }
    }
}