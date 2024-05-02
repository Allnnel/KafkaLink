#!/bin/bash

# Скачать Kafka
wget https://archive.apache.org/dist/kafka/2.7.0/kafka_2.13-2.7.0.tgz

# Распаковать Kafka
tar -xzf kafka_2.13-2.7.0.tgz

# Перейти в директорию Kafka
cd kafka_2.13-2.7.0

# Запустить Zookeeper
./bin/zookeeper-server-start.sh config/zookeeper.properties &

# Подождать, чтобы Zookeeper полностью запустился
sleep 5

# Запустить сервер Kafka
./bin/kafka-server-start.sh config/server.properties
