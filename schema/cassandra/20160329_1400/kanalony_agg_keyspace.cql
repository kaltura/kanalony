CREATE KEYSPACE IF NOT EXISTS kanalony_agg WITH replication = {'class': 'NetworkTopologyStrategy', 'us-west-2':'2'}  AND durable_writes = true;
