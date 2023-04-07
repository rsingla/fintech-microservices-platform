package main

import (
	"fmt"
	"log"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
)

func main() {
	r := gin.Default()
	r.LoadHTMLGlob("templates/*")
	r.MaxMultipartMemory = 8 << 20 // 8 MiB

	r.GET("/", indexPage)

	r.GET("/ping", healthCheck)

	r.POST("/upload", fileDiskUpload)

	r.GET("/env", envData)

	r.Run() // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
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

	s3Bucket := os.Getenv("S3_BUCKET")
	awsRegion := os.Getenv("AWS_REGION")

	c.String(http.StatusOK, fmt.Sprintf("Env info verification! %s, %s", s3Bucket, awsRegion))
}

func healthCheck(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{
		"message": "pong",
	})
}

func indexPage(c *gin.Context) {
	c.HTML(http.StatusOK, "index.html", gin.H{
		"title": "Zapitel file uploader service",
	})
}

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
