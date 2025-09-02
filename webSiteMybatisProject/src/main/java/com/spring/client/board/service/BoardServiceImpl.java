package com.spring.client.board.service;

import com.spring.client.board.mapper.BoardMapper;
import com.spring.client.board.vo.Board;
import com.spring.common.dto.RequestDTO;
import com.spring.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;

    /* @Override
    public List<Board> boardList(Board board) {
        List<Board> boardList = boardMapper.boardList(board);
        return boardList;
    }*/

    @Override
    public ResponseDTO<Board> list(RequestDTO requestDTO) {
        List<Board> boardList = boardMapper.boardList(requestDTO);
        ResponseDTO<Board> responseDTO = new ResponseDTO<Board>(boardList, requestDTO);
        return responseDTO;
    }

    @Override
    public int boardInsert(Board board) {
        int result = boardMapper.boardInsert(board);
        return result;
    }

    @Override
    public Board boardDetail(int boardNumber) {
        boardMapper.readCntUpdate(boardNumber);
        return boardMapper.boardDetail(boardNumber)
                .map(board -> {
                   board.setBoardContent(board.getBoardContent().replaceAll("\n","<br/>"));
                   return board;
                }).orElse(new Board());
    }

    // 글 수정 폼 구현
    @Override
    public Board updateForm(int boardNumber) {
        Board updateData = boardMapper.boardDetail(boardNumber).orElse(new Board());
        return updateData;
    }

    // 글 수정 구현
    @Override
    public int boardUpdate(Board board) {
        int result = boardMapper.boardUpdate(board);
        return result;
    }

    // 글 삭제 구현
    @Override
    public int boardDelete(int boardNumber) {
        int result = boardMapper.boardDelete(boardNumber);
        return result;
    }

    @Override
    public int pwdConfirm(Board board) {
        int result = boardMapper.pwdConfirm(board);
        return result;
    }
}
