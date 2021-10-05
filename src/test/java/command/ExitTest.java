package command;

import controller.commands.Command;
import controller.commands.Exit;
import exception.ExitException;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class ExitTest {

    @Test
    public void testProcessAbleExit() {
        Command command = new Exit();
        boolean processAble = command.processAble("exit");
        assertTrue(processAble);
    }

    @Test
    public void testExecuteExitCommandThrowsExitException() {
        Command command = new Exit();
        try {
            command.execute("exit");
            fail("Expected ExitException");
        } catch (ExitException e) {

        }
    }
}