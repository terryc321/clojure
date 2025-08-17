
/* useful debug ()+ to get antlr to slurp up all values */

grammar Numbers2;


numText
  : (numValue (WS+ numValue)*)+
;

numValue
  : NUMBER
;


fragment
INT
  : '0'..'9'+
;


NUMBER
  : ('0' | ( '1'..'9' INT* ))
;


WS: [ \n\t\r]+ -> skip;


