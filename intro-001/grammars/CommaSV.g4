/* comma separated values */
grammar CommaSV;

/*   : (numValue (',' numValue)*)?  */
/* skip one or more whitespace values*/

numText
  : numValue (',' numValue)*
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


