package utils

import (
	"fmt"
	"strings"

	"github.com/rsingla/FileService/model"
)

func InvokeChatAndCreateModel(chatResp *model.ChatCompletion) {

	myData := chatResp.Choices[0].Message.Content

	parsedData := parse(myData)

	for _, line := range parsedData {
		go invoke(line)
	}

}

func invoke(line string) {
	inputLine := "Create Gorm Object "
	resp := make(chan model.ChatCompletion)
	prompt := model.Prompt{}
	prompt.Message = inputLine + " " + line
	resp <- CallNParse(prompt)
}

func parse(myData string) []string {

	lines := strings.Split(myData, "\n")
	for _, line := range lines {
		fmt.Println(line)
	}

	return lines

}
