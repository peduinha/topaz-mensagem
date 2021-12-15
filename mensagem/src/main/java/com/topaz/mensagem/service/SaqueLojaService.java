package com.topaz.mensagem.service;

import java.net.Socket;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mensagem.dto.FiltroDTO;
import com.mensagem.dto.HttpResponseDTO;

@Service
public class SaqueLojaService {
	
	public HttpResponseDTO retiro(FiltroDTO filtro) {
		
		StringBuilder hhmmss = new StringBuilder();

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			 result2[0] = (byte) 0x00;
		     result2[1] = (byte) 0xD5; //213
		        
			String messageType = "0200";
			
			String message = "101918394908002000000000008000000000008000";
			message += filtro.getData();
			
			LocalDateTime a = LocalDateTime.now();
			hhmmss.append(this.ajustaHora(a.getHour()) );
			hhmmss.append(this.ajustaHora(a.getMinute()));
			hhmmss.append(this.ajustaHora(a.getSecond()));
			message += hhmmss;
			filtro.setHhmmss_200(hhmmss.toString());
			filtro.setMMDDhhmmss_200(filtro.getData() + hhmmss.toString());
			
			message += "005216133519";
			message += filtro.getData(); 
			message += "60110121199956146200009916005216TERMID01CARD ACCEPTOR  CARD ACCEPTOR NAME/LOCATICITY NAME    BR986986021#102@0999#103@";
			message += filtro.getConta();

			byte[] bitmap = generateBitMap_2();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			return HttpResponseDTO.fail("ERRO - SaqueLoja - " + e.getMessage());
		}
		return HttpResponseDTO.success("WebService - SaqueLoja executado !", "ret", filtro);
	}
	
	//Copiado do Retiro
	public HttpResponseDTO retiro420(FiltroDTO filtro) {
		
		StringBuilder hhmmss = new StringBuilder();

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			result2[0] = (byte) 0x00;
		    //result2[1] = (byte) 0xB1; //177
		    result2[1] = (byte) 0xB6; //182
		        
			String messageType = "0420";
			
			String message = "101918394908002000000000008000";
			message += filtro.getData();
			
			LocalDateTime a = LocalDateTime.now();
			hhmmss.append(this.ajustaHora(a.getHour()) );
			hhmmss.append(this.ajustaHora(a.getMinute()));
			hhmmss.append(this.ajustaHora(a.getSecond()));
			message += hhmmss;
			filtro.setHhmmss_420(hhmmss.toString());
			filtro.setMMDDhhmmss_420(filtro.getData() + hhmmss.toString());
			
			message += "005248192212";
			message += filtro.getData();
			
			//message += "6011 06 000358009916005216TERMID019860200005216";
			message += "60111199956146200009916005216TERMID019860200005216";
			message += filtro.getData();
			message += filtro.getHhmmss_200();
			
			message += "0000000035800000000000021#102@0999#103@";
			message += filtro.getConta();

			byte[] bitmap = generateBitMap_3();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			//return "ERRO - Retiro420 - " + e.getMessage();
			return HttpResponseDTO.fail("ERRO - Saque420 - " + e.getMessage());
		}
		//return "WebService - Retiro420 executado !";
		return HttpResponseDTO.success("WebService - Saque420 Reversa executado !", "ret", filtro);
	}
	
	
	
	public static byte[] generateBitMap_2() throws Exception {
	  
 		byte[] result2 = new byte[16];
 		result2[0] = (byte) 0xF6; //246
	    result2[1] = (byte) 0x38; //56
	    result2[2] = (byte) 0x44; //68
	    result2[3] = (byte) 0x01; //1
	    result2[4] = (byte) 0x08; //8
	    result2[5] = (byte) 0xE0; //224
	    result2[6] = (byte) 0xA0; //160
	    result2[7] = (byte) 0x00; //0
	    
	    result2[8] = (byte) 0x00; //0
	    result2[9] = (byte) 0x00; //0
	    result2[10] = (byte) 0x00; //0
	    result2[11] = (byte) 0x00; //0
	    result2[12] = (byte) 0x00; //0
	    result2[13] = (byte) 0x00; //0
	    result2[14] = (byte) 0x01; //1
	    result2[15] = (byte) 0x00; //0
	    
		return result2;	
	}
	
	public static byte[] generateBitMap_3() throws Exception {
		
		byte[] result2 = new byte[16];
 		result2[0] = (byte) 0xF2;
	    result2[1] = (byte) 0x38;
	    result2[2] = (byte) 0x40;
	    result2[3] = (byte) 0x01;
	    result2[4] = (byte) 0x08;
	    result2[5] = (byte) 0x80;
	    result2[6] = (byte) 0x80;
	    result2[7] = (byte) 0x00;
	    
	    result2[8] = (byte) 0x00;
	    result2[9] = (byte) 0x00;
	    result2[10] = (byte) 0x00;
	    result2[11] = (byte) 0x40;
	    result2[12] = (byte) 0x00;
	    result2[13] = (byte) 0x00;
	    result2[14] = (byte) 0x01;
	    result2[15] = (byte) 0x00;
	   
		return result2;
	}
	
	
	private String ajustaHora(Integer time) {
		String timeString = time.toString();
		String ret = "0";
		if (timeString.length() == 1) {
			ret += time;
		} else {
			ret = time.toString();
		}
		return ret;
	}
	
}
