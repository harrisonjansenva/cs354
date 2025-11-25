package bank

type SavingsAccountInterface interface {
	AccountInterface
}

type SavingsAccount struct {
	Account
	interest float64
}

func NewSavingsAccount(name string, number string, balance float64) *SavingsAccount {
	return &SavingsAccount{
		Account:  *NewAccount(name, number, balance),
		interest: 0,
	}
}

func (a *SavingsAccount) Accrue(rate float64) float64 {
	a.interest += a.balance * rate
	a.balance += a.balance * rate

	return a.balance * rate
}
