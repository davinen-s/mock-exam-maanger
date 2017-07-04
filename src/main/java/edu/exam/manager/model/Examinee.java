package edu.exam.manager.model;

import edu.exam.manager.utils.CertificationEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.ss.usermodel.Color;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Davi on 6/27/2017.
 */
public class Examinee {

    private String name;
    private List<MockExam> mockExams;
    private Date finalExamDate;
    private int finalExamIndex;
    private CertificationEnum certification;
    private Boolean voucherPurchased;




    /**
     * Default constructor.
     */
    public Examinee( String name){
        this.name = name;
        mockExams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MockExam> getMockExams() {
        return mockExams;
    }

    public void setMockExams(List<MockExam> mockExams) {
        this.mockExams = mockExams;
    }

    public void addMockExam (MockExam exam) {
        mockExams.add(exam);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.name);
        sb.append("{");
        sb.append(System.lineSeparator());

        if(mockExams != null && mockExams.size() > 0) {
            for(MockExam exam : mockExams) {
                sb.append(exam.toString());
                sb.append(System.lineSeparator());
            }
        }
        sb.append(System.lineSeparator());
        sb.append("}");



        return sb.toString();
    }

    public Date getFinalExamDate() {
        return finalExamDate;
    }

    public void setFinalExamDate(Date finalExamDate) {
        this.finalExamDate = finalExamDate;
    }

    public int getFinalExamIndex() {
        return finalExamIndex;
    }

    public void setFinalExamIndex(int finalExamIndex) {
        this.finalExamIndex = finalExamIndex;
    }

    public String getExamSession() {
        String session = StringUtils.EMPTY;

        if (this.finalExamDate != null ) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(finalExamDate);
            int hour = cal.get(Calendar.HOUR);
            session = (hour == 10)?  "10:15 AM" : "12.30 PM";
        }

        return session;
    }

    public CertificationEnum getCertification() {
        return certification;
    }

    public void setCertification(CertificationEnum certification) {
        this.certification = certification;
    }

    public Boolean getVoucherPurchased() {
        return voucherPurchased;
    }

    public void setVoucherPurchased(Boolean voucherPurchased) {
        this.voucherPurchased = voucherPurchased;
    }

    public void setVoucherPurchased (short colorHexCode) {
        voucherPurchased = colorHexCode == 50;
    }
}
