package com.example.authentificationsystem.registration.token;

import com.example.authentificationsystem.appuser.appUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @SequenceGenerator(
            name =  "confimationtoken_sequence",
            sequenceName = "confimationtoken_sequence",
            allocationSize =  1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "confimationtoken_sequence"
    )
    private Long Id ;
    @Column(nullable = false )
    private String token ;
    @Column(nullable = false )

    private LocalDateTime createdAt ;
    @Column(nullable = false )

    private LocalDateTime expiresAt ;
    private LocalDateTime confirmedAt ;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private appUser appuser ;


    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, appUser appuser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
       // this.confirmedAt = confirmedAt;
        this.appuser = appuser;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public static LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public static appUser getAppuser() {
        return appuser;
    }

    public void setAppuser(appUser appuser) {
        this.appuser = appuser;
    }
}
