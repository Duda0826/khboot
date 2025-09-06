package com.spring.client.board.repository;

import com.spring.client.board.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Slf4j
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    /* 게시판 등록 - save(): 주어진 엔티티를 저장 */
    @Test
    public void boardInsertTest(){
        Board board = new Board();
        board.setName("늘한봄");
        board.setTitle("노력 명언");
        board.setContent("집에 가고 싶다");
        board.setRegDate(LocalDateTime.now());

        log.info("### board 테이블에 첫번째 데이터 입력");
        boardRepository.save(board);

        Board board1 = new Board();
        board1.setName("아몰랑");
        board1.setTitle("끈기 명언");
        board1.setContent("집까지 열심히 가고싶다");
        board1.setRegDate(LocalDateTime.now());

        log.info("### board 테이블에서 두번째 데이터 입력");
        boardRepository.save(board1);

        Board board2 = new Board();
        board2.setName("아아알앍");
        board2.setTitle("명언");
        board2.setContent("아침에 일찍 일어나는 새가 피곤하다");

        log.info("### board 테이블에 세번째 데이터 입력");
        boardRepository.save(board2);

        Board board3 = new Board();
        board3.setName("강강강");
        board3.setTitle("언어 명언");
        board3.setContent("늦었다고 생각할 때가 가장 늦었다.");

        log.info("### board 테이블에 네번째 데이터 입력");
        boardRepository.save(board3);
    }

    /* 게시판 전체 레코드 수 구하기 - count(): 사용가능한 엔티티 수를 반환 */
    @Test
    public void boardCountTest() {
        long boardCount = boardRepository.count();
        log.info("레코드 수: {}", boardCount);
    }

    /* 게시판 리스트 - findAll(): T타입의 모든 인스턴스를 반환.*/
    @Test
    public void boardListTest() {
        List<Board> boardList = (List<Board>) boardRepository.findAll();
        boardList.forEach(board -> log.info(board.toString()));
    }

    /* 게시판 상세 조회 - findById(Id id) : ID로 엔티티를 검색 */
    @Test
    public void boardDetailTest(){
        Optional<Board> boardOptional = boardRepository.findById(1L);
        // isPresent() 메소드를 사용하여 Optional 객체에 저장된 값이 null인지 아닌지 먼저 확인
        if(boardOptional.isPresent()){
            Board board = boardOptional.get();
            log.info("1번글의 데이터: {}", board);
        }

        boardRepository.findById(1L)
                .ifPresent(board ->
                        log.info("데이터확인 : {}", board));
        boardRepository.findById(1L).ifPresentOrElse(
                board -> log.info("데이터: {}", board),
                () -> log.info("데이터 없음")
        );
    }

    /* 이코드는 JUnit 테스트에서 사용되는 단정문(assertion)으로,
    boardOptional.isPresent() 가 false여야 테스트가 통과한다는 뜻.
    즉, boardOptional에 값이 없어야(null이어야) 이 테스트는 성공이다. */
    @Test
    public void boardNotFoundTest(){
        Optional<Board> boardOptional = boardRepository.findById(9L);
        assertFalse(boardOptional.isPresent());//JUnit 사용
    }

    /* 게시판 수정 - findById() 메서드로 데이터를 가져와서 변경할 필드만 설정해 주면 된다. */
    @Test
    public void boardUpdateTest() {
        Optional<Board> boardOptional = boardRepository.findById(2L);

        if(boardOptional.isPresent()) {
            Board board =  boardOptional.get();
            board.setTitle("힘들때 힘이 되는 명언");
            board.setContent("조급해 하지 말고 조바심내지 말고, 할 수 있는 만큼 최선을 다하자.");

            log.info("### board 테이블에 데이터 수정");
            boardRepository.save(board);
        }
    }

    /* 게시판 삭제 - deleteById(ID id) : 주어진 ID를 가진 엔티티 삭제 */
    @Test
    public void boardDeleteTest(){
        boardRepository.findById(4L);
    }

    /* 게시판 제목, 이름, 내용 검색 */
    @Test
    public void findByTitleContainingTest() {
//        Board titleSearch = boardRepository.findByTitle("노력 명언");
//        log.info(titleSearch.toString());

        //제목검색
        List<Board> list = boardRepository.findByTitleContaining("노력");
        //이름검색
//        List<Board> list = boardRepository.findByNameContaining("강강강");
        //내용검색
//        List<Board> list = boardRepository.findByContentContaining("vz");

        // 등록일검색
//        log.info(LocalDateTime.now().minusDays(2).toString());
//        log.info(LocalDateTime.now().toString());
//        List<Board> list = boardRepository.findByRegDateBetween(LocalDateTime.now().minusDays(2), LocalDateTime.now());

        list.forEach(board -> log.info(board.toString()));
    }

    /* 게시판 번호 순 내림차순 정렬 - 정렬조건(OrderByNoDesc)이 검색조건에 추가되고 리스트(List) 형태로 조회결과를 반환. */
    @Test
    public void findByOrderByNoDescTest() {
        // findByOrderByNoDesc(): 내림차순으로 번호 정력
        List<Board> boardList = boardRepository.findByOrderByNoDesc();
        boardList.forEach(board -> log.info(board.toString()));
    }

    @Test
    public void findAllByPageAndSort() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "no");
        Page<Board> boardPage = boardRepository.findAll(pageRequest);
        boardPage.forEach(board -> log.info(board.toString()));
    }

    @Test
    public void findByTitleContainingWithPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "no");
        Page<Board> result = boardRepository.findByTitleContaining("노력", pageable);
        result.forEach(board -> log.info(board.toString()));
    }

    @Test
    public void boardAllInsertTest(){
        for (int i = 0; i <= 100; i++){
            Board board = new Board();
            board.setTitle("Title..." + i);
            board.setName("홍길동" + i);
            board.setContent("실패한 자가 패배하는 것이 아니라 포기한 자가 패배하는 것이다.");
            boardRepository.save(board);
        }
    }
}
