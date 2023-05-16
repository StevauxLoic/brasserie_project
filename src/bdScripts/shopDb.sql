USE shop_db;
CREATE TABLE festivity
(
	id INT NOT NULL AUTO_INCREMENT,
	tag VARCHAR(100) NOT NULL UNIQUE,
	date_of_the_event DATE NOT NULL,
	CONSTRAINT festivity_pk PRIMARY KEY(id)
);

CREATE TABLE adress_type
(
	id INT NOT NULL AUTO_INCREMENT,
    tag VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT adress_type_pk PRIMARY KEY (id)
);

CREATE TABLE product_type
(
	id INT NOT NULL AUTO_INCREMENT,
	tag VARCHAR(20) NOT NULL UNIQUE,
	CONSTRAINT product_type_pk PRIMARY KEY (id)
);

CREATE TABLE document_type
(
	id INT NOT NULL AUTO_INCREMENT,
	tag VARCHAR(60) NOT NULL UNIQUE,
	CONSTRAINT document_type_pk PRIMARY KEY (id)
);

CREATE TABLE product
(
	id VARCHAR(10) NOT NULL,
	type_id INT NOT NULL,
	tag VARCHAR(180) NOT NULL,
	vat DECIMAL(5,2) NOT NULL,
	quantity_in_stock INT NOT NULL,
    minimum_quantity_in_stock INT NOT NULL,
	is_sparkling BIT NOT NULL,
	alcohol_level DECIMAL(5,2) NOT NULL,
	launching_date DATE NOT NULL,
	price decimal (6,2) NOT NULL,
	description_of_the_product VARCHAR(100),
	CONSTRAINT product_pk PRIMARY KEY (id),
	CONSTRAINT type_fk FOREIGN KEY (type_id) REFERENCES product_type(id)
);



CREATE TABLE additional_restocking
(
	amount INT NOT NULL,
	product_id VARCHAR(10) NOT NULL,
	festivity_id INT NOT NULL,

	CONSTRAINT restocking_pk PRIMARY KEY(product_id, festivity_id),
	CONSTRAINT product_fk FOREIGN KEY(product_id) REFERENCES product(id),
	CONSTRAINT festivity_fk FOREIGN KEY(festivity_id) REFERENCES festivity(id)
);

CREATE TABLE country
(
	id VARCHAR(10) NOT NULL,
	tag VARCHAR(60) NOT NULL,
	CONSTRAINT country_pk PRIMARY KEY (id)
);

CREATE TABLE city
(
	id VARCHAR(10) NOT NULL,
	tag VARCHAR(60) NOT NULL,
	zip_code DECIMAL(9) NOT NULL,
	country_id VARCHAR(10) NOT NULL,
	CONSTRAINT city_pk PRIMARY KEY (id),
	CONSTRAINT country_fk FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE statut
(
	id VARCHAR(10) NOT NULL,
	denomination VARCHAR(60) NOT NULL,
	must_pay_deposit BIT,
	credit_limitation DECIMAL(7,2),
    discount BIT,
	CONSTRAINT statut_pk PRIMARY KEY (id)
);

CREATE TABLE business_entity
(
	id VARCHAR(10) NOT NULL,
	denomination VARCHAR(60) NOT NULL,
	vat_number INT NOT NULL,
	statut_id VARCHAR(10) NOT NULL,
    

	CONSTRAINT business_Entity_pk PRIMARY KEY (id),
	CONSTRAINT statut_id_fk FOREIGN KEY (statut_id) REFERENCES statut(id)
);

CREATE TABLE adress
(
	id VARCHAR(10) NOT NULL,
	street VARCHAR(26) NOT NULL,
	number_of_the_house INT NOT NULL,
	business_entity_id VARCHAR(10) NOT NULL,
	type_id INT NOT NULL,
	city_id VARCHAR(10) NOT NULL,

	CONSTRAINT adress_pk PRIMARY KEY (id),
	CONSTRAINT business_entity_id_fk FOREIGN KEY (business_entity_id) REFERENCES business_entity(id),
	CONSTRAINT type_id_fk FOREIGN KEY (type_id) REFERENCES adress_type(id),
	CONSTRAINT city_id_fk FOREIGN KEY (city_id) REFERENCES city(id)

);

CREATE TABLE document
(
	id INT NOT NULL AUTO_INCREMENT,
	creation_date DATE NOT NULL,
	discount DECIMAL(5,2),
	dead_line DATE,
    validity_date DATE,
	is_paid BIT,
	is_delivered BIT,
	delivery_date DATE,
	deposit DECIMAL(7,2),
	additional_fees DECIMAL(5,2),
	document_type_id INT NOT NULL,
	delivery_adress_id VARCHAR(10),
	CONSTRAINT document_pk PRIMARY KEY (id),
	CONSTRAINT document_type_fk FOREIGN KEY (document_type_id) REFERENCES document_type(id),
	CONSTRAINT delivery_adress_fk FOREIGN KEY (delivery_adress_id) REFERENCES adress(id)
);

	CREATE TABLE details_line
(
	price DECIMAL(7,2),
	quantity INT NOT NULL,
	anomalies_quantity INT,
	vat DECIMAL(5,2),
	discount DECIMAL(5,2),
	product_id VARCHAR(10) NOT NULL,
	document_id INT NOT NULL,
	CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES product(id),
	CONSTRAINT document_id_fk FOREIGN KEY (document_id) REFERENCES document(id),
	CONSTRAINT details_line_pk PRIMARY KEY (product_id, document_id) 
	);
    
	CREATE TABLE supplier_product_details
(
	price DECIMAL(7,2) NOT NULL,
    delivery_time INT NOT NULL,
    product_ref VARCHAR(10) NOT NULL,
    business_entity_ref VARCHAR(10) NOT NULL
);
