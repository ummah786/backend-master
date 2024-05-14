/*
package com.hesabbook.elastic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Document(indexName = "employee")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    @Id
    @Field(type = FieldType.Text,name = "id")
    private String id;
    private String name;
}
*/
