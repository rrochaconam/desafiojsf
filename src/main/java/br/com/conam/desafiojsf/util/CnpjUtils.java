package br.com.conam.desafiojsf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * Utilitário para validação de CNPJ.
 */
public final class CnpjUtils {

	/**
     * Tamanho do CNPJ (desconsiderando os pontos e traÃ§o).
     */
    private static final int TAMANHO = 14;

    /**
     * Divisor das somatórias para validação do resto.
     */
    private static final int DV_11 = 11;

    /**
     * Multiplicadores para validação do primeiro dí­gito do CPF.
     */
    private static final int[] MULTIPLICADORES_VALIDACAO_PRIMEIRO_DIGITO = new int[] {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    /**
     * Multiplicadores para validação do segundo dí­gito do CPF.
     */
    private static final int[] MULTIPLICADORES_VALIDACAO_SEGUNDO_DIGITO = new int[] {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    /**
     * Construtor privado para classe utilitária.
     */
    private CnpjUtils() {

    }

    /**
     * Verifica se o CNPJ é válido.
     * <ul>
     * <li>Verifica o tamanho - 14 caracteres sem a máscara</li>
     * <li>Verifica se é numérico, descontando-se a máscara</li>
     * <li>Verifica se os dígitos verificadores são válidos</li>
     * </ul>
     * @param cnpjBruto CNPJ no formato XX.XXX.XXX/XXXX-XX ou XXXXXXXXXXXXXX.
     * @return {@code true} se o CNPJ for válido, {@code false} caso contrário.
     */
    public static boolean isCnpj(final String cnpjBruto) {
        if (StringUtils.isBlank(cnpjBruto)) {
            return false;
        }

        String cnpj = cnpjBruto.trim().replace(".", "").replace("-", "").replace("/", "");
        if (cnpj.length() != TAMANHO || !StringUtils.isNumeric(cnpj)) {
            return false;
        }

        String cnpjSemDigitos = cnpj.substring(0, TAMANHO - 2);
        int primeiroDigito = Integer.parseInt(cnpj.substring(TAMANHO - 2, TAMANHO - 1));

        String cnpjSemUltimoDigito = cnpj.substring(0, TAMANHO - 1);
        int segundoDigito = Integer.parseInt(cnpj.substring(TAMANHO - 1, TAMANHO));

        return validaPrimeiroDigito(cnpjSemDigitos, primeiroDigito) && validaSegundoDigito(cnpjSemUltimoDigito, segundoDigito);
    }

    /**
     * Formata um CNPJ.
     * @param doc documento CNPJ a ser formatado
     * @return um CNPJ formatado
     */
    public static String formatarCNPJ(final String doc) {
        String cnpj = StringUtils.defaultString(doc);
        Pattern patternFormatado = Pattern.compile("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}\\-\\d{2}$");
        Matcher matcherFormatado = patternFormatado.matcher(cnpj);
        if (!matcherFormatado.matches()) {
            cnpj = cnpj.replaceAll("\\p{Punct}", "");
            Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
            Matcher matcher = pattern.matcher(cnpj);
            if (matcher.matches()) {
                cnpj = matcher.replaceAll("$1.$2.$3/$4-$5");
            }
        }

        return StringUtils.defaultIfBlank(cnpj, null);
    }

    /**
     * Num CNPJ do tipo XX.XXX.XXX/XXXX-AB, valida o dígito A.
     * @param cnpjSemDigitos String contendo o CNPJ, sem máscara, sem os dí­gitos AB
     * @param primeiroDigito o dígito A em formato inteiro
     * @return {@code true} se o dígito A for válido, {@code false} caso contrário.
     */
    private static boolean validaPrimeiroDigito(final String cnpjSemDigitos, final int primeiroDigito) {
        int soma = 0;
        for (int i = 0; i < TAMANHO - 2; i++) {
            soma += Integer.parseInt(Character.toString(cnpjSemDigitos.charAt(i))) * MULTIPLICADORES_VALIDACAO_PRIMEIRO_DIGITO[i];
        }

        int resto = DV_11 - soma % DV_11;
        if (resto >= 10) {
            resto = 0;
        }

        return resto == primeiroDigito;
    }

    /**
     * Num CNPJ do tipo XX.XXX.XXX/XXXX-AB, valida o dí­gito B.
     * @param cnpjSemUltimoDigito String contendo o CNPJ, sem máscara, sem o dí­gito B
     * @param segundoDigito o dí­gito B em formato inteiro
     * @return {@code true} se o dígito B for válido, {@code false} caso contrário.
     */
    private static boolean validaSegundoDigito(final String cnpjSemUltimoDigito, final int segundoDigito) {
        int soma = 0;
        for (int i = 0; i < TAMANHO - 1; i++) {
            soma += Integer.parseInt(Character.toString(cnpjSemUltimoDigito.charAt(i))) * MULTIPLICADORES_VALIDACAO_SEGUNDO_DIGITO[i];
        }

        int resto = DV_11 - soma % DV_11;
        if (resto >= 10) {
            resto = 0;
        }

        return resto == segundoDigito;
    }
}
