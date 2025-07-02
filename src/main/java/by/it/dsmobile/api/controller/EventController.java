package by.it.dsmobile.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import by.it.dsmobile.api.dto.request.EventDetailsRequest;
import by.it.dsmobile.api.dto.response.EventDetailsResponse;
import by.it.dsmobile.api.dto.response.EventSummary;
import by.it.dsmobile.core.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/events")
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@AllArgsConstructor
@Validated
@Tag(name = "Получение событий")
public class EventController {

    private final EventService eventService;

    @GetMapping(value = "/summary")
    @Operation(summary = "Получение сгруппированных по дням событий")
    public List<EventSummary> getSummary(
            @RequestParam("ids") final List<Integer> ids,
            @RequestParam("page") final int page,
            @RequestParam("size") final int size
    ) {
        return eventService.retrieveEventsSummary(ids, page, size);
    }

    @GetMapping(value = "/summary/count")
    @Operation(summary = "Получение количества сгруппированных элементов")
    public int getSummaryCount(@RequestParam("id") final int id) {
        return eventService.getEventsSummaryCount(id);
    }

    @GetMapping(value = "/details")
    @Operation(summary = "Получение событий за указанную дату")
    public List<EventDetailsResponse> getEvents(@Valid @NotNull final EventDetailsRequest eventDetails) {
        return eventService.retrieveEventsDetails(eventDetails);
    }

}
