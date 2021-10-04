package controller.commands;

import module.CiklumManager;
import view.View;

import java.util.LinkedHashSet;
import java.util.Set;

public class List implements Command {

    private View view;
    private CiklumManager dbManager;

    @Override
    public boolean processAble(String command) {
        return command.equals("list");
    }

    public List(View view, CiklumManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    @Override
    public void execute(String command) {
        Set<String> set = new LinkedHashSet<>();
        set.add("product name");
        set.add("times ordered");
        view.drawTable(dbManager.list());
    }
}
