package layeredarchitecture.architecture.presentation;

import layeredarchitecture.architecture.application.AuthenticationService;
import layeredarchitecture.architecture.domain.JsonWebToken;
import layeredarchitecture.architecture.presentation.response.AuthResponse;
import layeredarchitecture.common.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JsonWebToken jwt;

    private final AuthenticationService authenticationService;

    /**
     * 인증 정보를 전달 받아 JWT 를 생성하고 반환한다.
     *
     * @param authDto 인증 정보
     * @return ResponseEntity<AuthResponse>
     */
    @PostMapping
    public ResponseEntity<AuthResponse> generatedJwt(@RequestBody AuthDto authDto) {

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
    @GetMapping("/check")
    public ResponseEntity<AuthResponse> getId(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        boolean isValid = jwt.isTokenValid(token);

        return ResponseEntity.ok(AuthResponse.builder()
                                             .isValid(isValid)
                                             .build());
    }

}
