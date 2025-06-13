package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.request.EventDetailsRequest;
import by.it.dsmobile.api.dto.response.EventDetailsResponse;
import by.it.dsmobile.api.dto.response.EventSummary;
import by.it.dsmobile.core.model.Event;
import by.it.dsmobile.core.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static by.it.dsmobile.core.util.AppConstants.ZONE_ID;
import static by.it.dsmobile.core.util.AppConstants.ZONE_OFFSET;

@Service
@AllArgsConstructor
public class EventService {

    public static final String START_OF_SCHOOL_YEAR = "-09-01";

    private final EventRepository eventRepository;

    public List<EventSummary> retrieveEventsSummary(final List<Integer> ids, final int page, final int size) {
        final var startDate = getStartDate();
        return eventRepository.retrieveEventsSummary(ids, startDate, size, page * size);
    }

    public List<EventDetailsResponse> retrieveEventsDetails(final EventDetailsRequest eventDetails) {
        final var start = OffsetDateTime.of(eventDetails.getDate().atStartOfDay(), ZONE_OFFSET);
        final var end = OffsetDateTime.of(eventDetails.getDate().plusDays(1).atStartOfDay(), ZONE_OFFSET);

        return eventRepository
                .findAllByUserIdAndFiredAtBetween(eventDetails.getUserId(), start, end)
                .stream()
                .map(this::toEventDetailsResponse)
                .toList();
    }

    private EventDetailsResponse toEventDetailsResponse(final Event event) {
        final var eventDetailsResponse = new EventDetailsResponse();
        eventDetailsResponse.setTime(event.getFiredAt().atZoneSameInstant(ZONE_ID).toLocalTime());
        eventDetailsResponse.setPassType(event.getEventEntryType());
        return eventDetailsResponse;
    }

    private LocalDate getStartDate() {
        final var now = LocalDate.now();
        LocalDate startDate = LocalDate.parse(now.getYear() + START_OF_SCHOOL_YEAR);
        if (now.isBefore(startDate)) {
            startDate = startDate.minusYears(1);
        }
        return startDate;
    }

}
