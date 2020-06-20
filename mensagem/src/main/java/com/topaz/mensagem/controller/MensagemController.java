package com.topaz.mensagem.controller;

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
	public String compra(@RequestBody FiltroDTO filtro) {

		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			try {
				retorno = this.compraService.executaCompra200(filtro);
			} catch (Exception e) {
				// e.printStackTrace();
				return "ERRO - compra - " + e.getMessage();
			}
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
			try {
				retorno = this.compraService.executaCompraPara420(filtro);
				retorno = this.compraService.executaCompra420(filtro);
			} catch (Exception e) {
				// e.printStackTrace();
				return "ERRO - compraReversa - " + e.getMessage();
			}
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
			try {
				retorno = this.compraService.executaCompraUSD(filtro);
			} catch (Exception e) {
				// e.printStackTrace();
				return "ERRO - compraUSD - " + e.getMessage();
			}
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
			try {
				retorno = this.contaService.consultaSaldo(filtro);
			} catch (Exception e) {
				// e.printStackTrace();
				return "ERRO - consultaSaldo - " + e.getMessage();
			}
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
			try {
				retorno = this.contaService.consultaExtrato(filtro);
			} catch (Exception e) {
				// e.printStackTrace();
				return "ERRO - consultaExtrato - " + e.getMessage();
			}
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
			try {
				retorno = this.contaService.retiro(filtro);
			} catch (Exception e) {
				// e.printStackTrace();
				return "ERRO - retiro - " + e.getMessage();
			}
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
			try {
				retorno = this.contaService.retiroPara420(filtro);
				retorno = this.contaService.retiro420(filtro);
			} catch (Exception e) {
				// e.printStackTrace();
				return "ERRO - retiroReversa - " + e.getMessage();
			}
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
