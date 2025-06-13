package by.it.dsmobile.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConstants {

    public static final String SHORT_TYPE = "int2";
    public static final String ENUM_ID_TYPE = "int2";
    public static final String JSONB_TYPE = "jsonb";

    public static final ZoneId ZONE_ID = ZoneId.of("Europe/Minsk");
    public static final ZoneOffset ZONE_OFFSET = ZONE_ID.getRules().getOffset(Instant.now());

    // Minutes
    public static final int SECURITY_CODE_EXPIRATION_TIME = 10;

}
