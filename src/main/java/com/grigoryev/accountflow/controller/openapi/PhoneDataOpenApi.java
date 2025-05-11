package com.grigoryev.accountflow.controller.openapi;

import com.grigoryev.accountflow.dto.DeleteResponse;
import com.grigoryev.accountflow.dto.phone.PhoneDataRequest;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;

public interface PhoneDataOpenApi {

    @Operation(summary = "Получение телефона по его ID")
    @Parameter(name = "id", description = "ID телефона", example = "1")
    ResponseEntity<PhoneDataResponse> findById(Long id);

    @Operation(summary = "Сохранение телефона с привязкой по USER_ID")
    @Parameter(name = "USER_ID", description = "Json Web Token в котором содержится UserID", example = "Jwt", in = ParameterIn.HEADER)
    ResponseEntity<PhoneDataResponse> save(PhoneDataRequest request);

    @Operation(summary = "Обновление только своего телефона по USER_ID")
    @Parameter(name = "id", description = "ID телефона", example = "1")
    @Parameter(name = "USER_ID", description = "Json Web Token в котором содержится UserID", example = "Jwt", in = ParameterIn.HEADER)
    ResponseEntity<PhoneDataResponse> update(Long id, PhoneDataRequest request);

    @Operation(summary = "Удаление только своего телефона по USER_ID")
    @Parameter(name = "id", description = "ID телефона", example = "1")
    @Parameter(name = "USER_ID", description = "Json Web Token в котором содержится UserID", example = "Jwt", in = ParameterIn.HEADER)
    ResponseEntity<DeleteResponse> delete(Long id);

}
