package controller.commands;

import module.CiklumManager;
import module.Tool;
import view.View;

public class Insert implements Command {
    private final CiklumManager dbManager;
    private final View view;
    private final Tool tool;

    private static final String[] samples =  {"add product: id, name, price", "place product: id, status"};

    public Insert(View view, CiklumManager dbManager, Tool tool) {
        this.dbManager = dbManager;
        this.view = view;
        this.tool = tool;
    }

    @Override
    public boolean processAble(String command) {
        return command.startsWith("add product:") || command.startsWith("place order:");
    }

    @Override
    public void execute(String command) {
        String[] input = tool.refactorCommand(command);
        tool.validateCommand(input, samples);
        dbManager.insertData(input);
        view.type("Record was successfully added to the table.");
    }
}
