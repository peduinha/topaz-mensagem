package com.topaz.mensagem.controller;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensagem.dto.FiltroDTO;
import com.topaz.mensagem.service.CompraService;
import com.topaz.mensagem.service.ContaService;

@RestController
@RequestMapping("/testeatm")
public class MensagemController {

	@Autowired
	private CompraService compraService;
	
	@Autowired
	private ContaService contaService;

	@PostMapping("/compra")
	public String compra(@RequestBody FiltroDTO filtro) throws ConnectException {

		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			retorno = this.compraService.executaCompra200(filtro);
		} else {
			return validar;
		}
		
		return retorno;
	}
	
	@PostMapping("/compraReversa")
	public String compraReversa(@RequestBody FiltroDTO filtro) {

		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			retorno = this.compraService.executaCompraPara420(filtro);
			retorno = this.compraService.executaCompra420(filtro);
		} else {
			return validar;
		}
		
		return retorno;
	}
	
	@PostMapping("/compraUSD")
	public String compraUSD(@RequestBody FiltroDTO filtro) {

		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			retorno = this.compraService.executaCompraUSD(filtro);
		} else {
			return validar;
		}
		
		return retorno;
	}
	
	@PostMapping("/consultaSaldo")
	public String consultaSaldo(@RequestBody FiltroDTO filtro) {

		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			retorno = this.contaService.consultaSaldo(filtro);
		} else {
			return validar;
		}
		
		return retorno;
	}
	
	@PostMapping("/consultaExtrato")
	public String consultaExtrato(@RequestBody FiltroDTO filtro) {

		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			retorno = this.contaService.consultaExtrato(filtro);
		} else {
			return validar;
		}
		
		return retorno;
	}
	
	@PostMapping("/retiro")
	public String retiro(@RequestBody FiltroDTO filtro) {

		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			retorno = this.contaService.retiro(filtro);			
		} else {
			return validar;
		}
		
		return retorno;
	}
	
	@PostMapping("/retiroReversa")
	public String retiroReversa(@RequestBody FiltroDTO filtro) {

		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			retorno = this.contaService.retiroPara420(filtro);
			retorno = this.contaService.retiro420(filtro);
		} else {
			return validar;
		}
		
		return retorno;
	}

	public String validar(FiltroDTO filtro) {

		String erros = "OK";

		if (filtro.getData().length() != 4) {
			erros = "Campo 'Data' deve ter 4 caracters (mmdd) - Exemplo: '3012'";
		}

		if (filtro.getConta().length() != 7) {
			erros = "Campo 'Conta' deve ter 7 caracters";
		}

		return erros;

	}

}
