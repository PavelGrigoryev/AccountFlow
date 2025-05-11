package com.grigoryev.accountflow.dto.user;

import com.grigoryev.accountflow.validation.ValidDateOfBirth;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSearchRequest(

        @Size(max = 500)
        @Schema(description = "Фильтр по имени (like '{text}%')", example = "Иван")
        String name,

        @ValidDateOfBirth
        @Schema(description = "Фильтр по дате рождения (больше указанной, формат dd.MM.yyyy)", example = "14.01.1990")
        String dateOfBirth,

        @Pattern(regexp = "[78]\\d{10}", message = "Phone number must be 11 digits starting with 7 or 8 (example: 79207865432)")
        @Schema(description = "Фильтр по телефону (точное совпадение)", example = "79109876543")
        String phone,

        @Email
        @Schema(description = "Фильтр по email (точное совпадение)", example = "ivan.ivanov@example.com")
        String email,

        @Min(0)
        @Schema(description = "Номер страницы (по умолчанию 0)", example = "0")
        Integer page,

        @Min(1)
        @Schema(description = "Размер страницы (по умолчанию 10)", example = "10")
        Integer size) {
}
