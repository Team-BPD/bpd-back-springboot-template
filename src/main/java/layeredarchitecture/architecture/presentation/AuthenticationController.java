package layeredarchitecture.architecture.presentation;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import layeredarchitecture.architecture.application.AuthenticationService;
import layeredarchitecture.architecture.presentation.response.AuthResponse;
import layeredarchitecture.architecture.presentation.response.ErrorResponse;
import layeredarchitecture.common.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(
        name = "JWT API",
        description = "JWT 관련 API 입니다."
)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * 인증 정보를 전달 받아 JWT 를 생성하고 반환한다.
     *
     * @param authDto 인증 정보
     * @return ResponseEntity<AuthResponse>
     */
    @Operation(
            summary = "JWT 발급",
            description = "JWT 를 발급 받는다."
    )
    @ApiResponses(
            {@ApiResponse(
                    responseCode = "200",
                    description = "JWT 발급 성공",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"jwt\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJHUkVFTk5FVCIsImlhdCI6MTcyMjkyMzI1MSwiZXhwIjoxNzIyOTI2ODUxfQ.vl_MWoO5afqupgp-WcLioglI1DIKc-Dm7tuIDHbIU6n3MfUQibSmC_4GMS011ik8LAOaPVDwEnGksrdO7lxbPg\"}"),
                            schema = @Schema(implementation = Json.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "클라이언트 시스템 없음",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"type\":\"/errors/custom\",\"title\":\"Custom Error\",\"status\":404,\"detail\":\"존재하지 않는 클라이언트 시스템 입니다.\",\"instance\":\"/auth\"}"),
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ), @ApiResponse(
                    responseCode = "401",
                    description = "아이디 또는 비밀번호 불일치",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"type\":\"/errors/custom\",\"title\":\"Custom Error\",\"status\":401,\"detail\":\"아이디 또는 비밀번호가 일치하지 않습니다.\",\"instance\":\"/auth\"}"),
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )}
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> generatedJwt(@RequestBody @Validated AuthDto authDto) {
        String jwt = authenticationService.generatedJwt(authDto);

        return ResponseEntity.ok(AuthResponse.builder()
                                             .jwt(jwt)
                                             .build());
    }

    /**
     * 토큰 정보가 유효한지 확인하여 반환한다.
     *
     * @param authHeader 인증 정보
     * @return ResponseEntity<AuthResponse>
     */
    @Operation(
            summary = "JWT 유효성 확인",
            description = "JWT 유효성을 확인한다."
    )
    @ApiResponses(
            {@ApiResponse(
                    responseCode = "200",
                    description = "JWT 유효"
            )}
    )
    @GetMapping(
            value = "/check",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> validatedJwt(@RequestHeader("Authorization") String authHeader) {
        authenticationService.validatedJwt(authHeader);

        return ResponseEntity.ok()
                             .build();
    }

}
