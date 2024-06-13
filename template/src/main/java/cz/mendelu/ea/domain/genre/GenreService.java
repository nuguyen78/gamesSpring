package cz.mendelu.ea.domain.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        genreRepository.findAll().forEach(genres::add);
        return genres;
    }

    public Optional<Genre> findGenreById(Integer id) {
        return genreRepository.findById(id);
    }
}
