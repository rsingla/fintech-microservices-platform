package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/rsingla/FileService/utils"
)

func main() {
	//gojourney.Variable()
	//age := gojourney.MyAgeCalculator()

	//fmt.Println(age)

	api()
}

func api() {
	r := gin.Default()
	r.LoadHTMLGlob("templates/*")
	r.MaxMultipartMemory = 8 << 20 // 8 MiB

	r.GET("/", indexPage)

	r.GET("/ping", healthCheck)

	r.POST("/upload", utils.FileDiskUpload)

	r.POST("/upload/s3", utils.UploadS3)

	r.GET("/env", utils.EnvData)

	r.POST("/sendemail", utils.SendEmail)

	r.Run() // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")

}

func healthCheck(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{
		"message": "pong",
	})
}

func indexPage(c *gin.Context) {
	c.HTML(http.StatusOK, "index.html", gin.H{
		"title": "Rajeev Singla file uploader service",
	})
}
