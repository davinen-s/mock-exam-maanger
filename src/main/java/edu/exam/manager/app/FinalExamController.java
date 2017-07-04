package edu.exam.manager.app;

import edu.exam.manager.model.Examinee;
import edu.exam.manager.utils.CalendarUtils;
import edu.exam.manager.utils.ModelNameUtils;
import edu.exam.manager.utils.ReadExamFile;
import edu.exam.manager.utils.ReadExcelFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class FinalExamController {

/*@Value("${ocp.exam.file.location}")
String ocpFileLocation;*/

	@RequestMapping("/exam-dates")
	public String ocaOverview(Map<String, Object> model) {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 3);
		calendar.set(Calendar.MONTH, 6);
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		Date startDate = calendar.getTime();

		calendar.set(Calendar.DAY_OF_MONTH, 11);
		calendar.set(Calendar.MONTH, 7);
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		Date endDate = calendar.getTime();

        final List<String> workingDays = CalendarUtils.getWorkingDays(startDate, endDate);

        HashMap<String, List<Examinee>> workingDaysMap = CalendarUtils.getWorkingDaysMap(startDate, endDate);


		// workingDaysMap = ReadExamFile.c("C:\\Users\\davinen.s.curoopen\\Documents\\capa_oca.ocp\\30.06.2017\\OCA8_Final_Exam_planning_LastUpdate30.06.2017.xls", 0, workingDaysMap);
        // workingDaysMap = ReadExamFile.c("C:\\Users\\davinen.s.curoopen\\Documents\\capa_oca.ocp\\30.06.2017\\OCP7_Final_exam_planning_LastUpdated26.05.2017.xls", 4, workingDaysMap);

        workingDaysMap = ReadExamFile.c("C:\\Users\\Davi\\Dropbox\\exam_planning\\OCA8_Final_Exam_planning_LastUpdate30.06.2017.xls", 0, workingDaysMap);
        workingDaysMap = ReadExamFile.c("C:\\Users\\Davi\\Dropbox\\exam_planning\\OCP7_Final_exam_planning_LastUpdated26.05.2017.xls", 4, workingDaysMap);
        //workingDaysMap = ReadExamFile.c(ocpFileLocation, 4, workingDaysMap);

        model.put(ModelNameUtils.WORKING_DAY_LIST, workingDays);
        model.put(ModelNameUtils.EXAM_DATE_MAP, workingDaysMap);

        return "exam-dates";
	}

}