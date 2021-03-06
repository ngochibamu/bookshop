CREATE TABLE `Books`(
    id BIGINT auto_increment,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    language VARCHAR(255) NOT NULL,
    ISBN VARCHAR(255) NOT NULL,
    price DECIMAL(19,2) NOT NULL,
    weight FLOAT,
    number_of_pages INTEGER,
    dimensions VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    author VARCHAR(100),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);


CREATE TABLE `Customers`(
    id BIGINT auto_increment,
    email_address VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

CREATE TABLE `Addresses`(
    id BIGINT auto_increment,
    address_line1 VARCHAR(100),
    address_line2 VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    postal_code VARCHAR(10),
    country VARCHAR(50),
    `type` VARCHAR(50),
    customer_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(customer_id) references Customers(id),
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS `Line_Items`;
DROP TABLE IF EXISTS `Orders`;
DROP TABLE IF EXISTS `Carts`;

CREATE TABLE `Carts`(
    id BIGINT auto_increment,
    cart_status VARCHAR(10),
    customer_id INTEGER NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(customer_id) references Customers(id),
    PRIMARY KEY(id)
);

CREATE TABLE `Line_Items`(
    id BIGINT auto_increment,
    quantity INTEGER NOT NULL,
    price DECIMAL(19,2) NOT NULL,
    book_id BIGINT NOT NULL,
    cart_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(book_id) references Books(id),
    FOREIGN KEY(cart_id) references Carts(id),
    PRIMARY KEY(id)
);

CREATE TABLE `Orders`(
    id BIGINT auto_increment,
    total_price DECIMAL(19,2) NOT NULL,
    order_date DATE,
    order_status VARCHAR(15),
    cart_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(cart_id) references Carts(id),
    PRIMARY KEY(id)
);