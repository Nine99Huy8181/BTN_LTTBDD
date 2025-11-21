-- DỮ LIỆU MẪU HỆ THỐNG THƯƠNG MẠI ĐIỆN TỬ (ĐẦY ĐỦ)
-- Sinh bởi ChatGPT - Ngày tạo: 2025-10-18 15:18:32

-- ========================
-- 1. accounts
-- ========================
INSERT INTO accounts (accountid, account_status, email, password, registration_date, ROLE, avatar) VALUES
(1, 'ACTIVE', 'nguyenvana@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER','https://res.cloudinary.com/duffcwady/image/upload/v1761921986/60f525ad-e101-45f2-83ba-1fdf40fdff3b.png'),
(2, 'ACTIVE', 'tranthib@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER','https://res.cloudinary.com/duffcwady/image/upload/v1761921986/60f525ad-e101-45f2-83ba-1fdf40fdff3b.png'),
(3, 'ACTIVE', 'leminhc@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER','https://res.cloudinary.com/duffcwady/image/upload/v1761921986/60f525ad-e101-45f2-83ba-1fdf40fdff3b.png'),
(4, 'ACTIVE', 'phamduct@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER','https://res.cloudinary.com/duffcwady/image/upload/v1761921986/60f525ad-e101-45f2-83ba-1fdf40fdff3b.png'),
(5, 'ACTIVE', 'hoanglan@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER','https://res.cloudinary.com/duffcwady/image/upload/v1761921986/60f525ad-e101-45f2-83ba-1fdf40fdff3b.png'),
(6, 'ACTIVE', 'admin1@shop.vn', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'ADMIN','https://res.cloudinary.com/duffcwady/image/upload/v1761921986/60f525ad-e101-45f2-83ba-1fdf40fdff3b.png'),
(7, 'ACTIVE', 'admin2@shop.vn', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'ADMIN','https://res.cloudinary.com/duffcwady/image/upload/v1761921986/60f525ad-e101-45f2-83ba-1fdf40fdff3b.png'),
(8, 'ACTIVE', 'superadmin@shop.vn', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'SUPER','https://res.cloudinary.com/duffcwady/image/upload/v1761921986/60f525ad-e101-45f2-83ba-1fdf40fdff3b.png');

-- ========================
-- 2. customers
-- ========================
INSERT INTO customers (customerid, date_of_birth, full_name, gender, loyalty_points, phone_number, referral_code, accountid, created_at) VALUES
(1, '1990-05-12', 'Nguyễn Văn A', 'Nam', 120, '0905123456', 'REF001', 1, NOW()),
(2, '1995-08-23', 'Trần Thị B', 'Nữ', 200, '0912234567', 'REF002', 2, NOW()),
(3, '1988-11-03', 'Lê Minh C', 'Nam', 150, '0933456789', 'REF003', 3, NOW()),
(4, '1992-04-15', 'Phạm Đức T', 'Nam', 300, '0987123456', 'REF004', 4, NOW()),
(5, '1998-07-07', 'Hoàng Lan', 'Nữ', 180, '0978123123', 'REF005', 5, NOW());

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
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(1, 4.2, 720000, 'Việt Shop 2', NOW(), 'Áo blazer nam', 650000, 1, 'Cotton', 'Áo blazer nam GBZ0015-1NN', 124, 'ACTIVE', 7, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316779/GBZ0015-1NN-navy-1_s62ir9.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(2, 4.1, 320000, 'Việt Shop', NOW(), 'Áo phông Golf dài tay nữ', 290000, 1, 'Cotton', 'Áo phông Golf dài tay nữ DLP0003-1OB', 211, 'ACTIVE', 2, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316780/DLP0003-1OB-blue-1_kvznnd.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(3, 4.0, 340000, 'Việt Shop 2', NOW(), 'Áo phông Golf dài tay nữ', 310000, 1, 'Cotton', 'Áo phông Golf dài tay nữ DLP0004-1BD', 178, 'ACTIVE', 2, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316780/DLP0004-1BD-red-1_bnqa1b.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(4, 4.3, 280000, 'Việt Shop', NOW(), 'Áo polo cộc tay nam', 250000, 1, 'Cotton', 'Áo polo cộc tay nam Bernini BPL0033-1', 312, 'ACTIVE', 8, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316782/BPL003-1-blue-1_i0dejq.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(5, 4.1, 310000, 'Việt Shop', NOW(), 'Áo Polo cộc tay nam', 275000, 1, 'Cotton', 'Áo Polo cộc tay nam Bernini BPL0044-1', 198, 'ACTIVE', 8, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316789/BPL0044-1-white-1_wxzf3z.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(6, 4.0, 295000, 'Việt Shop', NOW(), 'Áo polo cộc tay nam', 270000, 1, 'Cotton', 'Áo polo cộc tay nam Bernini BPL0045-1', 241, 'ACTIVE', 8, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316791/BPL0045-1-white-1_omqayt.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(7, 4.2, 330000, 'Việt Shop', NOW(), 'Áo Sơ Mi Cộc Tay Nam', 300000, 1, 'Cotton', 'Áo Sơ Mi Cộc Tay Nam GSS0173-1', 176, 'ACTIVE', 7, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316795/GSS0173-1-pink-1_twsuvk.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(8, 4.5, 850000, 'Việt Shop', NOW(), 'Áo vest nam', 770000, 1, 'Cotton', 'Áo vest nam GJV0038-1DN', 204, 'ACTIVE', 7, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316794/GJV0038-1DN-navy-1_e4phkh.jpg');
-- 🔹 QUẦN NAM
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(9, 4.0, 480000, 'Việt Shop', NOW(), 'Quần Âu Nam', 440000, 1, 'Cotton', 'Quần Âu Nam GPT0109-1', 156, 'ACTIVE', 10, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316767/GPT0109-1-Navy-1_kr49yy.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(10, 4.2, 420000, 'Việt Shop', NOW(), 'Quần Khaki Nam', 380000, 1, 'Cotton', 'Quần Khaki Nam GCN0040-1DM', 199, 'ACTIVE', 10, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316764/GCN0040-1DM-mossgreen-1_lu4rui.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(11, 4.0, 415000, 'Việt Shop', NOW(), 'Quần Khaki Nam', 385000, 1, 'Cotton', 'Quần Khaki Nam GCN0042-1', 184, 'ACTIVE', 10, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316764/GCN0042-1-navy-1_xxdv06.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(12, 4.3, 490000, 'Việt Shop 2', NOW(), 'Quần Vest Nam', 460000, 1, 'Cotton', 'Quần Vest Nam GPV0037-1DR', 207, 'ACTIVE', 10, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316766/GPV0037-1DR-gray-1_ortuvp.jpg');
-- 🔹 PHỤ KIỆN NAM
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(13, 4.4, 250000, 'Việt Shop', NOW(), 'Dây Lưng Nam', 220000, 1, 'Leather', 'Dây Lưng Nam BBL0025-1', 155, 'ACTIVE', 5, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316769/BBL0025-1-black-1_u9wkjg.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(15, 4.2, 550000, 'Việt Shop', NOW(), 'Túi xách nam', 500000, 1, 'Leather', 'Túi xách nam Bernini BBB0014-1', 132, 'ACTIVE', 5, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316771/BBB0014-1-black-1_ocqczd.png');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(18, 4.3, 290000, 'Việt Shop', NOW(), 'Bóp Tay Nam', 260000, 1, 'Leather', 'Bóp Tay Nam BCL0009-1', 117, 'ACTIVE', 5, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316773/BCL0009-1-black-1_jdjzx5.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(20, 4.5, 310000, 'Việt Shop', NOW(), 'Ví nam Bernini', 285000, 1, 'Leather', 'Ví nam Bernini BWL0017-1', 210, 'ACTIVE', 5, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316777/BWL0017-1-black-1_b3i3xo.jpg');
-- 🔹 SẢN PHẨM NỮ
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(14, 4.6, 600000, 'Việt Shop 2', NOW(), 'Túi Nữ', 550000, 1, 'Leather', 'Túi Nữ OLD0003-1', 175, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316768/OLD0003-1-black-1_gzpnlm.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(16, 4.5, 680000, 'Việt Shop', NOW(), 'Túi xách nữ', 640000, 1, 'Leather', 'Túi xách nữ DLD0175-1', 198, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316771/DLD0175-1-milk-1_l7xhww.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(17, 4.1, 950000, 'Việt Shop 2', NOW(), 'Vali GIOVANNI', 880000, 1, 'ABS', 'Vali GIOVANNI GTL0014-1', 139, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316773/GTL0014-1-pink-1_comvoc.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(19, 4.3, 320000, 'Việt Shop', NOW(), 'Ví da nữ', 290000, 1, 'Leather', 'Ví da nữ GWL0060-1', 166, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316776/GWL0060-1-yellow-1_f49yz6.jpg');

-- ========================
-- 7. product_variants (40 dòng)
-- ========================
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(1, 'Xanh navy', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316779/GBZ0015-1NN-navy-2_hvzn64.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316780/GBZ0015-1NN-navy-3_dvjcg7.jpg"]', 1018.82, 'XL', 'SKU0001', 'AVAILABLE', 1);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(2, 'Xanh da trời', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316781/DLP0004-1BD-red-2_m3tfqy.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316781/DLP0004-1BD-red-3_etxr9z.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316783/DLP0004-1BD-red-4_du0xds.jpg"]', -28880.72, 'M', 'SKU0002', 'AVAILABLE', 2);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(3, 'Đỏ', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316781/DLP0004-1BD-red-2_m3tfqy.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316781/DLP0004-1BD-red-3_etxr9z.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316783/DLP0004-1BD-red-4_du0xds.jpg"]', 40133.37, 'L', 'SKU0003', 'AVAILABLE', 3);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(4, 'Xanh da trời', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316782/BPL003-1-blue-2_bj1llr.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316782/BPL003-1-blue-3_yvmfg1.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316783/BPL003-1-blue-4_wdedkj.jpg"]', -8691.49, 'M', 'SKU0004', 'AVAILABLE', 4);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(5, 'Trắng', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316791/BPL0044-1-white-2_wdxqq7.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316789/BPL0044-1-white-3_okohcq.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316789/BPL0044-1-white-4_sekfhc.jpg"]', 45389.39, 'XL', 'SKU0005', 'AVAILABLE', 5);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(6, 'Tím', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316788/BPL0044-1-pink-1_c4mryv.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316790/BPL0044-1-pink-2_lbhfxz.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316788/BPL0044-1-pink-3_gqcj8i.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316788/BPL0044-1-pink-4_xzvp6d.jpg"]', 45389.39, 'XL', 'SKU0005', 'AVAILABLE', 5);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(7, 'Xanh lá cây', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316786/BPL0044-1-green-1_goye0k.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316787/BPL0044-1-green-2_zopvk9.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316787/BPL0044-1-green-3_e7bq9b.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316789/BPL0044-1-green-4_wj3r03.jpg"]', 45389.39, 'XL', 'SKU0005', 'AVAILABLE', 5);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(8, 'Nâu', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316785/BPL0044-1-brown-1_jjwkfb.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316785/BPL0044-1-brown-2_gisast.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316785/BPL0044-1-brown-3_evkvcc.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316786/BPL0044-1-brown-4_sxfcoi.jpg"]', 45389.39, 'XL', 'SKU0005', 'AVAILABLE', 5);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(9, 'Đen', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316783/BPL0044-1-black-1_z6o2ab.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316783/BPL0044-1-black-2_cyt3jr.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316783/BPL0044-1-black-3_aieo2n.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316783/BPL0044-1-black-4_wflh2y.jpg"]', 45389.39, 'XL', 'SKU0005', 'AVAILABLE', 5);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(10, 'Trắng', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316791/BPL0045-1-white-2_hhxwpg.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316791/BPL0045-1-white-3_exhxnn.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316791/BPL0045-1-white-4_u5ykdt.jpg"]', -48369.52, 'L', 'SKU0006', 'AVAILABLE', 6);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(11, 'Đen', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316790/BPL0045-1-black-1_vqafmr.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316791/BPL0045-1-black-2_bipbkc.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316790/BPL0045-1-black-3_ymmrb0.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316791/BPL0045-1-black-4_fuzwlp.jpg"]', -48369.52, 'L', 'SKU0006', 'AVAILABLE', 6);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(12, 'Tím', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316793/GSS0173-1-pink-2_aihq0k.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316793/GSS0173-1-pink-3_qytnay.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316794/GSS0173-1-pink-4_gg1g68.jpg"]', 10018.51, 'S', 'SKU0007', 'AVAILABLE', 7);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(13, 'Xám', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316792/GSS0173-1-gray-1_lsrw73.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316793/GSS0173-1-gray-2_adtjzv.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316792/GSS0173-1-gray-3_edujxw.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316793/GSS0173-1-gray-4_qchy5i.jpg"]', 10018.51, 'S', 'SKU0007', 'AVAILABLE', 7);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(14, 'Xanh navy', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316794/GJV0038-1DN-navy-2_t8uwws.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316795/GJV0038-1DN-navy-3_lfwx68.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316796/GJV0038-1DN-navy-4_j41usg.jpg"]', 19236.79, 'XL', 'SKU0008', 'AVAILABLE', 8);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(15, 'Navy', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316767/GPT0109-1-Navy-3_anxq0n.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316767/GPT0109-1-Navy-4_xggnfl.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316769/GPT0109-1-Navy-2_eg2ml4.jpg"]', 16494.97, 'L', 'SKU0009', 'AVAILABLE',9);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(16, 'Xanh rêu', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316764/GCN0040-1DM-mossgreen-2_ban8np.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316764/GCN0040-1DM-mossgreen-3_c8kkou.jpg"]', -21071.98, 'L', 'SKU0010', 'AVAILABLE', 10);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(17, 'Navy', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316765/GCN0042-1-navy-2_zziuuh.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316766/GCN0042-1-navy-3_sj0pfx.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316765/GCN0042-1-navy-4_snpkyu.jpg"]', -48187.76, 'XL', 'SKU0011', 'AVAILABLE', 11);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(18, 'Cam', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316766/GCN0042-1-orange-1_cqtk4m.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316764/GCN0042-1-orange-2_u93dt9.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316765/GCN0042-1-orange-3_zfqwti.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316766/GCN0042-1-orange-4_r8dkky.jpg"]', -48187.76, 'XL', 'SKU0011', 'AVAILABLE', 11);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(19, 'Xám', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316766/GPV0037-1DR-gray-2_fx1jkd.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316766/GPV0037-1DR-gray-3_htfdfv.jpg"]', -39921.53, 'L', 'SKU0012', 'AVAILABLE', 12);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(20, 'Đen', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316768/BBL0025-1-black-2_zvvyut.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316768/BBL0025-1-black-3_wnd9pw.jpg"]', -14024.07, 'XL', 'SKU0013', 'AVAILABLE', 13);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(21, 'Đen', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316769/OLD0003-1-black-2_zbiswx.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316770/OLD0003-1-black-3_zfvn7g.jpg"]', 21942.42, 'XL', 'SKU0014', 'AVAILABLE', 14);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(22, 'Đỏ', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316769/OLD0003-1-red-1_i4hhuj.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316769/OLD0003-1-red-2_dbram2.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316770/OLD0003-1-red-3_v5orsi.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316770/OLD0003-1-red-4_s9atzn.jpg"]', 21942.42, 'XL', 'SKU0014', 'AVAILABLE', 14);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(23, 'Đen', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316776/BBB0014-1-black-2_xuqe4t.png", "https://res.cloudinary.com/duffcwady/image/upload/v1761316772/BBB0014-1-black-3_rxifet.png", "https://res.cloudinary.com/duffcwady/image/upload/v1761316771/BBB0014-1-black-4_a5vgz3.png"]', 32637.15, 'S', 'SKU0015', 'AVAILABLE', 15);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(24, 'Sữa', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316772/DLD0175-1-milk-2_gmlg9f.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316772/DLD0175-1-milk-3_gbensp.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316773/DLD0175-1-milk-4_pgjsfd.jpg"]', -26764.92, 'S', 'SKU0016', 'AVAILABLE', 16);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(25, 'Hồng', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316773/GTL0014-1-pink-2_wfoiw1.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316774/GTL0014-1-pink-3_hnd70d.jpg"]', 25822.99, 'S', 'SKU0017', 'AVAILABLE', 17);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(26, 'Đen', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316774/BCL0009-1-black-2_c7c5fv.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316774/BCL0009-1-black-3_ze5aqo.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316774/BCL0009-1-black-4_gqekk9.jpg"]', -46516.73, 'L', 'SKU0018', 'AVAILABLE', 18);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(27, 'Xanh navy', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316774/BCL0009-1-navy-1_zcztuf.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316774/BCL0009-1-navy-2_tfjyok.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316775/BCL0009-1-navy-3_ssgwrp.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316775/BCL0009-1-navy-4_oxa5ej.jpg"]', -46516.73, 'L', 'SKU0018', 'AVAILABLE', 18);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(28, 'Vàng', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316776/GWL0060-1-yellow-3_vqhs93.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316776/GWL0060-1-yellow-2_dwr2nz.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316776/GWL0060-1-yellow-4_hyg3c8.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316776/GWL0060-1-yellow-4_hyg3c8.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316777/GWL0060-1-yellow-5_c4hyxo.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316778/GWL0060-1-yellow-6_aosnk0.jpg"]', 40003.95, 'L', 'SKU0019', 'AVAILABLE', 19);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(29, 'Đỏ', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316775/GWL0060-1-red-1_hgvxol.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316775/GWL0060-1-red-3_hwfl6r.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316775/GWL0060-1-red-2_hs0zob.jpg"]', 40003.95, 'L', 'SKU0019', 'AVAILABLE', 19);
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(30, 'Đen', '["https://res.cloudinary.com/duffcwady/image/upload/v1761316778/BWL0017-1-brown-1_esxgky.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316778/BWL0017-1-brown-2_tophzl.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316778/BWL0017-1-brown-3_nrdo1n.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1761316778/BWL0017-1-brown-4_r8nsaj.jpg"]', -17276.69, 'S', 'SKU0020', 'AVAILABLE', 20);
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

-- ========================
-- 9. coupons
-- ========================
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (1, 'SALE001', 'Đơn trên 200k', 'Giảm giá ưu đãi với đơn hàng nhỏ', 'PERCENTAGE', 5, '2021-11-12', 100, '2024-12-17', 'ACTIVE', 2);
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (2, 'SALE002', 'Đơn trên 2sản phẩm', 'Giảm giá ưu đãi double', 'PERCENTAGE', 10, '2023-04-01', 100, '2025-04-23', 'ACTIVE', 10);
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (3, 'SALE003', 'Đơn trên 1 triệu', 'Giảm giá ưu đãi với đơn hàng vừa', 'PERCENTAGE', 15, '2022-03-19', 100, '2024-11-11', 'ACTIVE', 2);
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (4, 'SALE004', 'Đơn trên 2 triệu', 'Giảm giá ưu đãi với đơn hàng lớn', 'FIXED_AMOUNT', 20, '2023-05-13', 100, '2025-11-23', 'INACTIVE', 5);
INSERT INTO coupons (couponid, code, conditions, description, discount_type, discount_value, end_date, max_uses, start_date, status, used_count) VALUES (5, 'SALE005', 'Đơn trên 5 sản phẩm', 'Giảm giá ưu đãi penta', 'FIXED_AMOUNT', 25, '2021-11-26', 100, '2025-09-09', 'EXPIRED', 3);

-- ========================
-- 10. orders
-- ========================
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (1, 'Đơn hàng số 1', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 1061268.61, 2, 5, 1);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (2, 'Đơn hàng số 2', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 1368233.41, 4, 5, 3);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (3, 'Đơn hàng số 3', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 393385.67, 5, 1, 5);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (4, 'Đơn hàng số 4', NOW(), 'DELIVERED', NOW(), 'COD', 'PAID', 30000, 1327459.02, 1, 2, 2);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (5, 'Đơn hàng số 5', NOW(), 'DELIVERED', NOW(), 'VNPAY', 'PENDING', 30000, 1343524.91, 2, 4, 1);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (6, 'Đơn hàng số 6', NOW(), 'DELIVERED', NOW(), 'VNPAY', 'PAID', 30000, 924803.8, 2, 4, 5);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (7, 'Đơn hàng số 7', NOW(), 'DELIVERED', NOW(), 'COD', 'PENDING', 30000, 535666.28, 2, 4, 4);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (8, 'Đơn hàng số 8', NOW(), 'DELIVERED', NOW(), 'VNPAY', 'PAID', 30000, 1430916.23, 5, 2, 3);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (9, 'Đơn hàng số 9', NOW(), 'DELIVERED', NOW(), 'COD', 'PENDING', 30000, 1592299.01, 2, 1, 4);
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES (10, 'Đơn hàng số 10', NOW(), 'PENDING', NOW(), 'VNPAY', 'PAID', 30000, 427258.87, 1, 4, 3);

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
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (5, 1, 766601.18, 526605.3, 6, 3);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (6, 3, 296099.68, 442518.51, 10, 3);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (7, 2, 522515.26, 115250.38, 8, 5);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (8, 1, 629846.88, 238444.75, 7, 15);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (9, 1, 473826.57, 485265.68, 3, 25);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (10, 1, 783388.14, 420252.11, 1, 14);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (11, 2, 669239.82, 235274.96, 5, 3);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (12, 1, 439185.03, 544289.78, 3, 16);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (13, 2, 402523.62, 534818.5, 3, 3);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (14, 3, 723522.15, 264030.75, 2, 3);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (15, 3, 613840.38, 296107.32, 5, 6);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (16, 2, 321384.28, 414279.4, 10, 4);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (17, 3, 660811.6, 572551.98, 7, 3);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (18, 1, 457161.45, 205295.09, 4, 7);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (19, 3, 600687.7, 348367.75, 8, 18);
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES (20, 1, 727196.75, 319916.67, 2, 8);

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
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (6, 3, 5, 30);
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (7, 2, 1, 17);
INSERT INTO cart_items (cart_itemid, quantity, cartid, variantid) VALUES (8, 3, 5, 4);

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
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (1, 'Sản phẩm rất tốt  mặc thoáng mát', '["https://res.cloudinary.com/duffcwady/image/upload/v1763728523/api_mobile/image_btl/products/product_1763728520794_bojrff.jpg"]', 5, NOW(), 'APPROVED', 4, 7);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (2, 'Sản phẩm rẻ, bền', '["https://res.cloudinary.com/duffcwady/image/upload/v1763705621/api_mobile/image_btl/products/product_1763705620585_lx90p7.jpg"]', 4, NOW(), 'APPROVED', 5, 10);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (3, 'sản phẩm tuyệt vời', '["https://res.cloudinary.com/duffcwady/image/upload/v1763592946/api_mobile/image_btl/products/product_1763592944009_xx0ucn.jpg"]', 5, NOW(), 'APPROVED', 4, 4);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (4, 'Sản phẩm hơi nhỏ với size', '["https://res.cloudinary.com/duffcwady/image/upload/v1763632932/api_mobile/image_btl/products/product_1763632929604_fprr2r.jpg"]', 1, NOW(), 'APPROVED', 2, 3);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (5, 'Sản phẩm ship nhất nhanh', '["https://res.cloudinary.com/duffcwady/image/upload/v1763630495/api_mobile/image_btl/products/product_1763630492835_unqat0.jpg"]', 2, NOW(), 'APPROVED', 4, 10);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (6, 'Sản phẩm cũng tạm', '["https://res.cloudinary.com/duffcwady/image/upload/v1763630495/api_mobile/image_btl/products/product_1763630492835_unqat0.jpg"]', 4, NOW(), 'APPROVED', 1, 1);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (7, 'Sản phẩm ổn', '["https://res.cloudinary.com/duffcwady/image/upload/v1763633631/api_mobile/image_btl/products/product_1763633624045_h9e0bv.jpg"]', 4, NOW(), 'APPROVED', 1, 10);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (8, 'Sản phẩm đáng tiền', '["https://res.cloudinary.com/duffcwady/image/upload/v1763633631/api_mobile/image_btl/products/product_1763633624045_h9e0bv.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1763677601/api_mobile/image_btl/products/product_1763677599005_sq0dwq.jpg"]', 3, NOW(), 'INAPPROVED', 3, 6);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (9, 'Sản phẩm không đáng tiền', '["https://res.cloudinary.com/duffcwady/image/upload/v1763678297/api_mobile/image_btl/products/product_1763678294576_frt1ct.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1763633716/api_mobile/image_btl/products/product_1763633713253_zpd77h.jpg"]', 3, NOW(), 'INAPPROVED', 3, 2);
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES (10, 'xịn', '["https://res.cloudinary.com/duffcwady/image/upload/v1763677601/api_mobile/image_btl/products/product_1763677599005_sq0dwq.jpg", "https://res.cloudinary.com/duffcwady/image/upload/v1763686422/api_mobile/image_btl/products/product_1763686419694_p9uyp7.jpg"]', 4, NOW(), 'INAPPROVED', 3, 2);

-- ========================
-- 18. review_responses
-- ========================
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (1, 'Cảm ơn bạn đã đánh giá cho sản phẩm, nếu có thắc mắc gì về sản phẩm hãy liên hệ trực tiếp với cửa hàng chúng tôi.', NOW(), 'VISIBLE', 2, 1);
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (2, 'Cảm ơn bạn đã đánh giá cho sản phẩm, nếu có thắc mắc gì về sản phẩm hãy liên hệ trực tiếp với cửa hàng chúng tôi.', NOW(), 'VISIBLE', 1, 2);
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (3, 'Cảm ơn bạn đã đánh giá cho sản phẩm, nếu có thắc mắc gì về sản phẩm hãy liên hệ trực tiếp với cửa hàng chúng tôi.', NOW(), 'ACTIVE', 3, 3);
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (4, 'Cảm ơn bạn đã đánh giá cho sản phẩm, nếu có thắc mắc gì về sản phẩm hãy liên hệ trực tiếp với cửa hàng chúng tôi.', NOW(), 'VISIBLE', 2, 4);
INSERT INTO review_responses (responseid, response_content, response_date, status, adminid, reviewid) VALUES (5, 'Cảm ơn bạn đã đánh giá cho sản phẩm, nếu có thắc mắc gì về sản phẩm hãy liên hệ trực tiếp với cửa hàng chúng tôi.', NOW(), 'ACTIVE', 3, 5);


-- ================================================================
-- PHẦN 2: DỮ LIỆU BỔ SUNG (DÀNH CHO BÁO CÁO & THỐNG KÊ)
-- ================================================================

-- 1. Thêm tài khoản mới (Khách hàng 6-10)
INSERT INTO accounts (accountid, account_status, email, password, registration_date, role) VALUES
(9, 'ACTIVE', 'hoangvanhau@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', DATE_SUB(NOW(), INTERVAL 60 DAY), 'CUSTOMER'),
(10, 'ACTIVE', 'nguyenthithu@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', DATE_SUB(NOW(), INTERVAL 45 DAY), 'CUSTOMER'),
(11, 'ACTIVE', 'phamminhtuan@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', DATE_SUB(NOW(), INTERVAL 30 DAY), 'CUSTOMER'),
(12, 'ACTIVE', 'dothihong@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', DATE_SUB(NOW(), INTERVAL 15 DAY), 'CUSTOMER'),
(13, 'ACTIVE', 'levanlong@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER');

-- 2. Thêm thông tin khách hàng mới
INSERT INTO customers (customerid, date_of_birth, full_name, gender, loyalty_points, phone_number, referral_code, accountid, created_at) VALUES
(6, '1993-01-15', 'Hoàng Văn Hậu', 'Nam', 50, '0901234567', 'REF006', 9, DATE_SUB(NOW(), INTERVAL 60 DAY)),
(7, '1997-09-20', 'Nguyễn Thị Thu', 'Nữ', 250, '0912345678', 'REF007', 10, DATE_SUB(NOW(), INTERVAL 45 DAY)),
(8, '2000-12-12', 'Phạm Minh Tuấn', 'Nam', 0, '0923456789', 'REF008', 11, DATE_SUB(NOW(), INTERVAL 30 DAY)),
(9, '1995-03-08', 'Đỗ Thị Hồng', 'Nữ', 100, '0934567890', 'REF009', 12, DATE_SUB(NOW(), INTERVAL 15 DAY)),
(10, '1985-07-27', 'Lê Văn Long', 'Nam', 10, '0945678901', 'REF010', 13, NOW());

-- 3. Thêm địa chỉ cho khách hàng mới
INSERT INTO addresses (addressid, city, country, district, is_default, postal_code, recipient_name, recipient_phone, street_address, customerid) VALUES
(6, 'Hải Phòng', 'Việt Nam', 'Ngô Quyền', 1, '180000', 'Hoàng Văn Hậu', '0901234567', '15 Lạch Tray', 6),
(7, 'Đà Lạt', 'Việt Nam', 'Phường 1', 1, '670000', 'Nguyễn Thị Thu', '0912345678', '22 Bùi Thị Xuân', 7),
(8, 'Nha Trang', 'Việt Nam', 'Lộc Thọ', 1, '650000', 'Phạm Minh Tuấn', '0923456789', '10 Trần Phú', 8),
(9, 'Vũng Tàu', 'Việt Nam', 'Phường 3', 1, '790000', 'Đỗ Thị Hồng', '0934567890', '5 Lê Hồng Phong', 9),
(10, 'Biên Hòa', 'Việt Nam', 'Tân Hiệp', 1, '810000', 'Lê Văn Long', '0945678901', '30 Đồng Khởi', 10);

-- 4. Thêm giỏ hàng trống cho khách mới (Logic hệ thống thường tự tạo)
INSERT INTO carts (cartid, total_amount, updated_date, customerid) VALUES 
(6, 0, NOW(), 6),
(7, 0, NOW(), 7),
(8, 0, NOW(), 8),
(9, 0, NOW(), 9),
(10, 0, NOW(), 10);

-- 5. Thêm 5 Sản phẩm mới (Áo khoác, Đầm dạ hội - Đa dạng danh mục)
-- Product ID tiếp theo: 21
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(21, 5.0, 1500000.00, 'Việt Shop Luxury', NOW(), 'Đầm dạ hội cao cấp', 1450000.00, 1, 'Lụa', 'Đầm Dạ Hội Luxury DDH001', 10, 'ACTIVE', 9, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316779/GBZ0015-1NN-navy-1_s62ir9.jpg'),
(22, 4.5, 850000.00, 'Bernini', NOW(), 'Áo khoác gió nam chống nước', 790000.00, 0, 'Polyester', 'Áo Khoác Gió Bernini AKG002', 5, 'ACTIVE', 1, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316794/GJV0038-1DN-navy-1_e4phkh.jpg'),
(23, 4.0, 320000.00, 'Việt Shop', NOW(), 'Chân váy chữ A công sở', 299000.00, 0, 'Kaki', 'Chân Váy Chữ A CVA005', 12, 'ACTIVE', 9, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316772/DLD0175-1-milk-1_l7xhww.jpg'),
(24, 4.8, 450000.00, 'Việt Shop Sport', NOW(), 'Giày thể thao nam chạy bộ', 420000.00, 1, 'Vải dệt', 'Giày Sport Nam GSN008', 20, 'ACTIVE', 3, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316783/BPL0044-1-black-1_z6o2ab.jpg'),
(25, 3.5, 150000.00, 'Basic', NOW(), 'Mũ lưỡi trai unisex', 120000.00, 0, 'Cotton', 'Mũ Lưỡi Trai Basic MLT009', 2, 'ACTIVE', 5, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316768/BBL0025-1-black-1_u9wkjg.jpg');

-- 6. Thêm biến thể (Variants) cho sản phẩm mới (Variant ID tiếp theo: 31)
INSERT INTO product_variants (variantid, color, images, price_adjustment, size, sku, status, productid) VALUES
(31, 'Đỏ', '["https://example.com/img1.jpg"]', 0, 'M', 'SKU0021', 'active', 21),
(32, 'Đỏ', '["https://example.com/img1.jpg"]', 0, 'L', 'SKU0022', 'active', 21),
(33, 'Đen', '["https://example.com/img2.jpg"]', 0, 'XL', 'SKU0023', 'active', 22),
(34, 'Xanh Navy', '["https://example.com/img2.jpg"]', 0, 'L', 'SKU0024', 'active', 22),
(35, 'Đen', '["https://example.com/img3.jpg"]', 0, 'S', 'SKU0025', 'active', 23),
(36, 'Trắng', '["https://example.com/img4.jpg"]', 0, '41', 'SKU0026', 'active', 24),
(37, 'Trắng', '["https://example.com/img4.jpg"]', 0, '42', 'SKU0027', 'active', 24),
(38, 'Đen', '["https://example.com/img5.jpg"]', 0, 'FreeSize', 'SKU0028', 'active', 25);

-- 7. Thêm kho hàng (Inventory) cho sản phẩm mới
INSERT INTO inventory (inventoryid, quantity, reserved_quantity, updated_date, variantid) VALUES 
(31, 9, 0, NOW(), 31), (32, 15, 1, NOW(), 32), (33, 50, 5, NOW(), 33), (34, 40, 2, NOW(), 34),
(35, 7, 0, NOW(), 35), (36, 25, 2, NOW(), 36), (37, 25, 3, NOW(), 37), (38, 100, 0, NOW(), 38);

-- 8. Thêm Đơn hàng (Orders) - Đa dạng trạng thái và thời gian (Order ID tiếp theo: 11)
-- Đơn hàng tháng trước (DELIVERED)
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES 
(11, 'Giao giờ hành chính', DATE_SUB(NOW(), INTERVAL 35 DAY), 'DELIVERED', DATE_SUB(NOW(), INTERVAL 35 DAY), 'VNPAY', 'PAID', 30000, 1480000, NULL, 6, 6),
(12, 'Gọi trước khi giao', DATE_SUB(NOW(), INTERVAL 32 DAY), 'DELIVERED', DATE_SUB(NOW(), INTERVAL 32 DAY), 'COD', 'PAID', 30000, 450000, NULL, 7, 7);

-- Đơn hàng tuần trước (CANCELLED - Lý do thống kê)
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES 
(13, 'Khách đổi ý', DATE_SUB(NOW(), INTERVAL 7 DAY), 'CANCELLED', NULL, 'COD', 'UNPAID', 0, 790000, NULL, 8, 8);

-- Đơn hàng mới hôm qua/nay (PENDING, CONFIRMED, SHIPPING)
INSERT INTO orders (orderid, notes, order_date, order_status, payment_date, payment_method, payment_status, shipping_fee, total_amount, coupon_code, customerid, addressid) VALUES 
(14, 'Gói quà giúp mình', DATE_SUB(NOW(), INTERVAL 2 DAY), 'SHIPPING', DATE_SUB(NOW(), INTERVAL 2 DAY), 'VNPAY', 'PAID', 30000, 299000, 1, 9, 9),
(15, '', DATE_SUB(NOW(), INTERVAL 1 DAY), 'CONFIRMED', NULL, 'COD', 'PENDING', 30000, 840000, NULL, 10, 10),
(16, 'Giao nhanh', NOW(), 'PENDING', NULL, 'COD', 'PENDING', 30000, 120000, NULL, 6, 6);

-- 9. Thêm Chi tiết đơn hàng (Order Items) khớp với Orders mới
INSERT INTO order_items (order_itemid, quantity, sub_total, unit_price, orderid, variantid) VALUES 
(21, 1, 1450000, 1450000, 11, 31), -- Order 11
(22, 1, 420000, 420000, 12, 36),  -- Order 12
(23, 1, 790000, 790000, 13, 33),  -- Order 13 (Cancelled)
(24, 1, 299000, 299000, 14, 35),  -- Order 14
(25, 2, 840000, 420000, 15, 37),  -- Order 15
(26, 1, 120000, 120000, 16, 38);  -- Order 16

-- 10. Thêm Shipping cho các đơn hàng đã xác nhận/giao
INSERT INTO shipping (shippingid, carrier, estimated_delivery_date, shipping_fee, shipping_status, tracking_number, orderid) VALUES 
(11, 'Giao Hàng Nhanh', DATE_SUB(NOW(), INTERVAL 33 DAY), 30000, 'Đã giao', 'GHN00011', 11),
(12, 'Viettel Post', DATE_SUB(NOW(), INTERVAL 30 DAY), 30000, 'Đã giao', 'VTP00001', 12),
(13, 'Giao Hàng Tiết Kiệm', DATE_ADD(NOW(), INTERVAL 2 DAY), 30000, 'Đang giao', 'GHTK0001', 14);

-- 11. Thêm Reviews (Khen/Chê) để test chức năng đánh giá
INSERT INTO reviews (reviewid, comment, images, rating, review_date, status, customerid, productid) VALUES 
(11, 'Đầm đẹp xuất sắc, vải mịn', NULL, 5, NOW(), 'APPROVED', 6, 21),
(12, 'Áo khoác hơi mỏng so với ảnh', NULL, 3, NOW(), 'APPROVED', 7, 22),
(13, 'Giày đi êm chân, giao hàng nhanh', NULL, 5, NOW(), 'APPROVED', 7, 24),
(14, 'Chất lượng tệ, chỉ thừa nhiều', NULL, 2, NOW(), 'APPROVED', 9, 25);