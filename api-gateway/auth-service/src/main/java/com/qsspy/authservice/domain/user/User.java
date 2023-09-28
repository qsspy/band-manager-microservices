package com.qsspy.authservice.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USERS")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BAND_ADMIN_ID")
    private UUID ownedBandId;

    @Column(name = "MEMBER_BAND_ID")
    private UUID memberBandId;
}
