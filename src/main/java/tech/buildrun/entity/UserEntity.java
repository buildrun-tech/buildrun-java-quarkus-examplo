package tech.buildrun.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class UserEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID userId;

    @Column(name = "username")
    public String username;

    public UserEntity() {
    }

    public UserEntity(String username) {
        this.username = username;
    }
}
