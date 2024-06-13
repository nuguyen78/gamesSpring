package cz.mendelu.ea.domain.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryRequest {

    @NotNull
    private int id;

    @NotEmpty
    private String name;

    public void toCategory(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
