package command;

import controller.commands.Command;
import controller.commands.Unsupported;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import view.View;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class UnsupportedTest {
    private View view;
    private Command command;

    @Before
    public void setup() {
        view = mock(View.class);
        command = new Unsupported(view);
    }

    @Test
    public void testProcessAbleUnsupported() {
        boolean processAble = command.processAble("unsupported");
        assertTrue(processAble);
    }

    @Test
    public void testUnsupported() {
        command.execute("unsupported");
        Mockito.verify(view).type("Sorry, such command doesn't exist! Try again!");
    }
}