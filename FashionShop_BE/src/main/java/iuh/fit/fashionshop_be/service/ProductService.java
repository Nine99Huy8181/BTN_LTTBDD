/*
 * @ (#) r.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.dto.response.ProductResponse;
import iuh.fit.fashionshop_be.mapper.ProductMapper;
import iuh.fit.fashionshop_be.model.Product;
import iuh.fit.fashionshop_be.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // === Danh sách: trả về ProductResponse ===
    public List<ProductResponse> getAllProductResponses() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductResponseList(products, this);
    }

    public List<ProductResponse> getProductResponsesByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryCategoryID(categoryId);
        return productMapper.toProductResponseList(products, this);
    }

    public List<ProductResponse> getProductResponsesByBrand(String brand) {
        List<Product> products = productRepository.findByBrand(brand);
        return productMapper.toProductResponseList(products, this);
    }
    public ProductResponse getProductResponseById(Long id) {
        return productMapper.toProductResponse(productRepository.findByProductID(id), this);
    }

    // === Chi tiết: trả về Product (entity) ===
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(Product product) {
        product.setCreatedDate(LocalDateTime.now());
        product.setStatus("ACTIVE");
        product.setReviewCount(0);
        product.setAverageRating(0.0f);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setBrand(productDetails.getBrand());
        product.setBasePrice(productDetails.getBasePrice());
        product.setDiscountPrice(productDetails.getDiscountPrice());
        product.setMaterial(productDetails.getMaterial());
        product.setStatus(productDetails.getStatus());
        product.setIsFeatured(productDetails.getIsFeatured());
        product.setImage(productDetails.getImage());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public Integer getSoldQuantity(Long productId) {
        return productRepository.getSoldQuantity(productId);
    }

    //Huy
    public List<ProductResponse> searchProducts(String keyword, Float minPrice, Float maxPrice, Float minRating, Float maxRating) {
        // Gọi phương thức repository mới
        List<Product> products = productRepository.searchProducts(keyword, minPrice, maxPrice, minRating, maxRating);
        // Map kết quả sang Response
        return productMapper.toProductResponseList(products, this);
    }
    // Lấy danh sách sản phẩm
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public List<Product> searchByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return productRepository.findAll();
        }

        String lowerKeyword = keyword.toLowerCase().trim();

        // Nếu người dùng nhập số → tìm theo giá gần đúng
        Double priceValue = null;
        try {
            priceValue = Double.parseDouble(lowerKeyword);
        } catch (NumberFormatException ignored) {}

        Double finalPrice = priceValue;
        double priceRange = 30.0; // khoảng dao động ±50

        return productRepository.findAll().stream()
                .filter(p -> {
                    boolean matchName = p.getName() != null &&
                            p.getName().toLowerCase().contains(lowerKeyword);

                    boolean matchPrice = false;
                    if (finalPrice != null && p.getBasePrice() != null) {
                        matchPrice = Math.abs(p.getBasePrice() - finalPrice) <= priceRange;
                    }

                    return matchName || matchPrice;
                })
                .collect(Collectors.toList());
    }
}