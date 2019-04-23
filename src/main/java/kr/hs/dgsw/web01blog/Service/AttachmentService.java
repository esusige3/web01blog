package kr.hs.dgsw.web01blog.Service;


import kr.hs.dgsw.web01blog.Domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AttachmentService {
//    boolean UploadProfile(MultipartFile file,Long id);
//    boolean UploadContentfile(MultipartFile file, Long id);
//    void DownloadProfile(HttpServletRequest request, HttpServletResponse response,Long id);
//    void DownloadContentPic(HttpServletRequest request, HttpServletResponse response,Long id);
    boolean UploadAttachemnt(MultipartFile file,Long postedId);
    void DownLoadAttachement(List<Attachment> attachmentList,HttpServletRequest request,HttpServletResponse response);

}
