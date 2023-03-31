package br.com.conam.desafiojsf.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;


public class ValidacaoBasica {
	
	/**
	 * Valida do CPF
	 * @param strCpf
	 * @return
	 */
	static public boolean validarCpf(String strCpf) {
		
		strCpf = strCpf.replace(".", "");
		strCpf = strCpf.replace("-", "");
		
		if(!isDigit(strCpf)){
			return false;
		}
		
		if(strCpf.equals("00000000000") || strCpf.equals("11111111111") || strCpf.equals("22222222222") || strCpf.equals("33333333333")
				|| strCpf.equals("44444444444") || strCpf.equals("55555555555") || strCpf.equals("66666666666")
				|| strCpf.equals("77777777777") || strCpf.equals("88888888888") || strCpf.equals("99999999999")){
			return false;
		}
		
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount))
					.intValue();

			// multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
			// e assim por diante.
			d1 = d1 + (11 - nCount) * digitoCPF;

			// para o segundo digito repita o procedimento incluindo o primeiro
			// digito calculado no passo anterior.
			d2 = d2 + (12 - nCount) * digitoCPF;
		}
		;

		// Primeiro resto da divisão por 11.
		resto = (d1 % 11);

		// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
		// menos o resultado anterior.
		if (resto < 2)
			digito1 = 0;
		else
			digito1 = 11 - resto;

		d2 += 2 * digito1;

		// Segundo resto da divisão por 11.
		resto = (d2 % 11);

		// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
		// menos o resultado anterior.
		if (resto < 2)
			digito2 = 0;
		else
			digito2 = 11 - resto;

		// Digito verificador do CPF que está sendo validado.
		String nDigVerific = strCpf.substring(strCpf.length() - 2,
				strCpf.length());

		// Concatenando o primeiro resto com o segundo.
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		// comparar o digito verificador do cpf com o primeiro resto + o segundo
		// resto.
		return nDigVerific.equals(nDigResult);
	}

	/**
	 * Validação do CNPJ
	 * @param str_cnpj
	 * @return
	 */
	public static boolean validarCnpj(String cnpj) {
		
		if (!cnpj.substring(0, 1).equals("")) {
            try {
                cnpj = cnpj.replace(".", " ");//onde há ponto coloca espaço
                cnpj = cnpj.replace("/", " ");//onde há barra coloca espaço
                cnpj = cnpj.replace("-", " ");//onde há traço coloca espaço
                cnpj = cnpj.replaceAll(" ", "");//retira espaço
                int soma = 0, dig;
                String cnpj_calc = cnpj.substring(0, 12);
                if (cnpj.length() != 14) {
                    return false;
                }
                char[] chr_cnpj = cnpj.toCharArray();
                /* Primeira parte */
                for (int i = 0; i < 4; i++) {
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                        soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                        soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
                    }
                }
                dig = 11 - (soma % 11);
                cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                        dig);
                /* Segunda parte */
                soma = 0;
                for (int i = 0; i < 5; i++) {
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                        soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                        soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
                    }
                }
                dig = 11 - (soma % 11);
                cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                        dig);
                return cnpj.equals(cnpj_calc);
            }
            catch (Exception e) {
                return false;
            }
        }
        else {
            return false;
        }
	} 
	
	/**
	 * Faz a conversão da senha para MD5
	 * @param valor
	 * @return
	 */
	public static String convertStringToMd5(String valor) { 
		MessageDigest mDigest; 
		try {  
			mDigest = MessageDigest.getInstance("MD5"); 
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8")); 
			StringBuffer sb = new StringBuffer(); 
			
			for (byte b : valorMD5){ 
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3)); 
			} 
			
			return sb.toString(); 
		} 
		catch (NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
			return null; 
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace(); 
			return null; 
		} 
	}
	
	/**
	 * Faz a validação do NIT
	 * @param strNit
	 * @return
	 */
	static public boolean validarNit(String number) {
		 String weight;   
		 int total;   
		 int c;   
		 int rest;   
		 int result;   
		 boolean r;
		    
		 weight = "3298765432";  // peso estabelecido para gerar NIT PIS PASEP    
		 total = 0;
		 c = 0;
		        
		 //--fazendo um uma pre-valida├º├úo do NIT PIS PASEP, valida├º├úo 1 se ├® nulo   
		 if (number == null){   
		     r = false;   
		     return r;     
		 }
		    
		 //--fazendo um uma pre-valida├º├úo do PIS PASEP, valida├º├úo 2 se o tamanho ├® diferente de 11 digitos    
		 if(number.length() != 11){   
			 return false;
		 }
		  
		 if(number.equals("00000000000")){
		     return false;
		 }
		 
		 while(c <= 9){
			 result = Integer.parseInt(number.substring(c, c + 1)) * Integer.parseInt(weight.substring(c, c + 1));
			 total = total + result;
			 c = c + 1;
		 }
		 
		 // dividindo, n├úo ├® necess├írio, j├í vai ser feito direto no resto da divisao, apenas para acompanhar o algoritmo    
		 result = total / 11;    
		 //-- calculando resto da divisao   
		 rest = total % 11;
		    
		 rest = 11 - rest;   
		    
		 if(rest == 10 || rest == 11){   
		     rest = 0;    
		 }
		      
		 if(rest != Integer.parseInt(number.substring(10)) ){   
		     r = false;
		 }
	     else {   
		     r = true;    
		 }
		        
		 return r;
	}
	
	/**
	 * Verifica se a string é numérica
	 * @param s
	 * @return
	 */
	static boolean isDigit(String s) {
	    return s.matches("[0-9]*");
	}
	
	/**
	 * Formata uma string
	 * @param pattern
	 * @param value
	 * @return
	 */
	static public String format(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}