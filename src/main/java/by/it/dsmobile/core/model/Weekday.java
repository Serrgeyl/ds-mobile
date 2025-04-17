package by.it.dsmobile.core.model;

import by.it.dsmobile.core.repository.converter.parameter.IdParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Weekday implements IdParameter {

    MONDAY(1, "Понедельник"),
    TUESDAY(2, "Вторник"),
    WEDNESDAY(3, "Среда"),
    THURSDAY(4, "Четверг"),
    FRIDAY(5, "Пятница"),
    SATURDAY(6, "Суббота"),
    SUNDAY(7, "Воскресенье");

    private final int id;
    private final String name;

    @Override
    public Integer getId() {
        return this.id;
    }
}
