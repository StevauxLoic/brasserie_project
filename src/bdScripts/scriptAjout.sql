USE shop_db;

INSERT INTO product_type (tag)
VALUES ('bière'), ('liqueur'), ('soft');


INSERT INTO document_type (tag)
VALUES ('vente'), ('achat'), ('livraison');


INSERT INTO adress_type (tag)
VALUES ('livraison'), ('professionnelle');


INSERT INTO festivity (tag, date_of_the_event)
VALUES
	('Les Wallonies', STR_TO_DATE('09/07/1994', '%d/%m/%Y')), 
	('Carnaval', STR_TO_DATE('06/01/1994', '%d/%m/%Y')), 
    ('Fête de la bière', STR_TO_DATE('20/09/2022', '%d/%m/%Y'));

INSERT INTO country (id, tag)
VALUES ('be', 'Belgique'), ('fr', 'France'), ('de', 'Allemagne'), ('us', 'États-Unis'), ('it', 'italie');

INSERT INTO city (id, tag, zip_code, country_id)
VALUES 
	('nam', 'Namur', 5000, 'be'), 
	('pa', 'Paris',  701123, 'fr'),
    ('ny', 'New York', 10001, 'us'),
    ('camp', 'campanie', 80121, 'it');

INSERT INTO statut (id, denomination, must_pay_deposit, credit_limitation, discount)
VALUES 
	('fourn', 'fournisseur', null, null, null), 
	('fid', 'client fidèle', false, 50000.00, true), 
    ('cli', 'client', true, 1000.00, false), 
    ('vend', 'vendeur', null, null, null);

INSERT INTO business_entity (id, denomination, vat_number, statut_id)
VALUES 
	('shop', 'DrinkShop', 0123456789, 'vend'), 
	('loic', 'loic & co', 0123999869, 'fid'), 
	('ours', 'ours mielleux', 0696969789, 'fourn');

INSERT INTO adress(id, street, number_of_the_house, business_entity_id, type_id, city_id)
VALUES 
	('shop_adss', 'Rue de la bière', 69, 'shop', 2, 'nam'), 
	('liv_loic', 'Rue du solidaris', 14, 'loic', 1, 'nam'), 
    ('adss_ours', 'Rue de la foret', 80, 'ours', 2, 'pa'),
    ('pro_loic', 'Rue du ciol', 8, 'loic', 2, 'ny'),
    ('liv_loic_2', 'Rue des enfers', 666, 'loic', 1,'camp');

INSERT INTO document(creation_date, discount, dead_line, validity_date, is_paid, is_delivered, delivery_date, deposit, additional_fees, document_type_id, delivery_adress_id)
VALUES 
	(STR_TO_DATE('19/04/2022','%d/%m/%Y'), null, null, STR_TO_DATE('19/04/2027','%d/%m/%Y'),true, true, STR_TO_DATE('30/04/2022','%d/%m/%Y'), null, null, 3, 'liv_loic'),
	(STR_TO_DATE('25/05/2021','%d/%m/%Y'), 0, STR_TO_DATE('25/06/2021','%d/%m/%Y'), STR_TO_DATE('25/05/2022','%d/%m/%Y'), true, null, null, null, null, 1, null),
    (STR_TO_DATE('10/08/2013','%d/%m/%Y'), 0, STR_TO_DATE('25/09/2013','%d/%m/%Y'), STR_TO_DATE('10/08/2014','%d/%m/%Y'), true, null, null, null, 10, 1, null),
    (STR_TO_DATE('15/04/2018','%d/%m/%Y'), 0, STR_TO_DATE('15/05/2018','%d/%m/%Y'), STR_TO_DATE('15/04/2019','%d/%m/%Y'), false, null, null, null, null, 1, null);
    
    
INSERT INTO product(id, type_id, tag, vat, quantity_in_stock, minimum_quantity_in_stock, is_sparkling, alcohol_level, launching_date, price, description_of_the_product)
VALUES 
	('fant', 3, 'fanta 25cl', 21, 120, 75, true, 0, STR_TO_DATE('08/05/1945', '%d/%m/%Y'), 1.5, 'boisson orangée pétillante'), 
	('corn',1 ,'cornet 33cl', 21, 80, 50, true, 8.5, STR_TO_DATE('13/04/2003', '%d/%m/%Y'), 2.5,'biere blonde de dégustation'), 
    ('pek_viol',2 ,'peket violette (shot)', 21, 40, 20,false, 40, STR_TO_DATE('30/05/2004', '%d/%m/%Y'),1 ,'alcool pour shot a la violette'),
    ('biere1', 1, 'bière blonde 33cl', 21, 100, 50, true, 5.0, STR_TO_DATE('01/01/2022', '%d/%m/%Y'), 2.0, 'bière blonde légère'),
	('biere2', 1, 'bière brune 33cl', 21, 80, 40, false, 6.0, STR_TO_DATE('01/01/2022', '%d/%m/%Y'), 2.5, 'bière brune riche en saveurs'),
    ('coca', 3, 'coca 25cl', 21, 10, 50, true, 0, STR_TO_DATE('08/05/1945', '%d/%m/%Y'), 1.5, 'boisson au cola pétillante'),
    ('cochon', 1, 'rince cochon 33cl', 21, 50, 80, true, 9.5, STR_TO_DATE('01/01/1998', '%d/%m/%Y'), 2.5, 'biere pale ale belge');

INSERT INTO additional_restocking(amount, product_id, festivity_id)
VALUES 
	(50, 'pek_viol',1), 
	(100, 'fant', 2),
	(200, 'biere1', 3),
	(150, 'corn', 3);

INSERT INTO supplier_product_details(price, delivery_time, product_ref, business_entity_ref)
VALUES 
	(1, 3, 'fant', 'ours'), 
	(1.5, 2, 'corn', 'ours'),
	(2.5, 5, 'biere1', 'ours'),
	(2.8, 4, 'biere2', 'ours');




