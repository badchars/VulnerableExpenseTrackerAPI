
Create table if not exists `tbl_expenses`(
     `id` long auto_increment primary key,
     `expense_name` varchar(255) not null,
     `expense_amount` bigint not null,
     `description` varchar(255),
     `category` varchar(255) not null ,
     `billdata` varchar(255),
     `billUrl` varchar(255),
     `date` DATE,
     `created_at` DATE,
     `updated_at` DATE,
     `user_id` long
);

Create table if not exists `tbl_users`
(
    `id` long auto_increment primary key,
    `name` varchar(255) not null ,
    `age`  long not null,
    `email` varchar(255) unique ,
    `password` varchar(255) not null ,
    `user_encoded_image` varchar(255),
    `created_at`     DATE not null,
    `updated_at`     DATE
);



-- tbl_users tablosuna 10 örnek veri ekleme
insert into tbl_users(id, name, age, email, password, user_encoded_image, created_at, updated_at)
values
    (1, 'John Doe', 28, 'jdoe@gmail.com', '$2a$10$fMiSJWHE0Oo/84OIzrb1xOnR1wBBmu4SvIzW3o3hYritUjExB87Oq', 'encoded_image_john', '2023-11-24', '2023-11-24'),
    (2, 'Alice Johnson', 25, 'alicej@gmail.com', '$2a$10$fMiSJWHE0Oo/84OIzrb1xOnR1wBBmu4SvIzW3o3hYritUjExB87Oq', 'encoded_image_alicej', '2023-11-24', '2023-11-24'),
    (3, 'Bob Brown', 30, 'bobb@gmail.com', '$2a$10$fMiSJWHE0Oo/84OIzrb1xOnR1wBBmu4SvIzW3o3hYritUjExB87Oq', 'encoded_image_bobb', '2023-11-24', '2023-11-24'),
    (4, 'Charlie Green', 28, 'charlieg@gmail.com', '$2a$10$fMiSJWHE0Oo/84OIzrb1xOnR1wBBmu4SvIzW3o3hYritUjExB87Oq', 'encoded_image_charlieg', '2023-11-24', '2023-11-24'),
    (5,'David White', 35, 'davidw@gmail.com', '$2a$10$fMiSJWHE0Oo/84OIzrb1xOnR1wBBmu4SvIzW3o3hYritUjExB87Oq', 'encoded_image_davidw', '2023-11-24', '2023-11-24'),
    (6, 'Eva Black', 32, 'evab@gmail.com', '$2a$10$fMiSJWHE0Oo/84OIzrb1xOnR1wBBmu4SvIzW3o3hYritUjExB87Oq', 'encoded_image_evab', '2023-11-24', '2023-11-24'),
    (7, 'Frank Gray', 29, 'frankg@gmail.com', '$2a$10$fMiSJWHE0Oo/84OIzrb1xOnR1wBBmu4SvIzW3o3hYritUjExB87Oq', 'encoded_image_frankg', '2023-11-24', '2023-11-24'),
    (8, 'Grace Red', 31, 'gracer@gmail.com', '$2a$10$fMiSJWHE0Oo/84OIzrb1xOnR1wBBmu4SvIzW3o3hYritUjExB87Oq', 'encoded_image_gracer', '2023-11-24', '2023-11-24'),
    (9, 'Harry Yellow', 27, 'harryy@gmail.com', '$2a$10$fMiSJWHE0Oo/84OIzrb1xOnR1wBBmu4SvIzW3o3hYritUjExB87Oq', 'encoded_image_harryy', '2023-11-24', '2023-11-24');

-- tbl_expenses tablosuna 10 örnek veri ekleme
insert into tbl_expenses(expense_name, expense_amount, date, description, category, billdata, billUrl, created_at, updated_at, user_id)
values
    ('Water Bill', 50.00, '2023-11-22', 'October Water Bill', 'Bill', '2023-11-22-Water-Bill', 'https://jsonplaceholder.typicode.com/todos/1', '2023-11-22', '2023-11-22', 1),
    ('Electricity Bill', 80.00, '2023-11-24', 'November Electricity Bill', 'Bill', '2023-11-24-Electricity-Bill', 'https://jsonplaceholder.typicode.com/todos/2', '2023-11-24', '2023-11-24', 1),
    ('Internet Bill', 60.00, '2023-11-25', 'November Internet Bill', 'Bill', '2023-11-25-Internet-Bill', 'https://jsonplaceholder.typicode.com/todos/3', '2023-11-25', '2023-11-25', 2),
    ('Grocery Shopping', 150.00, '2023-11-26', 'Weekly Grocery Shopping', 'Grocery', '2023-11-26-Grocery-Shopping', 'https://jsonplaceholder.typicode.com/todos/4', '2023-11-26', '2023-11-26', 2),
    ('Gas Bill', 70.00, '2023-11-27', 'November Gas Bill', 'Bill', '2023-11-27-Gas-Bill', 'https://jsonplaceholder.typicode.com/todos/5', '2023-11-27', '2023-11-27', 3),
    ('Phone Bill', 45.00, '2023-11-28', 'November Phone Bill', 'Bill', '2023-11-28-Phone-Bill', 'https://jsonplaceholder.typicode.com/todos/6', '2023-11-28', '2023-11-28', 3),
    ('Dinner Out', 100.00, '2023-11-29', 'Dinner Out with Friends', 'Entertainment', '2023-11-29-Dinner-Out', 'https://jsonplaceholder.typicode.com/todos/7', '2023-11-29', '2023-11-29', 4),
    ('Gasoline', 40.00, '2023-12-07', 'Fuel for the car', 'Transportation', '2023-12-07-Gasoline', 'https://example.com/bill/gasoline', '2023-12-07', '2023-12-07', 7),
    ('Car Insurance Premium', 100.00, '2023-12-08', 'Monthly car insurance premium', 'Transportation', '2023-12-08-Car-Insurance', 'https://example.com/bill/car-insurance', '2023-12-08', '2023-12-08', 4),
    ('Weekly Groceries', 100.00, '2023-12-01', 'Grocery shopping for the week', 'Grocery', '2023-12-01-Weekly-Groceries', 'https://example.com/bill/groceries', '2023-12-01', '2023-12-01', 5),
    ('Electricity Bill', 80.00, '2023-12-03', 'Monthly electricity bill payment', 'Utilities', '2023-12-03-Electricity-Bill', 'https://example.com/bill/electricity', '2023-12-03', '2023-12-03', 5),
    ('Water Bill', 50.00, '2023-12-04', 'Monthly water bill payment', 'Utilities', '2023-12-04-Water-Bill', 'https://example.com/bill/water', '2023-12-04', '2023-12-04', 6),
    ('Bulk Food Purchase', 150.00, '2023-12-02', 'Purchase of bulk food items', 'Grocery', '2023-12-02-Bulk-Food-Purchase', 'https://example.com/bill/bulk-food', '2023-12-02', '2023-12-02', 6),
    ('Mobile Phone Bill', 50.00, '2023-12-11', 'Monthly mobile phone bill', 'Communication', '2023-12-11-Mobile-Phone-Bill', 'https://example.com/bill/mobile-phone', '2023-12-11', '2023-12-11', 7),
    ('Medical Checkup', 70.00, '2023-12-09', 'Routine medical checkup', 'Healthcare', '2023-12-09-Medical-Checkup', 'https://example.com/bill/medical-checkup', '2023-12-09', '2023-12-09', 7),
    ('Prescription Medications', 30.00, '2023-12-10', 'Monthly prescription medications', 'Healthcare', '2023-12-10-Prescription-Medications', 'https://example.com/bill/prescription-medications', '2023-12-10', '2023-12-10', 8),
    ('Internet Subscription', 60.00, '2023-12-12', 'Monthly internet subscription', 'Communication', '2023-12-12-Internet-Subscription', 'https://example.com/bill/internet-subscription', '2023-12-12', '2023-12-12', 8),
    ('Movie Night', 15.00, '2023-12-15', 'Movie night with friends', 'Entertainment', '2023-12-15-Movie-Night', 'https://example.com/bill/movie-night', '2023-12-15', '2023-12-15', 9),
    ('Concert Tickets', 75.00, '2023-12-16', 'Tickets for a live concert', 'Entertainment', '2023-12-16-Concert-Tickets', 'https://example.com/bill/concert-tickets', '2023-12-16', '2023-12-16', 9),
    ('Haircut', 25.00, '2023-12-13', 'Regular haircut', 'Personal Care', '2023-12-13-Haircut', 'https://example.com/bill/haircut', '2023-12-13', '2023-12-13', 10),
    ('Toiletries', 20.00, '2023-12-14', 'Purchase of personal hygiene products', 'Personal Care', '2023-12-14-Toiletries', 'https://example.com/bill/toiletries', '2023-12-14', '2023-12-14', 10),
    ('Tuition Payment', 200.00, '2023-12-17', 'Payment for tuition', 'Education', '2023-12-17-Tuition-Payment', 'https://example.com/bill/tuition-payment', '2023-12-17', '2023-12-17', 1),
    ('Books and Supplies', 50.00, '2023-12-18', 'Purchase of books and school supplies', 'Education', '2023-12-18-Books-and-Supplies', 'https://example.com/bill/books-and-supplies', '2023-12-18', '2023-12-18', 11),
    ('Clothing Shopping', 120.00, '2023-11-30', 'Clothing Shopping', 'Shopping', '2023-11-30-Clothing-Shopping', 'https://jsonplaceholder.typicode.com/todos/8', '2023-11-30', '2023-11-30', 2),
    ('Bank Fees', 10.00, '2023-12-19', 'Monthly bank fees', 'Financial Services', '2023-12-19-Bank-Fees', 'https://example.com/bill/bank-fees', '2023-12-19', '2023-12-19', 2),
    ('Credit Card Payment', 75.00, '2023-12-20', 'Payment for credit card charges', 'Financial Services', '2023-12-20-Credit-Card-Payment', 'https://example.com/bill/credit-card-payment', '2023-12-20', '2023-12-20', 3),
    ('Home Decor', 90.00, '2023-12-01', 'Home Decor Items', 'Home', '2023-12-01-Home-Decor', 'https://jsonplaceholder.typicode.com/todos/9', '2023-12-01', '2023-12-01', 3),
    ('Health Insurance Premium', 120.00, '2023-12-21', 'Monthly health insurance premium', 'Insurance', '2023-12-21-Health-Insurance-Premium', 'https://example.com/bill/health-insurance', '2023-12-21', '2023-12-21', 4),
    ('Car Insurance Premium', 90.00, '2023-12-22', 'Monthly car insurance premium', 'Insurance', '2023-12-22-Car-Insurance-Premium', 'https://example.com/bill/car-insurance', '2023-12-22', '2023-12-22', 4),
    ('Lunch Out', 25.00, '2023-12-02', 'Lunch Out at Work', 'Food', '2023-12-02-Lunch-Out', 'https://jsonplaceholder.typicode.com/todos/10', '2023-12-02', '2023-12-02', 5);




