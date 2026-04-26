package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/rsingla/FileService/utils"
)

func init() {
	utils.LoadEnv()
}

func main() {

	//utils.ConnectDB()

	r := gin.Default()
	r.LoadHTMLGlob("templates/*")
	r.MaxMultipartMemory = 8 << 20 // 8 MiB

	healthCheckApi(r)
	openAIAPIs(r)
	api(r)

	r.Run()

}

func blogAPIs(r *gin.Engine) {

	//r.GET("/posts", utils.EnvData)

	//r.GET("/post/:id", utils.SendEmail)

}

func openAIAPIs(r *gin.Engine) {

	r.POST("/openai/chat", utils.GPTCall)

}

func healthCheckApi(r *gin.Engine) {

	r.GET("/", indexPage)

	r.GET("/ping", healthCheck)
}

func api(r *gin.Engine) {

	r.POST("/upload", utils.FileDiskUpload)

	r.POST("/upload/s3", utils.UploadS3)

	r.GET("/env", utils.EnvData)

	r.POST("/sendemail", utils.SendEmail)

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
