package com.axcelerate.assessment.receiver;

import com.axcelerate.assessment.bean.HomeHubBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicRemoteControlReceiverTest {
    private final static HomeHubBean HOME_HUB_BEAN = new HomeHubBean();

    private RemoteControlReceiver receiver;

    @Test
    void whenProductIdIsValid_thenTogglePowerIsSuccessful() {
        receiver = new BasicRemoteControlReceiver.BasicRemoteControlReceiverBuilder(HOME_HUB_BEAN)
                .withProductId(1)
                .withAction("ON")
                .build();
        receiver.validateAndTogglePower();
        assertEquals("ON", HOME_HUB_BEAN.getStateMap().get("GARAGE_DOOR"));
    }

    @Test
    void whenUndoListHasItems_thenUndoLastActionIsSuccessful() {
        receiver = new BasicRemoteControlReceiver.BasicRemoteControlReceiverBuilder(HOME_HUB_BEAN)
                .withProductId(1)
                .withAction("ON")
                .build();
        receiver.validateAndTogglePower();
        assertEquals(1, HOME_HUB_BEAN.getUndoList().size());

        receiver.undoLastAction();
        assertEquals(0, HOME_HUB_BEAN.getUndoList().size());
    }

    @Test
    void whenProductIdIsInvalid_thenTogglePowerThrowsException() {
        receiver = new BasicRemoteControlReceiver.BasicRemoteControlReceiverBuilder(HOME_HUB_BEAN)
                .withProductId(4)
                .withAction("ON")
                .build();
        assertThrows(UnsupportedOperationException.class, () -> receiver.validateAndTogglePower());
    }
}
