package com.sujeong.jpa.project.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 화면에서 전달되는 목록 관련된 데이터에 대한 DTO를 PageRequestDTO 라는 이름으로 생성
// 목록 페이지를 요청할 때 사용하는 데이터를 재사용하기 쉽게 만든다
// -> 파라미터를 DTO로 선언하고 나중에 재사용하는 용도
@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO { // 목적 : JPA쪽에서 사용하는 Pageable 타입의 객체를 생성하는 것
    // 화면에서 절달되는 page,size 라는 파라미터를 수집한다
    private int page;
    private int size;

    // 검색 처리를 위해 추가
    private String type;
    private String keyword;


    public PageRequestDTO(){
        // 페이비 번호 등은 기본값을 가지는것이 좋기때문에 1과10이라는 값을 지정한다
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
                            // 페이지 번호가 0부터 시작한다는 점을 감안해 1페이지의 경우 0이 될수 있도록 page -1로 작성해준다
                            // 정렬은 다양한 상황에서 쓰기위해 별도의 파라미터로 받도록 설계
        return PageRequest.of(page -1, size,sort);
    }
}