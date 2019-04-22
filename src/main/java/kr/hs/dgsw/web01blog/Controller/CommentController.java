package kr.hs.dgsw.web01blog.Controller;


import kr.hs.dgsw.web01blog.Domain.Comment;
import kr.hs.dgsw.web01blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/view")
    public List<Comment> ViewComments(){
        return this.commentService.ViewComments();
    }

    @PostMapping("/comment/write")
    public boolean UploadComment(@RequestBody Comment comment){
        return this.commentService.UploadComment(comment);
    }

    @PutMapping("/comment/modi/{id}")
    public boolean ModComment(@PathVariable Long id,@RequestBody Comment comment){
        return this.commentService.ModComment(id, comment);
    }

    @DeleteMapping("/comment/delete/{id}")
    public boolean DeleteComment(@PathVariable Long id){
        return this.commentService.DeleteComment(id);
    }


}
