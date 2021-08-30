package org.localdevelopers.payfinder.dto;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "temp")
public class TestDto {
    @Id
    private Long id;
    private String name;

    @Override
    public String toString() {
        String str = id.toString() + '\n' + name.toString();
        return str;
    }

}
