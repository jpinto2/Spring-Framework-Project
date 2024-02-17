package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.Message;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    Message findById(int id);
    Message deleteById(int id);
    
    @Query("FROM Message where posted_by = :posted_by")
    List<Message> findAllByPostedBy(@Param(value = "posted_by") int id);

}
