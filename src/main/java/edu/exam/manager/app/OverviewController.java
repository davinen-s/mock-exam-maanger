package edu.exam.manager.app;

import java.util.List;
import java.util.Map;

import edu.exam.manager.model.Examinee;
import edu.exam.manager.utils.CertificationEnum;
import edu.exam.manager.utils.ModelNameUtils;
import edu.exam.manager.utils.ReadExcelFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OverviewController {

    @Value("${location.file.mock}")
    private String mockFileLocation;

	/**
	 * Get method for oca overview.
	 * @param model  the model
	 * @return the view name.
	 */
	@RequestMapping("/overview/oca")
	public String ocaOverview(Map<String, Object> model) {

		final List<Examinee> examineeList = ReadExcelFile.readFile(CertificationEnum.OCA, mockFileLocation);
		model.put(ModelNameUtils.EXAMINEE_LIST, examineeList);
		model.put(ModelNameUtils.CERTIFICATION, CertificationEnum.OCA);
		return "overview";
	}

	@RequestMapping("/overview/ocp")
	public String ocpOverview(Map<String, Object> model) {

		final List<Examinee> examineeList = ReadExcelFile.readFile(CertificationEnum.OCP, mockFileLocation);
		model.put(ModelNameUtils.EXAMINEE_LIST, examineeList);
		model.put(ModelNameUtils.CERTIFICATION, CertificationEnum.OCP);
		return "overview";
	}

}