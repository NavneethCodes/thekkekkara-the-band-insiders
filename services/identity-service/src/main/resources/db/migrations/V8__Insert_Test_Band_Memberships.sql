INSERT INTO band_memberships (user_id, band_id, role_id)
SELECT u.user_id,
    b.band_id,
    r.role_id
FROM(
        VALUES ('test_owner_1', 'Thekkekkara', 'OWNER'),
            ('test_manager_1', 'Thekkekkara', 'MANAGER'),
            ('test_member_1', 'Thekkekkara', 'MEMBER'),
            ('test_owner_2', 'Mezzoforte', 'OWNER'),
            ('test_manager_2', 'Mezzoforte', 'MANAGER'),
            ('test_member_2', 'Mezzoforte', 'MEMBER')
    ) AS test_members(username, band_name, role_name)
    JOIN users u ON u.username = test_members.username
    JOIN bands b ON b.band_name = test_members.band_name
    JOIN roles r ON r.band_id = b.band_id
    AND r.role_name = test_members.role_name;