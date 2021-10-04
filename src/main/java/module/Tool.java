package module;

public interface Tool {
     boolean verification();

     String[] refactorCommand(String command);

     void validateCommand(String[] data, String... samples);
}
