package utils

import (
	"os"

	"github.com/rsingla/FileService/model"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func ConnectDB() {

	//This is not a valid string, just an example
	dsn := os.Getenv("DB_CONNECTION")
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})

	if err != nil {
		panic("failed to connect database")
	}

	// Migrate the schema
	db.AutoMigrate(&model.User{})

}
