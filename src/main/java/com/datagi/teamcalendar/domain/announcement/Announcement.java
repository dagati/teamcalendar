package com.datagi.teamcalendar.domain.announcement;


import com.datagi.teamcalendar.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 100)
    private String title;

    @NotNull
    @Length(max = 2000)
    private String content;

    @NotNull
    private LocalDateTime createdDatetime;

    @ManyToOne
    @JoinColumn(name="user-id")
    private User user;

    @Builder
    public Announcement(Long id, String title, String content, LocalDateTime createdDatetime, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDatetime = createdDatetime;
        this.user = user;
    }
}
