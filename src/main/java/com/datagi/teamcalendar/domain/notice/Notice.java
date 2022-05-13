package com.datagi.teamcalendar.domain.notice;

import com.datagi.teamcalendar.domain.user.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 100)
    private String title;

    @NotNull
    @Column(columnDefinition = "text")
    private String content;

    @NotNull
    private LocalDateTime createdDatetime;

    @ManyToOne
    @JoinColumn(name="writer_id")
    private User user;
}
