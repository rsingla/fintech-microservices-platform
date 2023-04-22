package utils

import (
	"github.com/rsingla/FileService/model"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func ConnectDB() {

	dsn := "host=localhost user=gorm password=gorm dbname=gorm port=9920 sslmode=disable TimeZone=Asia/Shanghai"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})

	if err != nil {
		panic("failed to connect database")
	}

	// Migrate the schema
	db.AutoMigrate(&model.User{})

}
