package yj.springboard.board.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import yj.springboard.board.entity.BoardEntity;
import yj.springboard.board.service.BoardService;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @RequestMapping(value="/board/list")
    public String boardlist(Model model) {
        
        model.addAttribute("list", boardService.boardList());

        return "boardlist";
    }


    @GetMapping("/board/write")
    public String boardWriteForm(){

        return "boardwrite";
    }

    //작성버튼 누르면 동작
    @PostMapping("/board/writepro")
    public String boardWrite(BoardEntity boardEntity, Model model) {
        boardService.boardWrite(boardEntity);
        //model.addAttribute("list", boardService.boardList());
        return "redirect:/board/list";
    }

    //게시글 상세페이지 보여주기
    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    //게시글 삭제
    @Transactional
    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {

        boardService.boardDelete(id);
        return "redirect:/board/list";
    }
    

    //게시글 수정 페이지 보여주기
    @GetMapping("/board/modify/{id}")
    public String boardModify(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, BoardEntity boardEntity ) {

        BoardEntity boardTemp = boardService.boardView(id);
        boardTemp.setTitle(boardEntity.getTitle());
        boardTemp.setContent(boardEntity.getContent());
        boardTemp.setContent(boardEntity.getContent());
        boardTemp.setUpdDate(new Date());

        boardService.boardWrite(boardTemp);
        
        return "redirect:/board/list";
    }
    
    
    
}
