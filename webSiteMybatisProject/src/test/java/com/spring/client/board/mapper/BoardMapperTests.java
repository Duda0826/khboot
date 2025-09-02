package com.spring.client.board.mapper;

import com.spring.client.board.vo.Board;
import com.spring.common.dto.RequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.Optional;

@SpringBootTest // 통합 테스트
@Slf4j          // 로그 찍는 객체 자동 생성
public class BoardMapperTests {
    @Autowired  // Mapper 인터페이스 주입 - DB 쿼리 실행 가능
    private BoardMapper boardMapper;

    /* @Test
    public void boardListTest() {
        Board board = null;     // 검색 조건 없이 전체 조회 목적
        boardMapper.boardList(board).stream().forEach(boardData -> log.info(boardData.toString()));
    }*/
    @Test
    public void boardListTest() {
        RequestDTO requestDTO = new RequestDTO();
        boardMapper.boardList(requestDTO).stream().forEach(boardData -> log.info(boardData.toString()));
    }

    @Test
    public void boardSearchListTest() {
        RequestDTO requestDTO = RequestDTO.builder().search("b_title").keyword("회사").build();
        boardMapper.boardList(requestDTO).stream().forEach(board -> log.info(board.toString()));
    }

    @Test
    public void boardInsertTest() {
        Board board = Board.builder()
                .boardName("아무개")
                .boardTitle("아무말")
                .boardContent("아무말아무말아무말 적음")
                .boardPasswd("1234").build();

        int count = boardMapper.boardInsert(board);
        log.info("입력 여부 : {}", count);
    }

    @Test
    public void readCntUpdateTest() {
        int boardNumber = 1;
        int count = boardMapper.readCntUpdate(boardNumber);
        log.info("수정된 행의 수: {}", count);
    }

    /*Optional<T>는 null이 될수도 있는 객체 T를 감싸는 래퍼 클래스로, null을 직접 다루지 않고도 안전하게 값의 존재 여부를 처리할 수 있게 해준다.*/
    @Test
    public void boardDetailTest() {
        int boardNumber = 1;
        Optional<Board> board = boardMapper.boardDetail(boardNumber);
        log.info("데이터 존재 여부: {}", board.isPresent());
        log.info("데이터 조회: {}", board.toString());
    }

    @Test
    public void boardUpdateTest() {
        /* 비밀번호 변경 */
        Board board = Board.builder()
                .boardTitle("용기가 필요할 때 수정~")
                .boardContent("당신이 할 수 있다고 믿든, 할 수 없다고 믿든, 믿는 대로 될 것이다.~")
                .boardPasswd("4321")
                .boardNumber(4).build();

        /* 비밀번호 변경하지 않을 때
        Board board = Board.builder()
                .boardTitle("용기가 필요할 때 수정~")
                .boardContent("당신이 할 수 있다고 믿든, 할 수 없다고 믿든, 믿는 대로 될 것이다.~")
                .boardNumber(4).build(); */

        int count = boardMapper.boardUpdate(board);
        log.info("게시판 수정된 행의 수: {}", count);
    }

    @Test
    public void boardDeleteTest() {
        int boardNumber = 4;
        log.info("게시판 삭제된 행의 수: {}", boardMapper.boardDelete(boardNumber));
    }

    /* 일치: 1, 불일치: 0*/
    @Test
    public void testPwdConfirm() {
        Board board = Board.builder().boardNumber(1).boardPasswd("0000").build();
        int result = boardMapper.pwdConfirm(board);
        log.info("결과: {}", result);
        log.info("일치여부: {}", ((result == 1)?"일치":"불일치"));
    }
}
