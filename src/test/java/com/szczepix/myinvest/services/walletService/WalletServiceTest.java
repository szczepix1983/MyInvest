package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.dao.IWalletRepository;
import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.jobs.IJobFactory;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.EventService;
import org.junit.After;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = WalletServiceTest.WalletServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class WalletServiceTest {

    @Autowired
    private IWalletService walletService;

    @Autowired
    private IWalletRepository repository;

    @Autowired
    private WalletCache cache;

    @Autowired
    private IJobFactory jobFactory;

    @After
    public void tearDown() {
        reset(repository);
    }

    @Test
    public void init() {
        walletService.init();
        verify(cache, atLeast(1)).update(any());
        verify(jobFactory, atLeast(1)).createWalletJob(any());
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
        assertThat(walletService.getWallets().size()).isEqualTo(1);
    }

    @Configuration
    static class WalletServiceTestConfiguration {

        @Bean
        public IWalletRepository repository() {
            IWalletRepository repository = mock(IWalletRepository.class);
            List<WalletEntity> list = new ArrayList<>();
            list.add(new WalletEntity());
            Mockito.when(repository.findAll()).thenReturn(list);
            return repository;
        }

        @Bean
        public EventService eventService() {
            return mock(EventService.class);
        }

        @Bean
        public WalletCache cache() {
            WalletCache cache = mock(WalletCache.class);
            List<WalletModel> list = new ArrayList<>();
            list.add(mock(WalletModel.class));
            Mockito.when(cache.getCache()).thenReturn(list);
            return cache;
        }

        @Bean
        public IWalletService getWalletService() {
            return new WalletService(repository(), cache(), jobFactory(), eventService());
        }

        @Bean
        public IJobFactory jobFactory() {
            return mock(IJobFactory.class);
        }

    }
}