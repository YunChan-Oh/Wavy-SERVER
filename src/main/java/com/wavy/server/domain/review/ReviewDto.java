package com.wavy.server.domain.review;

import lombok.Getter;
import lombok.Setter;

public class ReviewDto {

    @Getter
    @Setter
    public static class Request {
        private String content;
        private String imageUrl;
        private String place;

        public Review toEntity() {
            Review review = new Review();
            review.setContent(content);
            review.setImageUrl(imageUrl);
            review.setPlace(place);
            return review;
        }
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String content;
        private String imageUrl;
        private String place;

        public Response(Review review) {
            this.id = review.getId();
            this.content = review.getContent();
            this.imageUrl = review.getImageUrl();
            this.place = review.getPlace();
        }
    }
}
