package kr.hs.dgsw.web01blog.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//고유식별값

    @Column(nullable = false)
    private Long writerId;//글쓴이 식별값

    //
    @Column(nullable = false)
    private String title;//제목

    @Column(columnDefinition = "TEXT")
    private String content;//본문

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Attachment> pictures;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private String filePath;//첨부사진 경로
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private String fileName;

    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

}
