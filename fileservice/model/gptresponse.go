package model

type ChatCompletion struct {
	ID      string       `json:"id"`
	Object  string       `json:"object"`
	Created int64        `json:"created"`
	Model   string       `json:"model"`
	Usage   UsageDetails `json:"usage"`
	Choices []Choice     `json:"choices"`
}

type UsageDetails struct {
	PromptTokens     int `json:"prompt_tokens"`
	CompletionTokens int `json:"completion_tokens"`
	TotalTokens      int `json:"total_tokens"`
}

type Choice struct {
	Message      MessageDetails `json:"message"`
	FinishReason string         `json:"finish_reason"`
	Index        int            `json:"index"`
}

type MessageDetails struct {
	Role    string `json:"role"`
	Content string `json:"content"`
}
