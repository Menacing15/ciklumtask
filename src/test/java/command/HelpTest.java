package command;

import controller.commands.Command;
import controller.commands.Help;
import org.junit.Test;
import org.mockito.Mockito;
import view.View;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class HelpTest {
    private View view;
    private Command command;

    public HelpTest(){
        view = mock(View.class);
        command = new Help(view);
    }

    @Test
    public void testProcessAbleHelp() {
        boolean processAble = command.processAble("help");
        assertTrue(processAble);
    }

    @Test
    public void testHelp(){
        command.execute("help");
        Mockito.verify(view).type("Here is what you can do:");
        Mockito.verify(view).type("\thelp - to see all commands available.");
        Mockito.verify(view).type("\tadd product: product id, name, price");
        Mockito.verify(view).type("\tplace order: order id, product id, quantity");
        Mockito.verify(view).type("\tshow: products/orders - to show info for products/orders");
        Mockito.verify(view).type("\tupdate quantity: order id, new quantity - to update quantity of ordered product");
        Mockito.verify(view).type("\tlist - to show list of product that have been ordered");
        Mockito.verify(view).type("\tfind order: id - to find product by id");
        Mockito.verify(view).type("\tdelete product: id/* - to delete product by id/to delete all products");
        Mockito.verify(view).type("\texit - to shut down the program.");
    }
}