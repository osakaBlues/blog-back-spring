package com.osakablues.blog.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("boards")
public class BoardController {

    final private BoardService boardService;
    // crud
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardDto boardDto) {
        log.info("Create A Board!: {}", boardDto.getTitle());
        return ResponseEntity
                .ok(boardService.saveBoard(boardDto));
    }

    @GetMapping
    public ResponseEntity<?> getBoards() {
        log.info("Get All Boards!");
        return ResponseEntity
                .ok(boardService.getBoards());
    }
//
//    @PutMapping("/{id}")
//    public BoardDto updateBoard() {
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBoard() {
//        return null;
//    }


}
