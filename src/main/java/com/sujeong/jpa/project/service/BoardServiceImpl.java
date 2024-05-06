package com.sujeong.jpa.project.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sujeong.jpa.project.dto.BoardDTO;
import com.sujeong.jpa.project.dto.PageRequestDTO;
import com.sujeong.jpa.project.dto.PageResultDTO;
import com.sujeong.jpa.project.entity.Board;
import com.sujeong.jpa.project.entity.Member;
import com.sujeong.jpa.project.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    @Autowired
    private final BoardRepository repository; //자동주입 final

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Function<Object[],BoardDTO> fn = (en -> entityToDTO((Board)en[0],(Member)en[1],(Long)en[2]));

        // Repository에서 바로 DTO로 받고 싶음
        Page<Object[]> result = repository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        List<BoardDTO> dtoList = result.stream().map(fn).collect(Collectors.toList());  // page로 반환하였는가? 어쨌던가....

        return new PageResultDTO<>(result,fn);
    }
}