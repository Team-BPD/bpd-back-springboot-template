package layeredarchitecture.architecture.application;

import layeredarchitecture.architecture.infrastructure.ConsumerRepository;
import layeredarchitecture.common.constants.ErrorCode;
import layeredarchitecture.common.dto.ConsumerDto;
import layeredarchitecture.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    /**
     * 소비자 정보 조회
     *
     * @param id 소비자 ID
     * @return ConsumerDto
     */
    @Transactional(readOnly = true)
    public ConsumerDto getConsumerInfo(String id) {
        return consumerRepository.findById(id)
                                 .map(consumer -> ConsumerDto.builder()
                                                             .id(consumer.getId())
                                                             .name(consumer.getName())
                                                             .age(consumer.getAge())
                                                             .money(consumer.getMoney())
                                                             .build())
                                 .orElseThrow(() -> new CustomException(ErrorCode.CONSUMER_NOT_FOUND));
    }

}
