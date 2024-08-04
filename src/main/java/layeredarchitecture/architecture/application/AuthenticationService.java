package layeredarchitecture.architecture.application;

import layeredarchitecture.architecture.domain.JsonWebToken;
import layeredarchitecture.architecture.infrastructure.ClientSystemRepository;
import layeredarchitecture.common.constants.ErrorCode;
import layeredarchitecture.common.dto.AuthDto;
import layeredarchitecture.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientSystemRepository clientSystemRepository;

    private final JsonWebToken jwt;

    /**
     * 클라이언트 시스템 정보를 전달 받아 JWT 를 생성하고 반환한다.
     *
     * @param authDto 인증할 클라이언트 시스템 정보
     * @return String
     */
    @Transactional(readOnly = true)
    public String generatedJwt(AuthDto authDto) {
        String clientSystemId = authDto.getId();
        String clientSystemPassword = authDto.getPassword();

        String password = clientSystemRepository.findPasswordById(authDto.getId())
                                                .orElseThrow(() -> new CustomException(ErrorCode.CLIENT_SYSTEM_NOT_FOUND));

        if (!clientSystemPassword.equals(password)) {
            throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCHED);
        }

        return jwt.generateToken(clientSystemId);
    }

}
