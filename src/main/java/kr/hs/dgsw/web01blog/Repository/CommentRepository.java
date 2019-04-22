package kr.hs.dgsw.web01blog.Repository;

import kr.hs.dgsw.web01blog.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findById(Long id);
}
