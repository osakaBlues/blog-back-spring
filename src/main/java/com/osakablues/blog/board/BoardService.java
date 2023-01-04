package com.osakablues.blog.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    final private BoardRepository boardRepository;
    final private BoardConverter boardConverter;

    public BoardDto saveBoard(final BoardDto boardDto) {
        return boardConverter.convert2Dto(
                boardRepository.save(BoardDto.toEntity(boardDto))
        );
    }

    public List<BoardDto> getBoards() {
        return boardRepository.findAll().stream()
                .map(boardConverter::convert2Dto)
                .collect(Collectors.toList());
    }
}
