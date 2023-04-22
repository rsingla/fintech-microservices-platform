package utils

import (
	"bytes"
	"fmt"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
	"github.com/rsingla/FileService/api"
	"github.com/rsingla/FileService/model"
)

func GPTCall(c *gin.Context) {

	var prompt model.Prompt

	// Bind the JSON body to the person struct
	if err := c.BindJSON(&prompt); err != nil {
		// Return an error response if the binding fails
		c.JSON(400, gin.H{"error": err.Error()})
		return
	}

	// Print the received person struct
	fmt.Printf("Received: %#v\n", prompt)

	url := os.Getenv("OPENAI_URL")
	authToken := os.Getenv("OPENAI_API_KEY")
	gptModel := os.Getenv("DEFAULT_GPT3_MODEL")

	apiRequest := model.APIRequest{
		Model: gptModel,
		Messages: []model.Message{
			{
				Role:    "user",
				Content: prompt.Message,
			},
		},
		Temperature:      0.3,
		MaxTokens:        1000,
		TopP:             1,
		FrequencyPenalty: 0,
		PresencePenalty:  0.1,
	}

	resp, err := api.CallAPI(apiRequest, url, authToken)

	if err != nil {
		panic(err)
	}

	fmt.Println("Response status code:", resp.StatusCode)
	buf := new(bytes.Buffer)
	_, _ = buf.ReadFrom(resp.Body)
	fmt.Println("Response body:", buf.String())

	c.JSON(http.StatusOK, buf.String())

}
