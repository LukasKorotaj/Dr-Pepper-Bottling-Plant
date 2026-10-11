import Logic.Logic;
import Logic.CLI.CLI;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Logic logic = new Logic();
        CLI cli = new CLI(logic);

        cli.init();
    }
}
