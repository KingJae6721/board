package yj.springboard.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yj.springboard.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
    
}
