package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.request.EventDetailsRequest;
import by.it.dsmobile.api.dto.response.EventDetailsResponse;
import by.it.dsmobile.api.dto.response.EventSummary;
import by.it.dsmobile.core.exception.ValueNotFoundException;
import by.it.dsmobile.core.model.Event;
import by.it.dsmobile.core.model.PassType;
import by.it.dsmobile.core.repository.DeviceRepository;
import by.it.dsmobile.core.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

import static by.it.dsmobile.config.AppConstants.ZONE_ID;
import static by.it.dsmobile.config.AppConstants.ZONE_OFFSET;

@Service
@AllArgsConstructor
public class EventService {

    private static final int LEFT_ENTER = 1;
    private static final int RIGHT_ENTER = 2;

    private final EventRepository eventRepository;
    private final DeviceRepository deviceRepository;

    public List<EventSummary> retrieveEventsSummary(final List<Integer> ids) {
        return eventRepository.retrieveEventsSummary(ids);
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
        final var device = deviceRepository.findFirstByControllerAndAddress(event.getController(), event.getDeviceAddress())
                .orElseThrow(() -> new ValueNotFoundException("Device not found [controller id = %s, address = %s]".formatted(event.getController().getId(), event.getDeviceAddress())));

        final var entry = device.getLeftIsEntry() ? LEFT_ENTER : RIGHT_ENTER;

        final var eventDetailsResponse = new EventDetailsResponse();
        eventDetailsResponse.setTime(event.getFiredAt().atZoneSameInstant(ZONE_ID).toLocalTime());
        if (event.getDirection() == entry) {
            eventDetailsResponse.setPassType(PassType.IN);
        } else {
            eventDetailsResponse.setPassType(PassType.OUT);
        }
        return eventDetailsResponse;
    }

}
