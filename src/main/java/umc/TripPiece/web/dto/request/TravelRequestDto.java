package umc.TripPiece.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class TravelRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String cityName;
        private String countryName;
        @Size(max = 15, message = "제목은 15자 이내")
        private String title;
        private LocalDate startDate;
        private LocalDate endDate;
    }

    @Getter
    public static class MemoDto {

        @NotBlank
        String description;
    }

    @Getter
    public static class EmojiDto {
        @Size(min = 4, max = 4)
        List<String> emojis;

        @NotBlank
        String description;
    }

    @Getter
    @Setter
    public static class UpdateRequestDto {
        private MultipartFile thumbnail;
        @Size(max = 15, message = "제목은 15자 이내")
        private String title;
        @Schema(description = "yyyy-mm-dd")
        private LocalDate startDate;
        @Schema(description = "yyyy-mm-dd")
        private LocalDate endDate;
    }

}
