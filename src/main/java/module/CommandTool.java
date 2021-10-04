package module;

import exception.ExitException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CommandTool implements Tool {
    public boolean verification() {
        Properties properties = new Properties();
        String password;

        try (FileReader fileReader = new FileReader("src\\main\\resources\\local.properties")) {
            properties.load(fileReader);
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Can't get property!", e);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the password: ");
        System.out.println("Type 'back'to cancel operation");
        while (true) {
            String command = scanner.nextLine();
            if (command.equals(password)) {
                return true;
            }
            else if (command.equals("back")) {
                return false;
            }
            else if (command.equals("exit")) {
                throw new ExitException();
            }
        }
    }

    public String[] refactorCommand(String command) {
        command = command.replaceAll("\\s+", "");
        String[] splitByComma = command.split("[,]");
        if (splitByComma.length != 1) {
            String[] splitByColon = splitByComma[0].split("[:]");
            List<String> list = new ArrayList<>(Arrays.asList(splitByComma));
            list.set(0, splitByColon[0]);
            list.add(1, splitByColon[1]);
            return list.toArray(new String[0]);
        } else {
            return splitByComma[0].split("[:]");
        }
    }

    public void validateCommand(String[] data, String[] samples) throws IllegalArgumentException {
        boolean valid = false;
        for (String s : samples) {
            if (data.length == getParameterLength(s)) {
                valid = true;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Something is missing... Quantity of parameters is " + (data.length - 1));
        }
    }

    public int getParameterLength(String command) {
        return command.split("[:]").length + command.split("[,]").length - 1;
    }
}