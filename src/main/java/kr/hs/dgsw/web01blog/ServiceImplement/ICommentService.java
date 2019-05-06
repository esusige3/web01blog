package kr.hs.dgsw.web01blog.ServiceImplement;

import kr.hs.dgsw.web01blog.Domain.Comment;
import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Repository.CommentRepository;
import kr.hs.dgsw.web01blog.Service.AttachmentService;
import kr.hs.dgsw.web01blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ICommentService implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public Comment UploadComment(Comment comment) {
        try {


            return this.commentRepository.save(comment);

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Comment> ViewComments() {

            List<Comment> CommentList = this.commentRepository.findAll();
            return CommentList;
    }

    @Override
    public boolean ModComment(Long id, Comment comment) {
        Optional<Comment> find = this.commentRepository.findById(id);
        if(find.isPresent()){
            Comment found = find.get();
            found.setTitle(comment.getTitle());
            found.setContent(comment.getContent());
            this.commentRepository.save(found);
            return true;
        }
        return false;
    }

    @Override
    public boolean DeleteComment(Long id) {
        try{
            this.commentRepository.delete(this.commentRepository.findById(id).get());
            return true;
        }catch (Exception e){
            return false;
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Comment get(Long writerId) {
        return this.commentRepository.findTopByWriterIdOrderByIdDesc(writerId).orElse(null);
    }

    @Override
    public void delete(Long postId) {
        this.commentRepository.deleteById(postId);
    }
}
