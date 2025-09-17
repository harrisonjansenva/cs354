#!/usr/bin/env guile -s
!#

(define (super-duper source count)
  (cond
    ((is-atom source) source)
    ((null? source) '())
    (else
       (let ((head (car source)))
         ((lambda (processed-head processed-tail)
            (insert-at-front processed-head processed-tail count))
          (if (pair? head) (super-duper head count) head)
          (super-duper (cdr source) count))))))

(define (is-atom source)
  (cond
    ((null? source) #f)              
    ((not (pair? source)) #t)        
    (else #f)))

(define (copy-list source)
  (if (null? source)
      '()
      (cons (copy-element (car source)) (copy-list (cdr source)))))

(define (copy-element source)
  (if (is-atom source)
      source
      (copy-list source)))

(define (insert-at-front head tail count)
  (if (<= count 0)
      tail
      (cons (copy-element head) (insert-at-front head tail (- count 1)))))