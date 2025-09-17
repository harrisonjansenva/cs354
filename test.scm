#! /usr/bin/env guile
(use-modules (ice-9 textual-ports))

(define (prompt-number msg)
    (display msg)
    (string->number (get-line (current-input-port))))