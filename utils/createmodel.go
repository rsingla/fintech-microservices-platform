package utils

import (
	"fmt"
	"strings"

	"github.com/rsingla/FileService/model"
)

func invokeChatAndCreateModel(chatResp *model.ChatCompletion) {

	myData := chatResp.Choices[0].Message.Content

	parse(myData)

}

func parse(myData string) {

	lines := strings.Split(myData, "\n")
	for _, line := range lines {
		fmt.Println(line)
	}

}
