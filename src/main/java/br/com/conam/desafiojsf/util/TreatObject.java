package br.com.conam.desafiojsf.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TreatObject {

	/**
	 * Gera um HASH unico do objeto passado.
	 * @param object
	 * @return
	 */
	public static String hash(Object object) {
		if (!(object instanceof Serializable)) {
			throw new IllegalArgumentException("Objeto [" + object + "] deve implementar Serializable.");
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		saveObject(object, out);
		try {
			byte[] data = out.toByteArray();
			return TreatString.md5(DatatypeConverter.printBase64Binary(data));
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

	private static void saveObject(Object object, OutputStream fileOut) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
			out.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

		}
	}
	
	
	public static String convertObjectToJson(Object object) {
        try {
    		ObjectMapper mapper = new ObjectMapper();
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    		mapper.setDateFormat(df);
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	
}
