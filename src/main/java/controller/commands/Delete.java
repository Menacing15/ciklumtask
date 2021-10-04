package controller.commands;

import module.CiklumManager;
import module.Tool;
import view.View;

public class Delete implements Command {

    private static final String COMMAND_SAMPLE = "delete product: *";
    private CiklumManager dbManager;
    private View view;
    private Tool tool;

    public Delete(View view, CiklumManager dbManager, Tool tool) {
        this.tool = tool;
        this.view = view;
        this.dbManager = dbManager;
    }

    @Override
    public boolean processAble(String command) {
        return command.startsWith("delete product:");
    }

    @Override
    public void execute(String command) {
        command = command.replaceAll("\\s+", "");
        String[] data = command.split("[:]");
        tool.validateCommand(data, COMMAND_SAMPLE);
        dbManager.delete(data[1]);
        view.type("Deleted from table");
    }
}
