package br.giraffus.converterjpa;

import br.giraffus.model.Estado;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoConverter implements AttributeConverter<Estado, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Estado estado) {
        return estado == null ? null : estado.getId();
    }

    @Override
    public Estado convertToEntityAttribute(Integer id) {
        return Estado.valueOf(id);
    }
}
