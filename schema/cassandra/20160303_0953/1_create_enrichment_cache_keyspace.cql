CREATE KEYSPACE enrichment_cache WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '2'}  AND durable_writes = true;
CREATE TABLE enrichment_cache.dim_partners (
    id int PRIMARY KEY,
    secret text,
    crm_id text
);
CREATE TABLE enrichment_cache.dim_entries (
    id text PRIMARY KEY,
    categories text
);
