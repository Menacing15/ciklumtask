package controller;

import controller.commands.*;
import exception.ExitException;
import module.CiklumManager;
import module.Tool;
import view.View;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainController {

    private CiklumManager dbManager;
    private View view;
    private Command[] commands;

    public MainController(View view, CiklumManager ciklumManager, Tool tool) {
        this.view = view;
        this.dbManager = ciklumManager;
        this.commands = new Command[]{
                new Exit(),
                new Help(view),
                new Insert(view, ciklumManager, tool),
                new Show(view, ciklumManager, tool),
                new Find(view,ciklumManager,tool),
                new List(view, ciklumManager),
                new Update(view, ciklumManager, tool),
                new Delete(view,ciklumManager,tool),
                new Unsupported(view)
        };
    }

    public void start() {
        try {
            connectToDB();
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        if (dbManager.isConnected()) {
            try {
                run();
            } catch (ExitException ex) {
                view.type("See ya!");
            }
        }
    }

    private void connectToDB() {
        Properties properties = new Properties();
        String database;
        String user;
        String password;

        try (FileReader fileReader = new FileReader("src\\main\\resources\\local.properties")) {
            properties.load(fileReader);
            database = properties.getProperty("database");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Can't get property!", e);
        }

        if (dbManager.connect(database, user, password)) {
            view.type("Connection successful");
        } else {
            view.type("Can't connect to your database");
        }
    }

    private void run() throws ExitException {
        view.type("Hello! If you are new here type 'help'");
        view.type("Type 'exit' to shut down the program");
        while (true) {
            String input = view.read();
            for (Command command : commands) {
                try {
                    if (command.processAble(input)) {
                        command.execute(input);
                        break;
                    }
                } catch (Exception ex) {
                    if (ex instanceof ExitException) {
                        throw ex;
                    }
                    printError(ex);
                    break;
                }
            }
        }
    }

    private void printError(Exception e) {
        String message = e.getMessage();
        System.out.println("Failed, the reason is: " + message + "\nTry again!");
    }
}
