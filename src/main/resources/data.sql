-- Seed Patients
INSERT INTO patients (first_name, last_name, email, address, city, zip_code) VALUES
('Peter', 'Parker', 'notspiderman@marvel.com', '123 NY St', 'New York City', '12312'),
('John', 'Smith', 'jsmith@jh.edu', '456 John Ct', 'Baltimore', '32121');

-- Seed Providers
INSERT INTO providers (first_name, last_name, npi_number, specialty) VALUES
('Doc', 'Oc', '1234567890', 'Octopus'),
('Dr', 'Strange', '0987654321', 'Time');
