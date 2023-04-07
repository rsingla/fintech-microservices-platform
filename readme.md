Purpose of the project: 
Build a file upload service using Gin framework to Disk and S3

#To create this project

1. Create a new directory for your project

# go mod init github.com/rsingla/FileService
# go get -u github.com/gin-gonic/gin

2. Create a new file called main.go

3. Add the following code to main.go

# https://pkg.go.dev/github.com/gin-gonic/gin#readme-running-gin
# https://gin-gonic.com/docs/examples/html-rendering/
# https://gin-gonic.com/docs/examples/upload-file/single-file/

4. Calling via API

curl -X POST http://localhost:8080/upload \
  -F "file=@/Users/appleboy/test.zip" \
  -H "Content-Type: multipart/form-data"