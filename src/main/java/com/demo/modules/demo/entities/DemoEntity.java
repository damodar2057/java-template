package com.demo.modules.demo.entities;

import com.demo.shared.base.BaseEntity;
import com.demo.shared.constants.DbTables;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity(name = DbTables.DEMO)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DemoEntity  extends BaseEntity{
    
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true)
    private int age;
}
