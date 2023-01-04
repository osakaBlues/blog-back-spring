package com.osakablues.blog.board;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter {
    private final ModelMapper modelMapper = new ModelMapper();

    public BoardDto convert2Dto(BoardEntity entity) {
        return modelMapper.map(entity, BoardDto.class);
    }

    public BoardEntity convert2Entity(BoardDto dto) {
        return modelMapper.map(dto, BoardEntity.class);
    }
}
