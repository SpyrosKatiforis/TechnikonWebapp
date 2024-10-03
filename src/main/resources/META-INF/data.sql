-- Delete existing data (optional, depending on your schema setup)
DELETE FROM property_repair;
DELETE FROM property;
DELETE FROM admin;
DELETE FROM property_owner;

-- Insert PropertyOwners
INSERT INTO property_owner (id, tin, name, surname, address, phone_number, email, username, password) VALUES
(1, '23534543', 'Panagiotis', 'Kostopoulos', 'Paris', '69349234', 'panagiotis@gmail.com', 'Pankos', '2222'),
(2, '235345', 'Spiros', 'Katiforis', 'London', '69234324324', 'spiros@gmail.com', 'Spikat', '3333'),
(3, '324123', 'Stefanos', 'Ountrakis', 'New York', '69324234234', 'stefanos@gmail.com', 'Stevoun', '1234');

-- Insert Admins
INSERT INTO admin (id, username, email, password) VALUES
(1, 'Katif', 'katiforis@gmail.com', '1212'),
(2, 'Pankost', 'kostop@gmail.com', '1111'),
(3, 'Stephen', 'stef6754@gmail.com', '1234');

-- Insert Properties
INSERT INTO property (id, address, property_type, owner_id) VALUES
(1, 'Chania', 'HOUSE', 3),
(2, 'Chania', 'APARTMENT', 3),
(3, 'Athens', 'HOUSE', 2),
(4, 'Athens', 'APARTMENT', 2),
(5, 'Patra', 'HOUSE', 1),
(6, 'Patra', 'APARTMENT', 1);

-- Insert Property Repairs
INSERT INTO property_repair (id, repair_type, description, submission_date, status, property_id, owner_id) VALUES
(1, 'PLUMBING', 'Losing water from toilet', '2023-10-01 00:00:00', 'PENDING', 1, 3),
(2, 'ELECTRICAL_WORK', 'No lights in reception', '2023-10-01 00:00:00', 'PENDING', 3, 2),
(3, 'INSULATION', 'Need to do some reworking..', '2023-10-01 00:00:00', 'PENDING', 5, 1);
