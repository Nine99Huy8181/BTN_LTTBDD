/*
 * @ (#) m.java     1.0    17-Oct-25
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review_responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {

    @Id
    @Column(name = "ResponseID")
    private String responseID;

    @OneToOne
    @JoinColumn(name = "ReviewID", nullable = false)
    private Review review;

    @ManyToOne
    @JoinColumn(name = "AdminID", nullable = false)
    private Admin admin;

    @Column(name = "ResponseContent")
    private String responseContent;

    @Column(name = "ResponseDate")
    private LocalDateTime responseDate;

    @Column(name = "Status")
    private String status;
}