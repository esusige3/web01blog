package kr.hs.dgsw.web01blog.Service;


import kr.hs.dgsw.web01blog.Domain.Comment;

import java.util.List;

public interface CommentService {
    boolean UploadComment(Comment comment);
    boolean ModComment(Long id,Comment comment);
    boolean DeleteComment(Long id);
    List<Comment> ViewComments();
}
