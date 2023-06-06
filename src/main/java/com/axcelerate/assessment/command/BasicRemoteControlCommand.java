package com.axcelerate.assessment.command;

import com.axcelerate.assessment.receiver.RemoteControlReceiver;

public class BasicRemoteControlCommand implements Command {
    private final RemoteControlReceiver receiver;

    public BasicRemoteControlCommand(RemoteControlReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.validateAndTogglePower();
    }

    @Override
    public void undo() {
        receiver.undoLastAction();
    }
}
