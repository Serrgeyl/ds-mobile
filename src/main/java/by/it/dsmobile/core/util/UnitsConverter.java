package by.it.dsmobile.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UnitsConverter {

    public static Long parseDoubleToMoneyUnits(Double value) {
        if (value == null) {
            return null;
        }

        return BigDecimal
                .valueOf(value * 100)
                .setScale(0, RoundingMode.HALF_UP)
                .longValue();
    }

    public static Double parseMoneyUnitsToDouble(Long value) {
        if (value == null) {
            return null;
        }

        return BigDecimal
                .valueOf((double) value / 100)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
