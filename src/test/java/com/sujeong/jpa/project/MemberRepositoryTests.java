package com.sujeong.jpa.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sujeong.jpa.project.dto.BoardDTO;
import com.sujeong.jpa.project.dto.PageRequestDTO;
import com.sujeong.jpa.project.dto.PageResultDTO;
import com.sujeong.jpa.project.entity.Board;
import com.sujeong.jpa.project.entity.Member;
import com.sujeong.jpa.project.entity.Reply;
import com.sujeong.jpa.project.repository.BoardRepository;
import com.sujeong.jpa.project.repository.MemberRepository;
import com.sujeong.jpa.project.repository.ReplyRepository;
import com.sujeong.jpa.project.service.BoardService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ReplyRepository replyRepository;

    @Test // Member 객체 100개  생성
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .email("user" + i + "@aaa.com")
                    .password("1111")
                    .name("USER" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test // 게시글 100개 생성 -> 한 명의 사용자가 하나의 게시물 등록하도록
    public void insertBoard(){

        IntStream.rangeClosed(1,100).forEach(i -> {
            Member writer = Member.builder().email("user" + i + "@aaa.com").build();

            Board board = Board.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer(writer)
                    .build();

            boardRepository.save(board);
        });
    } 


    @Test // 임의의 게시글을 대상으로 댓글추가(300개)
    public void insertReply(){

        IntStream.rangeClosed(1,300).forEach(i -> {
            // 1부터 100까지 임의의 번호
            long bno = (long)(Math.random() * 100) + 1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply...." + i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);

        });
    }

    @Test
    public void testReadWithWriter(){
        Object result = boardRepository.getBoardWithWriter(100L);

        Object[] arr = (Object[]) result;

        System.out.println("---------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testGetBoardWithReply(){

        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testWithReplyCount(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            
            System.out.println(Arrays.toString(arr));
        });

    }

    @Autowired
    private BoardService boardService;

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO : result.getDtoList()) {
            System.out.println(boardDTO);
        }
    }


}