package cz.mendelu.ea.domain.studio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudioService {

    private final StudioRepository studioRepository;

    @Autowired
    public StudioService(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public List<Studio> getAllStudios() {
        List<Studio> studios = new ArrayList<>();
        studioRepository.findAll().forEach(studios::add); // must convert iterable to list
        return studios;
    }

    public Studio createStudio(Studio studio) {
        return studioRepository.save(studio);
    }

    public Optional<Studio> getStudioById(Integer id) {
        return studioRepository.findById(id);
    }


}