/*
 * @ (#) AptResponse.java     1.0    24-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:24-Oct-25
 * @version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    @Builder.Default
    int code = 1000;
    String message;
    T result;
}
