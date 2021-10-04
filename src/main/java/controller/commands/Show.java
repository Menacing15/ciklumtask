package controller.commands;

import module.CiklumManager;
import module.Tool;
import view.View;

public class Show implements Command {
    private static final String COMMAND_SAMPLE = "show: products";
    private CiklumManager dbManager;
    private View view;
    private Tool tool;

    public Show(View view, CiklumManager dbManager, Tool tool) {
        this.tool = tool;
        this.view = view;
        this.dbManager = dbManager;
    }

    @Override
    public boolean processAble(String command) {
        return command.startsWith("show:");
    }

    @Override
    public void execute(String command) {
        command = command.replaceAll("\\s+", "");
        String[] data = command.split("[:]");
        tool.validateCommand(data, COMMAND_SAMPLE);
        String tableName = data[1];
        view.drawTable(dbManager.getTableData(tableName));
    }
}