package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{
}
