package module;

import java.util.List;

public interface CiklumManager {

    boolean connect(String database, String user, String password);

    boolean isConnected();

    void insertData(String[] input);

    void update(String id, String newValue);

    void delete(String input);

    List<Data> getTableData(String tableName);

    List<Data> list();

    List<Data> find(String input);
}
