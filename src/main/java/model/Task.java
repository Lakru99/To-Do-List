package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString

public class Task {
    private String title;

    public Task(String title) {
        this.title = title;
    }
}
