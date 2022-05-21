package com.datagi.teamcalendar.domain.notice;

import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.global.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Column(columnDefinition = "text")
    private String content;

    @NotNull
    @CreatedDate
    private LocalDateTime createdDatetime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User user;
}
