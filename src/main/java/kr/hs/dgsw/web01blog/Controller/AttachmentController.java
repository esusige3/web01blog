package kr.hs.dgsw.web01blog.Controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import kr.hs.dgsw.web01blog.Domain.Comment;
import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Repository.CommentRepository;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import kr.hs.dgsw.web01blog.Service.AttachmentService;
import kr.hs.dgsw.web01blog.Service.CommentService;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Optional;

@RestController
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    /*@PostMapping("/attachment/upload/user/{id}")
    public boolean UploadProfile(@RequestPart MultipartFile file, @PathVariable Long id){
        return attachmentService.UploadProfile(file,id);
    }*/
    /*@PostMapping("/attachment/upload/comment/{id}")
    public boolean Uploadcontentfile(@RequestPart MultipartFile file,@PathVariable Long id){
        return attachmentService.UploadContentfile(file,id);
    }*/

   /* @GetMapping("/attachment/download/{mode}/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id, @PathVariable int mode){

        if(mode == 1){
            try {
                Optional<User> user  = this.userRepository.findById(id);
                User target =  user.get();

                //String filepath = "D://SpringDOC/uploaded2019/56/04/49fad13b-951d-44b4-a329-53184e294434_P.png";
                String filepath = target.getProfilePath();
                //String filename = "49fad13b-951d-44b4-a329-53184e294434_P.png";
                String filename = target.getFileName();

                File file = new File(filepath);
                if (file.exists() == false) return;

                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                if (mimeType == null) mimeType = "application/octet-stream";
                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
                response.setContentLength((int) file.length());

                InputStream is = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(is,response.getOutputStream());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else{
            try {
                Optional<Comment> user  = this.commentRepository.findById(id);
                Comment target =  user.get();

                //String filepath = "D://SpringDOC/uploaded2019/56/04/49fad13b-951d-44b4-a329-53184e294434_P.png";
                String filepath = target.getFilePath();
                //String filename = "49fad13b-951d-44b4-a329-53184e294434_P.png";
                String filename = target.getFileName();

                File file = new File(filepath);
                if (!file.exists()) return;

                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                if (mimeType == null) mimeType = "application/octet-stream";
                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
                response.setContentLength((int) file.length());

                InputStream is = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(is,response.getOutputStream());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }*/

}
