package br.com.infnet.PetFriends.transporte.infra;


import br.com.infnet.PetFriends.transporte.domain.Endereco;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EnderecoConverter implements AttributeConverter<Endereco, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Endereco endereco) {
        if (endereco == null) return null;
        try {
            return mapper.writeValueAsString(endereco);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Erro ao serializar Endereco.", e);
        }
    }

    @Override
    public Endereco convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;
        try {
            return mapper.readValue(dbData, Endereco.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao desserializar Endereco.", e);
        }
    }
}
