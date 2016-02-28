package br.edu.fa7.cursojsf.model.converter;

import br.edu.fa7.cursojsf.util.Cep;

import javax.persistence.AttributeConverter;

public class CepConverter implements AttributeConverter<Cep, String> {

    @Override
    public String convertToDatabaseColumn(Cep attribute) {
        if (attribute != null)
            return attribute.toString();
        return null;
    }

    @Override
    public Cep convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            String[] split = dbData.split("-");
            if (split.length == 2) {
                return new Cep(split[0], split[1]);
            } else {
                System.out.println("Cep(" + dbData + ") no formato invalido, nao foi possivel converter.");
            }
        }
        return null;
    }

}
