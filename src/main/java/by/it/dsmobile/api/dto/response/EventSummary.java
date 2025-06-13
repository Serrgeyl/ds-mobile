package by.it.dsmobile.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import static by.it.dsmobile.core.util.AppConstants.ZONE_ID;

@Getter
public class EventSummary {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private final Integer userId;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime inTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime outTime;

    private final Integer count;

    public EventSummary(Date date, Integer userId, Date inTime, Date outTime, Integer count) {
        this.date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.userId = userId;
        this.count = count;
        if (inTime != null) {
            this.inTime = inTime.toInstant().atZone(ZONE_ID).toLocalTime().withNano(0);
        }
        if (outTime != null) {
            this.outTime = outTime.toInstant().atZone(ZONE_ID).toLocalTime().withNano(0);
        }
    }

}
