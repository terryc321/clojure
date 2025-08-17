grammar Numbers;

numSeq
  : (numValue (WS+ numSeq)*)+
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


