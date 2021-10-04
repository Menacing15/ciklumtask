package controller.commands;

import view.View;

public class Help implements Command {

    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean processAble(String command) {
        return command.equals("help");
    }

    @Override
    public void execute(String command) {
        view.type("Here is what you can do:");
        view.type("\thelp - to see all commands available.");
        view.type("\tadd product: product id, name, price");
        view.type("\tplace order: order id, product id, quantity");
        view.type("\tshow: products/orders - to show info for products/orders");
        view.type("\tupdate quantity: order id, new quantity - to update quantity of ordered product");
        view.type("\tlist - to show list of product that have been ordered");
        view.type("\tfind order: id - to find product by id");
        view.type("\tdelete product: id/* - to delete product by id/to delete all products");
        view.type("\texit - to shut down the program.");
    }
}