#!/bin/bash

artifacts_path=$1
fargate_definition_file=$2

docker compose up -d

docker cp $artifacts_path/. localstack:/gitradar/artifacts
docker cp $fargate_definition_file localstack:/var/lib/localstack/data/fargate/definition_file.json


docker cp C:\Users\Joel\Documents\Universidad\CuartoCurso\PrimerSemestre\TecnologiasDeServiciosParaCienciaDeDatos\gitradar\test2.json localstack:/var/lib/localstack/data/test2.json
docker cp C:\Users\Joel\Documents\Universidad\CuartoCurso\PrimerSemestre\TecnologiasDeServiciosParaCienciaDeDatos\gitradar\jar\. localstack:/var/lib/localstack/data/artifacts




docker exec localstack awslocal s3api create-bucket --bucket gitradar-artifacts
docker exec localstack awslocal s3api create-bucket --bucket gitradar-code-files
docker exec localstack awslocal s3api create-bucket --bucket gitradar-datalake

docker exec localstack awslocal s3 cp /var/lib/localstack/data/artifacts s3://gitradar-artifacts/artifacts --recursive
                            
docker exec localstack awslocal ecs create-cluster --cluster-name gitradar-fargate-cluster
docker exec localstack awslocal ecs register-task-definition --cli-input-json file://var/lib/localstack/data/fargate/definition_file.json

docker exec localstack awslocal lambda create-function --function-name tokenizer --runtime java17 --handler com.tokenizer.LambdaFunctionHandler --role arn:aws:iam::000000000000:role/lambda-role --code S3Bucket=gitradar-artifacts,S3Key=artifacts/tokenizer.jar

docker exec localstack awslocal lambda create-function \
  --function-name token-datamart-builder \
  --runtime java8 \
  --handler mi.paquete.MiClaseHandler (Función a la que se llama dentro del jar) \
  --role arn:aws:iam::aws:role/service-role/AWSLambdaBasicExecutionRole \
  --code S3Bucket=gitradar-artifacts,S3Key=token-datamart-builder.jar 

docker exec localstack awslocal lambda create-function \
  --function-name code-metric \
  --runtime java8 \
  --handler mi.paquete.MiClaseHandler (Función a la que se llama dentro del jar) \
  --role arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole \
  --code S3Bucket=gitradar-artifacts,S3Key=code-metric.jar






docker exec localstack awslocal s3api put-bucket-notification-configuration --bucket gitradar-code-files --notification-configuration file:///var/lib/localstack/data/test2.json





docker exec localstack awslocal lambda invoke --function-name tokenizer /var/lib/localstack/data/response.json --payload file:///var/lib/localstack/data/test.json


















































docker exec localstack awslocal events create-event-bus --name s3-lambda-bus
docker exec localstack awslocal events put-rule --name s3-events-rule --event-bus-name s3-lambda-bus --event-pattern /var/lib/localstack/data/s3-event-pattern.json
docker exec localstack awslocal events put-targets --event-bus-name s3-lambda-bus --rule s3-events-rule --targets "Id"="1","Arn"="arn:aws:lambda:us-east-1:000000000000:function:tokenizer"


docker exec localstack awslocal s3api put-bucket-notification-configuration --bucket gitradar-code-files --notification-configuration "{\"EventBridgeConfiguration\": {\"EventBridgeEnabled\": true}}"

docker exec localstack awslocal s3 cp /var/lib/localstack/data/test.json s3://gitradar-artifacts/test.json


aws s3api put-bucket-notification-configuration --bucket your-bucket-name --notification-configuration file://path/to/your/notification_config.json

awslocal s3api put-bucket-notification-configuration --bucket gitradar-code-files --notification-configuration file:///var/lib/localstack/data/notification-config.json

docker exec localstack awslocal s3api put-bucket-notification-configuration --bucket gitradar-code-files --notification-configuration file:///var/lib/localstack/data/test2.json

docker exec localstack awslocal s3 cp /var/lib/localstack/data/test.json s3://gitradar-artifacts/test5.json 


awslocal s3api put-bucket-notification-configuration --bucket gitradar-code-files --notification-configuration