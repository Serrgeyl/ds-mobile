package by.it.dsmobile.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {

    ENTRY_BY_PASS(0x00, "Проход по пропуску"),
    UNDEFINED(0xFF, "Неизвестное событие");

    private final int code;
    private final String description;

    public static EventType fromCode(int code) {
        for (EventType eventType : EventType.values()) {
            if (eventType.code == code) {
                return eventType;
            }
        }
        return UNDEFINED;
    }

}
