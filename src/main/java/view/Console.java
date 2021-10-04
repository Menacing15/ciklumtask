package view;

import module.Data;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console implements View {

    @Override
    public void type(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void drawTable(List<Data> tableData) {
        drawHeader(tableData);
        drawInnerData(tableData);
    }


    private void drawHeader(List<Data> tableData) {
        String result = "|";
        if(tableData.size() != 0) {
            Data data = tableData.get(0);
            for (String name : data.getNames()) {
                result += name + "|";
            }
            System.out.println(result);
        } else {
            System.out.println("Table is empty");
        }
    }

    private void drawInnerData(List<Data> tableData) {
        for (Data row : tableData) {
            printRow(row);
        }
    }

    private void printRow(Data row) {
        String result = "|";
        for (Object value : row.getValues()) {
            result += value + "|";
        }
        System.out.println(result);
    }
}
