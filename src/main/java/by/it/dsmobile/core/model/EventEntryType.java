package by.it.dsmobile.core.model;

import by.it.dsmobile.core.repository.converter.parameter.IdParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventEntryType implements IdParameter {

    IN(1, "Вход"),
    OUT(2, "Выход");

    private final Integer id;
    private final String name;

}
