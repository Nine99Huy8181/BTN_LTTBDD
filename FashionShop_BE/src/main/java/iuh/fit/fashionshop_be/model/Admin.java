/*
 * @ (#) g.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.model;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @Column(name = "AdminID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminID;

    @OneToOne
    @JoinColumn(name = "AccountID", nullable = false)
    private Account account;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Department")
    private String department;

    @Column(name = "Position")
    private String position;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "HireDate")
    private LocalDate hireDate;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<ReviewResponse> responses;
}