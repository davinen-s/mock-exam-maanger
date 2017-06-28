package edu.exam.manager.app;

import edu.exam.manager.model.Examinee;
import edu.exam.manager.utils.ModelNameUtils;
import edu.exam.manager.utils.ReadExcelFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

	// inject via application.properties
	@Value("${welcome.message}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String ocaOverview(Map<String, Object> model) {

		model.put(ModelNameUtils.MESSAGE, message);

		return "home";
	}

}