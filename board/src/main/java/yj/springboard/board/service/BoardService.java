package yj.springboard.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yj.springboard.board.controller.BoardController;
import yj.springboard.board.entity.BoardEntity;
import yj.springboard.board.repository.BoardRepository;
@Service
public class BoardService {
    
    @Autowired
    private BoardRepository boardRepository;

    //BoardEntity 클래스가 담신 ENTITY 반환
    public List<BoardEntity> boardList(){
        return boardRepository.findAll();
    }

    //작성버튼을 => 데이터가 DB에 저장, 수정
    public void boardWrite(BoardEntity boardEntity){
        boardRepository.save(boardEntity);
    }

    //게시글을 검색해서 불러오기
    public BoardEntity boardView(Integer id){
        return boardRepository.findById(id).get();
    }
    
    public void boardDelete(Integer id)
    {
        boardRepository.deleteById(id);
    }
}
