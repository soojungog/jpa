package com.sujeong.jpa.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujeong.jpa.project.dto.BoardDTO;
import com.sujeong.jpa.project.dto.PageRequestDTO;
import com.sujeong.jpa.project.dto.PageResultDTO;
import com.sujeong.jpa.project.entity.Board;
import com.sujeong.jpa.project.entity.Member;

@Service
public interface BoardService {

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO); // 목록처리
    
    default BoardDTO entityToDTO(Board board,Member member,Long replyCount){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) // long으로 나오므로 int로 처리
                .build();

        return boardDTO;
    }
}
