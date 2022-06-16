package com.revature.frittte.message;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageDao extends CrudRepository<Message,Integer> {
    @Query(value = "FROM Food WHERE id= :id AND sender = :sender")
    Message authenticateMessage(int id, String sender);

}
