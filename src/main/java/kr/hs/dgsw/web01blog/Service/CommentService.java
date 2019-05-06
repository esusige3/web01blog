package kr.hs.dgsw.web01blog.Service;


import kr.hs.dgsw.web01blog.Domain.Comment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommentService {
    Comment UploadComment(Comment comment);
    boolean ModComment(Long id,Comment comment);
    boolean DeleteComment(Long id);
    List<Comment> ViewComments();
    /////////////////////////////////////////////////////
    Comment get(Long writerId);
    void delete(Long postId);
}
