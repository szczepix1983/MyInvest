package com.szczepix.myinvest.views.components;

import com.szczepix.myinvest.entities.WalletEntityTest;
import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.events.WalletChangeEvent;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.EventService;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WalletItemComponentTest {

    private EventService eventService;
    private WalletItemComponentMock view;
    private WalletModel model;
    private JFXPanel fxPanel;

    @Before
    public void setUp() {
        fxPanel = new JFXPanel();
        eventService = mock(EventService.class);
        model = new WalletModel(new WalletEntityTest.WalletEntityMock(), eventService);
        view = new WalletItemComponentMock(model, eventService);
    }

    @Test
    public void initialize() throws Exception {
        view.initialize(null, null);
        Thread.sleep(1000);
        verify(eventService, times(1)).addListener(eq(BaseEventType.WALLET_CHANGE), any());
        assertThat(view.money).isEqualTo(model.getStats().get("money"));
    }

    @Test
    public void path() {
        assertThat(view.path()).isNotNull();
    }

    @Test
    public void onSameWalletChange() {
        model.getStats().put("money", 10.0);
        view.onWalletChange(new WalletChangeEvent(model));
        assertThat(view.money).isEqualTo(model.getStats().get("money"));
    }

    @Test
    public void onDifferentWalletChange() {
        model.getStats().put("money", 10.0);
        view.onWalletChange(new WalletChangeEvent(model));
        WalletModel newModel = new WalletModel(new WalletEntityTest.WalletEntityMock(), eventService);
        view.onWalletChange(new WalletChangeEvent(newModel));
        assertThat(view.money).isEqualTo(model.getStats().get("money"));
    }

    static class WalletItemComponentMock extends WalletItemComponent {

        private double money;

        public WalletItemComponentMock(final WalletModel model, final EventService eventService) {
            super(model, eventService, "PLN");
        }

        @Override
        public void onInitalize() {
            nameText = new TextField();
            valueText = new TextField();
            moneyText = new TextField();
            currencyText = new TextField();
            detailsButton = new Button();
            percentageIndicator = new ProgressIndicator();
            percentageText = new TextField();
            super.onInitalize();
        }

        @Override
        protected void setMoney(final double money) {
            this.money = money;
        }
    }

}