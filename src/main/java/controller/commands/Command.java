package controller.commands;

public interface Command {
    boolean processAble(String command);

    void execute(String command);
}
