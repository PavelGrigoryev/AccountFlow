package com.grigoryev.accountflow.controller.openapi;

import com.grigoryev.accountflow.dto.account.AccountResponse;
import com.grigoryev.accountflow.dto.account.TransferRequest;
import com.grigoryev.accountflow.dto.account.TransferResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;

public interface AccountOpenApi {

    @Operation(summary = "Получение счёта вместе с его владельцем")
    @Parameter(name = "id", description = "ID счёта", example = "1")
    ResponseEntity<AccountResponse> findWithUserById(Long id);

    @Operation(summary = "Трансфер денег от одного пользователя к другому")
    @Parameter(name = "USER_ID", description = "Json Web Token в котором содержится UserID", example = "Jwt", in = ParameterIn.HEADER)
    ResponseEntity<TransferResponse> transferMoney(TransferRequest request);

}
