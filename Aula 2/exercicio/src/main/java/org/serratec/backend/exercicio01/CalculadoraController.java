package org.serratec.backend.exercicio01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class CalculadoraController {

    public static void main(String[] args) {
        SpringApplication.run(CalculadoraController.class, args);
    }

    @GetMapping("/calcular")
    public String calcular(
            @RequestParam double num1,
            @RequestParam double num2,
            @RequestParam String operador
    ) {
        double resultado;
        switch (operador) {
            case "+" -> resultado = num1 + num2;
            case "-" -> resultado = num1 - num2;
            case "*" -> resultado = num1 * num2;
            case "/" -> {
                if (num2 == 0) return "Erro: divisão por zero!";
                resultado = num1 / num2;
            }
            default -> {
                return "Erro: operador inválido. Use +, -, * ou /";
            }
        }
        return "Resultado: " + resultado;
    }
}