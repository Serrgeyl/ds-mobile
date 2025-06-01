package by.it.dsmobile.core.model;

import by.it.dsmobile.core.repository.converter.parameter.IdParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PassOrderStatusType implements IdParameter {

    NEW(1, "Новый"),
    PAYED(2, "Оплачен"),
    COMPLETED(3, "Выполнен");

    private final Integer id;
    private final String name;


}
