package utils

import (
	"fmt"
	"log"
	"net/smtp"

	"github.com/gin-gonic/gin"
)

type Mail struct {
	To      []string `json:"to"`
	Subject string   `json:"subject"`
	Body    string   `json:"body"`
}

func SendEmail(c *gin.Context) {

	var mail Mail

	// Bind the JSON body to the person struct
	if err := c.BindJSON(&mail); err != nil {
		// Return an error response if the binding fails
		c.JSON(400, gin.H{"error": err.Error()})
		return
	}

	// Print the received person struct
	fmt.Printf("Received: %#v\n", mail)

	// Send the mail
	resp, err := send(mail.To, mail.Subject, mail.Body)

	if err != nil {
		// Return an error response if the sending fails
		c.JSON(500, gin.H{"error": err.Error()})
		return
	}

	// Return a success response
	if resp != "" {
		c.JSON(200, gin.H{"message": "mail Sent"})
	}

}

func send(to []string, subject string, body string) (string, error) {
	log.Println("Sending email..." + subject + " to " + to[0] + " Body ... " + body)

	errCh := make(chan error)

	go func() {
		auth := smtp.PlainAuth("", "username", "password", "mail.smtp2go.com")
		emailContent := "Subject: " + subject + "\r\n\r\n" + body
		errCh <- smtp.SendMail("mail.smtp2go.com:2525", auth, "your-email@email.com", to, []byte(emailContent))

	}()

	err := <-errCh
	fmt.Println(err)

	// Send the email.
	if err != nil {
		fmt.Println(err)
		return "", err
	}

	return "Email sent successfully!", nil
}
