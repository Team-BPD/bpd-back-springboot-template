package layeredarchitecture.architecture.application;

import layeredarchitecture.architecture.domain.Consumer;
import layeredarchitecture.architecture.infrastructure.ConsumerRepository;
import layeredarchitecture.common.constants.ErrorCode;
import layeredarchitecture.common.dto.ConsumerDto;
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
class ConsumerServiceTest {

    @Mock
    private ConsumerRepository consumerRepository;

    @InjectMocks
    private ConsumerService consumerService;

    private String validId;

    private Consumer validConsumer;

    @BeforeEach
    void setUp() {
        validId = "sh.park";
        validConsumer = Consumer.builder()
                                .id(validId)
                                .name("상훈")
                                .age(33L)
                                .money(10000000L)
                                .build();
    }

    @Test
    @DisplayName("소비자 정보를 조회하고 반환합니다.")
    void shouldReturnConsumerInfoWhenConsumerExists() {
        // given
        when(consumerRepository.findById(validId)).thenReturn(Optional.of(validConsumer));

        // when
        ConsumerDto result = consumerService.getConsumerInfo(validId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("sh.park");
        assertThat(result.getName()).isEqualTo("상훈");
        assertThat(result.getAge()).isEqualTo(33L);
        assertThat(result.getMoney()).isEqualTo(10000000L);
    }

    @Test
    @DisplayName("소비자를 찾을 수 없는 경우 예외를 발생시킵니다.")
    void shouldThrowExceptionWhenConsumerNotFound() {
        // given
        when(consumerRepository.findById(validId)).thenReturn(Optional.empty());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () -> {
            consumerService.getConsumerInfo(validId);
        });

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.CONSUMER_NOT_FOUND);
    }

}
