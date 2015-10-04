# kAnalony Project

## Goals
The purpose of the kAnalony project is to create a robust and scalable infrastructure to accommodate the existing and future Analytical needs of Kaltura:

* Aggregate event-based time-series data using predefined configurable dimensions on predefined time resolutions
* Custom batch processing on aggregated data
* Custom batch processing on raw data
* Expose raw events data for preferred clients

## Background
There are currently 3 different Analytics systems in Kaltura:

1. Comprehensive metrics for VOD data, based on Pentaho's Kettle open source project and MySQL
2. Basic metrics for Live entries (including DVR) data, based on Apache Spark and Cassandra
3. Comprehensive metrics for OTT data, based on Sisense product

The new kAnalony project will create a scalable and robust infrastructure to facilitate the requirements of all current 3 systems.
It is based on [Apache Spark](https://spark.apache.org/), [Cassandra](http://cassandra.apache.org/) and [Kafka](http://kafka.apache.org/), 
written mostly with [Scala](http://www.scala-lang.org/).

## Why kAnalony?
The project name is a combination of the words Kaltura and Analytics, 
and sound like the word Cannelloni which is a cylindrical type of pasta generally served baked with a filling and covered by a sauce in Italian cuisine (Source: [Wikipedia](http://en.wikipedia.org/wiki/Cannelloni)).

## Components
* **kAnalony Receivers** - a group of Node.js servers behind a load balancer, which receive analytics events and push a transformation of them to a Kafka cluster.

## Installation

* [Kafka Cluster Installation](https://kaltura.atlassian.net/wiki/display/KANAL/Kafka+Cluster+Installation)
* [Spark-Cassandra Cluster Installation](https://kaltura.atlassian.net/wiki/display/KANAL/Spark-Cassandra+Cluster+Installation)