package com.topaz.mensagem.service;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mensagem.dto.FiltroDTO;
import com.mensagem.dto.HttpResponseDTO;

@Service
public class CompraService {

	public HttpResponseDTO executaCompra200(FiltroDTO filtro) {
		
		StringBuilder hhmmss = new StringBuilder();

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			result2[0] = (byte) 0x00;
			result2[1] = (byte) 0xD0;

			String messageType = "0200";

			String message = "101918394910002000000000008000000000008000";
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
			message += "599901206123456009916005216TERMID01CARD ACCEPTOR  CARD ACCEPTOR NAME/LOCATICITY NAME    BR986986021#102@0999#103@";
			message += filtro.getConta();

			byte[] bitmap = generateBitMap_1();
			byte[] messageBytes = new byte[result2.length + messageType.getBytes().length + bitmap.length + message.getBytes().length];
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
				
		}  catch (Exception e) {
			//return "ERRO - Compra200 - " + e.getMessage();
			return HttpResponseDTO.fail("ERRO - Compra200 - " + e.getMessage());
		}
		
		//return "WebService - Compra200 executado !";
		return HttpResponseDTO.success("WebService - Compra200 executado !", "ret", filtro);
	}
	
	public HttpResponseDTO executaCompraPara420(FiltroDTO filtro) {
		
		StringBuilder hhmmss = new StringBuilder();

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			result2[0] = (byte) 0x00;
	        result2[1] = (byte) 0xD0;

			String messageType = "0200";
			
			String message = "101918394907002000000000008000000000008000";
			message += filtro.getData();
			
			LocalDateTime a = LocalDateTime.now();
			hhmmss.append(this.ajustaHora(a.getHour()) );
			hhmmss.append(this.ajustaHora(a.getMinute()));
			hhmmss.append(this.ajustaHora(a.getSecond()));
			message += hhmmss;
			
			filtro.setHhmmss_200(hhmmss.toString());
			filtro.setMMDDhhmmss_200(filtro.getData() + hhmmss.toString());
			
			message	+= "005216133519";
			message += filtro.getData();
			message	+= "599901206123456009916005216TERMID01CARD ACCEPTOR  CARD ACCEPTOR NAME/LOCATICITY NAME    BR986986021#102@0999#103@";
			message += filtro.getConta();

			byte[] bitmap = generateBitMap_1();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			//return "ERRO - CompraPara420 - " + e.getMessage();
			return HttpResponseDTO.fail("ERRO - CompraPara420 - " + e.getMessage());
		}

		//return "WebService - CompraPara420 executado !";
		return HttpResponseDTO.success("WebService - CompraPara420 executado !", "ret", filtro);
	}
	
	public HttpResponseDTO executaCompra420(FiltroDTO filtro) {
		
		StringBuilder hhmmss = new StringBuilder();

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			result2[0] = (byte) 0x00;
	        result2[1] = (byte) 0xB1;

	        
			String messageType = "0420";
			
			
			
			String message = "101918394907002000000000008000";
			message += filtro.getData();
			
			LocalDateTime a = LocalDateTime.now();
			hhmmss.append(this.ajustaHora(a.getHour()) );
			hhmmss.append(this.ajustaHora(a.getMinute()));
			hhmmss.append(this.ajustaHora(a.getSecond()));
			message += hhmmss;
			
			filtro.setHhmmss_420(hhmmss.toString());
			filtro.setMMDDhhmmss_420(filtro.getData() + hhmmss.toString());
			
			message	+= "005248192212";
			message += filtro.getData();
			message += filtro.getHhmmss_200();
			message += "123456009922005248TERMID019860200005216";
			
			message += filtro.getData();
			message += filtro.getHhmmss_200(); //aqui botar a hora do 200
			
			message += "0000012345600000000000021#102@0999#103@";
			message += filtro.getConta();
			
			byte[] bitmap = generateBitMap_2();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			//return "ERRO - Compra420 - " + e.getMessage();
			return HttpResponseDTO.fail("ERRO - Compra420 - " + e.getMessage());
			
		}
		//return "WebService - Compra420 executado !";
		return HttpResponseDTO.success("WebService - Compra420 executado !", "ret", filtro);
	}
	
		
	public HttpResponseDTO executaCompraUSD(FiltroDTO filtro) {
		
		StringBuilder hhmmss = new StringBuilder();

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			result2[0] = (byte) 0x00;
	        result2[1] = (byte) 0xD0;

			String messageType = "0200";
			
			String message = "101918394911002000000000008000000000008000";
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
			message += "599901206123456009916005216TERMID01CARD ACCEPTOR  CARD ACCEPTOR NAME/LOCATICITY NAME    BR840840021#102@0999#103@";
			message += filtro.getConta();
			
			byte[] bitmap = generateBitMap_1();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			//return "ERRO - CompraUSD - " + e.getMessage();
			return HttpResponseDTO.fail("ERRO - CompraUSD - " + e.getMessage());
		}
		//return "WebService - CompraUSD executado !";
		return HttpResponseDTO.success("WebService - CompraUSD executado !", "ret", filtro);
	}
	
	public static byte[] generateBitMap_1() throws Exception {
		byte[] result2 = new byte[16];
		result2[0] = (byte) 0xF6;
		result2[1] = (byte) 0x38;
		result2[2] = (byte) 0x44;
		result2[3] = (byte) 0x01;
		result2[4] = (byte) 0x08;
		result2[5] = (byte) 0xE0;
		result2[6] = (byte) 0xA0;
		result2[7] = (byte) 0x00;

		result2[8] = (byte) 0x00;
		result2[9] = (byte) 0x00;
		result2[10] = (byte) 0x00;
		result2[11] = (byte) 0x00;
		result2[12] = (byte) 0x00;
		result2[13] = (byte) 0x00;
		result2[14] = (byte) 0x01;
		result2[15] = (byte) 0x00;

		return result2;
	}
	
	public static byte[] generateBitMap_2() throws Exception {

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
