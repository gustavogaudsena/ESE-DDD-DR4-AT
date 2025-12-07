package br.com.infnet.PetFriends.almoxarifado.infra;

import br.com.infnet.PetFriends.almoxarifado.domain.Localizacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter(autoApply = true)
public class LocalizacaoConverter implements AttributeConverter<Localizacao, String> {

    @Override
    public String convertToDatabaseColumn(Localizacao localizacao) {
        if (localizacao == null) return null;
        return localizacao.getLongitude() + "," + localizacao.getLatitude();
    }

    @Override
    public Localizacao convertToEntityAttribute(String s) {
        if (s == null || s.isBlank()) return null;

        List<String> data = List.of(s.split(","));

        double longitude = Double.parseDouble(data.get(0));
        double latitude = Double.parseDouble(data.get(1));

        return new Localizacao(longitude, latitude);
    }
}
