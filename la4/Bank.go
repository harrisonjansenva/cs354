package bank

import "strings"

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

func (b *bank) String() string {
	sb := strings.Builder{}

	for a := range b.accounts {
		sb.WriteString(a.String())
	}
	return sb.String() + "\n"
}
