package edu.exam.manager.app;

import java.util.List;
import java.util.Map;

import edu.exam.manager.model.Examinee;
import edu.exam.manager.utils.ModelNameUtils;
import edu.exam.manager.utils.ReadExcelFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OverviewController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/overview/oca")
	public String ocaOverview(Map<String, Object> model) {

		final List<Examinee> examineeList = ReadExcelFile.readFile();
		model.put(ModelNameUtils.EXAMINEE_LIST, examineeList);

		return "overview";
	}

}