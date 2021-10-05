package command;

import controller.commands.Command;
import controller.commands.Insert;
import module.CiklumManager;
import module.Data;
import module.DataImpl;
import module.Tool;
import org.junit.Before;
import org.junit.Test;
import view.View;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class InsertTest {
    private CiklumManager dbManager;
    private View view;
    private Command command;
    private Tool tool;

    @Before
    public void setup() {
        dbManager = mock(CiklumManager.class);
        view = mock(View.class);
        tool = mock(Tool.class);
        command = new Insert(view, dbManager, tool);
    }

    @Test
    public void testProcessAbleProduct() {
        boolean processAble = command.processAble("add product:");
        assertTrue(processAble);
    }
    @Test
    public void testProcessAbleOrder() {
        boolean processAble = command.processAble("place order:");
        assertTrue(processAble);
    }

    @Test
    public void testInsert() {
        String input = "add product:1,supermilk,999";
        String[] array = {"addproduct","1","supermilk","999"};
        when(tool.refactorCommand(input)).thenReturn(array);
        command.execute(input);
        verify(dbManager).insertData(array);
        verify(view).type("Record was successfully added to the table.");
    }
}