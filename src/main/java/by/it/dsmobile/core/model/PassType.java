package by.it.dsmobile.core.model;

import lombok.Getter;

@Getter
public enum PassType {

    IN(1, "Вход"),
    OUT(2, "Выход");

    public final int id;
    public final String name;

    PassType(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

}
