package pl.sda.meetup.myownmeetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private Long id;

    @NotNull(message = "Pole nie może być puste")
    @NotBlank(message = "Pole nie może zawierać samych pustych znaków")
    private String title;

    @NotNull(message = "Pole nie może być puste")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate from;

    @NotNull(message = "Pole nie może być puste")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate to;

    @Size(min =5, message = "Opis musi mieć przynajmniej 20 znaków.")
    private String description;
}
