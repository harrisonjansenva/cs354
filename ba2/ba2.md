## 1.
var
    line: string
    done: boolean
line := read_line();

done := all_blanks(line);
while not done do
begin
    consume(line);
    line := read_line();
    done := all_blanks(line);
end;

* The biggest difference is that you have to duplicate code and track more variables compared to just having a mid-loop check.

## 2.
int first_zero_row = -1; /* none */
 int i, j;
 for (i = 0; i < n; i++) {
  for (j = 0; j < n; j++) {
   if (A[i][j] != 0) {
    break;
}
if (j == n-1) {
    first_zero_row = j;
    return first_zero_row;
}
  }
 }
return -1;

* It's fine, but why would you introduce complexity when the return statement is right there.... ?