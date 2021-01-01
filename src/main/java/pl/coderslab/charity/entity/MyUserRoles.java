package pl.coderslab.charity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name ="user_roles")
@Data
@NoArgsConstructor
@ToString
public class MyUserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private MyUser userId;

    @Column(nullable = false)
    private String role;
}
