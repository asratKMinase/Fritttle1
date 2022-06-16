package com.revature.frittte.chat;

import com.revature.frittte.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {
        @Id
        private String id;

        @ManyToOne(optional = false)
        @JoinColumn(name = "Customer", referencedColumnName = "username")
        private Customer chatAdmin;
        @Column(name = "title", length = 25, nullable = false)
        private String title;
        @Column(name = "date_t")
        private String date;
        private String people;


}
