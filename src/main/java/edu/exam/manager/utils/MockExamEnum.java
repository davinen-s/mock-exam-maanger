package edu.exam.manager.utils;

/**
 * Created by Davi on 6/27/2017.
 */
public enum MockExamEnum {

    MOCK_1 ("Diagnostic Test"),
    MOCK_2 ("Practical Test I"),
    MOCK_3 ("Practical Test II"),
    MOCK_4 ("Practical Test III"),
    MOCK_5 ("Final Test ");


    private String whizlabExamName;


    /**
     * Private constructor.
     * @param whizlabExamName the exam name on whizlab.
     */
    private MockExamEnum(String whizlabExamName) {
        this.whizlabExamName = whizlabExamName;
    }

    /**
     * Getter method.
     * @return
     */
    public String getWhizlabExamName() {
        return whizlabExamName;
    }

    /**
     * Setter method.
     * @param whizlabExamName the exam name on whizlab.
     */
    public void setWhizlabExamName(String whizlabExamName) {
        this.whizlabExamName = whizlabExamName;
    }
}
