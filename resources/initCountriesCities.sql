INSERT INTO pays (nom_pays,langue) VALUES 
('France', 'Francais'),
('Allemagne', 'Allemand'),
('Italie', 'Italien'),
('Espagne', 'Espagnol'),
('Pooland', 'Poolien');
INSERT INTO ville (nom,pays_id) VALUES 
('Parçay-Meslay', select id from pays where nom_pays='France'),
('Tours', select id from pays where nom_pays='France'),
('Paris', select id from pays where nom_pays='France'),
('Seur', select id from pays where nom_pays='France'),
('Blois', select id from pays where nom_pays='France'),
('Berlin', select id from pays where nom_pays='Allemagne'),
('Hangourg', select id from pays where nom_pays='Allemagne'),
('Stutgart', select id from pays where nom_pays='Allemagne'),
('Rome', select id from pays where nom_pays='Italie'),
('Naple', select id from pays where nom_pays='Italie'),
('Milan', select id from pays where nom_pays='Italie'),
('Madrid', select id from pays where nom_pays='Espagne'),
('Barcelone', select id from pays where nom_pays='Espagne'),
('Gerone', select id from pays where nom_pays='Espagne'),
('Valence', select id from pays where nom_pays='Espagne'),
('PooVille', select id from pays where nom_pays='Pooland'),
('PeeVille', select id from pays where nom_pays='Pooland');
