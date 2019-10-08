package co.edu.usbcali.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operaciones")
public class OperacionesMatematicas {

	@GetMapping("/sumar/{n1}/{n2}")
	public Resultado sumar(@PathVariable("n1") Integer numero1, @PathVariable("n2") Integer numero2) {
		return new Resultado(numero1 + numero2);
	}
	
	@GetMapping("/restar/{n1}/{n2}")
	public Resultado restar(@PathVariable("n1") Integer numero1, @PathVariable("n2") Integer numero2) {
		return new Resultado(numero1 - numero2);
	}
}

class Resultado {
	private Integer valor;

	public Resultado() {
	}

	public Resultado(int valor) {
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
