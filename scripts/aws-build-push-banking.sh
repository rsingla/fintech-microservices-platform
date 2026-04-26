#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
ENV_FILE="${ENV_FILE:-$ROOT_DIR/.env.aws}"

if [[ ! -f "$ENV_FILE" ]]; then
  echo "Missing $ENV_FILE. Create it from .env.aws.example and set AWS_REGION, AWS_PROFILE, and HOSTED_ZONE_ID." >&2
  exit 1
fi

set -a
source "$ENV_FILE"
set +a

AWS_REGION="${AWS_REGION:-us-east-1}"
AWS_PROFILE="${AWS_PROFILE:-default}"
AWS_ECR_REPOSITORY="${AWS_ECR_REPOSITORY:-banking-service}"
IMAGE_TAG="${IMAGE_TAG:-latest}"

ACCOUNT_ID="$(aws sts get-caller-identity --profile "$AWS_PROFILE" --query Account --output text)"
ECR_REGISTRY="$ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com"
IMAGE_URI="$ECR_REGISTRY/$AWS_ECR_REPOSITORY:$IMAGE_TAG"

aws ecr describe-repositories \
  --profile "$AWS_PROFILE" \
  --region "$AWS_REGION" \
  --repository-names "$AWS_ECR_REPOSITORY" >/dev/null 2>&1 || \
aws ecr create-repository \
  --profile "$AWS_PROFILE" \
  --region "$AWS_REGION" \
  --repository-name "$AWS_ECR_REPOSITORY" \
  --image-scanning-configuration scanOnPush=true \
  --encryption-configuration encryptionType=AES256 >/dev/null

aws ecr get-login-password --profile "$AWS_PROFILE" --region "$AWS_REGION" \
  | docker login --username AWS --password-stdin "$ECR_REGISTRY"

cd "$ROOT_DIR"
docker buildx build \
  --platform linux/arm64 \
  -f services/banking-service/Dockerfile \
  -t "$IMAGE_URI" \
  --load \
  .

docker push "$IMAGE_URI"

echo "$IMAGE_URI" > "$ROOT_DIR/.banking-image-uri"
echo "Pushed $IMAGE_URI"
