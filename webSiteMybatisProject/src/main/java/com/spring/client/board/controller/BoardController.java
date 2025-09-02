package com.spring.client.board.controller;

import com.spring.client.board.service.BoardService;
import com.spring.client.board.vo.Board;
import com.spring.common.dto.RequestDTO;
import com.spring.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    /*
    @GetMapping("/boardList")
    public String boardList(@ModelAttribute Board board, Model model) {
        List<Board> boardList = boardService.boardList(board);
        model.addAttribute("boardList", boardList);
        return "client/board/boardList";
    }*/

    @GetMapping("/boardList")
    public String boardList(Board board, RequestDTO requestDTO, Model model) {
        ResponseDTO<Board> boardList = boardService.list(requestDTO);
        model.addAttribute("boardList", boardList);
        return "client/board/boardList";
    }

    @GetMapping("/insertForm")
    public String insertForm(Board board) {
        return "client/board/insertForm";
    }

    @PostMapping("/boardInsert")
    public String boardInsert(Board board){
        boardService.boardInsert(board);
        return "redirect:/board/boardList";
    }

    @GetMapping("/{boardNumber}")
    public String boardDetail(@PathVariable int boardNumber, Model model) {
        Board detail = boardService.boardDetail(boardNumber);
        model.addAttribute("detail", detail);
        return "client/board/boardDetail";
    }

    @GetMapping(value="/updateForm")
    public String updateForm(@ModelAttribute Board board, Model model) {
        Board updateData = boardService.updateForm(board.getBoardNumber());
        model.addAttribute("updateData", updateData);
        return "client/board/updateForm";
    }

    /**************************************************************
     * 글수정 구현하기
     * @param  : Board
     * 참고 : RedirectAttributes 객체는 리다이렉트 시점(return "redirect:/경로")에
     * 한번만 사용되는 데이터를 전송할 수 있는 addFlashAttribute()라는 기능을 지원한다.
     * addFlashAttribute() 메서드는 브라우저까지 전송되기는 하지만,
     * URI상에는 보이지 않는 숨겨진 데이터의 형태로 전달된다.
     **************************************************************/
    @PostMapping("/boardUpdate")
    public String boardUpdate(@ModelAttribute Board board) {
        boardService.boardUpdate(board);
        return "redirect:/board/" + board.getBoardNumber();
    }

    @PostMapping(value="/boardDelete")
    public String boardDelete(@ModelAttribute Board board) {
        boardService.boardDelete(board.getBoardNumber());
        return "redirect:/board/boardList";
    }

    /**************************************************************
     * 비밀번호 확인
     * @param board: boardNumber-본인글 여부를 확인할 게시글 번호. boardPasswd-입력한 비밀번호
     * @return int로 result를 0 또는 1를 리턴할 수도 있고,
     *         String로 result 값을 "성공 or 실패, 일치 or 불일치"를 리턴할 수도 있다.(현재 문자열 리턴)
     * 참고 : @ResponseBody는 전달된 뷰를 통해서 출력하는 것이 아니라 HTTP Response Body에 직접 출력하는 방식.(ajax, fetch 요청시 사용)
     *       produces 속성은 지정한 미디어 타입과 관련된 응답을 생성하는데 사용한 실제 컨텐트 타입을 보장.
     **************************************************************/
    @ResponseBody
    @PostMapping(value="/pwdConfirm", produces = "text/plain; charset=UTF-8")
    public String pwdConfirm(Board board) {
        int result = boardService.pwdConfirm(board);
        return (result == 1) ? "일치" : "불일치";
    }
}
