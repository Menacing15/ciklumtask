package controller.commands;

import module.CiklumManager;
import module.Tool;
import view.View;

public class Update implements Command {
    private CiklumManager dbManager;
    private View view;
    private Tool tool;

    public Update(View view, CiklumManager dbManger, Tool tool) {
        this.dbManager = dbManger;
        this.view = view;
        this.tool = tool;
    }

    @Override
    public boolean processAble(String command) {
        return command.startsWith("update quantity:");
    }

    @Override
    public void execute(String command) {
        String[] input = tool.refactorCommand(command);
        tool.validateCommand(input, command);
        String id = input[1];
        String newValue = input[2];
        dbManager.update(id, newValue);
        view.type("Record was successfully updated.");
    }
}