package cz.mendelu.ea.domain.studio;

import cz.mendelu.ea.utils.exceptions.NotFoundException;
import cz.mendelu.ea.utils.response.ArrayResponse;
import cz.mendelu.ea.utils.response.ObjectResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("studio")
@Validated
public class StudioController {

    private final StudioService studioService;

    @Autowired
    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @GetMapping(value = "", produces = "application/json")
    @Valid
    public ArrayResponse<StudioResponse> getStudios() {
        return ArrayResponse.of(
                studioService.getAllStudios(),
                StudioResponse::new
        );
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Valid
    public ObjectResponse<StudioResponse> getStudioById(@PathVariable Integer id) {
        Studio studio = studioService
                .getStudioById(id)
                .orElseThrow(NotFoundException::new);
        return ObjectResponse.of(studio, StudioResponse::new);
    }

    @PostMapping(value = "", produces = "application/json")
    public Studio createStudio(@RequestBody Studio studio) {
        return studioService.createStudio(studio);
    }
}
