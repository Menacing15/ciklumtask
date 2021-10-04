package controller.commands;

import module.CiklumManager;
import module.Tool;
import view.View;

public class Find implements Command {

    private static final String COMMAND_SAMPLE = "find order: 1";
    private View view;
    private CiklumManager dbManager;
    private Tool tool;

    public Find(View view, CiklumManager dbManager, Tool tool) {
        this.view = view;
        this.dbManager = dbManager;
        this.tool = tool;
    }

    @Override
    public boolean processAble(String command) {
        return command.startsWith("find order:");
    }

    @Override
    public void execute(String command) {
        command = command.replaceAll("\\s+", "");
        String[] data = command.split("[:]");
        tool.validateCommand(data, COMMAND_SAMPLE);
        String tableName = data[1];
        view.drawTable(dbManager.find(tableName));
    }


}
