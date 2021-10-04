package view;

import module.Data;

import java.util.List;

public interface View {
    String read();

    void type(String message);

    void drawTable(List<Data> tableData);
}