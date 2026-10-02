#!/bin/bash

set -e

create_service_role() {
    DB_USER=$1
    DB_PASSWORD=$2
    DB_SCHEMA=$3

    psql -v ON_ERROR_STOP=1 \
        --username "$POSTGRES_USER" \
        --dbname "$POSTGRES_DB" <<EOSQL

CREATE ROLE ${DB_USER}
LOGIN
PASSWORD '${DB_PASSWORD}';

CREATE SCHEMA ${DB_SCHEMA}
AUTHORIZATION ${DB_USER};

GRANT CONNECT
ON DATABASE ${POSTGRES_DB}
TO ${DB_USER};

GRANT USAGE
ON SCHEMA ${DB_SCHEMA}
TO ${DB_USER};

EOSQL
}

create_service_role \
    "$IDENTITY_DB_USER" \
    "$IDENTITY_DB_PASSWORD" \
    "$IDENTITY_SCHEMA"

create_service_role \
    "$ASSIGNMENT_DB_USER" \
    "$ASSIGNMENT_DB_PASSWORD" \
    "$ASSIGNMENT_SCHEMA"

create_service_role \
    "$CALENDAR_DB_USER" \
    "$CALENDAR_DB_PASSWORD" \
    "$CALENDAR_SCHEMA"

create_service_role \
    "$FINANCE_DB_USER" \
    "$FINANCE_DB_PASSWORD" \
    "$FINANCE_SCHEMA"

create_service_role \
    "$GIG_DB_USER" \
    "$GIG_DB_PASSWORD" \
    "$GIG_SCHEMA"

create_service_role \
    "$MEDIA_DB_USER" \
    "$MEDIA_DB_PASSWORD" \
    "$MEDIA_SCHEMA"

create_service_role \
    "$NOTIFICATION_DB_USER" \
    "$NOTIFICATION_DB_PASSWORD" \
    "$NOTIFICATION_SCHEMA"

create_service_role \
    "$SETLIST_DB_USER" \
    "$SETLIST_DB_PASSWORD" \
    "$SETLIST_SCHEMA"

echo "Database initialization completed successfully."
