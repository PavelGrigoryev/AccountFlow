package com.grigoryev.accountflow.controller.openapi;

import com.grigoryev.accountflow.dto.DeleteResponse;
import com.grigoryev.accountflow.dto.email.EmailDataRequest;
import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;

public interface EmailDataOpenApi {

    @Operation(summary = "Получение почты по его ID")
    @Parameter(name = "id", description = "ID почты", example = "1")
    ResponseEntity<EmailDataResponse> findById(Long id);

    @Operation(summary = "Сохранение почты с привязкой по USER_ID")
    @Parameter(name = "USER_ID", description = "Json Web Token в котором содержится UserID", example = "Jwt", in = ParameterIn.HEADER)
    ResponseEntity<EmailDataResponse> save(EmailDataRequest request);

    @Operation(summary = "Обновление только своей почты по USER_ID")
    @Parameter(name = "id", description = "ID почты", example = "1")
    @Parameter(name = "USER_ID", description = "Json Web Token в котором содержится UserID", example = "Jwt", in = ParameterIn.HEADER)
    ResponseEntity<EmailDataResponse> update(Long id, EmailDataRequest request);

    @Operation(summary = "Удаление только своей почты по USER_ID")
    @Parameter(name = "id", description = "ID почты", example = "1")
    @Parameter(name = "USER_ID", description = "Json Web Token в котором содержится UserID", example = "Jwt", in = ParameterIn.HEADER)
    ResponseEntity<DeleteResponse> delete(Long id);

}
