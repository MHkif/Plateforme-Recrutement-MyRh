package mhkif.yc.myrh.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Question question;
    private String answer;
    private boolean rightAnswer;

}
