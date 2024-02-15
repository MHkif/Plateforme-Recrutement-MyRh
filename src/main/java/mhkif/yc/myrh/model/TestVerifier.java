package mhkif.yc.myrh.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TestVerifier {
    @Id
    private int applicant_id;
    private LocalDateTime date;
    private int tries;
}
