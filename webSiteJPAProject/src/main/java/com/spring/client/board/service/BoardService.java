package com.spring.client.board.service;

import com.spring.client.board.domain.Board;

import java.util.List;

public interface BoardService {
    public List<Board> boardList(Board board);
    public void boardInsert(Board board);
    public Board getBoard(Long no);
    public Board boardHitUpdate(Board board);
    public Board boardDetail(Board board);
    public void boardUpdate(Board board);
    public void boardDelete(Board board);
}
