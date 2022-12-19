package com.perficient.rulesengine.mapper;

import com.perficient.rulesengine.dto.RegisterDTO;
import com.perficient.rulesengine.model.Register;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    Register fromDTO(RegisterDTO registerDTO);
    RegisterDTO fromRegister(Register register);
}
