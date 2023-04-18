package main

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
	send(mail.To, mail.Subject, mail.Body)

	// Return a success response
	c.JSON(200, gin.H{"message": "mail Sent"})

}

func send(to []string, subject string, body string) {
	log.Println("Sending email..." + subject + " to " + to[0] + " Body ... " + body)
	// Set up authentication information.

	auth := smtp.PlainAuth("", "username", "password", "mail.smtp2go.com")

	emailContent := "Subject: " + subject + "\r\n\r\n" + body

	// Send the email.
	err := smtp.SendMail("mail.smtp2go.com:2525", auth, "your-email@email.com", to, []byte(emailContent))
	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Println("Email sent successfully!")
}
