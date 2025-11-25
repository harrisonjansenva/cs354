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

func (b *bank) Accrue(c chan<- float64, rate float64) {
	sum := 0.0
	for a := range b.accounts {
		sum += a.Accrue(rate)
	}
	c <- sum
}

func (b *bank) String() string {
	sb := strings.Builder{}

	for a := range b.accounts {
		sb.WriteString(a.String() + "\n")
	}
	return sb.String()
}


