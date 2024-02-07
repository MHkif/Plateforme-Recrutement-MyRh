package mhkif.yc.myrh.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mhkif.yc.myrh.enums.RoleEnum;
import mhkif.yc.myrh.enums.SubscriptionStatus;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String image;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.COMPANY;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscription = SubscriptionStatus.FREEMIUM;


    @Override
    public String toString() {
        return super.toString();
    }
}
