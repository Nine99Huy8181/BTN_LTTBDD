-- DỮ LIỆU MẪU HỆ THỐNG THƯƠNG MẠI ĐIỆN TỬ (ĐẦY ĐỦ)
-- Sinh bởi ChatGPT - Ngày tạo: 2025-10-18 15:18:32

-- ========================
-- 1. accounts
-- ========================
INSERT INTO accounts (accountid, account_status, email, password, registration_date, role) VALUES
(1, 'ACTIVE', 'nguyenvana@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER'),
(2, 'ACTIVE', 'tranthib@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER'),
(3, 'ACTIVE', 'leminhc@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER'),
(4, 'ACTIVE', 'phamduct@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER'),
(5, 'ACTIVE', 'hoanglan@gmail.com', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'CUSTOMER'),
(6, 'ACTIVE', 'admin1@shop.vn', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'ADMIN'),
(7, 'ACTIVE', 'admin2@shop.vn', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'ADMIN'),
(8, 'ACTIVE', 'superadmin@shop.vn', '$2a$10$0ANzrYbqco74VKmQ0PVD1enby0phRXLnW9FVJfsuwzhqKCzJ5BoiS', NOW(), 'SUPER');

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
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(1, 3.8, 258646.17, 'Việt Shop', NOW(), 'Áo blazer nam', 271799.97, 1, 'Cotton', 'Áo blazer nam GBZ0015-1NN', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316779/GBZ0015-1NN-navy-1_s62ir9.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(2, 3.8, 258646.17, 'Việt Shop', NOW(), 'Áo phông Golf dài tay nữ', 271799.97, 1, 'Cotton', 'Áo phông Golf dài tay nữ  DLP0003-1OB', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316780/DLP0003-1OB-blue-1_kvznnd.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(3, 3.8, 258646.17, 'Việt Shop', NOW(), 'Áo phông Golf dài tay nữ', 271799.97, 1, 'Cotton', 'Áo phông Golf dài tay nữ DLP0004-1BD', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316780/DLP0004-1BD-red-1_bnqa1b.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(4, 3.8, 258646.17, 'Việt Shop', NOW(), 'Áo polo cộc tay nam', 271799.97, 1, 'Cotton', 'Áo polo cộc tay nam Bernini BPL0033-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316782/BPL003-1-blue-1_i0dejq.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(5, 3.8, 258646.17, 'Việt Shop', NOW(), 'Áo Polo cộc tay nam', 271799.97, 1, 'Cotton', 'Áo Polo cộc tay nam Bernini BPL0044-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316789/BPL0044-1-white-1_wxzf3z.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(6, 3.8, 258646.17, 'Việt Shop', NOW(), 'Áo polo cộc tay nam', 271799.97, 1, 'Cotton', 'Áo polo cộc tay nam Bernini BPL0045-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316791/BPL0045-1-white-1_omqayt.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(7, 3.8, 258646.17, 'Việt Shop', NOW(), 'Áo Sơ Mi Cộc Tay Nam', 271799.97, 1, 'Cotton', 'Áo Sơ Mi Cộc Tay Nam GSS0173-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316795/GSS0173-1-pink-1_twsuvk.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(8, 3.8, 258646.17, 'Việt Shop', NOW(), 'Áo vest nam', 271799.97, 1, 'Cotton', 'Áo vest nam GJV0038-1DN', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316794/GJV0038-1DN-navy-1_e4phkh.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(9, 3.8, 258646.17, 'Việt Shop', NOW(), 'Quần Âu Nam', 271799.97, 1, 'Cotton', 'Quần Âu Nam GPT0109-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316767/GPT0109-1-Navy-1_kr49yy.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(10, 3.8, 258646.17, 'Việt Shop', NOW(), 'Quần Khaki Nam', 271799.97, 1, 'Cotton', 'Quần Khaki Nam GCN0040-1DM', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316764/GCN0040-1DM-mossgreen-1_lu4rui.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(11, 3.8, 258646.17, 'Việt Shop', NOW(), 'Quần Khaki Nam', 271799.97, 1, 'Cotton', 'Quần Khaki Nam GCN0042-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316764/GCN0042-1-navy-1_xxdv06.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(12, 3.8, 258646.17, 'Việt Shop', NOW(), 'Quần Vest Nam', 271799.97, 1, 'Cotton', 'Quần Vest Nam GPV0037-1DR', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316766/GPV0037-1DR-gray-1_ortuvp.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(13, 3.8, 258646.17, 'Việt Shop', NOW(), 'Dây Lưng Nam', 271799.97, 1, 'Cotton', 'Dây Lưng Nam BBL0025-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316769/BBL0025-1-black-1_u9wkjg.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(14, 3.8, 258646.17, 'Việt Shop', NOW(), 'Túi Nữ', 271799.97, 1, 'Cotton', 'Túi Nữ OLD0003-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316768/OLD0003-1-black-1_gzpnlm.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(15, 3.8, 258646.17, 'Việt Shop', NOW(), 'Túi xách nam', 271799.97, 1, 'Cotton', 'Túi xách nam Bernini BBB0014-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316771/BBB0014-1-black-1_ocqczd.png');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(16, 3.8, 258646.17, 'Việt Shop', NOW(), 'Túi xách nữ', 271799.97, 1, 'Cotton', 'Túi xách nữ DLD0175-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316771/DLD0175-1-milk-1_l7xhww.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(17, 3.8, 258646.17, 'Việt Shop', NOW(), 'Vali GIOVANNI', 271799.97, 1, 'Cotton', 'Vali GIOVANNI GTL0014-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316773/GTL0014-1-pink-1_comvoc.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(18, 3.8, 258646.17, 'Việt Shop', NOW(), 'Bóp Tay Nam', 271799.97, 1, 'Cotton', 'Bóp Tay Nam BCL0009-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316773/BCL0009-1-black-1_jdjzx5.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(19, 3.8, 258646.17, 'Việt Shop', NOW(), 'Ví da nữ', 271799.97, 1, 'Cotton', 'Ví da nữ GWL0060-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316776/GWL0060-1-yellow-1_f49yz6.jpg');
INSERT INTO products (productid, average_rating, base_price, brand, created_date, description, discount_price, is_featured, material, name, review_count, status, categoryid, image) VALUES
(20, 3.8, 258646.17, 'Việt Shop', NOW(), 'Ví nam Bernini', 271799.97, 1, 'Cotton', 'Ví nam Bernini BWL0017-1', 197, 'ACTIVE', 6, 'https://res.cloudinary.com/duffcwady/image/upload/v1761316777/BWL0017-1-black-1_b3i3xo.jpg');

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
