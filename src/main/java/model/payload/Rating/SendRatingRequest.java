package model.payload.Rating;

public record SendRatingRequest(Long userId, Long gameId, int stars, String description) {

}
