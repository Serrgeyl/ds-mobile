package by.it.dsmobile.core.model;

import by.it.dsmobile.core.repository.converter.parameter.IdParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType implements IdParameter {

    ENTRY_BY_PASS(1, 0x00, "Проход по пропуску"),
    UNDEFINED(2, 0xFF, "Неизвестное событие");

    private final Integer id;
    private final Integer code;
    private final String description;

}
