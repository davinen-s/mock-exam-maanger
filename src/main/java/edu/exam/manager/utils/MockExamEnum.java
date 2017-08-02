package edu.exam.manager.utils;

/**
 * Created by Davi on 6/27/2017.
 */
public enum MockExamEnum {

    MOCK_1 ("Diagnostic Test"),
    MOCK_2 ("Practice Test I"),
    MOCK_3 ("Practice Test II"),
    MOCK_4 ("Practice Test III"),
    MOCK_5 ("Final Test "),
    MOCK_6 ("Practice Test IV"),
    MOCK_7 ("Practice Test V") ;


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
