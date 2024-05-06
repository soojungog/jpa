package com.sujeong.jpa.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    /*
     * 지연로딩 => 테이블 조회 시 조인하는 member테이블을 조회하는 동시에 가져오지 않고 이 정보를 get하는 순간
     *            따로 테이블을 조회하는 방식 대신 Repository를 호출하는 Service에서 @Transaction 를 추가해야지만 
     *            필요한 순간에 DB를 재연결할 수 있음.
     */
    @ManyToOne(fetch = FetchType.LAZY) 
 	private Member writer;

}