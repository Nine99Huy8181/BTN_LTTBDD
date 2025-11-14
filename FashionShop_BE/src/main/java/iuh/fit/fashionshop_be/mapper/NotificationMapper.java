/*
 * @ (#) f.java     1.0    13-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.mapper;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:13-Nov-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.dto.NotificationDTO;
import iuh.fit.fashionshop_be.model.Notification;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {

    @Mapping(target = "customerID", source = "customer.customerID")
    NotificationDTO toDTO(Notification notification);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "notificationID", ignore = true)
    Notification toEntity(NotificationDTO dto);

    List<NotificationDTO> toDTOList(List<Notification> notifications);
}