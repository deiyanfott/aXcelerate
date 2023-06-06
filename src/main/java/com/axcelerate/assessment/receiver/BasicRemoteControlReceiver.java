package com.axcelerate.assessment.receiver;

import com.axcelerate.assessment.bean.HomeHubBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BasicRemoteControlReceiver implements RemoteControlReceiver {
    private static final Logger LOG = LoggerFactory.getLogger(BasicRemoteControlReceiver.class);
    private final HomeHubBean homeHubBean;
    private final Integer productId;
    private final String action;

    public BasicRemoteControlReceiver(BasicRemoteControlReceiverBuilder builder) {
        this.homeHubBean = builder.homeHubBean;
        this.productId = builder.productId;
        this.action = builder.action;
    }
    @Override
    public void validateAndTogglePower() {
        String productName = homeHubBean.getProductMap().get(productId);
        checkIfProductIsSupported(productName);
    }

    @Override
    public void undoLastAction() {
        if (!homeHubBean.getUndoList().isEmpty()) {
            Map<String,String> removedAction = homeHubBean.removeFromUndoList();
            removedAction.forEach((productName, state) -> {
                homeHubBean.getStateMap().replace(productName, state);
                LOG.info("{} is now turned {}.", productName, state);
            });
        } else {
            LOG.info("Nothing to undo.");
        }
    }

    private void checkIfProductIsSupported(String productName) {
        if (productName == null) {
            throw new UnsupportedOperationException("This product is not yet supported.");
        } else {
            checkIfStateHasChanged(productName);
        }
    }

    private void checkIfStateHasChanged(String productName) {
        String state = homeHubBean.getStateMap().get(productName);

        if (state.equalsIgnoreCase(action)) {
            LOG.info("{} is already turned {}.", productName, action);
        } else {
            togglePower(productName, state);
        }
    }

    private void togglePower(String productName, String state) {
        homeHubBean.addToUndoList(new HashMap<>(Map.of(productName, state)));
        homeHubBean.getStateMap().replace(productName, action);
        LOG.info("{} is now turned {}.", productName, action);
    }

    public static class BasicRemoteControlReceiverBuilder {
        private final HomeHubBean homeHubBean;
        private Integer productId;
        private String action;

        public BasicRemoteControlReceiverBuilder(HomeHubBean homeHubBean) {
            this.homeHubBean = homeHubBean;
        }

        public BasicRemoteControlReceiverBuilder withProductId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public BasicRemoteControlReceiverBuilder withAction(String action) {
            this.action = action;
            return this;
        }

        public BasicRemoteControlReceiver build() {
            return new BasicRemoteControlReceiver(this);
        }
    }
}
