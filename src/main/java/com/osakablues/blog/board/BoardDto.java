package com.osakablues.blog.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String date;

    public BoardDto(final BoardEntity boardEntity) {
        this.id = boardEntity.getId();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.date = boardEntity.getDate();
    }

    public static BoardEntity toEntity(final BoardDto boardDto) {
        return BoardEntity.builder()
                .id(boardDto.getId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .date(boardDto.getDate())
                .build();
    }
}
