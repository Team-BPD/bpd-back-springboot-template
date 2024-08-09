package layeredarchitecture.architecture.application;

import layeredarchitecture.architecture.domain.JsonWebToken;
import layeredarchitecture.architecture.dto.AuthDto;
import layeredarchitecture.architecture.infrastructure.ClientSystemRepository;
import layeredarchitecture.common.constants.ErrorCode;
import layeredarchitecture.common.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private ClientSystemRepository clientSystemRepository;

    @Mock
    private JsonWebToken jwt;

    @InjectMocks
    private AuthenticationService authenticationService;

    private AuthDto validAuthDto;

    @BeforeEach
    void setAuthDto() {
        validAuthDto = AuthDto.builder()
                              .name("GREENNET")
                              .password("1234")
                              .build();
    }

    @Test
    @DisplayName("클라이언트 시스템 정보를 전달받고, JWT를 생성하여 반환한다.")
    void shouldGenerateJwtWhenValidClientSystemInfoProvided() {
        // Given
        when(clientSystemRepository.findPasswordByName(validAuthDto.getName())).thenReturn(Optional.of("1234"));
        when(jwt.generateToken(validAuthDto.getName())).thenReturn("token");

        // When
        String token = authenticationService.generatedJwt(validAuthDto);

        // Then
        assertThat(token).isEqualTo("token");
    }

    @Test
    @DisplayName("클라이언트 시스템 정보를 찾을 수 없는 경우 예외를 발생시킨다.")
    void shouldThrowExceptionWhenClientSystemNotFound() {
        // Given
        when(clientSystemRepository.findPasswordByName(validAuthDto.getName())).thenReturn(Optional.empty());

        // When
        CustomException exception = assertThrows(CustomException.class, () -> authenticationService.generatedJwt(validAuthDto));

        // Then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.CLIENT_SYSTEM_NOT_FOUND);
    }

    @Test
    @DisplayName("클라이언트 시스템의 아이디와 패스워드가 일치하지 않은 경우 예외를 발생시킨다.")
    void shouldThrowExceptionWhenIdPasswordNotMatched() {
        // Given
        when(clientSystemRepository.findPasswordByName(validAuthDto.getName())).thenReturn(Optional.of("4321"));

        // When
        CustomException exception = assertThrows(CustomException.class, () -> authenticationService.generatedJwt(validAuthDto));

        // Then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.ID_PASSWORD_NOT_MATCHED);
    }

}
