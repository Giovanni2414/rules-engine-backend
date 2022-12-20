package com.perficient.rulesengine.mapper;

import com.perficient.rulesengine.dto.ColumnDTO;
import com.perficient.rulesengine.model.Column;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColumnMapper {

    Column fromDTO(ColumnDTO columnDTO);
    ColumnDTO fromColumn(Column column);
}
