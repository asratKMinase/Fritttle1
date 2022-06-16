package com.revature.frittte.chat;


import com.revature.frittte.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatInitializer {

    private String id;
 //   private String chatAdmin;
    private String title;
    private String date;
    private String people;


}
