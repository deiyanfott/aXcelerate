package com.axcelerate.assessment.invoker;

import com.axcelerate.assessment.command.Command;

public class RemoteControlInvoker {
    private final Command command;

    public RemoteControlInvoker(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }

    public void undo() {
        command.undo();
    }
}
