package by.it.dsmobile.core.model;

import by.it.dsmobile.core.repository.converter.parameter.IdParameter;
import lombok.Getter;

@Getter
public enum PlatformType implements IdParameter {

    IOS(1, "ios"),
    ANDROID(2, "android"),
    WEB(3, "web"),
    UNKNOWN(0, "Неизвестная платформа");

    public final Integer id;
    public final String description;

    PlatformType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

}
