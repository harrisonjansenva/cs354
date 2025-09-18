#!/usr/bin/env guile -s
!#

(load "super-duper.scm")

(define (run-test actual expected msg)
  (let ((ok (equal? expected actual)))
    (display (if ok "PASS: " "FAIL: "))
    (display msg) (newline)
    (if (not ok)
        (begin
          (display "  Actual output: ") (write actual) (newline)
          (display "  Expected: ") (write expected) (newline))
        )))

(define (super-tester)
  (display "RUNNING TESTS FOR SUPER-DUPER") (newline)
  (run-test (super-duper 123 1) 123 "testing atom (no attempt to duplicate)")
  (run-test (super-duper 123 2) 123 "testing atom (attempt to duplicate)")
  (run-test (super-duper '() 1) '() "testing empty list (no attempt to duplicate)")
  (run-test (super-duper '() 2) '() "testing empty list (attempt to duplicate)")
  (run-test (super-duper '(x) 1) '(x) "testing single element (no attempt to duplicate)")
  (run-test (super-duper '(x) 2) '(x x) "testing single element (attempt to duplicate)")
  (run-test (super-duper '(x y) 1) '(x y) "testing two elements (no duplication)")
  (run-test (super-duper '(x y) 2) '(x x y y) "testing two elements (with duplication)")
  (run-test (super-duper '((a b) y) 3) '((a a a b b b) (a a a b b b) (a a a b b b) y y y) "testing a nested list")
  (run-test (super-duper '( 1 2 3 x) 9) '( 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 x x x x x x x x x ) "stress testing duplication")
  )

;; run tests when the script is executed:
(super-tester)