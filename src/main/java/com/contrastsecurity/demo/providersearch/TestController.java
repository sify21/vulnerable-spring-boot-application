package com.contrastsecurity.demo.providersearch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping("/hello/{name}")
	@ResponseBody
	public String hello(Double m, @RequestHeader(required = false) String header, Long i, @PathVariable String name,
			Boolean b) {
		return "hello " + name;
	}
}
