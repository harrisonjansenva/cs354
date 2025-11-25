package bank

type CheckingAccountInterface interface {
	AccountInterface
}

type CheckingAccount struct {
	Account
}

func NewCheckingAccount(name string, number string, balance float64) *CheckingAccount {
	return &CheckingAccount{
		Account: *NewAccount(name, number, balance),
	}
}

func (ck *CheckingAccount) Accrue(rate float64) float64 {
	return 0
}