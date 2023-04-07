package main

import (
	"fmt"
	"io"
	"mime/multipart"
	"os"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/credentials"
	"github.com/aws/aws-sdk-go/aws/session"
)

func convertMultiParttoIOReader(fileHeader *multipart.FileHeader) (output *os.File) {

	file, err := fileHeader.Open()
	if err != nil {
		fmt.Println("Error opening file:", err)
		return
	}
	defer file.Close()

	// now you can read the file's contents using the returned io.Reader
	// for example, you can copy the contents to another file
	output, err = os.Create(fileHeader.Filename)
	if err != nil {
		fmt.Println("Error creating output file:", err)
		return
	}
	defer output.Close()

	_, err = io.Copy(output, file)
	if err != nil {
		fmt.Println("Error copying file:", err)
		return
	}

	return output

}

var AccessKeyID string
var SecretAccessKey string
var MyRegion string

func ConnectAws() *session.Session {
	AccessKeyID = GetEnvWithKey("AWS_ACCESS_KEY_ID")
	SecretAccessKey = GetEnvWithKey("AWS_SECRET_ACCESS_KEY")
	MyRegion = GetEnvWithKey("AWS_REGION")
	sess, err := session.NewSession(
		&aws.Config{
			Region: aws.String(MyRegion),
			Credentials: credentials.NewStaticCredentials(
				AccessKeyID,
				SecretAccessKey,
				"", // a token will be created when the session it's used.
			),
		})
	if err != nil {
		panic(err)
	}
	return sess
}

func GetEnvWithKey(key string) string {
	return os.Getenv(key)
}
