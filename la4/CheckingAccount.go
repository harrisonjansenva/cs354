package bank

type CheckingAccountInterface interface {
	AccountInterface
}

type CheckingAccount struct {
	Account
}

func NewCheckingAccount(name string, number string, balance float32) *CheckingAccount {
	return &CheckingAccount{
		Account: Account{
			customer: Customer{
				name: name,
			},
			number:  number,
			balance: balance,
		},
	}
}
