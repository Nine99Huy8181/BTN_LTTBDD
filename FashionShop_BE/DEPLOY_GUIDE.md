# 🚀 HƯỚNG DẪN DEPLOY BACKEND LÊN RENDER.COM

Hướng dẫn chi tiết để deploy ứng dụng Spring Boot FashionShop_BE lên Render.com thông qua Docker.

---

## 📋 Yêu Cầu Trước Khi Bắt Đầu

- ✅ Đã có tài khoản Render.com
- ✅ Đã có database MySQL trên Clever Cloud (đã có)
- ✅ Đã có Docker Desktop cài đặt trên máy
- ✅ Đã có Git và GitHub repository cho project
- ✅ Đã có các API keys (VNPay, Gemini AI)

---

## BƯỚC 1️⃣: Test Docker Image Trên Máy Local

### 1.1. Build Docker Image

Mở Terminal/PowerShell và chạy:

```bash
# Di chuyển vào thư mục project
cd d:\Nam_4_HK1\ReactNative\BaiTapNhom\BE\FashionShop_BE

# Build Docker image
docker build -t fashionshop-backend .
```

**Lưu ý:** Quá trình build lần đầu sẽ mất khoảng 5-10 phút. Hãy kiên nhẫn!

### 1.2. Test Chạy Container Locally

Tạo file `.env.docker` để test (KHÔNG commit file này lên Git):

```bash
# .env.docker
DB_HOST=bava8wsaru9tdvlcpyel-mysql.services.clever-cloud.com
DB_PORT=3306
DB_NAME=bava8wsaru9tdvlcpyel
DB_USER=urdc41cj0s5fnajk
DB_PASSWORD=x5x8zioH02iKoyEOkMbAh
MAIL_USERNAME=nguyenquochuy81819@gmail.com
MAIL_PASSWORD=qzaa wala aesi rtwk
JWT_SECRET=your-very-long-secret-key-at-least-32-characters-long-for-hs256
VNPAY_TMN_CODE=X641MD16
VNPAY_HASH_SECRET=MFR7KBZN4F0WZEVFZ13JGAKGBJEVKFEH
VNPAY_URL=https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
VNPAY_RETURN_URL=http://localhost:8080/api/payment/vnpay-return
GEMINI_API_KEY=AIzaSyCpKXoOu65eFeyAjygNSr_DGDJi9-iWnhc
```

Chạy container với env file:

```bash
docker run -p 8080:8080 --env-file .env.docker fashionshop-backend
```

### 1.3. Kiểm Tra Ứng Dụng

Mở trình duyệt hoặc Postman:

```
http://localhost:8080/actuator/health
```

Nếu trả về `{"status":"UP"}` là thành công! 🎉

---

## BƯỚC 2️⃣: Push Code Lên GitHub

### 2.1. Tạo Repository GitHub (Nếu Chưa Có)

1. Vào https://github.com
2. Click "New repository"
3. Đặt tên: `FashionShop_BE`
4. Chọn **Private** (vì có thông tin nhạy cảm)
5. Click "Create repository"

### 2.2. Push Code Lên GitHub

```bash
# Khởi tạo Git (nếu chưa có)
cd d:\Nam_4_HK1\ReactNative\BaiTapNhom\BE\FashionShop_BE
git init

# Add remote repository (thay YOUR_USERNAME bằng username GitHub của bạn)
git remote add origin https://github.com/YOUR_USERNAME/FashionShop_BE.git

# Add và commit tất cả files
git add .
git commit -m "Initial commit with Docker support"

# Push lên GitHub
git branch -M main
git push -u origin main
```

**⚠️ QUAN TRỌNG:** Đảm bảo file `.env` đã được thêm vào `.gitignore` để không push thông tin nhạy cảm lên GitHub!

---

## BƯỚC 3️⃣: Deploy Lên Render.com

### 3.1. Tạo Web Service Mới

1. Đăng nhập vào https://render.com
2. Click **"New +"** → Chọn **"Web Service"**
3. **Connect GitHub Repository:**
   - Click "Connect account" để kết nối GitHub
   - Chọn repository `FashionShop_BE`
   - Click "Connect"

### 3.2. Cấu Hình Service

Điền các thông tin sau:

| Field | Value |
|-------|-------|
| **Name** | `fashionshop-backend` (hoặc tên bạn thích) |
| **Region** | Singapore (gần Việt Nam nhất) |
| **Branch** | `main` |
| **Runtime** | **Docker** |
| **Instance Type** | **Free** (hoặc Starter nếu cần) |

### 3.3. Cấu Hình Environment Variables

Scroll xuống phần **"Environment Variables"** và thêm các biến sau:

Click **"Add Environment Variable"** cho mỗi biến:

#### 🗄️ Database (Clever Cloud)
```
DB_HOST = bava8wsaru9tdvlcpyel-mysql.services.clever-cloud.com
DB_PORT = 3306
DB_NAME = bava8wsaru9tdvlcpyel
DB_USER = urdc41cj0s5fnajk
DB_PASSWORD = x5x8zioH02iKoyEOkMbAh
```

#### 📧 Email
```
MAIL_USERNAME = nguyenquochuy81819@gmail.com
MAIL_PASSWORD = qzaa wala aesi rtwk
```

#### 🔐 JWT
```
JWT_SECRET = your-very-long-secret-key-at-least-32-characters-long-for-hs256
```

#### 💳 VNPay
```
VNPAY_TMN_CODE = X641MD16
VNPAY_HASH_SECRET = MFR7KBZN4F0WZEVFZ13JGAKGBJEVKFEH
VNPAY_URL = https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
VNPAY_RETURN_URL = https://fashionshop-backend.onrender.com/api/payment/vnpay-return
```

**⚠️ Lưu ý:** Thay `fashionshop-backend` trong VNPAY_RETURN_URL bằng tên service bạn đã đặt ở bước 3.2

#### 🤖 Gemini AI
```
GEMINI_API_KEY = AIzaSyCpKXoOu65eFeyAjygNSr_DGDJi9-iWnhc
```

### 3.4. Deploy!

1. Click nút **"Create Web Service"**
2. Render sẽ bắt đầu build và deploy ứng dụng
3. Quá trình này mất khoảng **10-15 phút**

---

## BƯỚC 4️⃣: Kiểm Tra Deployment

### 4.1. Xem Logs

Trong dashboard của Render:
- Click vào service `fashionshop-backend`
- Click tab **"Logs"**
- Xem logs để kiểm tra:
  - ✅ Build thành công
  - ✅ Ứng dụng start thành công
  - ✅ Kết nối database thành công

### 4.2. Test API

Sau khi deploy thành công, Render sẽ cung cấp URL:

```
https://fashionshop-backend.onrender.com
```

Test health endpoint:

```
https://fashionshop-backend.onrender.com/actuator/health
```

### 4.3. Test Từ Frontend

Cập nhật base URL trong React Native app của bạn:

```typescript
// constants/index.ts
export const API_BASE_URL = 'https://fashionshop-backend.onrender.com';
```

---

## 🔧 Các Vấn Đề Thường Gặp

### ❌ Build Failed

**Nguyên nhân:** Thiếu dependencies hoặc lỗi Maven

**Giải pháp:**
1. Kiểm tra logs để xem lỗi cụ thể
2. Đảm bảo `pom.xml` đúng format
3. Thử build lại locally: `docker build -t test .`

### ❌ Application Không Start

**Nguyên nhân:** Environment variables sai hoặc thiếu

**Giải pháp:**
1. Kiểm tra lại tất cả Environment Variables
2. Đảm bảo DB credentials đúng
3. Xem logs để kiểm tra lỗi cụ thể

### ❌ Database Connection Failed

**Nguyên nhân:** Clever Cloud database không cho phép kết nối từ Render

**Giải pháp:**
1. Vào Clever Cloud dashboard
2. Kiểm tra xem database có allow connections từ external sources
3. Kiểm tra lại host, port, username, password

### ⚠️ Render Free Tier Sleep

**Lưu ý:** Render free tier sẽ tự động sleep sau 15 phút không hoạt động

**Giải pháp:**
- Upgrade lên Starter plan ($7/tháng)
- Hoặc chấp nhận cold start (~30s) khi request đầu tiên

---

## 🔄 Auto-Deploy Khi Push Code

Sau khi setup xong, mỗi khi bạn push code mới lên GitHub:

```bash
git add .
git commit -m "Update feature xyz"
git push
```

Render sẽ **tự động** build và deploy lại ứng dụng! 🚀

---

## 📱 Cập Nhật CORS (Nếu Cần)

Nếu frontend React Native gặp lỗi CORS, bạn cần thêm CORS configuration trong Spring Boot.

Tạo file `CorsConfig.java`:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

---

## 🎉 Hoàn Thành!

Bây giờ backend của bạn đã được deploy thành công lên Render.com!

URL của bạn: `https://fashionshop-backend.onrender.com`

**Next steps:**
1. Cập nhật URL này trong React Native app
2. Test tất cả các API endpoints
3. Monitor logs trên Render dashboard

**Chúc mừng! 🎊**
