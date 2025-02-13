package com.wavy.server.domain.record;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class RecordDto {

    @Getter
    @Setter
    public static class Request {
        private String content;
        private String imageUrl;
        private LocalDate recordedAt;
        private String place;
        private int star;
        private List<String> emotions;
        private List<String> hashtags;

        public Record toEntity() {
            Record record = new Record();
            record.setContent(content);
            record.setImageUrl(imageUrl);
            record.setRecordedAt(recordedAt);
            record.setPlace(place);
            record.setStar(star);
            record.setEmotions(emotions);
            record.setHashtags(hashtags);
            return record;
        }
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String content;
        private String imageUrl;
        private LocalDate recordedAt;
        private String place;
        private int star;
        private List<String> emotions;
        private List<String> hashtags;

        public Response(Record record) {
            this.id = record.getId();
            this.content = record.getContent();
            this.imageUrl = record.getImageUrl();
            this.recordedAt = record.getRecordedAt();
            this.place = record.getPlace();
            this.star = record.getStar();
            this.emotions = record.getEmotions();
            this.hashtags = record.getHashtags();
        }
    }
}
