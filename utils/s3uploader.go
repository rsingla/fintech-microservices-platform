package utils

import (
	"context"
	"fmt"
	"log"
	"net/http"

	"github.com/aws/aws-sdk-go-v2/aws"
	"github.com/aws/aws-sdk-go-v2/config"
	"github.com/aws/aws-sdk-go-v2/feature/s3/manager"
	"github.com/aws/aws-sdk-go-v2/service/s3"
	"github.com/gin-gonic/gin"
)

func UploadS3(c *gin.Context) {

	// single file
	cfg, err := config.LoadDefaultConfig(context.TODO(), config.WithRegion("us-west-2"))

	if err != nil {
		log.Println(err)
		return
	}

	fileHeader, err := c.FormFile("file")

	if err != nil {
		log.Println(err)
		c.HTML(http.StatusOK, "index.html", gin.H{
			"error": "Failed to upload File to disk",
		})
	}

	client := s3.NewFromConfig(cfg)

	uploader := manager.NewUploader(client)

	file, err := fileHeader.Open()
	defer file.Close()
	if err != nil {
		fmt.Println("Error opening file:", err)
		return
	}

	result, err := uploader.Upload(context.TODO(), &s3.PutObjectInput{
		Bucket: aws.String("apicodebucket"),
		Key:    aws.String("mainfolder"),
		Body:   file,
		ACL:    "public-read",
	})

	if err != nil {
		log.Println(err)
	} else {
		log.Println(result)
	}

	c.String(http.StatusOK, fmt.Sprintf("'%s' is uploaded successfully to S3! ", result))
}
