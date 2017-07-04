package edu.exam.manager.utils;

import edu.exam.manager.model.Examinee;
import edu.exam.manager.model.MockExam;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import  edu.exam.manager.utils.ExcelUtils;

/**
 * Created by Davi.
 */
public class ReadExcelFile {


    public static List<Examinee>  readFile(CertificationEnum certification, String filePath) {
        List<Examinee> examineeList  = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(excelFile);

            Sheet datatypeSheet;
            if (certification == CertificationEnum.OCA){
                 datatypeSheet = workbook.getSheetAt(0);
            } else {
                datatypeSheet = workbook.getSheetAt(1);
            }
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();

                //skip the header row.
                if (currentRow.getRowNum() == 0) {
                    continue;
                }

                Examinee examinee = null;
                Cell nameCell = currentRow.getCell(0);

                if (ExcelUtils.isString(nameCell)) {
                    String name = nameCell.getStringCellValue();
                    if(!(name.contains("Not taking part") || name.contains("left acn"))) {
                        examinee = new Examinee(name);
                    }
                }

                if (examinee != null) {


                    processRowData(datatypeSheet, currentRow, examinee);
                    examineeList.add(examinee);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return examineeList;
    }


    /**
     * Maps all mock exam data in the row to the Examinee object,
     * @param datatypeSheet the work sheet.
     * @param currentRow the current row.
     * @param examinee the examinee.
     */
    private static void processRowData(Sheet datatypeSheet, Row currentRow, Examinee examinee) {

        Iterator<Cell> cellIterator = currentRow.iterator();

        while ( cellIterator.hasNext() ){
            Cell currentCell = cellIterator.next();
            if(ExcelUtils.isString(currentCell) && currentCell.getStringCellValue().contains("Mock")){
                String recordedStatus = currentCell.getStringCellValue();
                MockExam exam = new MockExam();

                setCurrentMock(recordedStatus, exam);
                setMockStatus(recordedStatus, exam);
                setAssignedAccount(currentRow, currentCell, exam);
                setMockDate(datatypeSheet, currentCell, exam);
                examinee.addMockExam(exam);

            }
        }
        while (examinee.getMockExams().size() < 5) {
            MockExam exam = new MockExam();
            exam.setStatus(MockExamStatus.UNPLANNED);
            examinee.addMockExam(exam);
        }

    }

    /**
     * Maps the mock date from excel cell to {@link MockExam} Object.
     * @param datatypeSheet the planning sheet.
     * @param currentCell the current cell.
     * @param exam the {@link MockExam}
     */
    private static void setMockDate(Sheet datatypeSheet, Cell currentCell, MockExam exam) {
        Cell dateCell = datatypeSheet.getRow(0).getCell(currentCell.getColumnIndex());

        if (dateCell.getCellTypeEnum() == CellType.NUMERIC){

                exam.setExamDate(dateCell.getDateCellValue());
        } else if (dateCell.getCellTypeEnum() == CellType.STRING) {
            String dateString = dateCell.getStringCellValue();


            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date formattedDate;
            try {
                formattedDate = df.parse(dateString);
                exam.setExamDate(formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Maps the assigned account data from row to {@link MockExam} Object.
     * @param currentRow the current row.
     * @param currentCell the current cell.
     * @param exam  the {@link MockExam}
     */
    private static void setAssignedAccount(Row currentRow, Cell currentCell, MockExam exam) {
        Cell accountCell = currentRow.getCell(currentCell.getColumnIndex() + 1);
        if (ExcelUtils.isString(accountCell)) {
            exam.setAccountAssigned(accountCell.getStringCellValue());
        }
    }

    /**
     * Maps the mock exam status from row to {@link MockExam} Object.
     * @param recordedStatus the recordededStatus.
     * @param exam the {@link MockExam}.
     */
    private static void setMockStatus(String recordedStatus, MockExam exam) {
        if (recordedStatus.contains("Scheduled")) {
            exam.setStatus(MockExamStatus.SCHEDULED);
        }else if (recordedStatus.contains("Attempted")) {
            exam.setStatus(MockExamStatus.ATTEMPTED);
        }else if (recordedStatus.contains("Reschedule")) {
            exam.setStatus(MockExamStatus.TO_RESCHEDULED);
        }
    }

    /**
     * Map the current mock from row to{@link MockExam} Object.
     * @param recordedStatus the recorded Status.
     * @param exam the {@link MockExam}
     */
    private static void setCurrentMock(String recordedStatus, MockExam exam) {
        if(recordedStatus.contains("Mock1")) {
            exam.setCurrentMockExam(MockExamEnum.MOCK_1);
        } else if(recordedStatus.contains("Mock2")) {
            exam.setCurrentMockExam(MockExamEnum.MOCK_2);
        } else if(recordedStatus.contains("Mock3")) {
            exam.setCurrentMockExam(MockExamEnum.MOCK_3);
        } else if(recordedStatus.contains("Mock4")) {
            exam.setCurrentMockExam(MockExamEnum.MOCK_4);
        } else if(recordedStatus.contains("Mock5")) {
            exam.setCurrentMockExam(MockExamEnum.MOCK_5);
        }
    }



}
