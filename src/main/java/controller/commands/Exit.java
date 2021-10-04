package controller.commands;

import exception.ExitException;

public class Exit implements Command {
    @Override
    public boolean processAble(String command) {
        return command.equals("exit");
    }

    @Override
    public void execute(String command) {
        throw new ExitException();
    }
}