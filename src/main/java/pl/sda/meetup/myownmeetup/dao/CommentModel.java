package pl.sda.meetup.myownmeetup.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime addingData;

    @Size(max = 500)//TODO czy to jest tu potzrebne jeśli jest na DTO i czym się różni w obsłudze
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventModel eventModel;



}
