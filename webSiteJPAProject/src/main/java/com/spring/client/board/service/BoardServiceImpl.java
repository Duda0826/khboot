package com.spring.client.board.service;

import com.spring.client.board.domain.Board;
import com.spring.client.board.repository.BoardRepository;
import com.spring.common.dto.PageRequestDTO;
import com.spring.common.dto.PageResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    public final BoardRepository boardRepository;

    @Override
    public List<Board> boardList(Board board) {
        List<Board> boardList = (List<Board>) boardRepository.findAll();
        return boardList;
    }

    @Override
    public PageResponseDTO<Board> list(PageRequestDTO pageRequestDTO){
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1, //1페이지가 0이므로 주의
                pageRequestDTO.getSize(),
                Sort.by("no").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        //조회된 page 객체에서 실제 데이터 리스트만 추룰
        List<Board> boardList = result.getContent().stream().collect(Collectors.toList());
        long totalCount = result.getTotalElements();
        PageResponseDTO<Board> responseDTO = PageResponseDTO.<Board>withAll()
                .dtoList(boardList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        return responseDTO;
    }

    @Override
    public void boardInsert(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long no) {
        return boardRepository.findById(no)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다."));
    }

    @Override
    @Transactional
    public Board boardHitUpdate(Board board) {
        Board dataBoard = getBoard(board.getNo());
        dataBoard.setHit(dataBoard.getHit() + 1);
        boardRepository.save(dataBoard);
        return dataBoard;
    }

    @Override
    public Board boardDetail(Board board) {
        Board detail = boardHitUpdate(board);
        return detail;
    }

    @Override
    public void boardUpdate(Board board) {
        Optional<Board> boardOptional = boardRepository.findById(board.getNo());
        Board updateBoard = boardOptional.orElseThrow();

        updateBoard.setTitle(board.getTitle());
        updateBoard.setContent(board.getContent());
        if(!board.getFilename().isEmpty()) {
            updateBoard.setFilename(board.getFilename());
        }

        boardRepository.save(updateBoard);
    }

    @Override
    public void boardDelete(Board board) {
        boardRepository.deleteById(board.getNo());
    }
}
