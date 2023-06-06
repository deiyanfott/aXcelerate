package com.axcelerate.assessment.controller;

import com.axcelerate.assessment.bean.HomeHubBean;
import com.axcelerate.assessment.command.BasicRemoteControlCommand;
import com.axcelerate.assessment.command.Command;
import com.axcelerate.assessment.invoker.RemoteControlInvoker;
import com.axcelerate.assessment.receiver.BasicRemoteControlReceiver;
import com.axcelerate.assessment.receiver.RemoteControlReceiver;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/remote-control")
class RemoteControlEndpoint {
    private final HomeHubBean homeHubBean;

    public RemoteControlEndpoint(HomeHubBean homeHubBean) {
        this.homeHubBean = homeHubBean;
    }

    @PutMapping("/turn-on/{productId}")
    void turnOn(@PathVariable Integer productId) {
        RemoteControlReceiver receiver = new BasicRemoteControlReceiver.BasicRemoteControlReceiverBuilder(homeHubBean)
                .withProductId(productId)
                .withAction("ON")
                .build();
        Command command = new BasicRemoteControlCommand(receiver);
        RemoteControlInvoker invoker = new RemoteControlInvoker(command);
        invoker.execute();
    }

    @PutMapping("/turn-off/{productId}")
    void turnOff(@PathVariable Integer productId) {
        RemoteControlReceiver receiver = new BasicRemoteControlReceiver.BasicRemoteControlReceiverBuilder(homeHubBean)
                .withProductId(productId)
                .withAction("OFF")
                .build();
        Command command = new BasicRemoteControlCommand(receiver);
        RemoteControlInvoker invoker = new RemoteControlInvoker(command);
        invoker.execute();
    }

    @DeleteMapping("/undo")
    void undo() {
        RemoteControlReceiver receiver = new BasicRemoteControlReceiver.BasicRemoteControlReceiverBuilder(homeHubBean)
                .build();
        Command command = new BasicRemoteControlCommand(receiver);
        RemoteControlInvoker invoker = new RemoteControlInvoker(command);
        invoker.undo();
    }
}
