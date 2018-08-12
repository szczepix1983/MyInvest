package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.ProfileEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProfileModelTest {

    private ProfileModel profileModel;

    @Before
    public void setUp() {
        ProfileEntity entity = new ProfileEntity();
        entity.setId(1);
        entity.setUserName("");
        profileModel = new ProfileModel(entity);
    }

    @Test
    public void creation() {
        assertThat(profileModel).isNotNull();
    }

    @Test
    public void getEntity() {
        assertThat(profileModel.getEntity()).isNotNull();
    }
}