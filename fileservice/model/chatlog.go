package model

import "os"

type ChatLog struct {
	Prompt         Prompt         `json:"prompt"`
	ChatCompletion ChatCompletion `json:"chat_completion"`
}

func (c *ChatLog) Save() string {

	fileName := "chatlog.json"

	if _, err := os.Stat(fileName); os.IsNotExist(err) {
		_, _ = os.Create(fileName)
	}

	file, err := os.OpenFile(fileName, os.O_APPEND|os.O_WRONLY, 0600)
	if err != nil {
		panic(err)
	}

	defer file.Close()

	_, err = file.WriteString(c.Prompt.Message)
	if err != nil {
		panic(err)
	}

	_, err = file.WriteString(c.ChatCompletion.Choices[0].Message.Content)
	if err != nil {
		panic(err)
	}

	return "Success"

}
