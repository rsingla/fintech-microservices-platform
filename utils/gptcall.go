package utils

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
	"github.com/rsingla/FileService/api"
	"github.com/rsingla/FileService/model"
)

func GPTCall(c *gin.Context) {

	var prompt model.Prompt

	if err := c.BindJSON(&prompt); err != nil {
		c.JSON(400, gin.H{"error": err.Error()})
		return
	}

	apiRequest, url, authToken := chatRequest(prompt)

	resp, err := api.CallAPI(apiRequest, url, authToken)

	if err != nil {
		panic(err)
	}

	chatResp := chatCompletion(resp)

	go chatStoreLog(prompt, chatResp)

	c.JSON(http.StatusOK, chatResp.Choices)

}

func chatStoreLog(prompt model.Prompt, chatResp model.ChatCompletion) {
	chatLog := model.ChatLog{
		Prompt:         prompt,
		ChatCompletion: chatResp,
	}

	chatLog.Save()
}

func chatCompletion(resp *http.Response) model.ChatCompletion {

	fmt.Println("Response status code:", resp.StatusCode)
	buf := new(bytes.Buffer)
	_, _ = buf.ReadFrom(resp.Body)
	fmt.Println("Response body:", buf.String())

	var chatCompletion model.ChatCompletion
	err := json.Unmarshal([]byte(buf.Bytes()), &chatCompletion)
	if err != nil {
		fmt.Println("Error:", err)
	}
	fmt.Printf("%+v", chatCompletion)

	return chatCompletion
}

func chatRequest(prompt model.Prompt) (model.APIRequest, string, string) {
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

	return apiRequest, url, authToken
}
