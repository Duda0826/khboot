package com.spring.client.board.mapper;

import com.spring.client.board.vo.Board;
import com.spring.common.dto.RequestDTO;

/* 본인글 여부 확인을 제외한 수정 및 삭제를 위한 코드 작성 */
public class BoardSqlProvider {
    public String updateQuery(Board board) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE spring_board SET b_title = #{boardTitle}, ");
        sql.append("b_content = #{boardContent} ");
        if(board.getBoardPasswd() != null && !board.getBoardPasswd().isEmpty()) {
            sql.append(", b_pwd = #{boardPasswd} ");
        }
        sql.append("WHERE b_num = #{boardNumber}");

        return sql.toString();
    }

    public String selectListQuery(RequestDTO requestDTO) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b_num, b_name, b_title, to_char(b_date, 'YYYY-MM-DD') AS b_date, b_readcnt ");
        sql.append("FROM spring_board ");

        String condition = "";
        if("b_title".equals(requestDTO.getSearch())) {
            condition = "b_title LIKE '%' || #{keyword} || '%'";
        }else if("b_content".equals(requestDTO.getSearch())) {
            condition = "b_content LIKE '%' || #{keyword} || '%'";
        }else if("b_name".equals(requestDTO.getSearch())) {
            condition = "b_name LIKE '%' || #{keyword} || '%'";
        }

        if(!condition.isEmpty()) {
            sql.append("WHERE ").append(condition).append(" ");
        }
        sql.append("ORDER BY b_num DESC");

        return sql.toString();
    }
}
