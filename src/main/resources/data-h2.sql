INSERT INTO `Customers` (`email_address`, `first_name`, `last_name`) VALUES ('ngoni@div2.net', 'ngoni', 'chibamu');
INSERT INTO `Addresses` (`address_line1`, `address_line2`, `city`, `state`, `postal_code`, `country`, `type`, `customer_id`) VALUES
('27 Main Street', 'Johanessburg central', 'Johanessburg', 'Gauteng', '2000', 'South Africa', 'Shipping', SELECT TOP 1 ID FROM Customers ORDER BY id DESC);

INSERT INTO `Customers` (`email_address`, `first_name`, `last_name`) VALUES ('jxv@div3.net', 'Arromat', 'Cake');
INSERT INTO `Addresses` (`address_line1`, `address_line2`, `city`, `state`, `postal_code`, `country`, `type`, `customer_id`) VALUES
('Turbine Hall', '65 Ntembi Piliso Street, Newtown', 'Johanessburg', 'Gauteng', '2011', 'South Africa', 'Shipping', SELECT TOP 1 ID FROM Customers ORDER BY id DESC);

INSERT INTO `Customers` (`email_address`, `first_name`, `last_name`) VALUES ('qreenbox@div6.net', 'Green', 'Box');
INSERT INTO `Addresses` (`address_line1`, `address_line2`, `city`, `state`, `postal_code`, `country`, `type`, `customer_id`) VALUES
('Plot 17530, Luthuli Road', 'P/Bag 00276', 'Gaborone', 'South East', '004324', 'Motswana land', 'Shipping', SELECT TOP 1 ID FROM Customers ORDER BY id DESC);
INSERT INTO `Addresses` (`address_line1`, `address_line2`, `city`, `state`, `postal_code`, `country`, `type`, `customer_id`) VALUES
('Carbo Centre Building','River Walk Customer Service Centre', 'Mostwana', 'Francis Town', '0032432', 'Lesotho', 'Billing', SELECT TOP 1 ID FROM Customers ORDER BY id DESC);


INSERT INTO `Books` (`title`, `description`, `language`, `ISBN`, `price`, `weight`, `number_of_pages`, `dimensions`, `publisher`, `image_url`, `author`) values
('Applied Cryptography', 'Protocols, Algorithms and Source Code in C', 'English', '978-1-119-09672-6', 4.50, 55, 889, '15 x 12 x 4', 'John Wiley & SOns, Inc', '2022-04-04 09-09-22.png', 'Bruce Schneier'),
('Bitcoin and Blockchain Security', '', 'English', '978-1-63081-013-9', 1.5, 200, 233, '15 x 9 x 2', 'ARTECH House', '2022-04-04 09-18-36.png', 'Ghassan Karame Elli Androulaki'),
('The Blockchain Developer', 'A Practical Guide for Designinhg, Implementing, Publishing, Testing, and Securing Distributed Blockchain-based Projects',
'English', '978-1-4842-4846-1',4.75, 250, 527, '14 x 12 x 3', 'Apress', '2022-04-04 09-30-48.png', 'Elad Elrom'),
('Blockchain Enabled Applications', 'Understand the Blockchain Ecosystem and How to Make it Work for You', 'English', '978-1-4842-3080-0', 6.65, 174, 225, '15 x 12 x 1.25', 'Apress', '2022-04-04 09-42-16.png', 'Vikram Dhillon, David Metcalf, Max Hooper');

