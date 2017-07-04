package edu.exam.manager.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {


	@RequestMapping("/")
	public String ocaOverview(Map<String, Object> model) {

		return "home";
	}

}