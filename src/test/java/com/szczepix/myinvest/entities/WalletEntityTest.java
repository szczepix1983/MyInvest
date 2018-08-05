package com.szczepix.myinvest.entities;

import com.szczepix.myinvest.enums.WalletType;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class WalletEntityTest {

    private WalletEntity walletEntity;

    @Before
    public void setUp() {
        walletEntity = new WalletEntity();
        walletEntity.setId(1);
        walletEntity.setCreatedAt(100L);
        walletEntity.setName("name");
        walletEntity.setWalletType(WalletType.INVESTMENT);
    }

    @Test
    public void checkToString() {
        assertThat(walletEntity.toString()).isEqualTo("[WalletEntity]:[1][name]");
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
        assertThat(walletEntity.getWalletType()).isEqualTo(WalletType.INVESTMENT);
    }

    @Test
    public void getCreatedAt() {
        assertThat(walletEntity.getCreatedAt()).isEqualTo(100L);
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
}