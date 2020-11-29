package pl.sda.meetup.myownmeetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private LocalDate addingData;

    @Size(max = 500, message = "Komentarz może mieć maksymalnie 500 znaków")
    private String content;

    public CommentDto(LocalDate addingData) {
        this.addingData = LocalDate.now();
    }
}
