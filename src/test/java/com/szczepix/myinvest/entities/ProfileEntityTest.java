package com.szczepix.myinvest.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProfileEntityTest {

    private ProfileEntity profileEntity;

    @Before
    public void onInit() {
        profileEntity = new ProfileEntity();
        profileEntity.setId(1);
        profileEntity.setMobile("mobile");
        profileEntity.setFirstName("firstName");
        profileEntity.setLastName("lastName");
        profileEntity.setPassword("password");
        profileEntity.setUserName("userName");
    }

    @Test
    public void getId() {
        assertThat(profileEntity.getId()).isEqualTo(1);
    }

    @Test
    public void getFirstName() {
        assertThat(profileEntity.getFirstName()).isEqualTo("firstName");
    }

    @Test
    public void getLastName() {
        assertThat(profileEntity.getLastName()).isEqualTo("lastName");
    }

    @Test
    public void getUserName() {
        assertThat(profileEntity.getUserName()).isEqualTo("userName");
    }

    @Test
    public void getPassword() {
        assertThat(profileEntity.getPassword()).isEqualTo("password");
    }

    @Test
    public void getEmail() {
        profileEntity.setEmail("szczepix");
        assertThat(profileEntity.getEmail()).isEqualTo("szczepix");
    }

    @Test
    public void getMobile() {
        assertThat(profileEntity.getMobile()).isEqualTo("mobile");
    }

    @Test
    public void checkToString() {
        assertThat(profileEntity.toString()).isEqualTo("[ProfileEntity]:[1][firstName][lastName]");
    }

    @Test
    public void checkIsEqual() {
        ProfileEntity anotherEntity = new ProfileEntity();
        anotherEntity.setId(1);
        anotherEntity.setFirstName("firstName");
        anotherEntity.setPassword("password");
        assertThat(anotherEntity.equals(profileEntity)).isTrue();
    }

    @Test
    public void checkIsNotEqual() {
        ProfileEntity anotherEntity = new ProfileEntity();
        anotherEntity.setId(1);
        anotherEntity.setFirstName("firstName");
        anotherEntity.setPassword("password1");
        assertThat(anotherEntity.equals(profileEntity)).isFalse();
    }
}