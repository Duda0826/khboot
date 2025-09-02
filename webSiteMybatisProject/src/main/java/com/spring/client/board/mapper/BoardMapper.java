package com.spring.client.board.mapper;

import com.spring.client.board.vo.Board;
import com.spring.common.dto.RequestDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    @Results(id = "boardResult", value= {
            @Result(property = "boardNumber", column = "b_num"),
            @Result(property = "boardName", column = "b_name"),
            @Result(property = "boardTitle", column = "b_title"),
            @Result(property = "boardContent", column = "b_content"),
            @Result(property = "boardPasswd", column = "b_pwd"),
            @Result(property = "boardDate", column = "b_date"),
            @Result(property = "boardReadcnt", column = "b_readcnt")
    })
    /* @Select("""
            SELECT b_num, b_name, b_title, to_char(b_date, 'YYYY-MM-DD') as b_date, b_readcnt
            FROM spring_board
            ORDER BY b_num desc
    """)
    public List<Board> boardList(Board board); 검색 포함 리스트 */
    @SelectProvider(type = BoardSqlProvider.class, method = "selectListQuery")
    public List<Board> boardList(RequestDTO requestDTO);

    @Insert("""
            INSERT INTO spring_board(b_num, b_name, b_title, b_content, b_pwd)
            VALUES(spring_board_seq.nextval, #{board.boardName}, #{board.boardTitle}, #{board.boardContent}, #{board.boardPasswd})
    """)
    public int boardInsert(@Param("board") Board board);

    @Update("UPDATE spring_board SET b_readcnt = b_readcnt + 1 WHERE b_num = #{boardNumber}")
    public int readCntUpdate(@Param("boardNumber") int boardNumber);

    @ResultMap("boardResult")
    @Select("""
            SELECT b_num, b_name, b_title, b_content, 
                   TO_CHAR(b_date, 'YYYY-MM-DD HH24:MI:SS') AS b_date, b_readcnt
            FROM spring_board
            WHERE b_num = #{boardNumber}   
    """)
    public Optional<Board> boardDetail(@Param("boardNumber") int boardNumber);

    @UpdateProvider(type = BoardSqlProvider.class, method="updateQuery")
    public int boardUpdate(Board board);

    @Delete("DELETE FROM spring_board WHERE b_num = #{boardNumber}")
    public int boardDelete(@Param("boardNumber") int boardNumber);

    @Select("""
        SELECT CASE WHEN EXISTS (
            SELECT 1 FROM spring_board
            WHERE b_num = #{boardNumber} AND b_pwd = #{boardPasswd}) THEN 1
            ELSE 0
            END AS state
        FROM dual
    """)
    public int pwdConfirm(Board board);
}
