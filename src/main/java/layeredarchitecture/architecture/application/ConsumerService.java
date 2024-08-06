package layeredarchitecture.architecture.application;

import layeredarchitecture.architecture.domain.Consumer;
import layeredarchitecture.architecture.infrastructure.ConsumerRepository;
import layeredarchitecture.architecture.infrastructure.mybatis.ConsumerMapper;
import layeredarchitecture.common.constants.ErrorCode;
import layeredarchitecture.common.dto.ConsumerDto;
import layeredarchitecture.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    private final ConsumerMapper consumerMapper;

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

    /**
     * 전체 소비자 정보 조회
     *
     * @return List<ConsumerDto>
     */
    @Transactional(readOnly = true)
    public List<ConsumerDto> getConsumers() {
        List<Consumer> consumers = consumerMapper.findAll();

        if (consumers.isEmpty()) {
            throw new CustomException(ErrorCode.CONSUMERS_NOT_FOUND);
        }

        return consumers.stream()
                        .map(consumer -> ConsumerDto.builder()
                                                    .id(consumer.getId())
                                                    .name(consumer.getName())
                                                    .age(consumer.getAge())
                                                    .money(consumer.getMoney())
                                                    .build())
                        .collect(Collectors.toList());
    }

}
