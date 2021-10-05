package command;

import controller.commands.Command;
import controller.commands.Find;
import controller.commands.Show;
import module.CiklumManager;
import module.Data;
import module.Tool;
import org.junit.Before;
import org.junit.Test;
import view.View;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class FindTest {

    private CiklumManager dbManager;
    private View view;
    private Command command;
    private Tool tool;

    @Before
    public void setup() {
        dbManager = mock(CiklumManager.class);
        view = mock(View.class);
        tool = mock(Tool.class);
        command = new Find(view, dbManager, tool);
    }

    @Test
    public void testProcessAbleFindWithParameters() {
        boolean processAble = command.processAble("find order:");
        assertTrue(processAble);
    }

    @Test
    public void testProcessAbleFindBadParameters() {
        boolean processAble = command.processAble("find:");
        assertFalse(processAble);
    }

    @Test
    public void testFind() {
        List<Data> data = new ArrayList<>();
        when(dbManager.find("1")).thenReturn(data);
        command.execute("find order: 1");
        verify(view, atLeastOnce()).
                drawTable(dbManager.find("1"));
    }
}
