package edu.exam.manager.model;

import edu.exam.manager.utils.MockExamEnum;
import edu.exam.manager.utils.MockExamStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * Created by Davi on 6/27/2017.
 */
public class MockExam {
    private MockExamEnum currentMockExam;
    private MockExamStatus status;
    private Date examDate;
    private String accountAssigned;

    public MockExamEnum getCurrentMockExam() {
        return currentMockExam;
    }

    public void setCurrentMockExam(MockExamEnum currentMockExam) {
        this.currentMockExam = currentMockExam;
    }

    public MockExamStatus getStatus() {
        return status;
    }

    public void setStatus(MockExamStatus status) {
        this.status = status;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getAccountAssigned() {
        return accountAssigned;
    }

    public void setAccountAssigned(String accountAssigned) {
        this.accountAssigned = accountAssigned;
    }

    public String getStatusCssClass(){
        String cssClass = "";
        if (this.status == MockExamStatus.ATTEMPTED) {
            cssClass = "success";
        } else if (this.status == MockExamStatus.SCHEDULED && isPassedScheduledDate()) {
            cssClass = "danger";
        } else if (this.status == MockExamStatus.TO_RESCHEDULED) {
            cssClass = "warning";
        }


        return cssClass;
    }

    private boolean isPassedScheduledDate() {
        final Date today = new Date();
        return !DateUtils.isSameDay(today, this.examDate) && this.examDate.before(today);
    }

    @Override
    public String toString(){
    StringBuilder sb = new StringBuilder(this.currentMockExam.getWhizlabExamName());
        sb.append(", status: " + this.status.toString());
        sb.append(", Date:" + this.examDate !=null ? examDate  : StringUtils.EMPTY);

        return  sb.toString();
    }
}
