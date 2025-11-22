package bank

type SavingsAccountInterface interface {
	AccountInterface
}

type SavingsAccount struct {
	Account
	interest float32
}

func NewSavingsAccount(name string, number string, balance float32) *SavingsAccount {
	return &SavingsAccount{
		Account: Account {
			customer: Customer {
				name: name,
			},
			number: number,
			balance: balance,
		},
		interest: 0,
	}
}

func (a *SavingsAccount) Accrue(rate float32) {
	a.interest += a.balance * rate
	a.balance += a.balance * rate

}