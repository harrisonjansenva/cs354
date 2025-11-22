package bank

type bank struct {
	accounts map[AccountInterface]struct{}
}

func NewBank() *bank {
	return &bank{
		accounts: make(map[AccountInterface]struct{}),
	}

}

func (b *bank) Add(acct AccountInterface) {
	b.accounts[acct] = struct{}{}
}

func (b *bank) Accrue(rate float32) {
	for a := range b.accounts {
		a.Accrue(rate)
	}
}
