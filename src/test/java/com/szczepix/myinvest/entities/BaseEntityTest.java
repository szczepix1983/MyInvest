package com.szczepix.myinvest.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BaseEntityTest {

    @Test
    public void equalsIstrue() {
        BaseEntity entity = new BaseEntityMock();
        assertThat(entity.equals(entity)).isTrue();
    }

    @Test
    public void equalsIsFalse() {
        BaseEntity oldEntity = new BaseEntityMock();
        BaseEntity newEntity = new BaseEntityMock();
        assertThat(oldEntity.equals(newEntity)).isFalse();
    }

    class BaseEntityMock extends BaseEntity {

    }
}