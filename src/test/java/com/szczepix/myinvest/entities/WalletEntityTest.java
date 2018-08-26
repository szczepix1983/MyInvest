package com.szczepix.myinvest.entities;

import com.szczepix.myinvest.enums.PeriodType;
import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.enums.WalletType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class WalletEntityTest {

    private WalletEntityMock walletEntity;

    @Before
    public void setUp() {
        walletEntity = new WalletEntityMock();
    }

    @Test
    public void checkToString() {
        assertThat(walletEntity.toString()).isEqualTo("[WalletEntityMock]:[1][name]");
    }

    @Test
    public void getId() {
        assertThat(walletEntity.getId()).isEqualTo(1);
    }

    @Test
    public void getName() {
        assertThat(walletEntity.getName()).isEqualTo("name");
    }

    @Test
    public void getWalletType() {
        assertThat(walletEntity.getWalletType()).isEqualTo(WalletType.INVESTMENT.getType());
    }

    @Test
    public void getCreatedAt() {
        assertThat(walletEntity.getCreatedAt()).isEqualTo(100L);
    }

    @Test
    public void getValue() {
        assertThat(walletEntity.getValue()).isEqualTo(1.10);
    }

    @Test
    public void getTargetType() {
        assertThat(walletEntity.getTargetType()).isEqualTo(TargetType.MONEY.getName());
    }

    @Test
    public void getPeriodType() {
        assertThat(walletEntity.getPeriodType()).isEqualTo(PeriodType.DAILY.getName());
    }

    @Test
    public void checkIsEqual() {
        WalletEntity anotherEntity = new WalletEntity();
        anotherEntity.setId(1);
        anotherEntity.setName("name");
        assertThat(anotherEntity.equals(walletEntity)).isTrue();
    }

    @Test
    public void checkIsNotEqual() {
        WalletEntity anotherEntity = new WalletEntity();
        anotherEntity.setId(1);
        anotherEntity.setName("name1");
        assertThat(anotherEntity.equals(walletEntity)).isFalse();
    }

    public static class WalletEntityMock extends WalletEntity {

        public WalletEntityMock() {
            setId(1);
            setCreatedAt(100L);
            setName("name");
            setValue(1.10);
            setWalletType(WalletType.INVESTMENT.getType());
            setPeriodType(PeriodType.DAILY.getName());
            setTargetType(TargetType.MONEY.getName());
        }
    }
}