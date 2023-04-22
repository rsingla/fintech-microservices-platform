package api

import (
	"bytes"
	"context"
	"encoding/json"
	"fmt"
	"net/http"
	"time"

	"github.com/rsingla/FileService/model"
)

func CallAPI(apiRequest model.APIRequest, url string, authToken string) (*http.Response, error) {
	// Create a context with a 30-second timeout
	ctx, cancel := context.WithTimeout(context.Background(), 30*time.Second)
	defer cancel()

	reqPayload, err := json.Marshal(apiRequest)
	if err != nil {
		fmt.Println("Error encoding JSON:", err)
		return nil, err
	}

	// Create a new HTTP request with the URL you want to call
	req, err := http.NewRequest("POST", url, bytes.NewBuffer(reqPayload))
	if err != nil {
		return nil, err
	}

	req.Header.Add("Content-Type", "application/json")
	req.Header.Add("Accept", "application/json")
	req.Header.Add("Authorization", "Bearer "+authToken)

	// Use the context to make the request with a timeout
	respChan := make(chan *http.Response, 1)
	errChan := make(chan error, 1)
	go func() {
		resp, err := http.DefaultClient.Do(req.WithContext(ctx))
		if err != nil {
			errChan <- err
			return
		}
		respChan <- resp
	}()

	// Wait for either the response or the timeout
	select {
	case resp := <-respChan:
		fmt.Println("Response received!")
		return resp, nil
	case err := <-errChan:
		return nil, err
	case <-ctx.Done():
		return nil, fmt.Errorf("API call timed out")
	}

}
