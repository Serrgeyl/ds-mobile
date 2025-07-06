package by.it.dsmobile.core.model;

import by.it.dsmobile.core.repository.converter.parameter.IdParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PassType implements IdParameter {

    EM_MARINE(1, "em-marine"),
    MIFARE(2, "mifare");

    public final Integer id;
    public final String name;

}
