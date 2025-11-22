package bank
type Customer struct {
	name string
}

func newCustomer(name string) *Customer {
	return &Customer {
		name: name,
	}
}

func (s *Customer) String() string {
	return s.name
}