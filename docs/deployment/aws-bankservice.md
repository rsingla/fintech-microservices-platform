# AWS Deployment: `bankservice.apicode.io`

This path deploys the public banking showcase to AWS using:

- Amazon ECR for the banking service container image.
- Amazon ECS Fargate for the Java 21 Spring Boot service.
- Amazon RDS PostgreSQL for persistence.
- Application Load Balancer for public traffic.
- AWS Certificate Manager for TLS.
- Route53 for `bankservice.apicode.io`.
- CloudWatch Logs for service logs.

## Prerequisites

- AWS CLI configured locally.
- Docker running locally.
- Route53 hosted zone for `apicode.io` in the target AWS account.
- Permissions for ECR, ECS, EC2/VPC, RDS, IAM, ACM, Route53, Secrets Manager, CloudWatch Logs, and CloudFormation.

## Configure

```bash
cp .env.aws.example .env.aws
```

Edit `.env.aws`:

```bash
AWS_REGION=us-east-1
AWS_PROFILE=default
AWS_STACK_NAME=bankservice-apicode
AWS_ECR_REPOSITORY=banking-service
IMAGE_TAG=latest
DOMAIN_NAME=bankservice.apicode.io
HOSTED_ZONE_ID=<route53-hosted-zone-id-for-apicode-io>
REPOSITORY_URL=https://github.com/rsingla/fintech-microservices-platform
WEBSITE_URL=https://apicode.io
```

Find the hosted zone id:

```bash
aws route53 list-hosted-zones-by-name \
  --dns-name apicode.io \
  --query 'HostedZones[0].Id' \
  --output text
```

## Deploy

```bash
chmod +x scripts/aws-*.sh
./scripts/aws-deploy-all.sh
```

The deploy script:

1. Creates the ECR repository if needed.
2. Builds `services/banking-service` for `linux/arm64`.
3. Pushes the image to ECR.
4. Deploys the CloudFormation stack.
5. Outputs the service URL and load balancer DNS.

## Public URLs

- Landing page: `https://bankservice.apicode.io/`
- Swagger UI: `https://bankservice.apicode.io/docs`
- OpenAPI JSON: `https://bankservice.apicode.io/v3/api-docs`
- Showcase JSON: `https://bankservice.apicode.io/api/v1/showcase`
- Health: `https://bankservice.apicode.io/actuator/health`

## Update After Code Changes

```bash
IMAGE_TAG=$(git rev-parse --short HEAD) ./scripts/aws-deploy-all.sh
```

## Check Service Health

```bash
aws cloudformation describe-stacks \
  --stack-name bankservice-apicode \
  --query 'Stacks[0].Outputs' \
  --output table

aws ecs list-services \
  --cluster fintech-microservices-platform-banking-service
```

## Teardown

The RDS database uses `DeletionPolicy: Snapshot`, so stack deletion keeps a final database snapshot.

```bash
aws cloudformation delete-stack --stack-name bankservice-apicode
```
