INSERT INTO roles (
        band_id,
        role_name,
        role_description
    )
SELECT band_id,
    'OWNER',
    'Band Owner'
FROM bands
WHERE band_name IN ('Thekkekkara', 'Mezzoforte');
INSERT INTO roles (
        band_id,
        role_name,
        role_description
    )
SELECT band_id,
    'MANAGER',
    'Band Manager'
FROM bands
WHERE band_name IN ('Thekkekkara', 'Mezzoforte');
INSERT INTO roles (
        band_id,
        role_name,
        role_description
    )
SELECT band_id,
    'MEMBER',
    'Band Member'
FROM bands
WHERE band_name IN ('Thekkekkara', 'Sample Band');