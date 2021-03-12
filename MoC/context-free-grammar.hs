data Answer
  = Ambiguous String
  | Unambiguous
  deriving (Eq,Show)

data Grammar
  = A | B | C
  deriving (Eq,Show)

part1, part2, part3 :: Answer

part1 = Ambiguous "ab"            

part2 = Ambiguous "aabbb"            

part3 = Unambiguous           

part4 :: [(Grammar,Grammar)]

part4 = [(A,A), (B,B), (C,C), (B,A), (C,A), (C,B), (B,C)]  
