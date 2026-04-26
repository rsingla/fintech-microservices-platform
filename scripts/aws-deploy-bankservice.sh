#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
ENV_FILE="${ENV_FILE:-$ROOT_DIR/.env.aws}"
TEMPLATE_FILE="$ROOT_DIR/infra/aws/cloudformation/bankservice-ecs.yml"
IMAGE_FILE="$ROOT_DIR/.banking-image-uri"

if [[ ! -f "$ENV_FILE" ]]; then
  echo "Missing $ENV_FILE. Create it from .env.aws.example and set deployment values." >&2
  exit 1
fi

set -a
source "$ENV_FILE"
set +a

AWS_REGION="${AWS_REGION:-us-east-1}"
AWS_PROFILE="${AWS_PROFILE:-default}"
AWS_STACK_NAME="${AWS_STACK_NAME:-bankservice-apicode}"
DOMAIN_NAME="${DOMAIN_NAME:-bankservice.apicode.io}"
REPOSITORY_URL="${REPOSITORY_URL:-https://github.com/rsingla/fintech-microservices-platform}"
WEBSITE_URL="${WEBSITE_URL:-https://apicode.io}"

if [[ -z "${HOSTED_ZONE_ID:-}" || "$HOSTED_ZONE_ID" == "Z123456789EXAMPLE" ]]; then
  echo "Set HOSTED_ZONE_ID in $ENV_FILE to the Route53 hosted zone id for apicode.io." >&2
  exit 1
fi

if [[ -z "${IMAGE_URI:-}" ]]; then
  if [[ ! -f "$IMAGE_FILE" ]]; then
    echo "No IMAGE_URI set and $IMAGE_FILE does not exist. Run scripts/aws-build-push-banking.sh first." >&2
    exit 1
  fi
  IMAGE_URI="$(cat "$IMAGE_FILE")"
fi

aws cloudformation deploy \
  --profile "$AWS_PROFILE" \
  --region "$AWS_REGION" \
  --stack-name "$AWS_STACK_NAME" \
  --template-file "$TEMPLATE_FILE" \
  --capabilities CAPABILITY_IAM \
  --parameter-overrides \
    DomainName="$DOMAIN_NAME" \
    HostedZoneId="$HOSTED_ZONE_ID" \
    RepositoryUrl="$REPOSITORY_URL" \
    WebsiteUrl="$WEBSITE_URL" \
    ImageUri="$IMAGE_URI"

aws cloudformation describe-stacks \
  --profile "$AWS_PROFILE" \
  --region "$AWS_REGION" \
  --stack-name "$AWS_STACK_NAME" \
  --query 'Stacks[0].Outputs' \
  --output table
