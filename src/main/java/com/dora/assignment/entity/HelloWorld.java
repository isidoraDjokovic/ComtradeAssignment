package com.dora.assignment.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Table("helloworld")
public class HelloWorld {

    @Id
    private long id;
    @Column("language_code")
    private String languageCode;
    private String text;

}
