package com.ecomerce.ms.service.order.infrastructure.mapper;

import com.ecomerce.ms.service.order.application.command.CreateCartCommand;
import com.ecomerce.ms.service.order.infrastructure.rest.model.CreateCartRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    public CreateCartCommand toCreateCartCommand(CreateCartRequest createCartRequest);
}
