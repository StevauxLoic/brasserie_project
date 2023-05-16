USE shop_db;

INSERT INTO product_type (tag)
VALUES ('bière'), ('liqueur'), ('soft'), ('vin');


INSERT INTO document_type (tag)
VALUES ('vente'), ('achat'), ('livraison');


INSERT INTO adress_type (tag)
VALUES ('livraison'), ('professionnelle');


INSERT INTO festivity (tag, date_of_the_event)
VALUES
    ('Les Wallonies', STR_TO_DATE('09/07/2020', '%d/%m/%Y')),
    ('Carnaval', STR_TO_DATE('06/01/2018', '%d/%m/%Y')),
    ('Fête de la bière', STR_TO_DATE('20/09/2022', '%d/%m/%Y'));

INSERT INTO country (id, tag)
VALUES ('be', 'Belgique'),
       ('fr', 'France'),
       ('de', 'Allemagne'),
       ('us', 'États-Unis'),
       ('it', 'Italie'),
       ('es', 'Espagne'),
       ('uk', 'Royaume-Uni');

INSERT INTO city (id, tag, zip_code, country_id)
VALUES
    ('nam', 'Namur', 5000, 'be'),
    ('pa', 'Paris',  701123, 'fr'),
    ('ny', 'New York', 10001, 'us'),
    ('camp', 'campanie', 80121, 'it'),
    ('mad', 'Madrid', 28001, 'es'),
    ('lon', 'Londres', 42458, 'uk');

INSERT INTO statut (id, denomination, must_pay_deposit, credit_limitation, discount)
VALUES
    ('fourn', 'fournisseur', null, null, null),
    ('fid', 'client fidèle', false, 50000.00, true),
    ('cli', 'client', true, 1000.00, false),
    ('vend', 'vendeur', null, null, null),
    ('part', 'partenaire', null, null, null);

INSERT INTO business_entity (id, denomination, vat_number, statut_id)
VALUES
    ('shop', 'DrinkShop', 0123456789, 'vend'),
    ('loic', 'loic & co', 0123999869, 'fid'),
    ('ours', 'ours mielleux', 0696969789, 'fourn'),
    ('win', 'Wineries', 0123456790, 'part'),
    ('bar', 'BeerBar', 0123999870, 'vend'),
    ('denji', 'Denji s Industries', 086434521, 'fourn'),
    ('romain', 'La Maison de Romain', 046813579, 'fourn');

INSERT INTO adress(id, street, number_of_the_house, business_entity_id, type_id, city_id)
VALUES
    ('shop_adss', 'Rue de la bière', 69, 'shop', 2, 'nam'),
    ('liv_loic', 'Rue du solidaris', 14, 'loic', 1, 'nam'),
    ('adss_ours', 'Rue de la foret', 80, 'ours', 2, 'pa'),
    ('pro_loic', 'Rue du ciol', 8, 'loic', 2, 'ny'),
    ('liv_loic_2', 'Rue des enfers', 666, 'loic', 1,'camp'),
    ('win_adss', 'Calle de la Vino', 73, 'win', 2, 'mad'),
    ('bar_adss', 'Rue de la Bière', 50, 'bar', 2, 'nam'),
    ('den_adss', 'Power Street', 42, 'denji', 1, 'lon'),
    ('rom_adr', 'Rue de la Maison', 15, 'romain', 1, 'nam');

INSERT INTO document(creation_date, discount, dead_line, validity_date, is_paid, is_delivered, delivery_date, deposit, additional_fees, document_type_id, delivery_adress_id)
VALUES
    (STR_TO_DATE('19/04/2022','%d/%m/%Y'), null, null, STR_TO_DATE('19/04/2027','%d/%m/%Y'),true, true, STR_TO_DATE('30/04/2022','%d/%m/%Y'), null, null, 3, 'liv_loic'),
    (STR_TO_DATE('25/05/2021','%d/%m/%Y'), 0, STR_TO_DATE('25/06/2021','%d/%m/%Y'), STR_TO_DATE('25/05/2022','%d/%m/%Y'), true, null, null, null, null, 1, null),
    (STR_TO_DATE('10/08/2013','%d/%m/%Y'), 0, STR_TO_DATE('25/09/2013','%d/%m/%Y'), STR_TO_DATE('10/08/2014','%d/%m/%Y'), true, null, null, null, 10, 1, null),
    (STR_TO_DATE('15/04/2018','%d/%m/%Y'), 0, STR_TO_DATE('15/05/2018','%d/%m/%Y'), STR_TO_DATE('15/04/2019','%d/%m/%Y'), false, null, null, null, null, 1, null),
    (STR_TO_DATE('19/04/2022','%d/%m/%Y'), null, null, STR_TO_DATE('19/04/2027','%d/%m/%Y'), true, true, STR_TO_DATE('30/04/2022','%d/%m/%Y'), null, null, 3, 'liv_loic'),
    (STR_TO_DATE('25/05/2021','%d/%m/%Y'), 0, STR_TO_DATE('25/06/2021','%d/%m/%Y'), STR_TO_DATE('25/05/2022','%d/%m/%Y'), true, null, null, null, null, 1, null),
    (STR_TO_DATE('10/08/2013','%d/%m/%Y'), 0, STR_TO_DATE('25/09/2013','%d/%m/%Y'), STR_TO_DATE('10/08/2014','%d/%m/%Y'), true, null, null, null, 10, 1, null),
    (STR_TO_DATE('15/04/2018','%d/%m/%Y'), 0, STR_TO_DATE('15/05/2018','%d/%m/%Y'), STR_TO_DATE('15/04/2019','%d/%m/%Y'), false, null, null, null, null, 1, null);


INSERT INTO product(id, type_id, tag, vat, quantity_in_stock, minimum_quantity_in_stock, is_sparkling, alcohol_level, launching_date, price, description_of_the_product)
VALUES
    ('fant', 3, 'fanta 25cl', 21, 120, 75, true, 0, STR_TO_DATE('08/05/1945', '%d/%m/%Y'), 1.5, 'boisson orangée pétillante'),
    ('corn',1 ,'cornet 33cl', 21, 80, 50, true, 8.5, STR_TO_DATE('13/04/2003', '%d/%m/%Y'), 2.5,'biere blonde de dégustation'),
    ('pek_viol',2 ,'peket violette 1l', 21, 40, 20,false, 40, STR_TO_DATE('30/05/2004', '%d/%m/%Y'),25 ,'alcool pour shot a la violette'),
    ('biere1', 1, 'bière blonde 33cl', 21, 100, 50, true, 5.0, STR_TO_DATE('01/01/2022', '%d/%m/%Y'), 2.0, 'bière blonde légère'),
    ('biere2', 1, 'bière brune 33cl', 21, 80, 40, false, 6.0, STR_TO_DATE('01/01/2022', '%d/%m/%Y'), 2.5, 'bière brune riche en saveurs'),
    ('coca', 3, 'coca 25cl', 21, 10, 50, true, 0, STR_TO_DATE('08/05/1945', '%d/%m/%Y'), 1.5, 'boisson au cola pétillante'),
    ('cochon', 1, 'rince cochon 33cl', 21, 50, 80, true, 9.5, STR_TO_DATE('01/01/1998', '%d/%m/%Y'), 2.5, 'biere pale ale belge'),
    ('vin1', 4, 'vins rouges', 21, 50, 20, false, 13.5, STR_TO_DATE('01/01/2023', '%d/%m/%Y'), 10.0, 'vins rouges de qualité'),
    ('vin2', 4, 'vins blancs', 21, 30, 10, false, 11.0, STR_TO_DATE('01/01/2023', '%d/%m/%Y'), 8.0, 'vins blancs rafraîchissants'),
    ('biere3', 1, 'bière ambrée 33cl', 21, 60, 30, true, 7.0, STR_TO_DATE('01/01/2023', '%d/%m/%Y'), 3.5, 'bière ambrée aux arômes maltés');


INSERT INTO details_line (quantity, price, document_id, product_id, vat)
VALUES
    (10, 2.0, 6, 'fant', 21.0),
    (5, 2.5, 6, 'corn', 21.0),
    (20, 25.0, 6, 'pek_viol', 21.0),
    (15, 2.0, 7, 'biere1', 21.0),
    (8, 2.5, 7, 'corn', 21.0),
    (12, 3.5, 7, 'biere3', 21.0),
    (4, 10.0, 8, 'vin1', 21.0),
    (6, 8.0, 8, 'vin2', 21.0);


INSERT INTO additional_restocking(amount, product_id, festivity_id)
VALUES
    (50, 'pek_viol',1),
    (100, 'fant', 2),
    (200, 'biere1', 3),
    (150, 'corn', 3),
    (100, 'biere3', 3),
    (50, 'vin1', 1),
    (80, 'vin2', 1);

INSERT INTO supplier_product_details(price, delivery_time, product_ref, business_entity_ref)
VALUES
    (1, 3, 'fant', 'ours'),
    (1.5, 2, 'corn', 'ours'),
    (1.5, 5, 'biere1', 'ours'),
    (1.8, 4, 'biere2', 'ours'),
    (9.0, 7, 'vin1', 'ours'),
    (6.5, 5, 'vin2', 'ours'),
    (3.0, 3, 'biere3', 'ours'),
    (1.5, 14, 'cochon', 'denji'),
    (1.25, 14, 'coca', 'denji'),
    (0.8, 2, 'fant', 'romain'),
    (2, 2, 'corn', 'romain'),
    (11, 2, 'pek_viol', 'romain'),
    (1.9, 2, 'biere1', 'romain'),
    (2, 2, 'biere2', 'romain'),
    (1, 2, 'coca', 'romain'),
    (1.8, 2, 'cochon', 'romain'),
    (8.2, 2, 'vin1', 'romain'),
    (7.5, 2, 'vin2', 'romain'),
    (2.2, 2, 'biere3', 'romain');
