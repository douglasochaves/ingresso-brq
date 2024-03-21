package br.com.brq.brqingresso.dataprovider.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum GeneroEnum {

    MASCULINO("M", 1),
    FEMININO("F", 2),
    NAO_BINARIO("B", 3),
    PREFIRO_NAO_INFORMAR("N", 4);

    private String letra;
    private Integer numero;

    public static Integer obterGenero(String letra) {
        for(GeneroEnum genero : GeneroEnum.values()) {
            if(genero.letra.equals(letra)) {
                return genero.numero;
            }
        }
        return null;
    }

    public static String obterGenero(Integer numero) {
        for(GeneroEnum genero : GeneroEnum.values()) {
            if(genero.letra.equals(numero)) {
                return genero.letra;
            }
        }
        return null;
    }
}
