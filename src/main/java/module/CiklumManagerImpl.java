package module;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CiklumManagerImpl implements CiklumManager {

    private Connection connection;

    @Override
    public boolean connect(String database, String user, String password) {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(database, user, password);
        } catch (SQLException e) {
            connection = null;
            throw new RuntimeException("Can't get connection to database '" + database + "', with the user: '" + user + "'", e);
        }

        return connection != null;
    }

    /*this is a bad code, because I hardcode the values,
    but I'm not sure about how can I improve this code,
    I could set column names in the command e.g add product: id, 1, name, 'milk'...
    but there is a problem with date, that should be set automatically etc.
    */

    @Override
    public List<Data> getTableData(String tableName) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            if (tableName.equals("products")) {
                resultSet = statement.executeQuery(String.format("select name, price, status from products"));
                return getDataResultSetData(resultSet);
            } else if (tableName.equals("orders")) {
                resultSet = statement.executeQuery(String.format("select orders.id, order_items.quantity*products.price " +
                        "as total_price, products.name, order_items.quantity, orders.created_at \n" +
                        "from orders \n" +
                        "join order_items on orders.id=order_items.order_id\n" +
                        "join products on order_items.product_id=products.id\n" +
                        "order by orders.id asc;"));
                return getDataResultSetData(resultSet);
            } else {
                throw new SQLException("Table doesn't exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertData(String[] input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd");
        try (Statement statement = connection.createStatement()) {
            if (input[0].equals("addproduct")) {
                statement.executeUpdate(String.format("INSERT INTO products (id, name, price, status, created_at) " +
                        "VALUES (%s, '%s', '%s', 'in_stock', '%s')", input[1], input[2], input[3], LocalDate.now().format(formatter)));
            } else {
                ResultSet rs = statement.executeQuery(String.format("SELECT * FROM products WHERE id = %s", input[2]));
                if (rs.next()) {
                    statement.executeUpdate(String.format("INSERT INTO orders(id, user_id, status, created_at) " +
                                    "VALUES (%s, %s, 'in progress', '%s')", input[1], Math.random() * ((9999 - 1000) + 1000),
                            LocalDate.now().format(formatter)));
                    statement.executeUpdate(String.format("INSERT INTO order_items(order_id, product_id, quantity) " +
                            "VALUES (%s, %s, %s)", input[1], input[2], input[3]));
                } else {
                    throw new RuntimeException("Such product doesn't exists");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't insert in table", e);
        }
    }

    @Override
    public void update(String id, String newValue) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format("UPDATE order_items SET quantity = %s WHERE order_id = %s", newValue, id));
        } catch (SQLException e) {
            throw new RuntimeException("Can't update the table", e);
        }
    }

    @Override
    public void delete(String input) {
        try (Statement statement = connection.createStatement()) {
            if(input.equals("*")) {
                statement.executeUpdate(String.format("TRUNCATE TABLE products"));
            } else {
                statement.executeUpdate(String.format("DELETE FROM products WHERE id = %s",input));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete from table", e);
        }
    }

    public List<Data> list() {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery
                (String.format("SELECT products.name, SUM(order_items.quantity) " +
                        "FROM order_items JOIN products ON order_items.product_id=products.id GROUP BY product_id;"))) {
            return getDataResultSetData(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Data> find(String input) {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery
                (String.format("select orders.id, order_items.quantity*products.price as total_price, products.name, order_items.quantity, orders.created_at \n" +
                        "from orders \n" +
                        "join order_items on orders.id=order_items.order_id\n" +
                        "join products on order_items.product_id=products.id\n" +
                        "where orders.id=%s\n" +
                        "order by orders.id asc;", input))) {
            return getDataResultSetData(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    private List<Data> getDataResultSetData(ResultSet resultSet) throws SQLException {
        List<Data> output = new ArrayList<>();
        ResultSetMetaData resultSetMD = resultSet.getMetaData();
        while (resultSet.next()) {
            Data data = new DataImpl();
            output.add(data);
            for (int i = 1; i <= resultSetMD.getColumnCount(); i++) {
                data.put(resultSetMD.getColumnName(i), resultSet.getObject(i));
            }
        }
        return output;
    }
}


