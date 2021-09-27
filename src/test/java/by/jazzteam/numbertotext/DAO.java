package by.jazzteam.numbertotext;

import by.jazzteam.numbertotext.bean.TestPair;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static final Logger logger = LoggerFactory.getLogger(DAO.class);

    public List<TestPair> getTestPairs(String fileName) {
        List<TestPair> testPairs = new ArrayList<>();

        FileReader fileReader = null;
        try {
            File file = new File(ClassLoader.getSystemClassLoader().getResource(fileName).getFile());
            fileReader = new FileReader(file, StandardCharsets.UTF_8);
            testPairs = new CsvToBeanBuilder(fileReader)
                    .withType(TestPair.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            logger.error(e.toString());
        } finally {
            try {
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }

        testPairs.remove(0);
        return testPairs;
    }
}
