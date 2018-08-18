package com.szczepix.myinvest.services.profileService;

import com.szczepix.myinvest.dao.IProfileRepository;
import com.szczepix.myinvest.entities.ProfileEntity;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = ProfileServiceTest.ProfileServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private IProfileRepository repository;

    @Autowired
    private ProfilesCache cache;

    @After
    public void tearDown() {
        reset(repository);
    }

    @Test
    public void init() {
        profileService.init();
        verify(cache, atLeast(1)).update(any());
    }

    @Test
    public void getProfiles() {
        assertThat(profileService.getProfiles()).isNotNull();
        assertThat(profileService.getProfiles().size()).isEqualTo(0);
    }

    @Test
    public void getFindProfileByUserNameAndPasswordWhenWrong() {
        assertThat(profileService.findProfileByUserNameAndPassword("szczepix", "password")).isNotNull();
        assertThat(profileService.findProfileByUserNameAndPassword("szczepix", "password").getEntity()).isNull();
    }

    @Test
    public void getFindProfileByUserNameAndPasswordWhenCorrect() {
        ProfileEntity entity = new ProfileEntity();
        entity.setPassword("password");
        entity.setUserName("szczepix");
        when(repository.findProfileByUserNameAndPassword(eq("szczepix"), eq("password"))).thenReturn(entity);
        assertThat(profileService.findProfileByUserNameAndPassword("szczepix", "password")).isNotNull();
        assertThat(profileService.findProfileByUserNameAndPassword("szczepix", "password").getEntity()).isNotNull();
        assertThat(profileService.findProfileByUserNameAndPassword("szczepix", "password").getEntity()).isEqualTo(entity);
    }

    @Configuration
    static class ProfileServiceTestConfiguration {

        @Bean
        public IProfileRepository repository() {
            IProfileRepository repository = mock(IProfileRepository.class);
            Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
            return repository;
        }

        @Bean
        public ProfilesCache cache() {
            return mock(ProfilesCache.class);
        }

        @Bean
        public ProfileService getProfileService() {
            return new ProfileService(repository(), cache());
        }
    }
}