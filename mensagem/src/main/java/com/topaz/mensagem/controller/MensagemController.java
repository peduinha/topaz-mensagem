package com.topaz.mensagem.controller;

import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensagem.dto.FiltroDTO;
import com.mensagem.dto.HttpResponseDTO;
import com.topaz.mensagem.service.CompraService;
import com.topaz.mensagem.service.ContaService;
import com.topaz.mensagem.service.SaqueLojaService;

@RestController
@RequestMapping("/testeatm")
public class MensagemController {

	@Autowired
	private CompraService compraService;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private SaqueLojaService saqueLojaService;

	@PostMapping("/compra")
	public HttpResponseDTO compra(@RequestBody FiltroDTO filtro) throws ConnectException {

		HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			httpResponseDTO = this.compraService.executaCompra200(filtro);
		} else {
			return HttpResponseDTO.fail("VALIDAR - ERROS - " + validar);
		}
		
		return httpResponseDTO;
	}
	
	@PostMapping("/compraReversa")
	public HttpResponseDTO compraReversa(@RequestBody FiltroDTO filtro) throws InterruptedException {

		HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			httpResponseDTO = this.compraService.executaCompraPara420(filtro);
			if(httpResponseDTO.isSuccess()) {
				retorno = httpResponseDTO.getMessage();
				TimeUnit.SECONDS.sleep(10);
				retorno += " | ";
				
				FiltroDTO dto = (FiltroDTO) httpResponseDTO.getContent().get("ret");
				filtro.setHhmmss_200(dto.getHhmmss_200());
				httpResponseDTO = this.compraService.executaCompra420(filtro);
				
				retorno += httpResponseDTO.getMessage();
			}else {
				retorno = httpResponseDTO.getMessage();
			}
			
		} else {
			return HttpResponseDTO.fail("VALIDAR - ERROS - " + validar);
		}
		
		httpResponseDTO.setMessage(retorno);
		return httpResponseDTO;
	}
	
	@PostMapping("/compraUSD")
	public HttpResponseDTO compraUSD(@RequestBody FiltroDTO filtro) {

		HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			httpResponseDTO = this.compraService.executaCompraUSD(filtro);
		} else {
			return HttpResponseDTO.fail("VALIDAR - ERROS - " + validar);
		}
		
		return httpResponseDTO;
	}
	
	@PostMapping("/consultaSaldo")
	public HttpResponseDTO consultaSaldo(@RequestBody FiltroDTO filtro) {
 
		HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			httpResponseDTO = this.contaService.consultaSaldo(filtro);
		} else {
			return HttpResponseDTO.fail("VALIDAR - ERROS - " + validar);
		}
		
		return httpResponseDTO;
	}
	
	@PostMapping("/consultaExtrato")
	public HttpResponseDTO consultaExtrato(@RequestBody FiltroDTO filtro) {

		HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			httpResponseDTO = this.contaService.consultaExtrato(filtro);
		} else {
			return HttpResponseDTO.fail("VALIDAR - ERROS - " + validar);
		}
		
		return httpResponseDTO;
	}
	
	@PostMapping("/retiro")
	public HttpResponseDTO retiro(@RequestBody FiltroDTO filtro) {

		HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			httpResponseDTO = this.contaService.retiro(filtro);			
		} else {
			return HttpResponseDTO.fail("VALIDAR - ERROS - " + validar);
		}
		
		return httpResponseDTO;
	}
	
	@PostMapping("/retiroReversa")
	public HttpResponseDTO retiroReversa(@RequestBody FiltroDTO filtro) throws InterruptedException {

		HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
		String retorno = "";
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			httpResponseDTO = this.contaService.retiroPara420(filtro);
			if(httpResponseDTO.isSuccess()) {
				retorno = httpResponseDTO.getMessage();
				TimeUnit.SECONDS.sleep(3);
				retorno += " | ";
				
				FiltroDTO dto = (FiltroDTO) httpResponseDTO.getContent().get("ret");
				filtro.setHhmmss_200(dto.getHhmmss_200());
				httpResponseDTO = this.contaService.retiro420(filtro);
				retorno += httpResponseDTO.getMessage();
				
			}else {
				retorno = httpResponseDTO.getMessage();
			}
			
		} else {
			return HttpResponseDTO.fail("VALIDAR - ERROS - " + validar);
		}
		
		httpResponseDTO.setMessage(retorno);
		return httpResponseDTO;
	}
	
	@PostMapping("/saqueLoja")
	public HttpResponseDTO saqueLoja(@RequestBody FiltroDTO filtro) {

		HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
		String validar = this.validar(filtro);

		if (validar.equalsIgnoreCase("OK")) {
			httpResponseDTO = this.saqueLojaService.retiro(filtro);			
		} else {
			return HttpResponseDTO.fail("VALIDAR - ERROS - " + validar);
		}
		
		return httpResponseDTO;
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
