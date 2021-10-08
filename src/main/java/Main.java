import model.GameFields;
import model.GetMVP;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ReadCSV readCSV = new ReadCSV();
        GetMVP getMVP = new GetMVP();
        try {
            List<GameFields> gamePlayers = readCSV.getData();
            String gameName = gamePlayers.get(0).getGameName();

            switch (gameName) {
                case "BASKETBALL":
                    System.out.println("MVP is: " + getMVP.basketball(gamePlayers, 3));

                    break;
                case "HANDBALL":
                    System.out.println("MVP is: " + getMVP.handball(gamePlayers, 2));
                    break;
                default:
                    throw new Exception("This file is wrong or unknown game");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
