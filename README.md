# scala-samples

Exercises made from different areas of Scala and FP programming using monads, monoids, shapeless etc.

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