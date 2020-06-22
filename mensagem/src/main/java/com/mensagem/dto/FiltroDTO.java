package com.mensagem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroDTO {
	
	private String servidor;
	private Integer porta;
	private String data;
	private String conta;
	private String hhmmss_200;
	private String MMDDhhmmss_200;
	
	private String hhmmss_420;
	private String MMDDhhmmss_420;
	
}
