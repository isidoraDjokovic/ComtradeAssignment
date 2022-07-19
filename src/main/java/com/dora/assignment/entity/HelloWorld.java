package com.dora.assignment.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table("helloworld")
public class HelloWorld {

  @Id
  @Column("id")
  private Long id;

  @Column("language_code")
  private String languageCode;

  @Column("text")
  private String text;
}
