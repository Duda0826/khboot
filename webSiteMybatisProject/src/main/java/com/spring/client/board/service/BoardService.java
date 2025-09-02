package com.spring.client.board.service;

import com.spring.client.board.vo.Board;
import com.spring.common.dto.RequestDTO;
import com.spring.common.dto.ResponseDTO;

import java.util.List;

public interface BoardService {
    /* public List<Board> boardList(Board board);  검색 포함 리스트 */
    public ResponseDTO<Board> list(RequestDTO requestDTO);
    public int boardInsert(Board board);
    public Board boardDetail(int boardNumber);
    public Board updateForm(int boardNumber);
    public int boardUpdate(Board board);
    public int boardDelete(int boardNumber);
    public int pwdConfirm(Board board);

}
