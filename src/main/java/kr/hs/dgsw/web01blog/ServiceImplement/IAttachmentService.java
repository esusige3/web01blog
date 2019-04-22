package kr.hs.dgsw.web01blog.ServiceImplement;

import kr.hs.dgsw.web01blog.Domain.Comment;
import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Repository.CommentRepository;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import kr.hs.dgsw.web01blog.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class IAttachmentService implements AttachmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean UploadProfile(MultipartFile file, Long id) {

        Optional<User> find = this.userRepository.findById(id);
        if(find.isPresent())
        {
            try {
                User target = find.get();
                String fileName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
                String destFilename = "D://web01blog/profiles"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd/"))
                        + fileName;

                File destFile = new File(destFilename);
                destFile.getParentFile().mkdirs();
                file.transferTo(destFile);

                target.setProfilePath(destFilename);
                target.setFileName(fileName);
                this.userRepository.save(target);
                return true;
            }
            catch (Exception ex){
                return false;
            }

        }
        return false;

    }

    @Override
    public boolean UploadContentfile(MultipartFile file, Long id) {

        Optional<Comment> find = this.commentRepository.findById(id);
        if(find.isPresent())
        {
            try {
                Comment target = find.get();
                String fileName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
                String destFilename = "D://web01blog/contentPics"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd/"))
                        + fileName;

                File destFile = new File(destFilename);
                destFile.getParentFile().mkdirs();
                file.transferTo(destFile);

                target.setFilePath(destFilename);
                target.setFileName(fileName);
                this.commentRepository.save(target);
                return true;
            }
            catch (Exception ex){
                return false;
            }

        }

        return false;
    }


    @Override
    public void DownloadProfile (HttpServletRequest request, HttpServletResponse response,Long id){

            try {
                Optional<User> user  = this.userRepository.findById(id);
                User target =  user.get();

                //String filepath = "D://SpringDOC/uploaded2019/56/04/49fad13b-951d-44b4-a329-53184e294434_P.png";
                String filepath = target.getProfilePath();
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

    @Override
    public void DownloadContentPic(HttpServletRequest request, HttpServletResponse response,Long id) {
        try {
            Optional<Comment> comment  = this.commentRepository.findById(id);
            Comment target =  comment.get();

            //String filepath = "D://SpringDOC/uploaded2019/56/04/49fad13b-951d-44b4-a329-53184e294434_P.png";
            String filepath = target.getFilePath();
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
}

