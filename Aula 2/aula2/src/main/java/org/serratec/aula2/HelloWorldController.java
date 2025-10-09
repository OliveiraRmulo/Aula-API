package org.serratec.aula2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

	@GetMapping
	public String OlaMundo() {
		return "Ola Mundo";
	}
	
	@GetMapping("/msg")
	public String msg() {
		return "oi";
	}
	
	@GetMapping("/maiuscula")
	public String maiuscula(@RequestParam String valor) {
		return valor.toUpperCase();
	}
}
