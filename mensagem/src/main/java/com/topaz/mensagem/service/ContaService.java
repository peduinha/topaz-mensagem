package com.topaz.mensagem.service;

import java.net.Socket;

import org.springframework.stereotype.Service;

import com.mensagem.dto.FiltroDTO;

@Service
public class ContaService {

	public String consultaSaldo(FiltroDTO filtro) {

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			result2[0] = (byte) 0x00;
	        result2[1] = (byte) 0x95;

			String messageType = "0200";
			
			String message = "10191839490730200005071542080052801242080507601101211123456     010415005280TERMID01CARD ACCEPTOR  986986021#102@0999#103@";
			message += filtro.getConta();

			byte[] bitmap = generateBitMap_1();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			return "ERRO - ConsultaSaldo - " + e.getMessage();
		}
		return "WebService - ConsultaSaldo executado !";
	}
	
	public String consultaExtrato(FiltroDTO filtro) {

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			result2[0] = (byte) 0x00;
	        result2[1] = (byte) 0x95;

			String messageType = "0200";
			
			String message = "10191839490731200005221542080052801242080522601101211123456     010415005280TERMID01CARD ACCEPTOR  986986021#102@0999#103@";
			message += filtro.getConta();

			byte[] bitmap = generateBitMap_1();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			return "ERRO - ConsultaExtrato - " + e.getMessage();
		}
		return "WebService - ConsultaExtrato executado !";
	}
	
	public String retiro(FiltroDTO filtro) {

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			 result2[0] = (byte) 0x00;
		     result2[1] = (byte) 0xD0;
		        
			String messageType = "0200";
			
			String message = "101918394908002000000000008000000000008000";
			message += filtro.getData();
			message += "163520005216133519";
			message += filtro.getData();
			message += "601101206123456009916005216TERMID01CARD ACCEPTOR  CARD ACCEPTOR NAME/LOCATICITY NAME    BR986986021#102@0999#103@";
			message += filtro.getConta();

			byte[] bitmap = generateBitMap_2();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			return "ERRO - Retiro - " + e.getMessage();
		}
		return "WebService - Retiro executado !";
	}
	
	public String retiroPara420(FiltroDTO filtro) {

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			result2[0] = (byte) 0x00;
		    result2[1] = (byte) 0xD0;
		        
			String messageType = "0200";
			
			String message = "101918394908002000000000008000000000008000";
			message += filtro.getData();
			message += "163520005216133519";
			message += filtro.getData();
			message += "601101206123456009916005216TERMID01CARD ACCEPTOR  CARD ACCEPTOR NAME/LOCATICITY NAME    BR986986021#102@0999#103@";
			message += filtro.getConta();

			byte[] bitmap = generateBitMap_2();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			return "ERRO - RetiroPara420 - " + e.getMessage();
		}
		return "WebService - RetiroPara420 executado !";
	}
	
	public String retiro420(FiltroDTO filtro) {

		try {
			Socket socket = new Socket(filtro.getServidor(), filtro.getPorta());
			byte[] result2 = new byte[2];

			result2[0] = (byte) 0x00;
		    result2[1] = (byte) 0xB1;
		        
			String messageType = "0420";
			
			String message = "101918394908002000000000008000";
			message += filtro.getData();
			message += "222212005248192212";
			message += filtro.getData();
			message += "601106123456009922005248TERMID01986020000521605121635200000012345600000000000021#102@0999#103@";
			message += filtro.getConta();
			

			byte[] bitmap = generateBitMap_3();
			byte[] messageBytes = new byte[result2.length+messageType.getBytes().length+bitmap.length+message.getBytes().length];		
			System.arraycopy(result2, 0, messageBytes, 0, result2.length);
			System.arraycopy(messageType.getBytes(), 0, messageBytes, result2.length, messageType.getBytes().length);
			System.arraycopy(bitmap, 0, messageBytes, result2.length + messageType.getBytes().length, bitmap.length);
			System.arraycopy(message.getBytes(), 0, messageBytes, result2.length + messageType.getBytes().length + bitmap.length, message.getBytes().length);

			socket.getOutputStream().write(messageBytes);
			
			
		} catch (Exception e) {
			return "ERRO - Retiro420 - " + e.getMessage();
		}
		return "WebService - Retiro420 executado !";
	}
	
	public static byte[] generateBitMap_1() throws Exception {

		byte[] result2 = new byte[16];
 		result2[0] = (byte) 0xE2;
	    result2[1] = (byte) 0x38;
	    result2[2] = (byte) 0x44;
	    result2[3] = (byte) 0x01;
	    result2[4] = (byte) 0x08;
	    result2[5] = (byte) 0xC0;
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
	
}
