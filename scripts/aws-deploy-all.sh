#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

"$ROOT_DIR/scripts/aws-build-push-banking.sh"
"$ROOT_DIR/scripts/aws-deploy-bankservice.sh"
