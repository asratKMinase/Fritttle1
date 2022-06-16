package com.revature.frittte.chat;

import com.revature.frittte.customer.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatDao extends CrudRepository<Chat, String> {
        @Query(value = "FROM Customer WHERE id= :id AND username = :username")
        Chat authenticateChat(String id, String username);


}
