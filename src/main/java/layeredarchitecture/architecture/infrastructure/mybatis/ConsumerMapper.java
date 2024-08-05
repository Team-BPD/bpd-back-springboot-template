package layeredarchitecture.architecture.infrastructure.mybatis;

import layeredarchitecture.architecture.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsumerMapper {

    List<Consumer> findAll();

}
