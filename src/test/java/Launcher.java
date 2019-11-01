import org.benchmarks.reports.util.CsvLoader;
import org.json.simple.JSONArray;
import org.junit.Test;

import java.io.IOException;

public class Launcher {

    @Test
    public void launcher() throws IOException {
        JSONArray jsonArray = CsvLoader.getDataFromCSV("/home/drosa/Downloads/buildtime.csv");

        System.out.println(jsonArray.toJSONString());
    }

}
