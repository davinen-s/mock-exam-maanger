package edu.exam.manager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Davi on 6/27/2017.
 */
public class Examinee {

    private String name;
    private List<MockExam> mockExams;

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
}
