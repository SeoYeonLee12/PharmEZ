CREATE TABLE medicine (
                          medicine_id BIGINT NOT NULL PRIMARY KEY,
                          pharmaceutical_company VARCHAR(255) NOT NULL, -- 제약사
                          medicine_name LongText NOT NULL, -- 약 이름
                          medicine_efficacy LongText NULL, -- 효능
                          medicine_use LongText NULL, -- 사용법
                          precaution_warn LongText NULL, -- 경고 사항
                          using_precaution LongText NULL, -- 사용 시 주의 사항
                          medicine_interactions LongText NULL, -- 약물 상호작용
                          medicine_side_effect LongText NULL, -- 부작용
                          medicine_storage LongText NULL, -- 저장 방법
                          medicine_image VARCHAR(255) -- 이미지 URL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pharmacy (
                          pharmacy_id BIGINT NOT NULL PRIMARY KEY,
                          pharmacy_address VARCHAR(255) NOT NULL, -- 주소
                          pharmacy_name VARCHAR(255) NOT NULL, -- 약국 이름
                          pharmacy_tel VARCHAR(20), -- 전화번호
                          mon_start VARCHAR(10), -- 월요일 시작 시간
                          mon_close VARCHAR(10), -- 월요일 종료 시간
                          tue_start VARCHAR(10), -- 화요일 시작 시간
                          tue_close VARCHAR(10), -- 화요일 종료 시간
                          wed_start VARCHAR(10), -- 수요일 시작 시간
                          wed_close VARCHAR(10), -- 수요일 종료 시간
                          thu_start VARCHAR(10), -- 목요일 시작 시간
                          thu_close VARCHAR(10), -- 목요일 종료 시간
                          fri_start VARCHAR(10), -- 금요일 시작 시간
                          fri_close VARCHAR(10), -- 금요일 종료 시간
                          sat_start VARCHAR(10), -- 토요일 시작 시간
                          sat_close VARCHAR(10), -- 토요일 종료 시간
                          sun_start VARCHAR(10), -- 일요일 시작 시간
                          sun_close VARCHAR(10), -- 일요일 종료 시간
                          public_holiday_start VARCHAR(10), -- 공휴일 시작 시간
                          public_holiday_close VARCHAR(10), -- 공휴일 종료 시간
                          front_zip_code INT, -- 우편번호 앞자리
                          back_zip_code INT, -- 우편번호 뒷자리
                          latitude DOUBLE, -- 위도
                          longitude DOUBLE -- 경도
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE stock (
                       stock_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       pharmacy_id BIGINT NOT NULL, -- 약국 ID (FK)
                       medicine_id BIGINT NOT NULL, -- 약 ID (FK)
                       is_out_of_stock BOOLEAN NOT NULL, -- 품절 여부 (true: 품절, false: 재고 있음)
                       FOREIGN KEY (pharmacy_id) REFERENCES pharmacy(pharmacy_id),
                       FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE category (
                          category_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          category_name VARCHAR(255) NOT NULL, -- 카테고리명
                          category_depth INT NOT NULL -- 카테고리 깊이
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE medicine_category (
                                   medicine_category_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                   medicine_id BIGINT NOT NULL, -- 약 ID (FK)
                                   category_id BIGINT NOT NULL, -- 카테고리 ID (FK)
                                   FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id),
                                   FOREIGN KEY (category_id) REFERENCES category(category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user (
                      user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      email VARCHAR(255) UNIQUE NOT NULL, -- 이메일 (unique)
                      password VARCHAR(255) NOT NULL, -- 비밀번호
                      mobile VARCHAR(20) -- 전화번호
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;