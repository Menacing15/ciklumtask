package command;

import controller.commands.Command;

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
import static org.mockito.Mockito.atLeastOnce;

public class ListTest {
    private CiklumManager dbManager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        dbManager = mock(CiklumManager.class);
        view = mock(View.class);
        command = new controller.commands.List(view, dbManager);
    }

    @Test
    public void testProcessAble() {
        boolean processAble = command.processAble("list");
        assertTrue(processAble);
    }

    @Test
    public void testList() {
        List<Data> data = new ArrayList<>();
        when(dbManager.list()).thenReturn(data);
        command.execute("list");
        verify(view, atLeastOnce()).
                drawTable(dbManager.list());
    }
}
