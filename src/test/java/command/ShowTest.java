package command;

import controller.commands.Command;
import controller.commands.Show;
import module.CiklumManager;
import module.Data;
import module.DataImpl;
import module.Tool;
import org.junit.Before;
import org.junit.Test;
import view.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class ShowTest {

    private CiklumManager dbManager;
    private View view;
    private Command command;
    private Tool tool;

    @Before
    public void setup() {
        dbManager = mock(CiklumManager.class);
        view = mock(View.class);
        tool = mock(Tool.class);
        command = new Show(view, dbManager, tool);
    }

    @Test
    public void testProcessAbleFindWithParameters() {
        boolean processAble = command.processAble("show:products");
        assertTrue(processAble);
    }

    @Test
    public void testProcessAbleFindWithoutParameters() {
        boolean processAble = command.processAble("show");
        assertFalse(processAble);
    }

    @Test
    public void testPrintTable() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd");
        String date = LocalDate.now().format(formatter);

        Data data = new DataImpl();
        data.put("id", 8);
        data.put("user_id", 1334);
        data.put("status", "in_progress");
        data.put("created_at", date);


        List<Data> dataList = new LinkedList<>(Arrays.asList(data));
        when(dbManager.getTableData("products")).thenReturn(dataList);

        command.execute("find:products");
        verify(view, atLeastOnce()).
                drawTable(dbManager.getTableData("products"));
    }

    @Test
    public void testPrintEmptyTableData() {
        when(dbManager.getTableData("user")).
                thenReturn(new ArrayList<>(0));
        command.execute("show:products");
        verify(view, atLeastOnce()).drawTable(dbManager.getTableData("user"));
    }
}