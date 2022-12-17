package com.contrastsecurity.demo.providersearch;

import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// interface上可以没有class level的注解
@RequestMapping("/default")
public interface ProviderControllerInterface {

	// 访问这个必须带着/，比如直接访问 /default 返回的404，得访问 /default/
	@GetMapping("/")
	public String index(Model model);

	@PostMapping("/")
	public String search(@RequestParam Map<String, String> body, Model model);
}
