package br.com.conam.desafiojsf.util;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class DefaultDate extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
		if (jp.getText() == null || jp.getText().length() == 0) {
			return null;
		}
	    return TreatDate.parseDate(jp.getText(), "yyyy-MM-dd HH:mm:ss");
	}
}