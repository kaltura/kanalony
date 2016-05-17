CREATE KEYSPACE kanalony_mng WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '2'}  AND durable_writes = true;

CREATE TABLE hourly_partitions (
	topic text, 
	hour timestamp, 
	partition int, 
	offset bigint, 
	PRIMARY KEY (topic, hour, partition)
) WITH CLUSTERING ORDER BY (hour DESC, partition ASC);
