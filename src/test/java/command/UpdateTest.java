package command;

import controller.commands.Command;
import controller.commands.Insert;
import controller.commands.Update;
import module.CiklumManager;
import module.Tool;
import org.junit.Before;
import org.junit.Test;
import view.View;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class UpdateTest {
    private CiklumManager dbManager;
    private View view;
    private Command command;
    private Tool tool;

    @Before
    public void setup() {
        dbManager = mock(CiklumManager.class);
        view = mock(View.class);
        tool = mock(Tool.class);
        command = new Update(view, dbManager, tool);
    }

    @Test
    public void testProcessAbleUpdate() {
        boolean processAble = command.processAble("update quantity:");
        assertTrue(processAble);
    }

    @Test
    public void testUpdate() {
        String input = "update quantity: 1, 2";
        String[] array = {"updatequantity","1","2"};
        when(tool.refactorCommand(input)).thenReturn(array);
        command.execute(input);
        verify(dbManager).update(array[1],array[2]);
        verify(view).type("Record was successfully updated.");
    }
}
