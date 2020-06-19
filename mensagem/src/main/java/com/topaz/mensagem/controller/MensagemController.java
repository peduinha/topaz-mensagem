package com.topaz.mensagem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensagem.dto.FiltroDTO;
import com.topaz.mensagem.service.CompraService;

@RestController
@RequestMapping("/api/mensagem")
public class MensagemController {
	
	@Autowired
	private CompraService compraService;
	
	
	@PostMapping("/compra200")
	public String compra(@RequestBody FiltroDTO filtro) {
		
		String retorno = ""; 
		String validar = this.validar(filtro);
		
		if(validar.equalsIgnoreCase("OK")) {
			try {
				retorno = this.compraService.executaCompra200(filtro);
			} catch (Exception e) {
				//e.printStackTrace();
				return "ERRO - Compra200 - " + e.getMessage();
			}
		}else {
			return validar;
		}
		
		
		return retorno;
	}
	
	
	
	public String validar(FiltroDTO filtro) {
		
		String erros = "OK";
		
		if(filtro.getData().length() != 4) {
			erros = "Campo 'Data' deve ter 4 caracters (ddmm) - Exemplo: '3012'";
		}
		
		if(filtro.getConta().length() != 7) {
			erros = "Campo 'Conta' deve ter 7 caracters";
		}
		
		return erros;
		
	}

}
