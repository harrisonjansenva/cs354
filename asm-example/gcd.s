// -*- comment-start: "// "; -*-
.section .rodata
data_entry_msg:          .asciz "Enter a number> "
num_read_specifier:      .asciz "%u"
num_print_specifier:     .asciz "You entered: %u\n"
result_print_specifier:  .asciz "gcd(%u, %u) = %u.\n"

.section .text
        .global _start
        .global gcd

gcd:   
        stp     x29, x30, [sp, -32]!
        mov     x29, sp
        str     w0, [sp, 28]
        str     w1, [sp, 24]
        b       .L2
.L4:
        ldr     w1, [sp, 28]
        ldr     w0, [sp, 24]
        cmp     w1, w0
        bls     .L3
        ldr     w1, [sp, 28]
        ldr     w0, [sp, 24]
        sub     w0, w1, w0
        str     w0, [sp, 28]
        b       .L2
.L3:
        ldr     w1, [sp, 24]
        ldr     w0, [sp, 28]
        sub     w0, w1, w0
        str     w0, [sp, 24]
.L2:
        ldr     w1, [sp, 28]
        ldr     w0, [sp, 24]
        cmp     w1, w0
        bne     .L4
        ldr     w0, [sp, 28]
        ldp     x29, x30, [sp], 32
        ret

// _start:
//         // Set up stack.
//         // sp + 28 is a.
//         // sp + 24 is b.
//         stp x29, x30, [sp, -32]!
//         mov x29, sp

//         // Display a prompt.
//         adrp x0, data_entry_msg
//         add x0, x0, :lo12:data_entry_msg
//         bl printf

//         // Read "a".
//         adrp x0, num_read_specifier
//         add x0, x0, :lo12:num_read_specifier
//         add x1, sp, #28
//         bl scanf

//         // Print "a".
//         adrp x0, num_print_specifier
//         add x0, x0, :lo12:num_print_specifier
//         ldr w1, [sp, 28]
//         bl printf

//         // Display another prompt.
//         adrp x0, data_entry_msg
//         add x0, x0, :lo12:data_entry_msg
//         bl printf

//         // Read "b".
//         adrp x0, num_read_specifier
//         add x0, x0, :lo12:num_read_specifier
//         add x1, sp, #24
//         bl scanf

//         // Print "b".
//         adrp x0, num_print_specifier
//         add x0, x0, :lo12:num_print_specifier
//         ldr w1, [sp, 24]
//         bl printf

//         // Call GCD.
//         ldr w0, [sp, 28]
//         ldr w1, [sp, 24]
//         bl gcd

//         // Move the return value into w3.
//         mov w3, w0

//         // w1 = *(sp + 28), w2 = *(sp + 24).
//         ldr w1, [sp, 28]
//         ldr w2, [sp, 24]

//         // Print the result.
//         adrp x0, result_print_specifier
//         add x0, x0, :lo12:result_print_specifier
//         bl printf
    
//         // exit system call
//         mov x8, #93                             // sys_exit system call number
//         mov x0, #0                              // exit status
//         svc #0                                  // supervisor call
