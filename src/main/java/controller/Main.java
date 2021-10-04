package controller;

import module.CommandTool;
import module.CiklumManager;
import module.CiklumManagerImpl;
import module.Tool;
import view.Console;
import view.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        CiklumManager ciklumManager = new CiklumManagerImpl();
        Tool tool = new CommandTool();
        MainController mc = new MainController(view, ciklumManager, tool);
        mc.start();
    }
}
