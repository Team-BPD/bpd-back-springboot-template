package layeredarchitecture.architecture.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import layeredarchitecture.architecture.application.ConsumerService;
import layeredarchitecture.architecture.presentation.response.ErrorResponse;
import layeredarchitecture.common.dto.ConsumerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(
        name = "고객 API",
        description = "고객 API"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerService consumerService;

    @Operation(
            summary = "고객 정보 조회",
            description = "고객 ID로 고객 정보를 조회하여 반환한다."
    )
    @ApiResponses(
            {@ApiResponse(
                    responseCode = "200",
                    description = "고객 정보 조회 성공",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"id\":\"psh\",\"name\":\"상훈\",\"age\":33,\"money\":10000000}"),
                            schema = @Schema(implementation = ConsumerDto.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "고객 정보 없음",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"type\": \"/errors/custom\",\"title\": \"Custom Error\",\"status\": 404,\"detail\": \"존재하지 않는 고객 ID 입니다.\",\"instance\": \"/consumers/psh\"}"),
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )}
    )
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ConsumerDto> getConsumer(@PathVariable String id) {
        ConsumerDto consumerDto = consumerService.getConsumerInfo(id);
        return ResponseEntity.ok(consumerDto);
    }

    @Operation(
            summary = "전체 고객 정보 조회",
            description = "전체 고객 정보를 조회하여 반환한다. (by myBatis)"
    )
    @GetMapping
    public ResponseEntity<List<ConsumerDto>> getConsumers() {
        List<ConsumerDto> consumerDto = consumerService.getConsumers();

        return ResponseEntity.ok(consumerDto);
    }

    @Operation(
            summary = "고객 회원 가입 조회",
            description = "회원 가입을 한다. (파라미터 유효성 검사용)"
    )
    @PostMapping
    public ResponseEntity<ConsumerDto> registerConsumer(@RequestBody @Validated ConsumerDto consumerDto) {
        return ResponseEntity.ok(consumerDto);
    }

}
