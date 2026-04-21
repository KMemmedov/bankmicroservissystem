package com.bank.cardservice.mapper;

import com.bank.cardservice.dto.CardRequestDto;
import com.bank.cardservice.dto.CardResponseDto;
import com.bank.cardservice.entity.Card;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")

public interface CardMapper {



    CardResponseDto toDto(Card request);

}
