package by.it.dsmobile.core.model;

import by.it.dsmobile.core.repository.converter.parameter.IdParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PassOrderType implements IdParameter {

    NEW(1, "Новый"),
    PROMOTION(2, "По акции"),
    REPEATEDLY(3, "Повторно");

    private final Integer id;
    private final String name;


}
