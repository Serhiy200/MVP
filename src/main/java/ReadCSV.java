import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import model.GameFields;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadCSV {

    public static final String PATH = "F:\\Книга1.csv";
    public static final String FILE_IS_NOT_CORRECT = "file is not correct";

    public List<GameFields> getData() {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        List<GameFields> players = new ArrayList<>();
        try( CSVReader reader = new CSVReaderBuilder(
                new FileReader(PATH))
                .withCSVParser(csvParser)   // custom CSV parser
                .build()) {
            String[] firstLine = reader.readNext();
            List<String[]> fields = reader.readAll();
            String gameName = firstLine[0];

            Set<String> niks = new HashSet<>();
            for (int i = 0; i < fields.size(); i++) {
                String goals = "";
                for (int n = 4; n < fields.get(i).length; n++)
                    goals = goals + fields.get(i)[n] + ";";
                players.add(new GameFields(gameName, fields.get(i)[0], fields.get(i)[1], fields.get(i)[2], fields.get(i)[3], goals));
                niks.add(fields.get(i)[1]);
            }
            if(niks.size() < players.size()){
                throw new Exception(FILE_IS_NOT_CORRECT);
            }
            return players;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
