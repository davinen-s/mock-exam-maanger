package edu.exam.manager.utils;

import edu.exam.manager.model.Examinee;
import edu.exam.manager.model.MockExam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static edu.exam.manager.utils.CalendarUtils.getFormattedDateString;

/**
 * Created by Davi on 7/4/2017.
 */
public class ReadExamFile {

    public static HashMap<String, List<Examinee>> process(String filePath, CertificationEnum certification, int sheetIndex, HashMap<String, List<Examinee>> workingDaysMap) {

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
                    if (!(name.contains("Not taking part") || name.contains("left acn") || name.contains("dropped"))) {
                        examinee = new Examinee(name);
                        examinee.setCertification(certification);
                        setFinalExamDate(datatypeSheet, currentRow.getRowNum(), examinee);
                        setPurchasedVoucherStatus(certification, datatypeSheet, currentRow, examinee);

                        System.out.println(examinee.getName() + " :" + examinee.getFinalExamDate());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(examinee.getFinalExamDate());
                        cal.set(Calendar.MILLISECOND, 0);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MINUTE, 0);
                        cal.set(Calendar.HOUR_OF_DAY, 0);

                        String keyDateString = getFormattedDateString(cal.getTime());

                        List<Examinee> examinees = workingDaysMap.get(keyDateString);
                        if (examinees != null) {
                            examinees.add(examinee);
                        } else {
                            System.out.println("!!!!!!! Error fetching Date from Map:" + keyDateString);
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
     * Set the purchased Voucher status to true if cell background color is green.
     * @param certification the current certification reading mode..
     * @param datatypeSheet The datasheet being read.
     * @param currentRow the current row.
     * @param examinee the examinee for whom we are updating the purchased voucher status.
     */
    private static void setPurchasedVoucherStatus(CertificationEnum certification, Sheet datatypeSheet, Row currentRow, Examinee examinee) {
        int coloredColumnIndex = (certification == CertificationEnum.OCA) ? 1 : 2;
        final Cell cell = datatypeSheet.getRow(currentRow.getRowNum()).getCell(coloredColumnIndex);
        CellStyle cs = cell.getCellStyle();
        examinee.setVoucherPurchased(cs.getFillForegroundColor());
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




}
