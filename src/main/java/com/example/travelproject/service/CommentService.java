package com.example.travelproject.service;

import com.example.travelproject.model.dto.CommentDto;
import java.util.List;

public interface CommentService {
    // 댓글 작성
    void saveComment(CommentDto dto);

    // 댓글 수정
    void updateComment(Long id, CommentDto dto);

    // 댓글 삭제
    void deleteComment(Long id);

    // ID로 댓글 조회
    CommentDto findCommentById(Long id);

    // 모든 댓글 조회
    List<CommentDto> findAllComments();
}
