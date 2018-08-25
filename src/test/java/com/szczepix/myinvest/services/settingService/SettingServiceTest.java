package com.szczepix.myinvest.services.settingService;

import com.szczepix.myinvest.config.IInternalConfig;
import com.szczepix.myinvest.dao.ISettingRepository;
import com.szczepix.myinvest.entities.SettingEntity;
import com.szczepix.myinvest.entities.SettingEntityTest;
import com.szczepix.myinvest.jobs.IJobFactory;
import com.szczepix.myinvest.models.SettingModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = SettingServiceTest.SettingServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class SettingServiceTest {

    @Autowired
    private ISettingService settingService;

    @Autowired
    private ISettingRepository repository;

    @Autowired
    private SettingCache cache;

    @Before
    public void setUp() {
        when(cache.getById(1)).thenReturn(createSettingsMock());
    }

    @After
    public void tearDown() {
        reset(repository);
    }

    @Test
    public void init() {
        settingService.init();
        verify(repository, atLeast(1)).save(any(SettingEntity.class));
    }

    @Test
    public void getSettings() {
        assertThat(settingService.getSettings()).isNotNull();
    }

    @Test
    public void save() {
        settingService.save();
        verify(repository, atLeast(1)).save(any(SettingEntity.class));
    }

    private SettingModel createSettingsMock() {
        return new SettingModel(new SettingEntityTest.SettingEntityMock());
    }

    @Configuration
    static class SettingServiceTestConfiguration {

        @Bean
        public ISettingRepository repository() {
            ISettingRepository repository = mock(ISettingRepository.class);
            when(repository.findAll()).thenReturn(new ArrayList<>());
            return repository;
        }

        @Bean
        public SettingCache cache() {
            return mock(SettingCache.class);
        }

        @Bean
        public ISettingService settingService() {
            return new SettingService(repository(), cache(), jobFactory(), config());
        }

        @Bean
        public IInternalConfig config() {
            IInternalConfig config = mock(IInternalConfig.class);
            when(config.getDefaultResourceInterval()).thenReturn(10);
            return config;
        }

        @Bean
        public IJobFactory jobFactory() {
            return mock(IJobFactory.class);
        }
    }
}