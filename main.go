package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()
	r.LoadHTMLGlob("templates/*")
	r.MaxMultipartMemory = 8 << 20 // 8 MiB

	r.GET("/", indexPage)

	r.GET("/ping", healthCheck)

	r.POST("/upload", fileDiskUpload)

	r.POST("/upload/s3", uploadS3)

	r.GET("/env", envData)

	r.POST("/sendemail", SendEmail)

	r.Run() // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
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
