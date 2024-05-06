package com.sujeong.jpa.project.dto;

import java.time.LocalDateTime;

import com.sujeong.jpa.project.entity.Board;
import com.sujeong.jpa.project.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bno;

    private String title;

    private String content;

    private String writerEmail; // 작성자의 이메일(id)

    private String writerName; // 작성자의 이름

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private int replyCount; // 해당 게시글의 댓글 수

    // 방법 1 간단한 2개의 테이블의 조인이라면 BoardDTO에서 바로 엔티티로 생성하여서 insert하는 방식으로 해도 간단함
    public Board toEntity(){ 
        Member member = Member.builder()
                            .email(writerEmail)
                            .build();

        Board board = Board.builder()
                        .bno(bno)
                        .title(title)
                        .content(content)
                        .writer(member)
                        .build();

        return board;
    }
}
