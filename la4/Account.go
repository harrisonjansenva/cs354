package bank

import "strconv"

type AccountInterface interface {
	Balance() float32
	Deposit(amnt float32) 
	Withdraw(amnt float32)
	Accrue(rate float32)
	String() string

}

type Account struct {
	number   string
	balance  float32
	customer Customer
}

func NewAccount(name string, number string, balance float32) *Account {
	return &Account{
		customer: *newCustomer(name),
		number:   number,
		balance:  balance,
	}
}

// func (a *Account) Customer() string {
// 	return a.customer.String()
// }

func (acct *Account) Balance() float32 {
	return acct.balance
}

func (acct *Account) Deposit(amnt float32) {
	acct.balance += amnt
}

func (acct *Account) Withdraw(amnt float32) {
	acct.balance -= amnt
}
func (acct *Account) Accrue(rate float32) {}

func (acct *Account) String() string {
	return acct.number + ":" + acct.customer.String() + ":" + strconv.Itoa(int(acct.balance))
}
