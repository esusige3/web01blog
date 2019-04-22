package kr.hs.dgsw.web01blog.Service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {

    boolean UploadProfile(MultipartFile file,Long id);
    boolean UploadContentfile(MultipartFile file, Long id);
    void DownloadProfile(HttpServletRequest request, HttpServletResponse response,Long id);
    void DownloadContentPic(HttpServletRequest request, HttpServletResponse response,Long id);
}
