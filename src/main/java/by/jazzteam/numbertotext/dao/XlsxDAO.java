package by.jazzteam.numbertotext.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import by.jazzteam.numbertotext.bean.ExponentOfTen;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XlsxDAO implements DAO {

    @Override
    public List<ExponentOfTen> getExponentsOfTen()
    {
        List<ExponentOfTen> exponentOfTenList = new ArrayList<>();

        try
        {
            File file = new File(ClassLoader.getSystemClassLoader().getResource("ExponentsOfTen.xlsx").getFile());
            FileInputStream fis = new FileInputStream(file);

            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            itr.next(); //skipping header row
            while (itr.hasNext())
            {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                ExponentOfTen exponentOfTen = new ExponentOfTen();
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    int collIndex = cell.getColumnIndex();

                    if (collIndex == 0){
                        exponentOfTen.setName(cell.getStringCellValue());
                    }

                    if (collIndex == 1){
                        exponentOfTen.setShortScaleExponent((long) cell.getNumericCellValue());
                    }

                    if (collIndex == 2){
                        exponentOfTen.setLongScaleExponent((long) cell.getNumericCellValue());
                    }

                }
                exponentOfTenList.add(exponentOfTen);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exponentOfTenList;
    }
}
