package edu.exam.manager.app;

import edu.exam.manager.model.Examinee;
import edu.exam.manager.utils.CalendarUtils;
import edu.exam.manager.utils.CertificationEnum;
import edu.exam.manager.utils.ModelNameUtils;
import edu.exam.manager.utils.ReadExamFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import static edu.exam.manager.utils.CalendarUtils.getDate;

@Controller
public class FinalExamController {

    @Value("${location.file.exam.ocp}")
    String ocpFileLocation;

    @Value("${location.file.exam.oca}")
    String ocaFileLocation;

	@RequestMapping("/exam-dates")
	public String ocaOverview(Map<String, Object> model) {

        Date startDate = getDate(3, 6);
        Date endDate = getDate(30, 10);

        final List<String> workingDays = CalendarUtils.getWorkingDays(startDate, endDate);

        HashMap<String, List<Examinee>> workingDaysMap = CalendarUtils.getWorkingDaysMap(startDate, endDate);


		// workingDaysMap = ReadExamFile.process("C:\\Users\\davinen.s.curoopen\\Documents\\capa_oca.ocp\\30.06.2017\\OCA8_Final_Exam_planning_LastUpdate30.06.2017.xls", 0, workingDaysMap);
        // workingDaysMap = ReadExamFile.process("C:\\Users\\davinen.s.curoopen\\Documents\\capa_oca.ocp\\30.06.2017\\OCP7_Final_exam_planning_LastUpdated26.05.2017.xls", 4, workingDaysMap);

        workingDaysMap = ReadExamFile.process(ocaFileLocation, CertificationEnum.OCA, 0, workingDaysMap);
        workingDaysMap = ReadExamFile.process(ocpFileLocation, CertificationEnum.OCP, 4, workingDaysMap);
        //workingDaysMap = ReadExamFile.process(ocpFileLocation, 4, workingDaysMap);

        model.put(ModelNameUtils.WORKING_DAY_LIST, workingDays);
        model.put(ModelNameUtils.EXAM_DATE_MAP, workingDaysMap);

        return "exam-dates";
	}

}