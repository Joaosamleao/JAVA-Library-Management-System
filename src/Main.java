import UI.UI;

import Handlers.Level1Support;
import Handlers.Level2Support;
import Handlers.Level3Support;

public class Main {
    public static void main(String[] args) {
        UI UI = new UI();

        Level1Support Level1 = new Level1Support();
        Level2Support Level2 = new Level2Support();
        Level3Support Level3 = new Level3Support();

        Level1.setNextHandler(Level2);
        Level2.setNextHandler(Level3);

    } 
}