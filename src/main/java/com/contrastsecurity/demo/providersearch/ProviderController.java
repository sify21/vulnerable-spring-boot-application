package com.contrastsecurity.demo.providersearch;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// interface based controller必须有@Controller注解
@Controller
@RequestMapping("/")
public class ProviderController implements ProviderControllerInterface {
	@Value("${spring.application.name}")
	String appName;

	@Autowired
	ProviderSearchDAO providerSearchDAO;

	@Override
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("appName", appName);
		model.addAttribute("noSearch", true);
		return "index";
	}

	@Override
	public String search(Map<String, String> body, Model model) {
		model.addAttribute("zipCode", body.get("zipCode"));
		List<Object[]> results = providerSearchDAO.getProvidersInZipCode(body.get("zipCode"));
		model.addAttribute("results", results);
		return "index";
	}
}
