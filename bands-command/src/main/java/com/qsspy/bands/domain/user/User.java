package com.qsspy.bands.domain.user;

import com.qsspy.bands.domain.band.Band;
import jakarta.persistence.*;
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

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToOne(mappedBy = "adminUser", fetch = FetchType.LAZY)
    private Band ownedBand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_BAND_ID", referencedColumnName = "ID")
    private Band memberBand;
}
