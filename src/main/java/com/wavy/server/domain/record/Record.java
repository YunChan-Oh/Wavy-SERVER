package com.wavy.server.domain.record;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String imageUrl;

    private String place;

    private int star;

    private LocalDate recordedAt;

    @ElementCollection
    @CollectionTable(name = "record_emotions", joinColumns = @JoinColumn(name = "record_id"))
    @Column(name = "emotion")
    private List<String> emotions;

    @ElementCollection
    @CollectionTable(name = "record_hashtags", joinColumns = @JoinColumn(name = "record_id"))
    @Column(name = "hashtag")
    private List<String> hashtags;
}

