set artifacts_path=%1
set jsons_path=%2

docker compose up -d

docker cp %artifacts_path%/. localstack:/var/lib/localstack/data/artifacts
docker cp %jsons_path%/. localstack:/var/lib/localstack/data/jsons

docker exec localstack awslocal s3api create-bucket --bucket gitradar-artifacts
docker exec localstack awslocal s3api create-bucket --bucket gitradar-code-files
docker exec localstack awslocal --endpoint-url=http://localhost:4566 s3api create-bucket --bucket gitradar-metrics
docker exec localstack awslocal s3api create-bucket --bucket gitradar-datalake

docker exec localstack awslocal s3 cp /var/lib/localstack/data/artifacts s3://gitradar-artifacts/artifacts --recursive

docker exec localstack awslocal iam create-role --role-name lambda-s3-role --assume-role-policy-document file:///var/lib/localstack/data/jsons/trust-policy.json
docker exec localstack awslocal iam attach-role-policy --role-name lambda-s3-role --policy-arn arn:aws:iam::aws:policy/AWSLambdaExecute

docker exec localstack awslocal lambda create-function --function-name tokenizer --runtime java17 --handler com.tokenizer.LambdaFunctionHandler --role arn:aws:iam::000000000000:role/lambda-s3-role --code S3Bucket=gitradar-artifacts,S3Key=artifacts/tokenizer-2.0-bucket.jar
docker exec localstack awslocal lambda create-function --function-name token-datamart-builder --runtime java17 --handler com.builder.LambdaFunctionHandler --role arn:aws:iam::000000000000:role/lambda-s3-role --code S3Bucket=gitradar-artifacts,S3Key=artifacts/token-datamart-builder-2.0.jar
docker exec localstack awslocal lambda create-function --function-name code-metrics --runtime java17 --handler com.metrics.LambdaFunctionHandler --role arn:aws:iam::000000000000:role/lambda-s3-role --code S3Bucket=gitradar-artifacts,S3Key=artifacts/metrics-1.0.jar 

docker exec localstack awslocal s3api put-bucket-notification-configuration --bucket gitradar-code-files --notification-configuration file:///var/lib/localstack/data/jsons/code-files-bucket-notification-configurations.json
docker exec localstack awslocal s3api put-bucket-notification-configuration --bucket gitradar-datalake --notification-configuration file:///var/lib/localstack/data/jsons/datalake-bucket-notification-configurations.json
