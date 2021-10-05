package command;

import controller.commands.Command;
import controller.commands.Delete;
import controller.commands.Find;
import module.CiklumManager;
import module.Data;
import module.Tool;
import org.junit.Before;
import org.junit.Test;
import view.View;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class DeleteTest {

    private CiklumManager dbManager;
    private View view;
    private Command command;
    private Tool tool;

    @Before
    public void setup() {
        dbManager = mock(CiklumManager.class);
        view = mock(View.class);
        tool = mock(Tool.class);
        command = new Delete(view, dbManager, tool);
    }

    @Test
    public void testProcessAbleDelete() {
        boolean processAble = command.processAble("delete product:");
        assertTrue(processAble);
    }

    @Test
    public void testDelete() {
        command.execute("delete product: 1");
        verify(view).type("Deleted from table");
    }

}
