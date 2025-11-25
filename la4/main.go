package main

import (
	"fmt"
	"la4/bank"
)

func main() {
	b := bank.NewBank()

	b.Add(bank.NewCheckingAccount("Ann", "01001", 100.00))
	b.Add(bank.NewSavingsAccount("Ann", "01002", 200.00))
	c := make(chan float64)
	go b.Accrue(c, 0.02)
	fmt.Printf("Interest Accrued: $%.2f\n", <-c)
	fmt.Println(b.String())
}