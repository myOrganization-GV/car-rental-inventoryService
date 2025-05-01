-- Images for the Blue Malibu
INSERT INTO car_images (image_id, car_id, image_url, image_order, created_at, updated_at) VALUES
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'Blue' LIMIT 1), 'https://drive.google.com/file/d/1ToHwfhxhDD57Uw89x4RFlL3DiVc5OoSs/view?usp=drive_link', 1, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'Blue' LIMIT 1), 'https://drive.google.com/file/d/1LrZ19vcvslixE5sZlElLGcHkPE9fH6r_/view?usp=drive_link', 2, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'Blue' LIMIT 1), 'https://drive.google.com/file/d/1TrMkCtfTxqLtYREGl_5H55NXDEkLBqgS/view?usp=drive_link', 3, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'Blue' LIMIT 1), 'https://drive.google.com/file/d/1Y3Fj46ys55nQ-hqmqvuIHIGrIFp_W9o1/view?usp=drive_link', 4, NOW(), NOW());

-- Images for the Black Malibu
INSERT INTO car_images (image_id, car_id, image_url, image_order, created_at, updated_at) VALUES
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'Black' LIMIT 1), 'https://drive.google.com/file/d/1ToHwfhxhDD57Uw89x4RFlL3DiVc5OoSs/view?usp=drive_link', 1, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'Black' LIMIT 1), 'https://drive.google.com/file/d/1LrZ19vcvslixE5sZlElLGcHkPE9fH6r_/view?usp=drive_link', 2, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'Black' LIMIT 1), 'https://drive.google.com/file/d/1TrMkCtfTxqLtYREGl_5H55NXDEkLBqgS/view?usp=drive_link', 3, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'Black' LIMIT 1), 'https://drive.google.com/file/d/1Y3Fj46ys55nQ-hqmqvuIHIGrIFp_W9o1/view?usp=drive_link', 4, NOW(), NOW());

-- Images for the White Malibu
INSERT INTO car_images (image_id, car_id, image_url, image_order, created_at, updated_at) VALUES
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'White' LIMIT 1), 'https://drive.google.com/file/d/1ToHwfhxhDD57Uw89x4RFlL3DiVc5OoSs/view?usp=drive_link', 1, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'White' LIMIT 1), 'https://drive.google.com/file/d/1LrZ19vcvslixE5sZlElLGcHkPE9fH6r_/view?usp=drive_link', 2, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'White' LIMIT 1), 'https://drive.google.com/file/d/1TrMkCtfTxqLtYREGl_5H55NXDEkLBqgS/view?usp=drive_link', 3, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Malibu' AND color = 'White' LIMIT 1), 'https://drive.google.com/file/d/1Y3Fj46ys55nQ-hqmqvuIHIGrIFp_W9o1/view?usp=drive_link', 4, NOW(), NOW());

INSERT INTO car_images (image_id, car_id, image_url, image_order, created_at, updated_at) VALUES
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'White' LIMIT 1), 'https://drive.google.com/file/d/1rVxSqt-aKLrGVslWY4VhSQbpWHZewPme/view?usp=drive_link', 1, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'White' LIMIT 1), 'https://drive.google.com/file/d/1-n5QzvhZBgIV0SvwZo6JgKTNpfvpQaDn/view?usp=drive_link', 2, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'White' LIMIT 1), 'https://drive.google.com/file/d/1lvLzX8hxuMbFqrOTxShwepudQl608-1H/view?usp=drive_link', 3, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'White' LIMIT 1), 'https://drive.google.com/file/d/1gUDHfT5q7m5ozwYLMWczfHfLyJTcXJWy/view?usp=drive_link', 4, NOW(), NOW());

-- Images for the Silver Sienna
INSERT INTO car_images (image_id, car_id, image_url, image_order, created_at, updated_at) VALUES
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'Silver' LIMIT 1), 'https://drive.google.com/file/d/1rVxSqt-aKLrGVslWY4VhSQbpWHZewPme/view?usp=drive_link', 1, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'Silver' LIMIT 1), 'https://drive.google.com/file/d/1-n5QzvhZBgIV0SvwZo6JgKTNpfvpQaDn/view?usp=drive_link', 2, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'Silver' LIMIT 1), 'https://drive.google.com/file/d/1lvLzX8hxuMbFqrOTxShwepudQl608-1H/view?usp=drive_link', 3, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'Silver' LIMIT 1), 'https://drive.google.com/file/d/1gUDHfT5q7m5ozwYLMWczfHfLyJTcXJWy/view?usp=drive_link', 4, NOW(), NOW());

-- Images for the Gray Sienna
INSERT INTO car_images (image_id, car_id, image_url, image_order, created_at, updated_at) VALUES
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'Gray' LIMIT 1), 'https://drive.google.com/file/d/1rVxSqt-aKLrGVslWY4VhSQbpWHZewPme/view?usp=drive_link', 1, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'Gray' LIMIT 1), 'https://drive.google.com/file/d/1-n5QzvhZBgIV0SvwZo6JgKTNpfvpQaDn/view?usp=drive_link', 2, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'Gray' LIMIT 1), 'https://drive.google.com/file/d/1lvLzX8hxuMbFqrOTxShwepudQl608-1H/view?usp=drive_link', 3, NOW(), NOW()),
(UUID_TO_BIN(UUID()), (SELECT car_id FROM cars WHERE model = 'Sienna' AND color = 'Gray' LIMIT 1), 'https://drive.google.com/file/d/1gUDHfT5q7m5ozwYLMWczfHfLyJTcXJWy/view?usp=drive_link', 4, NOW(), NOW());


