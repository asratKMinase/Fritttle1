package com.revature.frittte.food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "food")
public class Food {

    private String id;
    @Id
    @Column(name = "item_name", length = 50, nullable = false)
    private String itemName;

    @Column(name = "cost_t")
    private int cost;
    private int weight;

    private int volume;

    private boolean frozen;

}



