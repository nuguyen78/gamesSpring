package cz.mendelu.ea.domain.review;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewResponse {

    @NotNull
    private int id;

    @NotEmpty
    private String text;

    @NotNull
    private int gameId;

    @NotEmpty
    private Date createdAt;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.text = review.getText();
        this.gameId = review.getGame().getId();
        this.createdAt = review.getCreatedAt();
    }
}
