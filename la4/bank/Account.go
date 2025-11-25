package bank

import "strconv"

type AccountInterface interface {
	Balance() float64
	Deposit(amnt float64)
	Withdraw(amnt float64)
	Accrue(rate float64) float64
	String() string
}

type Account struct {
	number   string
	balance  float64
	customer Customer
}

func NewAccount(name string, number string, balance float64) *Account {
	return &Account{
		customer: *NewCustomer(name),
		number:   number,
		balance:  balance,
	}
}

// func (a *Account) Customer() string {
// 	return a.customer.String()
// }

func (acct *Account) Balance() float64 {
	return acct.balance
}

func (acct *Account) Deposit(amnt float64) {
	acct.balance += amnt
}

func (acct *Account) Withdraw(amnt float64) {
	acct.balance -= amnt
}

func (acct *Account) String() string {
	return acct.number + ":" + acct.customer.String() + ":" + strconv.Itoa(int(acct.balance))
}
