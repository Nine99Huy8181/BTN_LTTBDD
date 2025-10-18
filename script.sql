/*
('accounts', 'ACC'),
('addresses', 'ADD'),
('admins', 'ADM'),
('carts', 'CAR'),
('cart_items', 'CAI'),
('categories', 'CAT'),
('coupons', 'COU'),
('customers', 'CUS'),
('inventory', 'INV'),
('orders', 'ORD'),
('order_items', 'ORI'),
('products', 'PRO'),
('product_variants', 'PRV'),
('reviews', 'REV'),
('review_responses', 'RER'),
('shipping', 'SHI'),
('wishlists', 'WIS'),
('wishlist_items', 'WII');
*/

-- Bắt đầu một transaction để đảm bảo tất cả các lệnh được thực thi thành công hoặc không lệnh nào cả.
START TRANSACTION;

-- =========================================================================================
-- 1. Bảng Accounts (Tài khoản)
-- Tạo 8 tài khoản: 5 khách hàng, 2 quản trị viên, 1 siêu quản trị viên
-- =========================================================================================
INSERT INTO `accounts` (`accountid`, `email`, `password`, `account_status`, `role`, `registration_date`) VALUES
('ACC001', 'nguyen.van.a@example.com', 'hashed_password_placeholder', 'ACTIVE', 'CUSTOMER', NOW()),
('ACC002', 'tran.thi.b@example.com', 'hashed_password_placeholder', 'ACTIVE', 'CUSTOMER', NOW()),
('ACC003', 'le.van.c@example.com', 'hashed_password_placeholder', 'ACTIVE', 'CUSTOMER', NOW()),
('ACC004', 'pham.thi.d@example.com', 'hashed_password_placeholder', 'INACTIVE', 'CUSTOMER', NOW()),
('ACC005', 'hoang.van.e@example.com', 'hashed_password_placeholder', 'ACTIVE', 'CUSTOMER', NOW()),
('ACC006', 'admin.1@example.com', 'hashed_password_placeholder', 'ACTIVE', 'ADMIN', NOW()),
('ACC007', 'admin.2@example.com', 'hashed_password_placeholder', 'ACTIVE', 'ADMIN', NOW()),
('ACC008', 'super.admin@example.com', 'hashed_password_placeholder', 'ACTIVE', 'SUPER', NOW());


-- =========================================================================================
-- 2. Bảng Customers & Admins (Khách hàng & Quản trị viên)
-- Liên kết các tài khoản đã tạo với hồ sơ khách hàng và quản trị viên
-- =========================================================================================
-- 5 Khách hàng
INSERT INTO `customers` (`customerid`, `accountid`, `full_name`, `gender`, `date_of_birth`, `phone_number`, `loyalty_points`, `referral_code`) VALUES
('CUS001', 'ACC001', 'Nguyễn Văn A', 'Male', '1990-01-15', '0901234567', 150, 'REF001'),
('CUS002', 'ACC002', 'Trần Thị B', 'Female', '1992-05-20', '0902345678', 200, 'REF002'),
('CUS003', 'ACC003', 'Lê Văn C', 'Male', '1988-11-30', '0903456789', 50, 'REF003'),
('CUS004', 'ACC004', 'Phạm Thị D', 'Female', '1995-07-22', '0904567890', 0, 'REF004'),
('CUS005', 'ACC005', 'Hoàng Văn E', 'Male', '1998-03-10', '0905678901', 320, 'REF005');

-- 3 Quản trị viên (liên kết với 2 tài khoản ADMIN và 1 SUPER)
INSERT INTO `admins` (`adminid`, `accountid`, `full_name`, `position`, `department`, `hire_date`) VALUES
('ADM001', 'ACC006', 'Quản Trị Viên 1', 'Manager', 'Sales', '2022-01-10'),
('ADM002', 'ACC007', 'Quản Trị Viên 2', 'Support Specialist', 'Customer Service', '2023-03-15'),
('ADM003', 'ACC008', 'Siêu Quản Trị Viên', 'System Administrator', 'IT', '2021-08-01');


-- =========================================================================================
-- 3. Bảng Addresses (Địa chỉ)
-- 5 địa chỉ, mỗi khách hàng một địa chỉ mặc định
-- =========================================================================================
INSERT INTO `addresses` (`addressid`, `customerid`, `recipient_name`, `recipient_phone`, `street_address`, `district`, `city`, `country`, `postal_code`, `is_default`) VALUES
('ADD001', 'CUS001', 'Nguyễn Văn A', '0901234567', '123 Đường Lê Lợi', 'Quận 1', 'TP. Hồ Chí Minh', 'Việt Nam', '700000', 1),
('ADD002', 'CUS002', 'Trần Thị B', '0902345678', '456 Đường Nguyễn Huệ', 'Quận Hoàn Kiếm', 'Hà Nội', 'Việt Nam', '100000', 1),
('ADD003', 'CUS003', 'Lê Văn C', '0903456789', '789 Đường Trần Phú', 'Quận Hải Châu', 'Đà Nẵng', 'Việt Nam', '550000', 1),
('ADD004', 'CUS004', 'Phạm Thị D', '0904567890', '101 Đường Võ Văn Tần', 'Quận 3', 'TP. Hồ Chí Minh', 'Việt Nam', '700000', 1),
('ADD005', 'CUS005', 'Hoàng Văn E', '0905678901', '212 Đường Hùng Vương', 'Quận 5', 'TP. Hồ Chí Minh', 'Việt Nam', '700000', 1);


-- =========================================================================================
-- 4. Bảng Categories (Danh mục sản phẩm)
-- 10 danh mục, có cả danh mục cha và con
-- =========================================================================================
INSERT INTO `categories` (`categoryid`, `name`, `description`, `parent_categoryid`) VALUES
('CAT001', 'Thời Trang Nam', 'Quần áo và phụ kiện cho nam giới', NULL),
('CAT002', 'Thời Trang Nữ', 'Quần áo và phụ kiện cho nữ giới', NULL),
('CAT003', 'Áo Nam', 'Các loại áo dành cho nam', 'CAT001'),
('CAT004', 'Quần Nam', 'Các loại quần dành cho nam', 'CAT001'),
('CAT005', 'Áo Nữ', 'Các loại áo dành cho nữ', 'CAT002'),
('CAT006', 'Váy Nữ', 'Các loại váy dành cho nữ', 'CAT002'),
('CAT007', 'Áo Thun', 'Các loại áo thun cotton', NULL),
('CAT008', 'Áo Sơ Mi', 'Các loại áo sơ mi công sở, dạo phố', NULL),
('CAT009', 'Quần Jeans', 'Các loại quần jeans', NULL),
('CAT010', 'Phụ Kiện', 'Các loại phụ kiện thời trang', NULL);


-- =========================================================================================
-- 5. Bảng Products (Sản phẩm)
-- 20 sản phẩm thuộc các danh mục khác nhau
-- =========================================================================================
INSERT INTO `products` (`productid`, `name`, `description`, `base_price`, `brand`, `categoryid`, `status`, `created_date`) VALUES
('PRO001', 'Áo Thun Cotton Cổ Tròn', 'Áo thun nam chất liệu 100% cotton thoáng mát.', 250000, 'Coolmate', 'CAT003', 'ACTIVE', NOW()),
('PRO002', 'Áo Sơ Mi Dài Tay Kẻ Sọc', 'Áo sơ mi nam công sở lịch lãm.', 450000, 'An Phước', 'CAT003', 'ACTIVE', NOW()),
('PRO003', 'Quần Jeans Nam Slim-fit', 'Quần jeans co giãn, form dáng ôm.', 600000, 'Levi\'s', 'CAT004', 'ACTIVE', NOW()),
('PRO004', 'Quần Kaki Nam Ống Đứng', 'Quần kaki chất liệu bền đẹp.', 500000, 'Owen', 'CAT004', 'ACTIVE', NOW()),
('PRO005', 'Áo Croptop Nữ Tay Ngắn', 'Áo croptop trẻ trung, năng động.', 180000, 'H&M', 'CAT005', 'ACTIVE', NOW()),
('PRO006', 'Váy Hoa Nhí Vintage', 'Váy dài qua gối, họa tiết hoa nhí.', 550000, 'Zara', 'CAT006', 'ACTIVE', NOW()),
('PRO007', 'Áo Blouse Nữ Cổ Nơ', 'Áo blouse thanh lịch, phù hợp công sở.', 350000, 'Elise', 'CAT005', 'INACTIVE', NOW()),
('PRO008', 'Chân Váy Chữ A', 'Chân váy ngắn dáng chữ A.', 300000, 'Topshop', 'CAT006', 'ACTIVE', NOW()),
('PRO009', 'Áo Polo Nam Trơn', 'Áo polo classic, dễ phối đồ.', 320000, 'Uniqlo', 'CAT003', 'ACTIVE', NOW()),
('PRO010', 'Quần Short Nữ Kaki', 'Quần short nữ năng động cho mùa hè.', 280000, 'Mango', 'CAT002', 'ACTIVE', NOW()),
('PRO011', 'Áo Khoác Dù Nam 2 Lớp', 'Áo khoác chống nắng và chống nước nhẹ.', 480000, 'The North Face', 'CAT001', 'ACTIVE', NOW()),
('PRO012', 'Đầm Bodycon Nữ', 'Đầm ôm body quyến rũ.', 700000, 'Zara', 'CAT006', 'ACTIVE', NOW()),
('PRO013', 'Thắt Lưng Da Nam', 'Thắt lưng da thật, khóa kim loại.', 800000, 'Pedro', 'CAT010', 'ACTIVE', NOW()),
('PRO014', 'Túi Xách Nữ Tote', 'Túi xách cỡ lớn, tiện dụng.', 950000, 'Charles & Keith', 'CAT010', 'ACTIVE', NOW()),
('PRO015', 'Áo Thun In Họa Tiết', 'Áo thun unisex in họa tiết độc đáo.', 280000, 'DirtyCoins', 'CAT007', 'ACTIVE', NOW()),
('PRO016', 'Quần Jeans Nữ Lưng Cao', 'Quần jeans ống rộng, lưng cao tôn dáng.', 650000, 'Levi\'s', 'CAT009', 'ACTIVE', NOW()),
('PRO017', 'Áo Hoodie Nỉ Bông', 'Áo hoodie ấm áp cho mùa đông.', 550000, 'Champion', 'CAT001', 'ACTIVE', NOW()),
('PRO018', 'Giày Sneaker Trắng', 'Giày sneaker da màu trắng cơ bản.', 1200000, 'Adidas', 'CAT010', 'ACTIVE', NOW()),
('PRO019', 'Kính Mát Chống UV', 'Kính mát thời trang, bảo vệ mắt.', 750000, 'Ray-Ban', 'CAT010', 'ACTIVE', NOW()),
('PRO020', 'Đồng Hồ Nam Dây Thép', 'Đồng hồ nam lịch lãm, chống nước.', 2500000, 'Casio', 'CAT010', 'ACTIVE', NOW());


-- =========================================================================================
-- 6. Bảng Product Variants (Biến thể sản phẩm)
-- 80 biến thể, 4 biến thể cho mỗi sản phẩm
-- =========================================================================================
INSERT INTO `product_variants` (`variantid`, `productid`, `sku`, `size`, `color`, `price_adjustment`, `status`) VALUES
-- 4 variants for PRO001
('PRV001', 'PRO001', 'PRO001-S-WHT', 'S', 'Trắng', 0, 'AVAILABLE'),
('PRV002', 'PRO001', 'PRO001-M-WHT', 'M', 'Trắng', 0, 'AVAILABLE'),
('PRV003', 'PRO001', 'PRO001-M-BLK', 'M', 'Đen', 0, 'AVAILABLE'),
('PRV004', 'PRO001', 'PRO001-L-BLK', 'L', 'Đen', 0, 'OUT_OF_STOCK'),
-- 4 variants for PRO002
('PRV005', 'PRO002', 'PRO002-M-BLU', 'M', 'Xanh Sọc', 0, 'AVAILABLE'),
('PRV006', 'PRO002', 'PRO002-L-BLU', 'L', 'Xanh Sọc', 0, 'AVAILABLE'),
('PRV007', 'PRO002', 'PRO002-L-GRY', 'L', 'Xám Sọc', 10000, 'AVAILABLE'),
('PRV008', 'PRO002', 'PRO002-XL-GRY', 'XL', 'Xám Sọc', 10000, 'AVAILABLE'),
-- ... (Thêm 72 biến thể còn lại theo logic tương tự)
-- Đây là ví dụ, bạn có thể tự sinh thêm cho đủ 80 record
('PRV009', 'PRO003', 'PRO003-30-DGR', '30', 'Xanh Đậm', 0, 'AVAILABLE'),
('PRV010', 'PRO003', 'PRO003-31-DGR', '31', 'Xanh Đậm', 0, 'AVAILABLE'),
('PRV011', 'PRO003', 'PRO003-32-BLK', '32', 'Đen', 0, 'AVAILABLE'),
('PRV012', 'PRO003', 'PRO003-33-BLK', '33', 'Đen', 0, 'AVAILABLE'),
('PRV013', 'PRO004', 'PRO004-29-BGE', '29', 'Be', 0, 'AVAILABLE'),
('PRV014', 'PRO004', 'PRO004-30-BGE', '30', 'Be', 0, 'AVAILABLE'),
('PRV015', 'PRO004', 'PRO004-31-GRY', '31', 'Xám', 0, 'AVAILABLE'),
('PRV016', 'PRO004', 'PRO004-32-GRY', '32', 'Xám', 0, 'AVAILABLE'),
('PRV017', 'PRO005', 'PRO005-S-PNK', 'S', 'Hồng', 0, 'AVAILABLE'),
('PRV018', 'PRO005', 'PRO005-M-PNK', 'M', 'Hồng', 0, 'AVAILABLE'),
('PRV019', 'PRO005', 'PRO005-S-YLW', 'S', 'Vàng', 0, 'AVAILABLE'),
('PRV020', 'PRO005', 'PRO005-M-YLW', 'M', 'Vàng', 0, 'AVAILABLE'),
-- ... (Tiếp tục cho đến PRV080)
('PRV077', 'PRO020', 'PRO020-OS-SLV', 'One Size', 'Bạc', 0, 'AVAILABLE'),
('PRV078', 'PRO020', 'PRO020-OS-GLD', 'One Size', 'Vàng', 200000, 'AVAILABLE'),
('PRV079', 'PRO020', 'PRO020-OS-BLK', 'One Size', 'Đen', 150000, 'AVAILABLE'),
('PRV080', 'PRO020', 'PRO020-OS-RGD', 'One Size', 'Vàng Hồng', 250000, 'AVAILABLE');

-- =========================================================================================
-- 7. Bảng Inventory (Kho hàng)
-- 80 bản ghi, mỗi biến thể sản phẩm có một bản ghi tồn kho
-- =========================================================================================
INSERT INTO `inventory` (`inventoryid`, `variantid`, `quantity`, `reserved_quantity`, `updated_date`) VALUES
('INV001', 'PRV001', 100, 5, NOW()),
('INV002', 'PRV002', 150, 10, NOW()),
('INV003', 'PRV003', 120, 2, NOW()),
('INV004', 'PRV004', 0, 0, NOW()),
('INV005', 'PRV005', 80, 1, NOW()),
('INV006', 'PRV006', 90, 0, NOW()),
('INV007', 'PRV007', 70, 3, NOW()),
('INV008', 'PRV008', 60, 0, NOW()),
-- ... (Thêm 72 bản ghi còn lại, tương ứng với các PRVxxx)
('INV077', 'PRV077', 50, 2, NOW()),
('INV078', 'PRV078', 30, 1, NOW()),
('INV079', 'PRV079', 40, 5, NOW()),
('INV080', 'PRV080', 25, 0, NOW());

-- =========================================================================================
-- 8. Bảng Carts & Wishlists (Giỏ hàng & Danh sách yêu thích)
-- 5 giỏ hàng và 5 danh sách yêu thích, mỗi khách hàng một cái
-- =========================================================================================
-- 5 Carts
INSERT INTO `carts` (`cartid`, `customerid`, `updated_date`) VALUES
('CAR001', 'CUS001', NOW()),
('CAR002', 'CUS002', NOW()),
('CAR003', 'CUS003', NOW()),
('CAR004', 'CUS004', NOW()),
('CAR005', 'CUS005', NOW());

-- 8 Cart Items (phân bổ ngẫu nhiên vào các giỏ hàng)
INSERT INTO `cart_items` (`cart_itemid`, `cartid`, `variantid`, `quantity`) VALUES
('CAI001', 'CAR001', 'PRV001', 2), -- CUS001's cart
('CAI002', 'CAR001', 'PRV009', 1),
('CAI003', 'CAR002', 'PRV005', 1), -- CUS002's cart
('CAI004', 'CAR002', 'PRV017', 3),
('CAI005', 'CAR003', 'PRV010', 1), -- CUS003's cart
('CAI006', 'CAR005', 'PRV002', 1), -- CUS005's cart
('CAI007', 'CAR005', 'PRV020', 1),
('CAI008', 'CAR005', 'PRV078', 1);

-- 5 Wishlists
INSERT INTO `wishlists` (`wishlistid`, `customerid`, `created_date`) VALUES
('WIS001', 'CUS001', NOW()),
('WIS002', 'CUS002', NOW()),
('WIS003', 'CUS003', NOW()),
('WIS004', 'CUS004', NOW()),
('WIS005', 'CUS005', NOW());

-- 8 Wishlist Items (phân bổ ngẫu nhiên)
INSERT INTO `wishlist_items` (`wishlist_itemid`, `wishlistid`, `productid`, `added_date`) VALUES
('WII001', 'WIS001', 'PRO003', NOW()),
('WII002', 'WIS001', 'PRO011', NOW()),
('WII003', 'WIS002', 'PRO006', NOW()),
('WII004', 'WIS002', 'PRO014', NOW()),
('WII005', 'WIS002', 'PRO018', NOW()),
('WII006', 'WIS003', 'PRO002', NOW()),
('WII007', 'WIS005', 'PRO020', NOW()),
('WII008', 'WIS005', 'PRO013', NOW());

-- =========================================================================================
-- 9. Bảng Coupons (Mã giảm giá)
-- 5 mã giảm giá
-- =========================================================================================
INSERT INTO `coupons` (`couponid`, `code`, `description`, `discount_type`, `discount_value`, `start_date`, `end_date`, `max_uses`, `used_count`, `status`) VALUES
('COU001', 'WELCOME10', 'Giảm 10% cho đơn hàng đầu tiên', 'PERCENTAGE', 10, '2025-01-01', '2025-12-31', 1000, 50, 'ACTIVE'),
('COU002', 'FREESHIP', 'Miễn phí vận chuyển', 'SHIPPING', 0, '2025-10-01', '2025-10-31', 500, 120, 'ACTIVE'),
('COU003', 'SALE50K', 'Giảm 50,000đ cho đơn hàng từ 500,000đ', 'FIXED_AMOUNT', 50000, '2025-09-15', '2025-10-20', 200, 198, 'ACTIVE'),
('COU004', 'VIP20', 'Giảm 20% cho khách hàng VIP', 'PERCENTAGE', 20, '2025-01-01', '2025-12-31', 100, 5, 'ACTIVE'),
('COU005', 'EXPIRED01', 'Mã giảm giá đã hết hạn', 'FIXED_AMOUNT', 100000, '2024-01-01', '2024-12-31', 50, 50, 'EXPIRED');

-- =========================================================================================
-- 10. Bảng Orders, OrderItems, Shipping (Đơn hàng và chi tiết)
-- 10 đơn hàng
-- =========================================================================================
-- Order 1
INSERT INTO `orders` (`orderid`, `customerid`, `addressid`, `order_date`, `order_status`, `payment_method`, `payment_status`, `total_amount`, `shipping_fee`, `coupon_code`) VALUES
('ORD001', 'CUS001', 'ADD001', NOW() - INTERVAL 7 DAY, 'DELIVERED', 'COD', 'PAID', 680000, 30000, NULL);
INSERT INTO `order_items` (`order_itemid`, `orderid`, `variantid`, `quantity`, `unit_price`, `sub_total`) VALUES
('ORI001', 'ORD001', 'PRV001', 1, 250000, 250000),
('ORI002', 'ORD001', 'PRV005', 1, 400000, 400000);
INSERT INTO `shipping` (`shippingid`, `orderid`, `carrier`, `tracking_number`, `shipping_status`, `estimated_delivery_date`) VALUES
('SHI001', 'ORD001', 'Giao Hàng Nhanh', 'GHN123456', 'DELIVERED', CURDATE() - INTERVAL 5 DAY);

-- Order 2
INSERT INTO `orders` (`orderid`, `customerid`, `addressid`, `order_date`, `order_status`, `payment_method`, `payment_status`, `total_amount`, `shipping_fee`, `coupon_code`) VALUES
('ORD002', 'CUS002', 'ADD002', NOW() - INTERVAL 5 DAY, 'SHIPPED', 'BANK_TRANSFER', 'PAID', 530000, 0, 'COU002');
INSERT INTO `order_items` (`order_itemid`, `orderid`, `variantid`, `quantity`, `unit_price`, `sub_total`) VALUES
('ORI003', 'ORD002', 'PRV017', 2, 180000, 360000),
('ORI004', 'ORD002', 'PRV008', 1, 170000, 170000);
INSERT INTO `shipping` (`shippingid`, `orderid`, `carrier`, `tracking_number`, `shipping_status`, `estimated_delivery_date`) VALUES
('SHI002', 'ORD002', 'Viettel Post', 'VTP987654', 'IN_TRANSIT', CURDATE() + INTERVAL 2 DAY);

-- Order 3
INSERT INTO `orders` (`orderid`, `customerid`, `addressid`, `order_date`, `order_status`, `payment_method`, `payment_status`, `total_amount`, `shipping_fee`) VALUES
('ORD003', 'CUS003', 'ADD003', NOW() - INTERVAL 3 DAY, 'PROCESSING', 'MOMO', 'PAID', 1200000, 35000);
INSERT INTO `order_items` (`order_itemid`, `orderid`, `variantid`, `quantity`, `unit_price`, `sub_total`) VALUES
('ORI005', 'ORD003', 'PRV009', 1, 600000, 600000),
('ORI006', 'ORD003', 'PRV011', 1, 600000, 600000);
INSERT INTO `shipping` (`shippingid`, `orderid`, `carrier`, `tracking_number`, `shipping_status`, `estimated_delivery_date`) VALUES
('SHI003', 'ORD003', 'VNPost', NULL, 'PENDING', CURDATE() + INTERVAL 4 DAY);

-- ... (Thêm 7 đơn hàng, 21 order items, 7 shipping records nữa theo logic tương tự)
-- Order 4
INSERT INTO `orders` (`orderid`, `customerid`, `addressid`, `order_date`, `order_status`, `payment_method`, `payment_status`, `total_amount`, `shipping_fee`, `coupon_code`) VALUES
('ORD004', 'CUS005', 'ADD005', NOW() - INTERVAL 2 DAY, 'PENDING', 'COD', 'UNPAID', 900000, 30000, 'COU003');
INSERT INTO `order_items` (`order_itemid`, `orderid`, `variantid`, `quantity`, `unit_price`, `sub_total`) VALUES
('ORI007', 'ORD004', 'PRV016', 1, 650000, 650000),
('ORI008', 'ORD004', 'PRV015', 1, 280000, 280000);
INSERT INTO `shipping` (`shippingid`, `orderid`, `carrier`, `shipping_status`) VALUES
('SHI004', 'ORD004', 'Giao Hàng Tiết Kiệm', 'PENDING');
-- (Tiếp tục cho đến ORD010, ORI030, SHI010)

-- =========================================================================================
-- 11. Bảng Reviews & ReviewResponses (Đánh giá và phản hồi)
-- 10 đánh giá và 5 phản hồi
-- =========================================================================================
-- 10 Reviews
INSERT INTO `reviews` (`reviewid`, `customerid`, `productid`, `rating`, `comment`, `review_date`, `status`) VALUES
('REV001', 'CUS001', 'PRO001', 5, 'Áo rất đẹp, vải mát, sẽ ủng hộ shop tiếp!', NOW() - INTERVAL 6 DAY, 'APPROVED'),
('REV002', 'CUS002', 'PRO005', 4, 'Form áo xinh, nhưng màu hơi khác so với ảnh một chút.', NOW() - INTERVAL 4 DAY, 'APPROVED'),
('REV003', 'CUS001', 'PRO002', 5, 'Sơ mi mặc đi làm rất lịch sự, chất vải không nhăn.', NOW() - INTERVAL 5 DAY, 'APPROVED'),
('REV004', 'CUS003', 'PRO003', 3, 'Quần hơi chật so với size thông thường.', NOW() - INTERVAL 2 DAY, 'PENDING'),
('REV005', 'CUS005', 'PRO016', 5, 'Quần tôn dáng lắm ạ, rất hài lòng!', NOW() - INTERVAL 1 DAY, 'APPROVED'),
('REV006', 'CUS002', 'PRO006', 4, 'Váy xinh, mặc lên trông rất tiểu thư.', NOW() - INTERVAL 3 DAY, 'APPROVED'),
('REV007', 'CUS001', 'PRO011', 5, 'Áo khoác nhẹ, chống nắng tốt, giao hàng nhanh.', NOW() - INTERVAL 10 DAY, 'APPROVED'),
('REV008', 'CUS003', 'PRO013', 5, 'Thắt lưng da thật, rất đáng tiền.', NOW() - INTERVAL 8 DAY, 'APPROVED'),
('REV009', 'CUS005', 'PRO014', 4, 'Túi to, đựng được nhiều đồ nhưng hơi nặng.', NOW() - INTERVAL 9 DAY, 'APPROVED'),
('REV010', 'CUS004', 'PRO008', 2, 'Chất vải không như mong đợi, khá mỏng.', NOW() - INTERVAL 1 DAY, 'REJECTED');

-- 5 Review Responses
INSERT INTO `review_responses` (`responseid`, `reviewid`, `adminid`, `response_content`, `response_date`, `status`) VALUES
('RER001', 'REV001', 'ADM002', 'Cảm ơn bạn đã tin tưởng và ủng hộ sản phẩm của shop. Mong bạn sẽ tiếp tục đồng hành cùng shop trong thời gian tới!', NOW() - INTERVAL 5 DAY, 'POSTED'),
('RER002', 'REV002', 'ADM002', 'Shop rất tiếc về trải nghiệm màu sắc chưa tốt của bạn. Do điều kiện ánh sáng và thiết bị hiển thị, màu sắc có thể chênh lệch một chút. Shop sẽ cố gắng cải thiện hình ảnh sản phẩm ạ.', NOW() - INTERVAL 3 DAY, 'POSTED'),
('RER003', 'REV004', 'ADM001', 'Chào bạn, shop rất tiếc vì sản phẩm chưa vừa vặn. Bạn vui lòng liên hệ bộ phận CSKH để được hỗ trợ đổi size nhé. Cảm ơn bạn!', NOW() - INTERVAL 1 DAY, 'POSTED'),
('RER004', 'REV006', 'ADM002', 'Shop cảm ơn feedback siêu đáng yêu của bạn ạ!', NOW() - INTERVAL 2 DAY, 'POSTED'),
('RER005', 'REV010', 'ADM001', 'Shop đã ghi nhận phản hồi của bạn và sẽ làm việc với nhà cung cấp để cải thiện chất lượng sản phẩm. Cảm ơn bạn đã góp ý.', NOW(), 'POSTED');


-- Kết thúc transaction, lưu tất cả các thay đổi vào cơ sở dữ liệu
COMMIT;