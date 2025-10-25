/*
 * @ (#) CategoryMapper.java     1.0    25-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.mapper;

import iuh.fit.fashionshop_be.dto.response.CategoryResponse;
import iuh.fit.fashionshop_be.dto.response.ProductResponse;
import iuh.fit.fashionshop_be.model.Category;
import iuh.fit.fashionshop_be.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:25-Oct-25
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "parentID", source = "parent.categoryID")
    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}
