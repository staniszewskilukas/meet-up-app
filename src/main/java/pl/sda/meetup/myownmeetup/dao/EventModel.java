package pl.sda.meetup.myownmeetup.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "event")
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(name = "from_date")
    private LocalDate from;

    @Column(name = "to_date")
    private LocalDate to;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @OneToMany
    private Set<CommentModel> commentModels;
}
