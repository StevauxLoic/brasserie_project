USE shop_db;

INSERT INTO product_type (tag)
VALUES ('bière'), ('liqueur'), ('soft');


INSERT INTO document_type (tag)
VALUES ('vente'), ('achat'), ('livraison');


INSERT INTO adress_type (tag)
VALUES ('livraison'), ('professionnelle');


INSERT INTO festivity (tag, date_of_the_event)
VALUES ('Les Wallonies', STR_TO_DATE('09/07/1994', '%d/%m/%Y')), ('Carnaval', STR_TO_DATE('06/01/1994', '%d/%m/%Y'));

INSERT INTO country (id, tag)
VALUES ('be', 'Belgique'), ('fr', 'France'), ('de', 'Allemagne');

INSERT INTO city (id, tag, zip_code, country_id)
VALUES ('nam', 'Namur', 5000, 'be'), ('pa', 'Paris',  701123, 'fr');

INSERT INTO statut (id, denomination, must_pay_deposit, credit_limitation, discount)
VALUES ('fourn', 'fournisseur', null, null, null), ('fid', 'client fidèle', false, 50000.00, true), ('cli', 'client', true, 1000.00, false), ('vend', 'vendeur', null, null, null);

INSERT INTO business_entity (id, denomination, vat_number, statut_id)
VALUES ('shop', 'DrinkShop', 0123456789, 'vend'), ('loic', 'loic & co', 0123999869, 'fid'), ('ours', 'ours mielleux', 0696969789, 'fourn');

INSERT INTO adress(id, street, number_of_the_house, business_entity_id, type_id, city_id)
VALUES ('shop_adss', 'Rue de la bière', 69, 'shop', 2, 'nam'), ('liv_loic', 'Rue du solidaris', 14, 'loic', 1, 'nam'), ('adss_ours', 'Rue de la foret', 80, 'ours', 2, 'pa');

INSERT INTO document(creation_date, discount, dead_line, validity_date, is_paid, is_delivered, delivery_date, deposit, additional_fees, document_type_id, delivery_adress_id)
VALUES (STR_TO_DATE('19/04/2022','%d/%m/%Y'), null, null, STR_TO_DATE('19/04/2027','%d/%m/%Y'),true, true, STR_TO_DATE('30/04/2022','%d/%m/%Y'), null, null, 3, 'liv_loic');

INSERT INTO product(id, type_id, tag, vat, quantity_in_stock, minimum_quantity_in_stock, is_sparkling, alcohol_level, launching_date, price, description_of_the_product)
VALUES ('fant', 3, 'fanta 25cl', 21, 120, 75, true, 0, STR_TO_DATE('08/05/1945', '%d/%m/%Y'), 1.5, 'boisson orangée pétillante'), ('corn',1 ,'cornet 33cl', 21, 80, 50, true, 8.5, STR_TO_DATE('13/04/2003', '%d/%m/%Y'), 2.5,'biere blonde de dégustation'), ('pek_viol',2 ,'peket violette (shot)', 21, 40, 20,false, 40, STR_TO_DATE('30/05/2004', '%d/%m/%Y'),1 ,'alcool pour shot a la violette');

INSERT INTO additional_restocking(amount, product_id, festivity_id)
VALUES (50, 'pek_viol',1), (100, 'fant', 2);

INSERT INTO supplier_product_details(price, delivery_time, product_ref, business_entity_ref)
VALUES (1, 3, 'fant', 'ours'), (1.5, 2, 'corn', 'ours');


