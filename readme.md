
# Project 
Builds REST API using Golang GIN and Postgres SQL. 
Other feature includes calling OpenAI API 
Includes a file upload service using Gin framework to Disk and S3

# To create this project

1. Create a new directory for your project

go mod init github.com/rsingla/FileService
go get -u github.com/gin-gonic/gin

2. Create a new file called main.go

3. Add the following code to main.go

https://pkg.go.dev/github.com/gin-gonic/gin#readme-running-gin

https://gin-gonic.com/docs/examples/html-rendering/

https://gin-gonic.com/docs/examples/upload-file/single-file/

4. Calling via API

curl -X POST http://localhost:3001/upload \
  -F "file=@/Users/appleboy/test.zip" \
  -H "Content-Type: multipart/form-data"


5. Setup S3 bucket and give programmatic access

6. Get AWS SDK for GO

https://github.com/aws/aws-sdk-go-v2

Commands

    go get github.com/aws/aws-sdk-go-v2/aws
    go get github.com/aws/aws-sdk-go-v2/config
    go get github.com/aws/aws-sdk-go-v2/service/s3
    go get github.com/joho/godotenv
    go get github.com/aws/aws-sdk-go-v2/feature/s3/manager

https://aws.github.io/aws-sdk-go-v2/docs/making-requests/

Reference Doc

https://asanchez.dev/blog/amazon-s3-v2-golang/