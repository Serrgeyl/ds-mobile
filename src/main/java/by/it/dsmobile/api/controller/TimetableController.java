package by.it.dsmobile.api.controller;

import by.it.dsmobile.api.dto.request.TimetableSearchCriteria;
import by.it.dsmobile.api.dto.response.TimetableResponse;
import by.it.dsmobile.core.service.TimetableService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/timetable")
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@AllArgsConstructor
@Validated
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping
    public List<TimetableResponse> getTimetable(@Valid @NotNull final TimetableSearchCriteria searchCriteria) {
        return timetableService.getTimetable(searchCriteria);
    }

}
