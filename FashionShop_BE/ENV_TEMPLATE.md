# 📝 TEMPLATE ENVIRONMENT VARIABLES CHO RENDER.COM

Danh sách tất cả các environment variables cần thiết để deploy FashionShop_BE lên Render.com

---

## 🗄️ DATABASE (Clever Cloud MySQL)

Lấy thông tin từ Clever Cloud dashboard:

```env
DB_HOST=bava8wsaru9tdvlcpyel-mysql.services.clever-cloud.com
DB_PORT=3306
DB_NAME=bava8wsaru9tdvlcpyel
DB_USER=urdc41cj0s5fnajk
DB_PASSWORD=x5x8zioH02iKoyEOkMbAh
```

**Giải thích:**
- `DB_HOST`: Địa chỉ MySQL server trên Clever Cloud
- `DB_PORT`: Port của MySQL (mặc định 3306)
- `DB_NAME`: Tên database
- `DB_USER`: Username để kết nối database
- `DB_PASSWORD`: Password để kết nối database

---

## 📧 EMAIL (Gmail SMTP)

Để gửi OTP qua email:

```env
MAIL_USERNAME=nguyenquochuy81819@gmail.com
MAIL_PASSWORD=qzaa wala aesi rtwk
```

**Giải thích:**
- `MAIL_USERNAME`: Địa chỉ Gmail của bạn
- `MAIL_PASSWORD`: App Password (KHÔNG phải password Gmail thông thường)

**⚠️ Cách lấy App Password:**
1. Vào https://myaccount.google.com/security
2. Bật "2-Step Verification"
3. Tìm "App passwords"
4. Tạo password mới cho "Mail"
5. Copy password 16 ký tự (có dạng: xxxx xxxx xxxx xxxx)

---

## 🔐 JWT (JSON Web Token)

Để mã hóa token authentication:

```env
JWT_SECRET=your-very-long-secret-key-at-least-32-characters-long-for-hs256
```

**Giải thích:**
- `JWT_SECRET`: Key bí mật để sign JWT tokens
- **YÊU CẦU:** Tối thiểu 32 ký tự
- **KHUYẾN NGHỊ:** Sử dụng random string, ví dụ:
  ```
  JWT_SECRET=9k3mP2nQ5rT8xY6zA1bC4dE7fG0hI3jL5mN8pR1sT4uV7wX0yZ3
  ```

**🔧 Tạo JWT Secret ngẫu nhiên:**

```bash
# Trên Linux/Mac
openssl rand -base64 32

# Trên PowerShell
-join ((48..57) + (65..90) + (97..122) | Get-Random -Count 64 | % {[char]$_})
```

---

## 💳 VNPAY (Payment Gateway)

Thông tin VNPay sandbox:

```env
VNPAY_TMN_CODE=X641MD16
VNPAY_HASH_SECRET=MFR7KBZN4F0WZEVFZ13JGAKGBJEVKFEH
VNPAY_URL=https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
VNPAY_RETURN_URL=https://YOUR_RENDER_APP_NAME.onrender.com/api/payment/vnpay-return
```

**Giải thích:**
- `VNPAY_TMN_CODE`: Mã website/merchant của bạn trên VNPay
- `VNPAY_HASH_SECRET`: Secret key để mã hóa giao dịch
- `VNPAY_URL`: URL của VNPay API (sandbox hoặc production)
- `VNPAY_RETURN_URL`: URL callback sau khi thanh toán

**⚠️ QUAN TRỌNG:** 
- Thay `YOUR_RENDER_APP_NAME` bằng tên service bạn đặt trên Render
- Ví dụ: Nếu service name là `fashionshop-backend` thì:
  ```
  VNPAY_RETURN_URL=https://fashionshop-backend.onrender.com/api/payment/vnpay-return
  ```

---

## 🤖 GEMINI AI (Google AI)

Để sử dụng Gemini AI chatbot:

```env
GEMINI_API_KEY=AIzaSyCpKXoOu65eFeyAjygNSr_DGDJi9-iWnhc
```

**Giải thích:**
- `GEMINI_API_KEY`: API key từ Google AI Studio

**🔧 Cách lấy Gemini API Key:**
1. Vào https://aistudio.google.com/app/apikey
2. Click "Create API Key"
3. Copy key vừa tạo

---

## 🔴 REDIS (TẠM THỜI DISABLED)

**Lưu ý:** Redis đã được tắt trong production config để deploy dễ dàng hơn.

Nếu muốn enable Redis sau này, cần thêm:

```env
REDIS_HOST=your-redis-host.com
REDIS_PORT=6379
REDIS_PASSWORD=your-redis-password
```

**💡 Options để có Redis free:**
1. **Upstash Redis**: https://upstash.com (Free tier 10,000 commands/day)
2. **Redis Labs**: https://redis.com (Free 30MB)
3. **Render Redis**: https://render.com (Paid, $7/month)

---

## 📋 CHECKLIST KHI CẤU HÌNH TRÊN RENDER

Khi thêm Environment Variables trên Render.com, hãy kiểm tra:

- [ ] ✅ Đã thêm tất cả 10-11 biến (tùy có Redis hay không)
- [ ] ✅ KHÔNG có khoảng trắng thừa ở đầu/cuối giá trị
- [ ] ✅ `VNPAY_RETURN_URL` đã thay đúng tên service
- [ ] ✅ `JWT_SECRET` dài ít nhất 32 ký tự
- [ ] ✅ `MAIL_PASSWORD` là App Password, không phải Gmail password
- [ ] ✅ Database credentials copy đúng từ Clever Cloud

---

## 🔒 BẢO MẬT

**⚠️ TUYỆT ĐỐI KHÔNG:**
- ❌ Commit file `.env` lên Git
- ❌ Share API keys/passwords công khai
- ❌ Screenshot environment variables và share trên mạng xã hội

**✅ NÊN:**
- ✅ Sử dụng Environment Variables trên Render
- ✅ Rotate (thay đổi) keys định kỳ
- ✅ Sử dụng các services có free tier để test
- ✅ Backup các keys quan trọng ở nơi an toàn (password manager)

---

## 🎯 MẪU FILE .ENV CHO LOCAL TESTING

Tạo file `.env.docker` (KHÔNG commit):

```bash
# Database (Clever Cloud)
DB_HOST=bava8wsaru9tdvlcpyel-mysql.services.clever-cloud.com
DB_PORT=3306
DB_NAME=bava8wsaru9tdvlcpyel
DB_USER=urdc41cj0s5fnajk
DB_PASSWORD=x5x8zioH02iKoyEOkMbAh

# Email
MAIL_USERNAME=nguyenquochuy81819@gmail.com
MAIL_PASSWORD=qzaa wala aesi rtwk

# JWT
JWT_SECRET=your-very-long-secret-key-at-least-32-characters-long-for-hs256

# VNPay
VNPAY_TMN_CODE=X641MD16
VNPAY_HASH_SECRET=MFR7KBZN4F0WZEVFZ13JGAKGBJEVKFEH
VNPAY_URL=https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
VNPAY_RETURN_URL=http://localhost:8080/api/payment/vnpay-return

# Gemini AI
GEMINI_API_KEY=AIzaSyCpKXoOu65eFeyAjygNSr_DGDJi9-iWnhc
```

**Sử dụng:**
```bash
docker run -p 8080:8080 --env-file .env.docker fashionshop-backend
```

---

**✅ Hoàn thành!** Bạn đã có đầy đủ thông tin về Environment Variables cần thiết.
