#!/bin/bash

container_name=$1
num_keys=$2

for ((i=1;i<=$num_keys;i++))
do
    docker exec $container_name awslocal dynamodb create-table \
        --table-name ngram$(printf "%02d" $i) \
        --attribute-definitions \
            AttributeName=context,AttributeType=S \
        --key-schema \
            AttributeName=context,KeyType=HASH \
        --billing-mode PAY_PER_REQUEST \
        --region eu-central-2
done