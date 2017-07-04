package edu.exam.manager.utils;

import edu.exam.manager.model.Examinee;
import edu.exam.manager.model.MockExam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Davi on 7/4/2017.
 */
public class ReadExamFile {



    public static HashMap<String, List<Examinee>> c(String filePath, int sheetIndex, HashMap<String, List<Examinee>> workingDaysMap) {
        Date startDate = getDate(3, 6);
        Date endDate = getDate(11, 7);
        Boolean startProcessing = Boolean.FALSE;

        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(sheetIndex);

            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();

                //skip the header row.
                if (! startProcessing) {
                    Cell dateCell = datatypeSheet.getRow(currentRow.getRowNum()).getCell(0);
                    startProcessing = (ExcelUtils.isString(dateCell) &&  dateCell.getStringCellValue().equals("Resource Name"));
                    continue;
                }

                Examinee examinee = null;
                Cell nameCell = currentRow.getCell(0);

                if (ExcelUtils.isString(nameCell)) {
                    String name = nameCell.getStringCellValue();
                    if (!(name.contains("Not taking part") || name.contains("left acn"))) {
                        examinee = new Examinee(name);
                        setFinalExamDate(datatypeSheet, currentRow.getRowNum(), examinee);
                        System.out.println(examinee.getName() + " :" + examinee.getFinalExamDate());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(examinee.getFinalExamDate());
                        cal.set(Calendar.MILLISECOND, 0);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MINUTE, 0);
                        cal.set(Calendar.HOUR_OF_DAY, 0);

                        Date keyDate = cal.getTime();

                        List<Examinee> examinees = workingDaysMap.get(keyDate.toString());
                        if (examinees != null) {
                            examinees.add(examinee);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return workingDaysMap;


    }

    /**
     * Maps the final exam date from excel cell to {@link MockExam} Object.
     * @param datatypeSheet the planning sheet.
     * @param rowNum the current row index.
     * @param examinee the {@link Examinee}
     */
    private static void setFinalExamDate(Sheet datatypeSheet, int rowNum, Examinee examinee) {
        Cell dateCell = datatypeSheet.getRow(rowNum).getCell(2);

        if (dateCell.getCellTypeEnum() == CellType.NUMERIC){

            examinee.setFinalExamDate(dateCell.getDateCellValue());
        } else if (dateCell.getCellTypeEnum() == CellType.STRING) {
            String dateString = dateCell.getStringCellValue();


            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date formattedDate;
            try {
                formattedDate = df.parse(dateString);
                examinee.setFinalExamDate(formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }


    private static Date getDate(int day, int month) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
       return calendar.getTime();
    }

}
