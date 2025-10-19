-- DỮ LIỆU MẪU HỆ THỐNG THƯƠNG MẠI ĐIỆN TỬ (ĐẦY ĐỦ)
-- Sinh bởi ChatGPT - Ngày tạo: 2025-10-18 15:18:32

-- ========================
-- 1. accounts
-- ========================
INSERT INTO accounts (accountid, account_status, email, password, registration_date, role) VALUES
(1, 'ACTIVE', 'nguyenvana@gmail.com', '123456', NOW(), 'CUSTOMER'),
(2, 'ACTIVE', 'tranthib@gmail.com', '123456', NOW(), 'CUSTOMER'),
(3, 'ACTIVE', 'leminhc@gmail.com', '123456', NOW(), 'CUSTOMER'),
(4, 'ACTIVE', 'phamduct@gmail.com', '123456', NOW(), 'CUSTOMER'),
(5, 'ACTIVE', 'hoanglan@gmail.com', '123456', NOW(), 'CUSTOMER'),
(6, 'ACTIVE', 'admin1@shop.vn', 'admin123', NOW(), 'ADMIN'),
(7, 'ACTIVE', 'admin2@shop.vn', 'admin123', NOW(), 'ADMIN'),
(8, 'ACTIVE', 'superadmin@shop.vn', 'super123', NOW(), 'SUPER');

-- ========================
-- 2. customers
-- ========================
INSERT INTO customers (customerid, date_of_birth, full_name, gender, loyalty_points, phone_number, referral_code, accountid) VALUES
(1, '1990-05-12', 'Nguyễn Văn A', 'Nam', 120, '0905123456', 'REF001', 1),
(2, '1995-08-23', 'Trần Thị B', 'Nữ', 200, '0912234567', 'REF002', 2),
(3, '1988-11-03', 'Lê Minh C', 'Nam', 150, '0933456789', 'REF003', 3),
(4, '1992-04-15', 'Phạm Đức T', 'Nam', 300, '0987123456', 'REF004', 4),
(5, '1998-07-07', 'Hoàng Lan', 'Nữ', 180, '0978123123', 'REF005', 5);

-- ========================
-- 3. admins
-- ========================
INSERT INTO admins (adminid, department, full_name, hire_date, position, accountid) VALUES
(1, 'Quản lý bán hàng', 'Nguyễn Quang Admin', '2021-02-01', 'Trưởng phòng', 6),
(2, 'Hỗ trợ khách hàng', 'Trần Thị Hỗ Trợ', '2022-05-10', 'Nhân viên CSKH', 7),
(3, 'Hệ thống', 'Super Admin', '2020-01-01', 'Quản trị hệ thống', 8);

-- ========================
-- 4. addresses
-- ========================
INSERT INTO addresses (addressid, city, country, district, is_default, postal_code, recipient_name, recipient_phone, street_address, customerid) VALUES
(1, 'TP. Hồ Chí Minh', 'Việt Nam', 'Quận 1', 1, '700000', 'Nguyễn Văn A', '0905123456', '12 Nguyễn Huệ', 1),
(2, 'Hà Nội', 'Việt Nam', 'Ba Đình', 1, '100000', 'Trần Thị B', '0912234567', '45 Kim Mã', 2),
(3, 'Đà Nẵng', 'Việt Nam', 'Hải Châu', 1, '550000', 'Lê Minh C', '0933456789', '78 Bạch Đằng', 3),
(4, 'Cần Thơ', 'Việt Nam', 'Ninh Kiều', 1, '900000', 'Phạm Đức T', '0987123456', '89 Nguyễn Trãi', 4),
(5, 'Huế', 'Việt Nam', 'Phú Nhuận', 1, '530000', 'Hoàng Lan', '0978123123', '101 Hùng Vương', 5);

-- ========================
-- 5. categories
-- ========================
INSERT INTO categories (categoryid, description, image, name, parent_categoryid) VALUES
(1, 'Thời trang nam', 'cat_men.jpg', 'Nam', NULL),
(2, 'Thời trang nữ', 'cat_women.jpg', 'Nữ', NULL),
(3, 'Giày dép nam', 'cat_shoes_men.jpg', 'Giày Nam', 1),
(4, 'Giày dép nữ', 'cat_shoes_women.jpg', 'Giày Nữ', 2),
(5, 'Phụ kiện nam', 'cat_accessories_men.jpg', 'Phụ kiện Nam', 1),
(6, 'Phụ kiện nữ', 'cat_accessories_women.jpg', 'Phụ kiện Nữ', 2),
(7, 'Áo sơ mi', 'cat_shirt.jpg', 'Áo sơ mi', 1),
(8, 'Áo thun', 'cat_tshirt.jpg', 'Áo thun', 1),
(9, 'Váy', 'cat_dress.jpg', 'Váy', 2),
(10, 'Quần', 'cat_pants.jpg', 'Quần', 1);

-- ========================
-- 6. products (10 sản phẩm)
-- ========================
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(1, 3.8, 258646.17, 'Việt Shop', NOW(), 'Sản phẩm áo thun nam cổ tròn', 271799.97, 1, 'Cotton', 'Áo thun nam cổ tròn', 197, 'ACTIVE', 6);
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(2, 3.16, 409859.3, 'Việt Shop', NOW(), 'Sản phẩm áo sơ mi trắng công sở', 554540.58, 1, 'Cotton', 'Áo sơ mi trắng công sở', 38, 'ACTIVE', 1);
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(3, 3.43, 684359.3, 'Việt Shop', NOW(), 'Sản phẩm quần jean nam xanh đậm', 259124.61, 1, 'Cotton', 'Quần jean nam xanh đậm', 143, 'ACTIVE', 8);
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(4, 3.44, 372440.39, 'Việt Shop', NOW(), 'Sản phẩm váy hoa nhí mùa hè', 616717.22, 1, 'Cotton', 'Váy hoa nhí mùa hè', 54, 'ACTIVE', 6);
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(5, 3.41, 435308.24, 'Việt Shop', NOW(), 'Sản phẩm giày thể thao nữ', 529061.79, 1, 'Cotton', 'Giày thể thao nữ', 5, 'ACTIVE', 5);
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(6, 3.22, 721008.14, 'Việt Shop', NOW(), 'Sản phẩm giày da nam công sở', 204801.52, 1, 'Cotton', 'Giày da nam công sở', 66, 'ACTIVE', 2);
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(7, 3.85, 564334.57, 'Việt Shop', NOW(), 'Sản phẩm túi xách nữ thời trang', 528136.11, 1, 'Cotton', 'Túi xách nữ thời trang', 159, 'ACTIVE', 10);
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(8, 4.98, 700549.93, 'Việt Shop', NOW(), 'Sản phẩm thắt lưng da bò nam', 487857.95, 1, 'Cotton', 'Thắt lưng da bò nam', 52, 'ACTIVE', 2);
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(9, 3.85, 613033.7, 'Việt Shop', NOW(), 'Sản phẩm mũ lưỡi trai nam', 524922.19, 1, 'Cotton', 'Mũ lưỡi trai nam', 46, 'ACTIVE', 4);
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid) VALUES
(10, 3.84, 633335.9, 'Việt Shop', NOW(), 'Sản phẩm áo khoác gió nữ', 534303.96, 1, 'Cotton', 'Áo khoác gió nữ', 157, 'ACTIVE', 4);

-- ========================
-- 7. product_variants (40 dòng)
-- ========================
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(1, 'Xanh', '["img_1.jpg", "img_2.jpg"]', 1018.82, 'XL', 'SKU0001', 'AVAILABLE', 1);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(2, 'Đen', '["img_2.jpg", "img_2.jpg"]', -28880.72, 'M', 'SKU0002', 'AVAILABLE', 1);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(3, 'Đen', '["img_3.jpg", "img_2.jpg"]', 40133.37, 'L', 'SKU0003', 'AVAILABLE', 1);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(4, 'Đỏ', '["img_4.jpg", "img_2.jpg"]', -8691.49, 'M', 'SKU0004', 'AVAILABLE', 1);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(5, 'Xanh', '["img_5.jpg", "img_2.jpg"]', 45389.39, 'XL', 'SKU0005', 'AVAILABLE', 2);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(6, 'Xanh', '["img_6.jpg", "img_2.jpg"]', -48369.52, 'L', 'SKU0006', 'AVAILABLE', 2);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(7, 'Đỏ', '["img_7.jpg", "img_2.jpg"]', 10018.51, 'S', 'SKU0007', 'AVAILABLE', 2);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(8, 'Trắng', '["img_8.jpg", "img_2.jpg"]', 19236.79, 'XL', 'SKU0008', 'AVAILABLE', 2);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(9, 'Đỏ', '["img_9.jpg", "img_2.jpg"]', 16494.97, 'L', 'SKU0009', 'AVAILABLE', 3);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(10, 'Đỏ', '["img_10.jpg", "img_2.jpg"]', -21071.98, 'L', 'SKU0010', 'AVAILABLE', 3);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(11, 'Đen', '["img_11.jpg", "img_2.jpg"]', -48187.76, 'XL', 'SKU0011', 'AVAILABLE', 3);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(12, 'Đen', '["img_12.jpg", "img_2.jpg"]', -39921.53, 'L', 'SKU0012', 'AVAILABLE', 3);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUESproduct_variants
(13, 'Xanh', '["img_13.jpg", "img_2.jpg"]', -14024.07, 'XL', 'SKU0013', 'AVAILABLE', 4);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(14, 'Trắng', '["img_14.jpg", "img_2.jpg"]', 21942.42, 'XL', 'SKU0014', 'AVAILABLE', 4);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(15, 'Đen', '["img_15.jpg", "img_2.jpg"]', 32637.15, 'S', 'SKU0015', 'AVAILABLE', 4);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(16, 'Đen', '["img_16.jpg", "img_2.jpg"]', -26764.92, 'S', 'SKU0016', 'AVAILABLE', 4);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(17, 'Xanh', '["img_17.jpg", "img_2.jpg"]', 25822.99, 'S', 'SKU0017', 'AVAILABLE', 5);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(18, 'Xanh', '["img_18.jpg", "img_2.jpg"]', -46516.73, 'L', 'SKU0018', 'AVAILABLE', 5);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(19, 'Đỏ', '["img_19.jpg", "img_2.jpg"]', 40003.95, 'L', 'SKU0019', 'AVAILABLE', 5);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(20, 'Đen', '["img_20.jpg", "img_2.jpg"]', -17276.69, 'S', 'SKU0020', 'AVAILABLE', 5);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(21, 'Đen', '["img_21.jpg", "img_2.jpg"]', -21366.53, 'S', 'SKU0021', 'AVAILABLE', 6);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(22, 'Đen', '["img_22.jpg", "img_2.jpg"]', -44563.49, 'XL', 'SKU0022', 'AVAILABLE', 6);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(23, 'Trắng', '["img_23.jpg", "img_2.jpg"]', -1841.61, 'L', 'SKU0023', 'AVAILABLE', 6);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(24, 'Đen', '["img_24.jpg", "img_2.jpg"]', 43187.84, 'L', 'SKU0024', 'AVAILABLE', 6);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(25, 'Đỏ', '["img_25.jpg", "img_2.jpg"]', -20462.19, 'M', 'SKU0025', 'AVAILABLE', 7);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(26, 'Đỏ', '["img_26.jpg", "img_2.jpg"]', 16521.59, 'M', 'SKU0026', 'AVAILABLE', 7);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(27, 'Trắng', '["img_27.jpg", "img_2.jpg"]', -35472.55, 'L', 'SKU0027', 'AVAILABLE', 7);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(28, 'Đen', '["img_28.jpg", "img_2.jpg"]', -38117.44, 'M', 'SKU0028', 'AVAILABLE', 7);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(29, 'Trắng', '["img_29.jpg", "img_2.jpg"]', -40687.08, 'XL', 'SKU0029', 'AVAILABLE', 8);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(30, 'Trắng', '["img_30.jpg", "img_2.jpg"]', -49518.04, 'M', 'SKU0030', 'AVAILABLE', 8);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(31, 'Xanh', '["img_31.jpg", "img_2.jpg"]', -48783.25, 'S', 'SKU0031', 'AVAILABLE', 8);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(32, 'Đỏ', '["img_32.jpg", "img_2.jpg"]', -29716.94, 'XL', 'SKU0032', 'AVAILABLE', 8);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(33, 'Đỏ', '["img_33.jpg", "img_2.jpg"]', -30320.85, 'S', 'SKU0033', 'AVAILABLE', 9);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(34, 'Đen', '["img_34.jpg", "img_2.jpg"]', -28889.09, 'S', 'SKU0034', 'AVAILABLE', 9);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(35, 'Đỏ', '["img_35.jpg", "img_2.jpg"]', 28454.83, 'M', 'SKU0035', 'AVAILABLE', 9);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(36, 'Đỏ', '["img_36.jpg", "img_2.jpg"]', -5455.4, 'S', 'SKU0036', 'AVAILABLE', 9);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(37, 'Xanh', '["img_37.jpg", "img_2.jpg"]', 43037.54, 'L', 'SKU0037', 'AVAILABLE', 10);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(38, 'Đỏ', '["img_38.jpg", "img_2.jpg"]', 32399.99, 'XL', 'SKU0038', 'AVAILABLE', 10);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(39, 'Xanh', '["img_39.jpg", "img_2.jpg"]', 32404.36, 'M', 'SKU0039', 'AVAILABLE', 10);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(40, 'Đen', '["img_40.jpg", "img_2.jpg"]', 42847.59, 'S', 'SKU0040', 'AVAILABLE', 10);

-- ========================
-- 8. inventory
-- ========================
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (1, 81, 2, NOW(), 1);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (2, 10, 5, NOW(), 2);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (3, 61, 9, NOW(), 3);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (4, 23, 3, NOW(), 4);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (5, 72, 8, NOW(), 5);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (6, 14, 9, NOW(), 6);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (7, 50, 8, NOW(), 7);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (8, 13, 7, NOW(), 8);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (9, 72, 3, NOW(), 9);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (10, 39, 3, NOW(), 10);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (11, 36, 9, NOW(), 11);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (12, 53, 10, NOW(), 12);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (13, 38, 3, NOW(), 13);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (14, 88, 4, NOW(), 14);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (15, 60, 8, NOW(), 15);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (16, 36, 3, NOW(), 16);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (17, 99, 10, NOW(), 17);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (18, 91, 0, NOW(), 18);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (19, 82, 2, NOW(), 19);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (20, 30, 8, NOW(), 20);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (21, 48, 5, NOW(), 21);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (22, 50, 1, NOW(), 22);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (23, 72, 0, NOW(), 23);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (24, 86, 0, NOW(), 24);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (25, 83, 10, NOW(), 25);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (26, 71, 0, NOW(), 26);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (27, 85, 2, NOW(), 27);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (28, 14, 9, NOW(), 28);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (29, 65, 2, NOW(), 29);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (30, 32, 2, NOW(), 30);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (31, 76, 5, NOW(), 31);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (32, 65, 4, NOW(), 32);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (33, 11, 7, NOW(), 33);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (34, 43, 1, NOW(), 34);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (35, 21, 8, NOW(), 35);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (36, 30, 8, NOW(), 36);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (37, 84, 4, NOW(), 37);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (38, 86, 1, NOW(), 38);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (39, 42, 1, NOW(), 39);
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES (40, 83, 1, NOW(), 40);

-- ========================
-- 9. coupons
-- ========================
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (1, 'SALE001', 'Đơn trên 200k', 'Giảm giá ưu đãi 1', 'PERCENT', 5, '2021-11-12', 100, '2024-12-17', 'ACTIVE', 2);
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (2, 'SALE002', 'Đơn trên 200k', 'Giảm giá ưu đãi 2', 'PERCENT', 10, '2023-04-01', 100, '2025-04-23', 'ACTIVE', 10);
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (3, 'SALE003', 'Đơn trên 200k', 'Giảm giá ưu đãi 3', 'PERCENT', 15, '2022-03-19', 100, '2024-11-11', 'ACTIVE', 2);
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (4, 'SALE004', 'Đơn trên 200k', 'Giảm giá ưu đãi 4', 'PERCENT', 20, '2023-05-13', 100, '2025-11-23', 'ACTIVE', 5);
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (5, 'SALE005', 'Đơn trên 200k', 'Giảm giá ưu đãi 5', 'PERCENT', 25, '2021-11-26', 100, '2025-09-09', 'ACTIVE', 3);

-- ========================
-- 10. orders
-- ========================
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (1, 'Đơn hàng số 1', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 1061268.61, 2, 5, 1);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (2, 'Đơn hàng số 2', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 1368233.41, 4, 5, 3);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (3, 'Đơn hàng số 3', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 393385.67, 5, 1, 5);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (4, 'Đơn hàng số 4', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 1327459.02, 1, 2, 2);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (5, 'Đơn hàng số 5', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 1343524.91, 2, 4, 1);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (6, 'Đơn hàng số 6', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 924803.8, 2, 4, 5);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (7, 'Đơn hàng số 7', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 535666.28, 2, 4, 4);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (8, 'Đơn hàng số 8', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 1430916.23, 5, 2, 3);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (9, 'Đơn hàng số 9', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 1592299.01, 2, 1, 4);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (10, 'Đơn hàng số 10', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 427258.87, 1, 4, 3);

-- ========================
-- 11. shipping
-- ========================
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (1, 'Giao Hàng Nhanh', '2025-12-04', 30000, 'Đã giao', 'GHN00001', 1);
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (2, 'Giao Hàng Nhanh', '2024-05-26', 30000, 'Đã giao', 'GHN00002', 2);
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (3, 'Giao Hàng Nhanh', '2021-05-04', 30000, 'Đã giao', 'GHN00003', 3);
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (4, 'Giao Hàng Nhanh', '2024-06-23', 30000, 'Đã giao', 'GHN00004', 4);
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (5, 'Giao Hàng Nhanh', '2025-10-10', 30000, 'Đã giao', 'GHN00005', 5);
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (6, 'Giao Hàng Nhanh', '2025-08-10', 30000, 'Đã giao', 'GHN00006', 6);
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (7, 'Giao Hàng Nhanh', '2025-03-24', 30000, 'Đã giao', 'GHN00007', 7);
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (8, 'Giao Hàng Nhanh', '2021-06-26', 30000, 'Đã giao', 'GHN00008', 8);
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (9, 'Giao Hàng Nhanh', '2021-02-15', 30000, 'Đã giao', 'GHN00009', 9);
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES (10, 'Giao Hàng Nhanh', '2020-04-18', 30000, 'Đã giao', 'GHN00010', 10);

-- ========================
-- 12. order_items
-- ========================
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (1, 2, 259021.75, 472429.34, 1, 19);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (2, 1, 318181.04, 143271.3, 7, 10);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (3, 2, 765368.19, 599972.49, 1, 11);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (4, 3, 419496.91, 195140.51, 3, 22);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (5, 1, 766601.18, 526605.3, 6, 32);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (6, 3, 296099.68, 442518.51, 10, 34);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (7, 2, 522515.26, 115250.38, 8, 5);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (8, 1, 629846.88, 238444.75, 7, 15);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (9, 1, 473826.57, 485265.68, 3, 25);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (10, 1, 783388.14, 420252.11, 1, 14);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (11, 2, 669239.82, 235274.96, 5, 35);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (12, 1, 439185.03, 544289.78, 3, 16);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (13, 2, 402523.62, 534818.5, 3, 32);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (14, 3, 723522.15, 264030.75, 2, 34);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (15, 3, 613840.38, 296107.32, 5, 6);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (16, 2, 321384.28, 414279.4, 10, 4);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (17, 3, 660811.6, 572551.98, 7, 31);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (18, 1, 457161.45, 205295.09, 4, 7);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (19, 3, 600687.7, 348367.75, 8, 18);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (20, 1, 727196.75, 319916.67, 2, 38);

-- ========================
-- 13. carts
-- ========================
INSERT INTO carts (cartid, total_amount, updated_date, customerid) VALUES (1, 992172.39, NOW(), 1);
INSERT INTO carts (cartid, total_amount, updated_date, customerid) VALUES (2, 762748.21, NOW(), 2);
INSERT INTO carts (cartid, total_amount, updated_date, customerid) VALUES (3, 302700.52, NOW(), 3);
INSERT INTO carts (cartid, total_amount, updated_date, customerid) VALUES (4, 225826.62, NOW(), 4);
INSERT INTO carts (cartid, total_amount, updated_date, customerid) VALUES (5, 465428.38, NOW(), 5);

-- ========================
-- 14. cart_items
-- ========================
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (1, 2, 5, 3);
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (2, 3, 1, 29);
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (3, 3, 4, 24);
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (4, 3, 4, 11);
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (5, 1, 3, 9);
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (6, 3, 5, 31);
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (7, 2, 1, 17);
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (8, 3, 5, 35);

-- ========================
-- 15. wishlists
-- ========================
INSERT INTO wishlists (wishlistid, created_date, customerid) VALUES (1, NOW(), 1);
INSERT INTO wishlists (wishlistid, created_date, customerid) VALUES (2, NOW(), 2);
INSERT INTO wishlists (wishlistid, created_date, customerid) VALUES (3, NOW(), 3);
INSERT INTO wishlists (wishlistid, created_date, customerid) VALUES (4, NOW(), 4);
INSERT INTO wishlists (wishlistid, created_date, customerid) VALUES (5, NOW(), 5);

-- ========================
-- 16. wishlist_items
-- ========================
INSERT INTO wishlist_items (wishlist_itemid, added_date, productid, wishlistid) VALUES (1, NOW(), 8, 3);
INSERT INTO wishlist_items (wishlist_itemid, added_date, productid, wishlistid) VALUES (2, NOW(), 3, 1);
INSERT INTO wishlist_items (wishlist_itemid, added_date, productid, wishlistid) VALUES (3, NOW(), 5, 2);
INSERT INTO wishlist_items (wishlist_itemid, added_date, productid, wishlistid) VALUES (4, NOW(), 2, 4);
INSERT INTO wishlist_items (wishlist_itemid, added_date, productid, wishlistid) VALUES (5, NOW(), 10, 5);
INSERT INTO wishlist_items (wishlist_itemid, added_date, productid, wishlistid) VALUES (6, NOW(), 1, 4);
INSERT INTO wishlist_items (wishlist_itemid, added_date, productid, wishlistid) VALUES (7, NOW(), 5, 2);
INSERT INTO wishlist_items (wishlist_itemid, added_date, productid, wishlistid) VALUES (8, NOW(), 4, 1);

-- ========================
-- 17. reviews
-- ========================
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (1, 'Sản phẩm rất tốt 1', '["rev1.jpg", "rev_2.jpg"]', 5, NOW(), 'APPROVED', 4, 7);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (2, 'Sản phẩm rất tốt 2', '["rev2.jpg", "rev_2.jpg"]', 4, NOW(), 'APPROVED', 5, 10);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (3, 'Sản phẩm rất tốt 3', '["rev3.jpg", "rev_2.jpg"]', 5, NOW(), 'APPROVED', 4, 4);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (4, 'Sản phẩm rất tốt 4', '["rev4.jpg", "rev_2.jpg"]', 5, NOW(), 'APPROVED', 2, 3);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (5, 'Sản phẩm rất tốt 5', '["rev5.jpg", "rev_2.jpg"]', 5, NOW(), 'APPROVED', 4, 10);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (6, 'Sản phẩm rất tốt 6', '["rev6.jpg", "rev_2.jpg"]', 4, NOW(), 'APPROVED', 1, 1);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (7, 'Sản phẩm rất tốt 7', '["rev7.jpg", "rev_2.jpg"]', 4, NOW(), 'APPROVED', 1, 10);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (8, 'Sản phẩm rất tốt 8', '["rev8.jpg", "rev_2.jpg"]', 3, NOW(), 'APPROVED', 3, 6);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (9, 'Sản phẩm rất tốt 9', '["rev9.jpg", "rev_2.jpg"]', 3, NOW(), 'APPROVED', 3, 2);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (10, 'Sản phẩm rất tốt 10', '["rev10.jpg", "rev_2.jpg"]', 4, NOW(), 'APPROVED', 3, 2);

-- ========================
-- 18. review_responses
-- ========================
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (1, 'Cảm ơn bạn đã đánh giá 1', NOW(), 'VISIBLE', 2, 1);
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (2, 'Cảm ơn bạn đã đánh giá 2', NOW(), 'VISIBLE', 1, 2);
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (3, 'Cảm ơn bạn đã đánh giá 3', NOW(), 'VISIBLE', 3, 3);
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (4, 'Cảm ơn bạn đã đánh giá 4', NOW(), 'VISIBLE', 2, 4);
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (5, 'Cảm ơn bạn đã đánh giá 5', NOW(), 'VISIBLE', 3, 5);
