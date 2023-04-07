package main

import (
	"fmt"
	"log"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
)

func fileDiskUpload(c *gin.Context) {
	// single file
	file, err := c.FormFile("file")
	if err != nil {
		log.Println(err)
		c.HTML(http.StatusOK, "index.html", gin.H{
			"error": "Failed to upload File to disk",
		})
	}
	log.Println(file.Filename)

	// Upload the file to specific dst.
	c.SaveUploadedFile(file, "./assests/"+file.Filename)

	c.String(http.StatusOK, fmt.Sprintf("'%s' uploaded!", file.Filename))
}

func envData(c *gin.Context) {
	err := godotenv.Load()
	if err != nil {
		log.Println("Error loading .env file")
	}

	if os.Getenv("S3_BUCKET") == "" {
		log.Println("S3 BUCKET seems to be missing")
		c.String(http.StatusNotFound, fmt.Sprintf(" Environment variable not found!"))
	}

	s3Bucket := os.Getenv("AWS_S3_BUCKET")
	awsRegion := os.Getenv("AWS_REGION")

	c.String(http.StatusOK, fmt.Sprintf("Env info verification! %s, %s", s3Bucket, awsRegion))
}
