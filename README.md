# scala-samples

Exercises made from different areas of Scala and FP programming using monads, monoids, shapeless etc.

### Shapeless (`com.mwronski.shapeless`)

Samples of shapeless usage embracing type-level programming. 

a) `CheckSum`

Check-sum checks whether list has a proper length and whether its elements give proper check-sum.


### Type level programming (`com.mwronski.typelevel`)

Type-level programming involves calculations that are done during compilation time while type-inferring/type-checking.

It's demonstrated using:
 
a) Church encoding for booleans with basic operations:

Church encoding is a means of representing data and operators in the lambda calculus.

b) Peano numbers with basic arithmetic

Peano numbers are a simple way of representing the natural numbers using only a zero value and a successor function.

c) HList (heterogenous lists) whose length and element types are known at compile-time.

### Type classes (`com.mwronski.typeclass`)

Sample parser built using type classes. Parser can convert string into chosen model.

A type class C defines some behaviour in the form of operations that must be supported by a type T for it to be a member of type class C.

Whether the type T is a member of the type class C is not inherent in the type. 

Membership is established based on providing implementations of operations the type must support.

In provided sample type class C is a `Parser` and there are two types T:
 
a) `ModelParser` 
 
Member of type class by providing some proofs that tell explicitly how to parse strings into chosen model thus requires bigger implementation effort.

b) `ShapelessModelParser` 

Member of type class by providing induction rules that allows to parse strings into chosen model.
 
Shapeless model parser just tells how to parse basic types (strings, integers etc.) 

and how to build HList composed of parsers based on any input string to be parsed.
 
HList allows to split compound types (represented as strings) into smaller pieces.  

`caseClassParser` is an induction step that helps to provide parser for any type - basic or compound (using HList). 

### Path dependent types (`com.mwronski.pathdependenttypes`)

Sample of path dependent types using movie examples where teams are built using characters from chosen movie.

It shows how mixin teams, using characters from different movies, cannot be built thanks to path dependent types.

More details can be found [here](http://lampwww.epfl.ch/~amin/dot/fpdt.pdf).
