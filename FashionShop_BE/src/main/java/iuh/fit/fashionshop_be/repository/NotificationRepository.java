/*
 * @ (#) f.java     1.0    13-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.repository;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:13-Nov-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByCustomerCustomerIDOrderByCreatedDateDesc(Long customerID);

    List<Notification> findByCustomerCustomerIDAndIsReadFalseOrderByCreatedDateDesc(Long customerID);

    // Admin / system notifications where customer is null
    List<Notification> findByCustomerIsNullOrderByCreatedDateDesc();

    List<Notification> findByCustomerIsNullAndIsReadFalseOrderByCreatedDateDesc();
}