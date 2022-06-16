package com.revature.frittte.message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageInitializer {

    private int id;
    private String text;
    private String created_date;


}
