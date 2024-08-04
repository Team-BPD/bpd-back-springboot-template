package layeredarchitecture.architecture.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JsonWebTokenTest {

    private JsonWebToken jsonWebToken;

    private final String secret = "d8f7e5b5e0d1f2b5d4c3e2f7c1d5a2b8c5f2d3c8d5e3a5c2b6d4e8f7c5d2a5e0b1c7d3f4c1e2d7b8a3c5f6";

    private final long expiration = 3600000; // 1 hour

    private final String clientId = "GREENNET";

    @BeforeEach
    void setUp() {
        jsonWebToken = new JsonWebToken(secret, expiration);
    }

    @Test
    @DisplayName("JWT를 생성하고 반환합니다.")
    void shouldGenerateToken() {
        // when
        String token = jsonWebToken.generateToken(clientId);

        // then
        assertThat(token).isNotNull();
        assertThat(token.split("\\.").length).isEqualTo(3);
    }

    @Test
    @DisplayName("JWT에서 ID를 추출합니다.")
    void shouldExtractIdFromToken() {
        // given
        String token = jsonWebToken.generateToken(clientId);

        // when
        String extractedId = jsonWebToken.extractId(token);

        // then
        assertThat(extractedId).isEqualTo(clientId);
    }

    @Test
    @DisplayName("JWT의 유효성을 확인합니다.")
    void shouldValidateToken() {
        // given
        String token = jsonWebToken.generateToken(clientId);

        // when
        boolean isValid = jsonWebToken.isTokenValid(token);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("유효하지 않은 JWT의 유효성을 확인합니다.")
    void shouldInvalidateInvalidToken() {
        // given
        String invalidToken = "invalid.token.value";

        // when
        boolean isValid = jsonWebToken.isTokenValid(invalidToken);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("만료된 JWT의 유효성을 확인합니다.")
    void shouldInvalidateExpiredToken() throws InterruptedException {
        // Given
        JsonWebToken jwt = new JsonWebToken(secret, 1); // 만료시간 1ms
        String token = jwt.generateToken(clientId);

        Thread.sleep(2);

        // When
        boolean isValid = jwt.isTokenValid(token);

        // Then
        assertThat(isValid).isFalse();
    }

}
