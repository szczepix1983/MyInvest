package com.szczepix.myinvest.services.profileService;

import com.szczepix.myinvest.entities.ProfileEntity;
import com.szczepix.myinvest.models.ProfileModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProfilesCacheTest {

    private ProfilesCache cache;

    @Before
    public void setUp() {
        cache = new ProfilesCache();
    }

    @Test
    public void update() {
        List<ProfileModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getCache()).isEqualTo(newList);
    }


    @Test
    public void getById() {
        assertThat(cache.getById(1)).isNull();
        List<ProfileModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getById(1)).isNotNull();
    }

    @Test
    public void getCache() {
        assertThat(cache.getCache()).isNotNull();
        assertThat(cache.getCache().size()).isEqualTo(0);
        List<ProfileModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getCache()).isNotNull();
        assertThat(cache.getCache().size()).isEqualTo(3);
    }

    private List<ProfileModel> createList(int i) {
        List<ProfileModel> list = new ArrayList<>();
        while (i > 0) {
            ProfileEntity entity = new ProfileEntity();
            entity.setId(list.size());
            list.add(list.size(), new ProfileModel(entity));
            i--;
        }
        return list;
    }
}