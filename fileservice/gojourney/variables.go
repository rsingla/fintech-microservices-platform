package gojourney

import "time"

func Variable() {
	first := "Hello world"
	println(first)
}

func myAge() int {
	age := 1
	return age
}

func myDOB() time.Time {
	dob := "01/01/2000"

	layout := "01/02/2006" // standard date format
	birthDate, _ := time.Parse(layout, dob)

	return birthDate
}

func MyAgeCalculator() int {
	now := time.Now()
	age := now.Year() - myDOB().Year()
	return age
}
