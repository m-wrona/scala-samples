package com.mwronski.typelevel

sealed trait GT extends Comparable {

  type Match[IfLT <: Up, IfEQ <: Up, IfGT <: Up, Up] = IfGT

}

sealed trait LT extends Comparable {

  type Match[IfLT <: Up, IfEQ <: Up, IfGT <: Up, Up] = IfLT

}

sealed trait EQ extends Comparable {

  type Match[IfLT <: Up, IfEQ <: Up, IfGT <: Up, Up] = IfEQ
  
}

sealed trait Comparable {

  type Match[IfLT <: Up, IfEQ <: Up, IfGT <: Up, Up] <: Up

  type gt = Match[False, False, True, Bool]

  type ge = Match[False, True, True, Bool]

  type eq = Match[False, True, False, Bool]

  type le = Match[True, True, False, Bool]

  type lt = Match[True, False, False, Bool]

}
