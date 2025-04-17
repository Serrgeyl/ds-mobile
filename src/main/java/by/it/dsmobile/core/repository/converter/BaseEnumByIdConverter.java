package by.it.dsmobile.core.repository.converter;

import by.it.dsmobile.core.exception.EnumConverterException;
import by.it.dsmobile.core.repository.converter.parameter.IdParameter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.stream.Stream;

@Converter
@AllArgsConstructor
public class BaseEnumByIdConverter<T extends Enum<T> & IdParameter> implements AttributeConverter<T, Integer> {

    private final Class<T> enumClass;

    @Override
    public Integer convertToDatabaseColumn(final T enumType) {
        return enumType.getId();
    }

    @Override
    public T convertToEntityAttribute(final Integer id) {
        return id == null
                ? null
                : Stream.of(enumClass.getEnumConstants())
                .filter(enumType -> Objects.equals(enumType.getId(), id))
                .findFirst()
                .orElseThrow(() -> new EnumConverterException("Failed to convert value [%s] to enum [%s]".formatted(id, enumClass)));
    }

}
