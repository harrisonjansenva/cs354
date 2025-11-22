package bank
import "strconv"
type AccountInterface interface {
	number() string
	balance() float32
	customer() Customer
}

type Account struct {
	number string
	balance float32
	customer Customer
}

func NewAccount(name string, number string, balance float32) *Account {
	return &Account {
		customer: Customer {
			name: name,
		},
		number: number,
		balance: balance,
	}
}

// func (a *Account) Customer() string {
// 	return a.customer.String()
// }

func (a *Account) Balance() float32 {
	return a.balance
}

func (a *Account) Deposit(amount float32) {
	a.balance += amount
}

func (a *Account) Withdraw(amount float32) {
	a.balance -= amount
}
func (a *Account) Accrue(rate float32) {}

func (a *Account) String() string {
	return a.number + ":" + a.customer.String() + ":" + strconv.Itoa(int(a.balance))
}

