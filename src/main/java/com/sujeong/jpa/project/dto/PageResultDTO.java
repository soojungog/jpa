package com.sujeong.jpa.project.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

// 화면에서 필요한 결과는 PageResultDTO라는 이름으로 생성한다
@Data
// 다양한 곳에서 사용할 수 있도록 제네릭 타입을 이용해 DTO 와 EN(entity) 이라는 타입을 지정한다
public class PageResultDTO<DTO,EN>{

    // DTO리스트
    private List<DTO> dtoList;

    // 총 페이지 번호
    private int totalPage;

    // 현재 페이지 번호
    private int page;

    // 목록 사이즈
    private int size;

    // 시작페이지,끝페이지 번호
    private int start,end;

    // 이전, 다음
    private boolean prev, next;

    // 페이지 번호 목록
    private List<Integer> pageList;

    // Function<EN,DTO> : 엔티티 객체들을 DTO로 변환해주는 기능
    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber() + 1 ; // 0부터 시작하므로 1을 더해준다
        this.size = pageable.getPageSize();

        // temp end page
        // 끝번호를 미리 계산하는 이유 : 시작번호 계산 수월하게 하기위해
        int tempEnd = (int)(Math.ceil(page / 10.0)) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd : totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }

}