package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
public class WalletCacheTest {

    private WalletCache cache;

    @Before
    public void setUp() {
        cache = new WalletCache();
    }

    @Test
    public void update() {
        List<WalletModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getCache()).isEqualTo(newList);
    }

    @Test
    public void getById() {
        assertThat(cache.getById(1)).isNull();
        List<WalletModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getById(1)).isNotNull();
    }

    @Test
    public void getCache() {
        assertThat(cache.getCache()).isNotNull();
        assertThat(cache.getCache().size()).isEqualTo(0);
        List<WalletModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getCache()).isNotNull();
        assertThat(cache.getCache().size()).isEqualTo(3);
    }

    private List<WalletModel> createList(int i) {
        List<WalletModel> list = new ArrayList<>();
        while (i > 0) {
            WalletEntity entity = new WalletEntity();
            entity.setId(list.size());
            list.add(list.size(), new WalletModel(entity, mock(EventService.class)));
            i--;
        }
        return list;
    }
}