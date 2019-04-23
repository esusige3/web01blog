package kr.hs.dgsw.web01blog.Controller;


import kr.hs.dgsw.web01blog.Domain.Comment;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import kr.hs.dgsw.web01blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/view")
    public ResponseFormat ViewComments(){
        try{
            return new ResponseFormat(ResponseType.POST_GET, this.commentService.ViewComments());
        }catch (Exception e){
            return new ResponseFormat(ResponseType.FAIL,"Fail");
        }

    }

    /*@PostMapping("/comment/write")
    public ResponseFormat UploadComment(@RequestBody Comment comment){
        return new ResponseFormat(ResponseType.POST_ADD, this.commentService.UploadComment(comment));
    }*/

    @PutMapping("/comment/modi/{id}")
    public ResponseFormat ModComment(@PathVariable Long id,@RequestBody Comment comment){
        try{
            return new ResponseFormat(ResponseType.POST_UPDATE,this.commentService.ModComment(id, comment));
        }catch (Exception e){
            return new ResponseFormat(ResponseType.FAIL,"Fail");
        }

    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseFormat DeleteComment(@PathVariable Long id){
        try{
            return new ResponseFormat(ResponseType.POST_DELETE, this.commentService.DeleteComment(id));
        }catch (Exception e){
            return new ResponseFormat(ResponseType.FAIL,"Fail");
        }

    }


}
